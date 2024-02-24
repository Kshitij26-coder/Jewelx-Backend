package in.jewelx.jewelxbackend.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.accounting.AccountingShortDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponseDto {

	protected Long saleIdxId;
	protected UUID saleId;
	protected LocalDateTime createdOn;
	protected CustomerShortDto customer;
	protected BigDecimal netAmount;
	protected BigDecimal payableAmount;
	protected AccountingShortDto accounting;
	protected Long customerOrderId;
	protected UserShortDetailsDto user;

}
