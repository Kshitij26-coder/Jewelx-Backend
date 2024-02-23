package in.jewelx.jewelxbackend.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.accounting.AccountingShortDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleResponse;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseById extends SaleResponseDto{

	private Long saleIdxId;
	private UUID saleId;
	private LocalDateTime createdOn;
	private CustomerShortDto customer;
	private BigDecimal netAmount;
	private BigDecimal payableAmount;
	private AccountingShortDto accounting;
	private Long customerOrderId;
	private UserShortDetailsDto user;
	private List<ItemSaleResponse> itemSaleList;
	
}
