package in.jewelx.jewelxbackend.dto.itemsale;

import java.math.BigDecimal;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaleResponse {

	private ArticleStockShortDto articleStock;

	private Long idxId;

	private UUID itemSaleId;

	private BigDecimal itemAmount;

	private BigDecimal metalRate;

	private BigDecimal artifactAmount;

	private BigDecimal makingCharges;

	private BigDecimal payableAmount;
}
