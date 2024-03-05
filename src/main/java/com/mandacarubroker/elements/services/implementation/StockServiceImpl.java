package com.mandacarubroker.elements.services.implementation;

import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.dtos.ResponseStockDTO;
import com.mandacarubroker.elements.models.Stock;
import com.mandacarubroker.elements.repositories.StockRepository;
import com.mandacarubroker.elements.services.StockService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	//Get All States
	public List<ResponseStockDTO> findAll(){

		return stockRepository.findAll().stream().map(ResponseStockDTO::new).toList();
	}	
	
	//Get State By Id
	public ResponseStockDTO findById(String id) {

		return stockRepository.findById(id).map(ResponseStockDTO::new).orElse(null);
	}	
	
	//Delete State
	public void delete(String id) {
		stockRepository.deleteById(id);
	}
	
	//Update State





	public Stock validateAndUpdateStock(String id, RequestStockDTO data) {
		validateRequestDTO(data);
		Optional<Stock> stockId = stockRepository.findById(id);
		if(stockId.isPresent()) {
			Stock stockBD = stockId.get();
			findComp(stockBD, data);


		}
		return stockId
				.map(stock -> {
					stock.setSymbol(data.symbol());
					float newPrice = stock.changePrice(data.price());
					stock.setPrice(newPrice);


					return stockRepository.save(stock);
				}).orElse(null);
	}





	public static void validateRequestDTO(RequestStockDTO data) {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<RequestStockDTO>> violations = validator.validate(data);

			if (!violations.isEmpty()) {
				StringBuilder errorMessage = new StringBuilder("Validation failed. Details: ");

				for (ConstraintViolation<RequestStockDTO> violation : violations) {
					errorMessage.append(String.format("[%s: %s], ", violation.getPropertyPath(), violation.getMessage()));
				}

				errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

				throw new ConstraintViolationException(errorMessage.toString(), violations);
			}
		}catch (ValidationException ve) {
			throw new ValidationException(ve.getMessage());
		}

	}

	private void findBySymbol(RequestStockDTO data) {
		Optional<Stock> stock = stockRepository.findBySymbol(data.symbol());
		if(stock.isPresent()) {
			throw new DataIntegratyViolationException("Stock already registered with this symbol");
		}
	}


	private void findComp(Stock stockBD,RequestStockDTO data) {
		if((!Objects.equals(data.symbol(), stockBD.getSymbol()))){
			findBySymbol(data);
		}
	}

	public void validateAndCreateStock(RequestStockDTO data) {
		validateRequestDTO(data);
		findBySymbol(data);

		Stock newStock = new Stock(data);
		stockRepository.save(newStock);
	}





}
