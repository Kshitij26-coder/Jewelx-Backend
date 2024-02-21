package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounting")
public class AccountingEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx_id")
	private Long idxId;
    
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accounting_id")
    private UUID accountingId;

    @Column(name = "opening_balance", precision = 15,scale=2,  nullable = false)
    private BigDecimal openigBalance;
    
    @Column(name = "closing_balance", precision = 15,scale=2,  nullable = false)
    private BigDecimal closingBalance;

    @Column(name = "transaction_amount", precision = 15, nullable = false)
    private BigDecimal transactionAmount;

    //this for payment type (credit/debit)
    @Column(name = "transaction_type", length = 2, nullable = false)
    private String transactionType;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    //this is for payment mode
    @Column(name = "transaction_mode", length = 10, nullable = false)
    private String transactionMode;
    
    @Column(name = "cheque_no", length=30)
    private String chequeNo;
    
    @Column(name="cheque_amount",precision = 15,scale=2)
    private BigDecimal chequeAmount;
    
    @Column(name="cash_amount",precision = 15,scale=2)
    private BigDecimal cashAmount;
    
    @Column(name="netbanking_utr", length=30)
    private String netBankingUTR;
    
    @Column(name="netbanking_amount",precision = 15,scale=2)
    private BigDecimal netBankingAmount;
    
    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false,updatable = false)
    private LocalDateTime updatedOn;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false,updatable = false)
    private UserEntity updatedBy;
    
    @ManyToOne
    @JoinColumn(name = "brand_id",nullable = false)
    private BrandEntity brand;
    // Constructors, getters, and setters
    
    @ManyToOne
    @JoinColumn(name = "subsidiary_id", nullable = false)
    private SubsidiaryEntity subsidiary;
    
    public AccountingEntity() {
    	this.transactionDate = Date.valueOf(LocalDate.now());
    	this.accountingId = UUID.randomUUID();
    }
}
