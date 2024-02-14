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
@Table(name = "customer_orders")
public class CustomerOrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "order_id")
	private UUID orderId;

	@Column(name = "metal_id", nullable = false)
	private Long metalId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "purity", nullable = false)
	private Long purity;

	@Column(name = "article_description", length = 200, nullable = false)
	private String articleDescription;

	@Column(name = "gross_weight", precision = 8, nullable = false)
	private BigDecimal grossWeight;

	@Column(name = "net_weight", nullable = false)
	private Long netWeight;

	@Column(name = "paid_amount", precision = 15, nullable = false)
	private BigDecimal paidAmount;

	@Column(name = "order_status", length = 2, nullable = false)
	private String orderStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "fulfill_date", nullable = false)
	private Date fulfillDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date", nullable = false)
	private Date orderDate;

	@Column(name = "metal_rate", precision = 8, nullable = false)
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

	// Constructors, getters, and setters

	public CustomerOrderEntity() {
		// Default constructor
	}
}
