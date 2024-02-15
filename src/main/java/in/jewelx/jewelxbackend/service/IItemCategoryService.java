package in.jewelx.jewelxbackend.service;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;

import org.springframework.data.domain.Page;

public interface IItemCategoryService {

	String createItemcategory(ItemCategoryDto itemCategoryDto);

	Page<ItemCategoryRespDto> getAllItemCategories(int pageNumber, int pageSize);

	String deleteItemCategoryById(Short id);

	ItemCategoryRespDto getItemCategoryById(Short id);

	String updateItemCategory(Short id, ItemCategoryDto itemCategoryDto);

}
