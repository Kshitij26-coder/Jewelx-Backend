package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockRepoDto;
import in.jewelx.jewelxbackend.entity.MetalStockEntity;

public interface IMetalStockService {
	
	MetalStockEntity addMetalStock(MetalStockDto metalStockDto);
	
	String deleteById(Long id);
	
	MetalStockRepoDto getMetalStockById(Long id);
	
	String updateMetalStockById(Long id, MetalStockDto metalStockDto);
	
	Page<MetalStockRepoDto> getAllMetalStocks(int pageNumber, int pageSize);
}
