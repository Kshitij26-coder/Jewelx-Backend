package in.jewelx.jewelxbackend.dto.articlestock;

import in.jewelx.jewelxbackend.dto.itemcategory.ItemCategoryRespDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleStockRespDto {
	
	private String barcode;

    private Double grossWeight;

    private Double netWeight;

    private Double purity;

    private Double stoneWeight;

    private String huid;

    private ItemCategoryRespDto category;
}
