package in.jewelx.jewelxbackend.dto.userpurchase;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPurchaseResponseDto {
	
	private Long idxId;

	private UUID purchaseId;

	private Long metal;

	private Long customer;

	private BigDecimal purity;

	private String articleDescription;

	private BigDecimal netWeight;

	private BigDecimal grossWeight;

	private BigDecimal metalRate;

	private BigDecimal totalAmount;
	
	private Long accounting;

	private Long subsidiaryId;

	private Long brandId;
}
