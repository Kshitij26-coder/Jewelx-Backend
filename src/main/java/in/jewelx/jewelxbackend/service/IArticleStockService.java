package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;

public interface IArticleStockService {

	String deleteByArticleStockId(Long articleStockId);

	ArticleStockRespDto getArticleStockById(Long articleStockId);

	String updateArticleStockById(Long id, ArticleStockDto articleStockDto);

	String addArticleStock(ArticleStockDto articleStockDto);

	void updatedArtifactStatus(Long tagId, String status);

	Page<ArticleStockRespDto> getAllArticleStocks(int pageNumber, int pageSize, Long subsidiaryId, Long brandId,
			String role);
}
