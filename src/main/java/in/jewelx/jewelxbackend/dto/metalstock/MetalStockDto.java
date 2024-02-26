package in.jewelx.jewelxbackend.dto.metalstock;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MetalStockDto {

	private BigDecimal weight;

	private Long userId;

	private Long metalId;

	private Long brandId;

	private Long subsidiaryid;

	private Long uom;

}
