package com.mandacarubroker;

import com.mandacarubroker.elements.models.Stock;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.StockService;
import com.mandacarubroker.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public String goHome(Model model){
        List<Stock> stocks = stockService.findAll();

        long positiveVariationCount = stocks.stream()
                .filter(stock -> stock.getVariation() > 0)
                .count();

        long negativeVariationCount = stocks.stream()
                .filter(stock -> stock.getVariation() < 0)
                .count();

        long neutralVariationCount = stocks.stream()
                .filter(stock -> stock.getVariation() == 0)
                .count();
        model.addAttribute("stocksQ", stocks.size());
        model.addAttribute("positiveVariationQ", positiveVariationCount);
        model.addAttribute("negativeVariationQ", negativeVariationCount);
        model.addAttribute("neutralVariationQ", neutralVariationCount);


        model.addAttribute("companiesQ", companyService.findAll().size());
        model.addAttribute("usersQ", companyService.findAll().size());

        return "index";
    }

    @GetMapping("/profile")
    public String goProfile(){
        return "assets/profile";
    }


    @GetMapping("/elements")
    public String elements(){
        return "/elements/index";
    }


    @GetMapping("/security")
    public String security(){
        return "/security/index";
    }

}
