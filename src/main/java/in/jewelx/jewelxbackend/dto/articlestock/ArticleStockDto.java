package in.jewelx.jewelxbackend.dto.articlestock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleStockDto {
	
	private String articleName;
	
    private Double grossWeight;

    private Double netWeight;

    private Double purity;

    private Double stoneWeight;

    private String huid;

    private Short category;

    private Long subsidiaryId;
    
    private Long userIdx;
    
    private Long brandId;

}
