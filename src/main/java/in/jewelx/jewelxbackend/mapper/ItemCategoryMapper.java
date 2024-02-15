package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class ItemCategoryMapper {

	public static ItemCategoryEntity dtoToItemCategoryEntity(ItemCategoryDto itemCategoryDto) {
		ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
		itemCategoryEntity.setCategoryName(itemCategoryDto.getCategoryName());
		itemCategoryEntity.setCategoryMetal(itemCategoryDto.getCategoryMetal());
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(itemCategoryDto.getUserId());
		itemCategoryEntity.setCreatedBy(userEntity);
		itemCategoryEntity.setUpdatedBy(userEntity);
		return itemCategoryEntity;
	}

	public static ItemCategoryRespDto itemCategoryEntityToDto(ItemCategoryEntity itemCategoryEntity) {
		ItemCategoryRespDto itemCategoryRespDto = new ItemCategoryRespDto();
		itemCategoryRespDto.setId(itemCategoryEntity.getCategoryId());
		itemCategoryRespDto.setCategoryName(itemCategoryEntity.getCategoryName());
		itemCategoryRespDto.setCategoryMetal(itemCategoryEntity.getCategoryMetal());
		return itemCategoryRespDto;

	}
}
