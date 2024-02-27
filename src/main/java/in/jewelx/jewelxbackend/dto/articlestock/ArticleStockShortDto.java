package in.jewelx.jewelxbackend.dto.articlestock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleStockShortDto {

	private Long tagId;

	private String articleName;

	private String barcode;

	private Double grossWeight;

	private Double netWeight;

	private Double purity;

	private Double stoneWeight;

	private String huid;
	
	private String status;

}
