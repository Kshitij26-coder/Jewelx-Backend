package in.jewelx.jewelxbackend.dto.sale;

import java.math.BigDecimal;
import java.util.List;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDto {

	private Long customerId;
	private BigDecimal sgst;
	private BigDecimal cgst;
	private BigDecimal totalMakingCharges;
	private BigDecimal discount;
	private BigDecimal netAmount;
	private BigDecimal payableAmount;
	private Long subsidiaryId;
	private Long accountingId;
	private Long orderId;
	private Long userId;
	private Long brandId;

	// Accounting Dto
	private String transactionType;
	private String transactionDescription;
	private String transactionMode;
	private String chequeNo;
	private BigDecimal chequeAmount;
	private String netBankingUTR;
	private BigDecimal netBankingAmount;
	private BigDecimal cashAmount;

	// ItemSale Dto
	private List<ItemSaleDto> saleItems;
}
