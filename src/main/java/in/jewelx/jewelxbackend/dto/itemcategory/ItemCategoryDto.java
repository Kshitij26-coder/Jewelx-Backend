package in.jewelx.jewelxbackend.dto.itemcategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategoryDto {
	
	private Long categoryId;
	private String categoryName;
	private Long metalId;
	private Long userId;
	private Long brandId;

}
