package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;

public interface IArticleStockService {
	
	String addArticleStock(ArticleStockDto articleStockDto);
	
	String deleteByArticleStockId(Long articleStockId);
	
	ArticleStockRespDto getArticleStockById(Long articleStockId);
	
	String updateArticleStockById(Long id, ArticleStockDto articleStockDto);
	
	Page<ArticleStockRespDto> getAllArticleStocks(int pageNumber, int pageSize);
}