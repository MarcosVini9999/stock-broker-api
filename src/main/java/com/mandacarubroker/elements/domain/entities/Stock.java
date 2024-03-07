package com.mandacarubroker.elements.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mandacarubroker.elements.domain.dtos.RequestStockDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;

import java.text.DecimalFormat;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String symbol;
    private Float price;
    private Float variation;
    private static final DecimalFormat DEC = new DecimalFormat("#.####");

    @ManyToOne
    @JoinColumn(name="companyid", insertable=false, updatable=false)
    private Company company;

    private String companyid;

    private String details;

    public Stock(RequestStockDTO requestStockDTO) {
        this.symbol = requestStockDTO.symbol();
        this.price =  requestStockDTO.price();
        this.details = requestStockDTO.details();
        this.companyid = requestStockDTO.companyid();
        changePrice(this.price);

    }


    public Float changePrice(float amount) {
        if (this.price != 0) {
            String number = DEC.format(((amount - this.price) / this.price) * 100);
            this.variation = Float.parseFloat(number.replace(",", "."));
        }
        return amount;
    }





}
