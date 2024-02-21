package in.jewelx.jewelxbackend.dto.accounting;

import java.math.BigDecimal;
//import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountingDto {

	private String transactionType;

	private String description;

	private String transactionMode;

	private String chequeNo;

	private BigDecimal chequeAmount;

	private BigDecimal cashAmount;

	private String netBankingUTR;

	private BigDecimal netBankingAmount;

	private Long userId;

	private Long brandId;

	private Long subsidiaryId;
}
