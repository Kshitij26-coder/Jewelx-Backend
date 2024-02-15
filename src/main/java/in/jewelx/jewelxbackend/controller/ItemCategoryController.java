package in.jewelx.jewelxbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryDto;
import in.jewelx.jewelxbackend.service.impl.ItemCategoryService;

@RestController
@RequestMapping("/item-category")
public class ItemCategoryController {

	@Autowired
	private ItemCategoryService itemCategoryService;

	@PostMapping
	public ResponseEntity<?> createItemCategory(@RequestBody ItemCategoryDto itemCategoryDto) {
		return ResponseEntity.ok(itemCategoryService.createItemcategory(itemCategoryDto));
	}

	@GetMapping
	public ResponseEntity<?> getAllItemCategory(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(itemCategoryService.getAllItemCategories(page, size));
	}

	@GetMapping("/{itemCategoryId}")
	public ResponseEntity<?> getItemcategoryById(@PathVariable("itemCategoryId") Short id) {
		return ResponseEntity.ok(itemCategoryService.getItemCategoryById(id));
	}

	@PutMapping("/{itemCategoryId}")
	public ResponseEntity<?> updateItemCategoryById(@PathVariable("customerId") Short id,
			@RequestBody ItemCategoryDto itemCategoryDto) {
		return ResponseEntity.ok(itemCategoryService.updateItemCategory(id, itemCategoryDto));
	}

	@DeleteMapping("/{itemCategoryId}")
	public ResponseEntity<?> deleteItemCategoryById(@PathVariable("customerId") Short id) {
		return ResponseEntity.ok(itemCategoryService.deleteItemCategoryById(id));
	}
}
