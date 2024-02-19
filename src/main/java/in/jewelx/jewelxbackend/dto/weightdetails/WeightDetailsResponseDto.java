package in.jewelx.jewelxbackend.dto.weightdetails;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightDetailsResponseDto {
	
	private Long idxId;
	
	private UUID detailId;
	
	private Long customer;

	private Long metalID;

	private BigDecimal metalWeight;

	private Long uomId;

	private Long userID;
}
