package com.mandacarubroker.elements.controllers;

import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StockController {
	@Autowired private StockService stockService;
	@Autowired private CompanyService companyService;

	public  Model addModelAttribute(Model model){
		model.addAttribute("stocks", stockService.findAll());
		model.addAttribute("companies", companyService.findAll());
		return model;
	}

	//Get All States
	@GetMapping("/elements/stocks")
	public String findAll(Model model){
		addModelAttribute(model);
		return "/elements/stocks";
	}

	@GetMapping("/elements/stockAdd")
	public String addStock(Model model){
		addModelAttribute(model);
		return "elements/stockAdd";
	}

	@GetMapping("/elements/stock/{op}/{id}")
	public String editStock (@PathVariable String id, @PathVariable String op, Model model){
		addModelAttribute(model);
		model.addAttribute("stock", stockService.findById(id));
		return "/elements/stock" + op;
	}

//	@PutMapping(value="/elements/stock/Editar/{id}")
//	public String editStock(@PathVariable String id, RequestStockDTO data) {
//		stockService.validateAndUpdateStock(id, data);
//		return "redirect:/elements/stocks";
//	}





	@PostMapping(value="/elements/stocks")
	public String addNew(RequestStockDTO data) {
		stockService.validateAndCreateStock(data);
		return "redirect:/elements/stocks";
	}


	@RequestMapping(value="/elements/stocks/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable String id) {
		stockService.delete(id);
		return "redirect:/elements/stocks";
	}

}
