package in.jewelx.jewelxbackend.dto.customerorder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.metal.MetalShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRespDto {

	private Long idxId;

	private UUID orderId;

	private MetalShortDto metal;

	private CustomerShortDto customer;

	private BigDecimal purity;

	private String articleDescription;

	private BigDecimal grossWeight;

	private BigDecimal netWeight;

	private String orderStatus;

	private Date fulfillDate;

	private Date orderDate;

	private Long brandId;

	private Long subsidiaryId;

}
