package in.jewelx.jewelxbackend.dto.itemsale;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaleDto {

	private Long tagId;

	private BigDecimal itemAmount;

	private BigDecimal metalRate;

	private BigDecimal makingCharges;

	private BigDecimal payableAmount;

	private BigDecimal artifactAmount;

	private Long userId;

	private Long saleId;
}
