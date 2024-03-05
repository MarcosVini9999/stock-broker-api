package com.mandacarubroker.security.models;

import com.mandacarubroker.security.dtos.RequestUserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String confirmpassword;

    public User(RequestUserDTO requestUserDTO) {
        this.firstname = requestUserDTO.firstname();
        this.lastname =  requestUserDTO.lastname();
        this.username = requestUserDTO.username();
        this.password = requestUserDTO.password();
        this.confirmpassword = requestUserDTO.confirmpassword();

    }






    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    Set<Role> roles = new HashSet<>();

}
