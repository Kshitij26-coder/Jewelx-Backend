package in.jewelx.jewelxbackend.dto.userpurchase;

import java.math.BigDecimal;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
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

	private MetalShortDto metal;

	private CustomerShortDto customer;

	private BigDecimal purity;

	private String articleDescription;

	private BigDecimal netWeight;

	private BigDecimal grossWeight;

	private BigDecimal metalRate;

	private BigDecimal totalAmount;

	private Long accounting;

	private SubsidiaryShortDetailsDto subsidiaryId;

	private Long brandId;
}
