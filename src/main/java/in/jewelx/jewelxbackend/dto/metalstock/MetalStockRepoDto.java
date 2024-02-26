package in.jewelx.jewelxbackend.dto.metalstock;

import java.math.BigDecimal;

import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetalStockRepoDto {

	private MetalShortDto metal;

	private BigDecimal openingWeight;

	private BigDecimal closingWeight;

	private UserShortDetailsDto updatedBy;

	private UserShortDetailsDto createdBy;

	private BigDecimal transactionWeight;

}
