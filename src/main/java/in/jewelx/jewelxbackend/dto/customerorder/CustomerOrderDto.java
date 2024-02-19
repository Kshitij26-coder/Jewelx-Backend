package in.jewelx.jewelxbackend.dto.customerorder;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderDto {
	
	private Long metalId; 
	
	private Long customerId;
	
	private BigDecimal purity;
	
	private String articleDescription;
	
	private BigDecimal grossWeight;
	
	//This is weight for metal stock entity
	private BigDecimal netWeight;
	
	private BigDecimal paidAmount;
	
	private String orderStatus;
	
	private Date fullfillDate;
	
	private BigDecimal metalRate;
	
	private Long userID;
	
//	private Long accountingId;
	
	
	//for Accounting Entity
		private String transactionType; //only 1 length(credit / debit)
		
		private String transactionDescription;
		
		private String transactionMode; // lenght is 3
		
		private String chequeNo;
		
		private BigDecimal chequeAmount;
		
		private BigDecimal cashAmount;
		
		private String netBankingUTR;
	    
	    private BigDecimal netBankingAmount;
	    
	    
	    //for Weight Details Entity 
	    private Long uomId; 
	    
	    private String weightTransactionType;
}
