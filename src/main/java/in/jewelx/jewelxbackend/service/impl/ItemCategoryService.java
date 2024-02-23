package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.ItemCategoryMapper;
import in.jewelx.jewelxbackend.repository.ItemCategoryRepository;
import in.jewelx.jewelxbackend.service.IItemCategoryService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ItemCategoryService implements IItemCategoryService {

	@Autowired
	private ItemCategoryRepository itemCategoryRepo;

	@Override
	public String createItemcategory(ItemCategoryDto itemCategoryDto) {
		if (itemCategoryDto == null) {
			throw new NullObjectException("ItemCategoryDto is null");
		}
		ItemCategoryEntity itemCategoryEntity = ItemCategoryMapper.dtoToItemCategoryEntity(itemCategoryDto);
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(itemCategoryDto.getUserId());
		itemCategoryEntity.setCreatedBy(userEntity);
		itemCategoryEntity.setUpdatedBy(userEntity);
		itemCategoryRepo.save(itemCategoryEntity);
		return "Item category created successfully";
	}

	@Override
	public Page<ItemCategoryRespDto> getAllItemCategories(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<ItemCategoryEntity> allItemcategories = itemCategoryRepo.findAll(pageRequest);
		return allItemcategories.map(ItemCategoryMapper::itemCategoryEntityToDto);
	}

	@Override
	public String deleteItemCategoryById(Short id) {
		getItemCategoryById(id);
		itemCategoryRepo.deleteById(id);
		return "Item Category deleted successfully";
	}

	@Override
	public ItemCategoryRespDto getItemCategoryById(Short id) {
		ItemCategoryEntity foundItemCategory = itemCategoryRepo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Item Category Id"));
		return ItemCategoryMapper.itemCategoryEntityToDto(foundItemCategory);
	}

	@Override
	public String updateItemCategory(Short id, ItemCategoryDto itemCategoryDto) {
		ItemCategoryEntity updatedItemCategory = ItemCategoryMapper.dtoToItemCategoryEntity(itemCategoryDto);
		ItemCategoryEntity foundItemCategory = itemCategoryRepo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Item Category Id"));
		foundItemCategory.setCategoryName(updatedItemCategory.getCategoryName());
		foundItemCategory.setMetal(updatedItemCategory.getMetal());
		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(itemCategoryDto.getUserId());
		foundItemCategory.setUpdatedBy(userEntity);
		itemCategoryRepo.save(foundItemCategory);
		return "Item category updated successfully";
	}

}
