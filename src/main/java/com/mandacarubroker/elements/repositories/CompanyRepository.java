package com.mandacarubroker.elements.repositories;

import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,  String> {

    @Query(value = "SELECT c FROM Company c WHERE " +
            "c.name LIKE %?1% OR " +
            "CAST(c.capital AS string) LIKE %?1% OR " + // Convertendo o campo float para string para a comparação
            "c.ticker LIKE %?1%")
    List<Company> findByKeyword1(String keyword);

    @Query(value = "select c from Company c where " +
            "concat(c.name, c.capital, c.cnpj, c.ticker, c.nationality) like %?1%")
    List<Company> findByKeyword(String keyword);


    Optional<Company> findByCnpj(String cnpj);



}
