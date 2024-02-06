package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;
import java.sql.Date;
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

@Entity
@Table(name = "accounting")
public class AccountingEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accounting_id")
    private UUID accountingId;

    @Column(name = "current_balance", precision = 15,  nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "transaction_amount", precision = 15, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_type", length = 1, nullable = false)
    private String transactionType;

    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "transaction_mode", length = 3, nullable = false)
    private String transactionMode;

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
    // Constructors, getters, and setters

    public AccountingEntity() {
        // Default constructor
    }
}
