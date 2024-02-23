package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;
import in.jewelx.jewelxbackend.entity.ArticleStockEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public class ArticleStockMapper {
	
	public static ArticleStockEntity dtoToEntity(ArticleStockDto dto) {
		ArticleStockEntity entity = new ArticleStockEntity();
		entity.setArticleName(dto.getArticleName());
		entity.setGrossWeight(dto.getGrossWeight());
		entity.setHuid(dto.getHuid());
		entity.setNetWeight(dto.getNetWeight());
		entity.setPurity(dto.getPurity());
		entity.setStoneWeight(	dto.getStoneWeight());
		
		BrandEntity brand = new BrandEntity(dto.getBrandId());
		
		SubsidiaryEntity subsidiary = new SubsidiaryEntity(dto.getSubsidiaryId());
		
		entity.setBrand(brand);
		entity.setSubsidiary(subsidiary);
		
		ItemCategoryEntity itemEntity = new ItemCategoryEntity();
		itemEntity.setCategoryId(dto.getCategory());
		entity.setCategory(itemEntity);
		return entity;
		
	}
	
	public static ArticleStockRespDto entityToDto(ArticleStockEntity entity) {
		ArticleStockRespDto dto = new ArticleStockRespDto();
		dto.setArticleName(entity.getArticleName());
		dto.setTagId(entity.getTagId());
		dto.setBarcode(entity.getBarcode());
		dto.setGrossWeight(entity.getGrossWeight());
		dto.setHuid(entity.getHuid());
		dto.setNetWeight(entity.getNetWeight());
		dto.setPurity(entity.getPurity());
		dto.setStoneWeight(entity.getStoneWeight());
		dto.setBrandId(entity.getBrand().getBrandId());
		dto.setSubsidiary(entity.getSubsidiary().getIdxId());
		dto.setCategory(entity.getCategory().getCategoryId());
		return dto;
		
	}
}
