package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;
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

@Entity
@Table(name = "user_purchase")
public class UserPurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "purchase_id")
	private UUID purchaseId;

	@ManyToOne
	@JoinColumn(name = "metal_id", nullable = false)
	private MetalEntity metal;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "purity", precision = 3, nullable = false)
	private BigDecimal purity;

	@Column(name = "article_description", length = 50, nullable = false)
	private String articleDescription;

	@Column(name = "net_weight", precision = 8, nullable = false)
	private BigDecimal netWeight;

	@Column(name = "gross_weight", precision = 8, nullable = false)
	private BigDecimal grossWeight;

	@Column(name = "metal_rate", precision = 8, nullable = false)
	private BigDecimal metalRate;

	@Column(name = "total_amount", precision = 8, nullable = false)
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "accounting_id", nullable = false)
	private AccountingEntity accounting;

	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false, updatable = false)
	private LocalDateTime updatedOn;

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false, updatable = false)
	private UserEntity createdBy;

	@ManyToOne
	@JoinColumn(name = "updated_by", nullable = false, updatable = false)
	private UserEntity updatedBy;

	// Constructors, getters, and setters

	public UserPurchaseEntity() {
		// Default constructor
	}
}
