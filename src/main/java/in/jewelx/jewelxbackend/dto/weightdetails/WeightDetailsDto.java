package in.jewelx.jewelxbackend.dto.weightdetails;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightDetailsDto {

	private Long customer;

	private Long metalID;

	private BigDecimal metalWeight;

	private Long uomId;

	private Long userID;
	
	private String weightTransactionType;

}
