package in.jewelx.jewelxbackend.dto.subsidiary_maintenance;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubsidiaryMaintenanceDto {

	private String maintenanceDescription;

	private Long accountingId;

	private Long userId;

	// for Accounting Entity
	private String transactionType; // only 1 length(credit / debit)

	private String transactionDescription;

	private String transactionMode; // lenght is 3

	private String chequeNo;

	private BigDecimal chequeAmount;

	private BigDecimal cashAmount;

	private String netBankingUTR;

	private BigDecimal netBankingAmount;
}
