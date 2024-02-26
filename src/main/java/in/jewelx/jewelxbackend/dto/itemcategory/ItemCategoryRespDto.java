package in.jewelx.jewelxbackend.dto.itemcategory;

import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategoryRespDto {

	private Short id;
	private String categoryName;
	private MetalShortDto metal;
	private Long userId;
	private Long brandId;

}
