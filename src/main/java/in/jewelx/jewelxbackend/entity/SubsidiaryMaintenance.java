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
@Table(name = "subsidiary_maintenance")
public class SubsidiaryMaintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "transaction_id")
	private UUID transactionId;

	@Column(name = "transaction_amount", precision = 8, nullable = false)
	private BigDecimal transactionAmount;

	@Column(name = "transaction_type", length = 1, nullable = false)
	private String transactionType;

	@Column(name = "transaction_description", precision = 15, nullable = false)
	private BigDecimal transactionDescription;

	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Column(name = "transaction_mode", length = 3, nullable = false)
	private String transactionMode;

	@ManyToOne
	@JoinColumn(name = "accounting_id", nullable = false)
	private AccountingEntity accounting;

	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false)
	private LocalDateTime updatedOn;

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false, updatable = false)
	private UserEntity createdBy;

	@ManyToOne
	@JoinColumn(name = "updated_by", nullable = false)
	private UserEntity updatedBy;

}
