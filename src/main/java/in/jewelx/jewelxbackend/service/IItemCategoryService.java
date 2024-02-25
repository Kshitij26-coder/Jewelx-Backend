package in.jewelx.jewelxbackend.service;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;

public interface IItemCategoryService {

	String createItemcategory(ItemCategoryDto itemCategoryDto);

	String deleteItemCategoryById(Short id);

	ItemCategoryRespDto getItemCategoryById(Short id);

	String updateItemCategory(Short id, ItemCategoryDto itemCategoryDto);

	Page<ItemCategoryRespDto> getAllItemCategories(int pageNumber, int pageSize, Long brandId);

}
