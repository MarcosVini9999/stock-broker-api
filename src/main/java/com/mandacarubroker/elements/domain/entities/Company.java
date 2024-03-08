package com.mandacarubroker.elements.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mandacarubroker.elements.domain.dtos.RequestCompanyDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String cnpj;
	private Float capital;
	private String name;
	private String nationality;
	private String ticker;
	public Company(RequestCompanyDTO requestCompanyDTO) {
		this.cnpj = requestCompanyDTO.cnpj();
		this.capital =  requestCompanyDTO.capital();
		this.name = requestCompanyDTO.name();
		this.nationality = requestCompanyDTO.nationality();
		this.ticker = requestCompanyDTO.ticker();
	}
	@OneToMany(mappedBy="company")
	private List<Stock> stocks;
}