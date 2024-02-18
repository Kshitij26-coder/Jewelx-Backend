package in.jewelx.jewelxbackend.dto.metalstock;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetalStockDto {

	private BigDecimal weight;

	private Long userId;
	
	private Long metalId;
	
	
}
