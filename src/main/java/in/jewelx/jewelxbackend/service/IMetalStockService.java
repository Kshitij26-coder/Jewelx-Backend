package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockRepoDto;

public interface IMetalStockService {
	
	String addMetalStock(MetalStockDto metalStockDto);
	
	String deleteById(Long id);
	
	MetalStockRepoDto getMetalStockById(Long id);
	
	String updateMetalStockById(Long id, MetalStockDto metalStockDto);
	
	Page<MetalStockRepoDto> getAllMetalStocks(int pageNumber, int pageSize);
}
