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
import lombok.Getter;
import lombok.Setter;

/*
 * This entity stores information about any credit or debit in terms of metal of a customer.
 * */
@Getter
@Setter
@Entity
@Table(name = "weight_details")
public class WeightDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "detail_id", length = 36)
	private UUID detailId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@ManyToOne
	@JoinColumn(name = "metal_id", nullable = false)
	private MetalEntity metal;

	@Column(name = "metal_weight", precision = 6, scale = 3, nullable = false)
	private BigDecimal metalWeight;

	@Column(name = "transaction_type", length = 2, nullable = false)
	private String metalTransactionType;

	@ManyToOne
	@JoinColumn(name = "uom_code", nullable = false)
	private UnitOfMeasurementEntity uom;

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

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private BrandEntity brand;

	@ManyToOne
	@JoinColumn(name = "subsidiary_id", nullable = false)
	private SubsidiaryEntity subsidiary;

	// Constructors, getters, and setters
	public WeightDetailEntity() {
		this.detailId = UUID.randomUUID();

	}
}
