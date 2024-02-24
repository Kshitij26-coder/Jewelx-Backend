package in.jewelx.jewelxbackend.dto.accounting;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountingShortDto {

	private Long id;
	private UUID accountingId;
	private BigDecimal closingBalance;
	private String transactionMode;
}
