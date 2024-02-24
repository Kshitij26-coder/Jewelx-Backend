package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name = "customer_orders")
public class CustomerOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "order_id")
	private UUID orderId;

	@ManyToOne
	@JoinColumn(name = "metal_id", nullable = false)
	private MetalEntity metal;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "purity", precision = 8, scale = 3, nullable = false)
	private BigDecimal purity;

	@Column(name = "article_description", length = 200, nullable = false)
	private String articleDescription;

	@Column(name = "gross_weight", precision = 8, scale = 3)
	private BigDecimal grossWeight;

	@Column(name = "net_weight", precision = 8, scale = 3)
	private BigDecimal netWeight;

	@Column(name = "paid_amount", precision = 15)
	private BigDecimal paidAmount;

	@Column(name = "order_status", length = 2, nullable = false)
	private String orderStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fulfill_date", nullable = false, updatable = false)
	private Date fulfillDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date", nullable = false)
	@CreationTimestamp
	private Date orderDate;

	@Column(name = "metal_rate", precision = 8, scale = 2, nullable = false)
	private BigDecimal metalRate;

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

	@ManyToOne
	@JoinColumn(name = "accounting_id", nullable = false, updatable = false)
	private AccountingEntity accounting;

	@ManyToOne
	@JoinColumn(name = "subsidiary_id", nullable = false)
	private SubsidiaryEntity sunsidiary;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private BrandEntity brand;

	// Constructors, getters, and setters

	public CustomerOrderEntity() {
		this.orderId = UUID.randomUUID();
	}

	public CustomerOrderEntity(MetalEntity metal, CustomerEntity customer, BigDecimal purity, String articleDescription,
			BigDecimal grossWeight, BigDecimal netWeight, BigDecimal paidAmount, String orderStatus, Date fulfillDate,
			BigDecimal metalRate) {
		super();
		this.orderId = UUID.randomUUID();
		this.metal = metal;
		this.customer = customer;
		this.purity = purity;
		this.articleDescription = articleDescription;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.paidAmount = paidAmount;
		this.orderStatus = orderStatus;
		this.fulfillDate = fulfillDate;
		this.metalRate = metalRate;
	}

	public CustomerOrderEntity(Long idxId) {
		super();
		this.idxId = idxId;
	}

}
