package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;
import in.jewelx.jewelxbackend.entity.ArticleStockEntity;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public class ArticleStockMapper {
	
	public static ArticleStockEntity dtoToEntity(ArticleStockDto dto) {
		ArticleStockEntity entity = new ArticleStockEntity();
		entity.setBarcode(dto.getBarcode());
		entity.setGrossWeight(dto.getGrossWeight());
		entity.setHuid(dto.getHuid());
		entity.setNetWeight(dto.getNetWeight());
		entity.setPurity(dto.getPurity());
		entity.setStoneWeight(	dto.getStoneWeight());
		
		ItemCategoryEntity itemEntity = new ItemCategoryEntity();
		itemEntity.setCategoryId(dto.getCategory());
		entity.setCategory(itemEntity);
		
		SubsidiaryEntity subEntity = new SubsidiaryEntity();
		subEntity.setIdxId(dto.getSubsidiaryId());
		entity.setSubsidiary(subEntity);
		return entity;
		
	}
	
	public static ArticleStockRespDto entityToDto(ArticleStockEntity entity) {
		ArticleStockRespDto dto = new ArticleStockRespDto();
		dto.setBarcode(entity.getBarcode());
		dto.setGrossWeight(entity.getGrossWeight());
		dto.setHuid(entity.getHuid());
		dto.setNetWeight(entity.getNetWeight());
		dto.setPurity(entity.getPurity());
		dto.setStoneWeight(entity.getStoneWeight());
		
		return dto;
		
	}
}
