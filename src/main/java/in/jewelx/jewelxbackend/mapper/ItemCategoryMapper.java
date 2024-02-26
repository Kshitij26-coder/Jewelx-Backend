package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.MetalEntity;

public class ItemCategoryMapper {

	public static ItemCategoryEntity dtoToItemCategoryEntity(ItemCategoryDto itemCategoryDto) {
		ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
		itemCategoryEntity.setCategoryName(itemCategoryDto.getCategoryName());

		MetalEntity metal = new MetalEntity();
		metal.setMetalId(itemCategoryDto.getMetalId());
		itemCategoryEntity.setMetal(metal);

		BrandEntity brand = new BrandEntity(itemCategoryDto.getBrandId());
		itemCategoryEntity.setBrand(brand);

		return itemCategoryEntity;
	}

	public static ItemCategoryRespDto itemCategoryEntityToDto(ItemCategoryEntity itemCategoryEntity) {
		ItemCategoryRespDto itemCategoryRespDto = new ItemCategoryRespDto();
		itemCategoryRespDto.setId(itemCategoryEntity.getCategoryId());
		itemCategoryRespDto.setCategoryName(itemCategoryEntity.getCategoryName());
		itemCategoryRespDto.setMetal(MetalMapper.entityToMetalShortDto(itemCategoryEntity.getMetal()));
		return itemCategoryRespDto;

	}
}
