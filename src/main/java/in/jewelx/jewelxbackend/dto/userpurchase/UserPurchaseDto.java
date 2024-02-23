package in.jewelx.jewelxbackend.dto.userpurchase;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPurchaseDto {
		
	private Long metalId;

	private Long customerId;

	private BigDecimal purity;

	private String articleDescription;

	private BigDecimal netWeight;

	private BigDecimal grossWeight;

	private BigDecimal metalRate;

	//private BigDecimal totalAmount;
	
	private Long subsidiaryId;
	
	private Long brandId;
	
	private Long userId;
	
	private String transactionType;
	
	private String transactionMode;

	private String chequeNo;

	private BigDecimal chequeAmount;

	private BigDecimal cashAmount;

	private String netBankingUTR;

	private BigDecimal netBankingAmount;
}
