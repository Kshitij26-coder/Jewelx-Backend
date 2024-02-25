package in.jewelx.jewelxbackend.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.accounting.AccountingShortDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryResponseDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleResponseDto {

	private Long saleIdxId;
	private UUID saleId;
	private LocalDateTime createdOn;
	private BigDecimal discount;
	private BigDecimal netAmount;
	private BigDecimal payableAmount;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private CustomerShortDto customer;
	private AccountingShortDto accounting;
	private Long customerOrderId;
	private SubsidiaryResponseDto subsidiary;
	private UserShortDetailsDto user;
	
}
