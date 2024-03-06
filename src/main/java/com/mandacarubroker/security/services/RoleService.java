package com.mandacarubroker.security.services;


import com.mandacarubroker.security.domain.entities.Role;
import com.mandacarubroker.security.domain.entities.User;
import com.mandacarubroker.security.repositories.RoleRepository;
import com.mandacarubroker.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    //Get All Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //Get Role By Id
    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    //Delete Role
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    //Update Role
    public void save(Role role) {
        roleRepository.save(role);
    }

    //Assign Role to User
    public void assignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    //Unassign Role to User
    public void unassignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> Objects.equals(x.getId(), roleId));
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }



    public List<Role> getUserNotRoles(User user) {
        return roleRepository.getUserNotRoles(user.getId());
    }

}
