package in.jewelx.jewelxbackend.dto.itemcategory;

import in.jewelx.jewelxbackend.entity.MetalEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategoryDto {

	private String categoryName;
	private MetalEntity categoryMetal;
	private Long userId;

}
