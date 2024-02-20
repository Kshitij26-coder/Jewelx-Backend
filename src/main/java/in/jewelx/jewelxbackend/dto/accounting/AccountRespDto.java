package in.jewelx.jewelxbackend.dto.accounting;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountRespDto {
	private Long id;

	private UUID accountingId;

	private BigDecimal openigBalance;

	private BigDecimal closingBalance;

	private BigDecimal transactionAmount;

	private String transactionType;

	private Date transactionDate;

	private String description;

	private String transactionMode;

	private String chequeNo;

	private BigDecimal chequeAmount;

	private BigDecimal cashAmount;

	private String netBankingUTR;

	private BigDecimal netBankingAmount;

}
