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
/*
 * This entity store the data of metal inventory 
 * */
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "metal_stocks")
public class MetalStockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long stockId;
	
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "stock_uid")
	private UUID metalStockUUID;

	@ManyToOne
	@JoinColumn(name = "metal_id", nullable = false)
	private MetalEntity metal;

	@Column(name = "opening_weight", precision = 8, scale = 3, nullable = false)
	private BigDecimal openingWeight;

	@Column(name = "closing_weight", precision = 8, scale = 3, nullable = false)
    private BigDecimal closingWeight;

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

	// Constructors, getters, and setters
	@Column(name = "transaction_weight", precision = 8, scale = 3, nullable = false)
    private BigDecimal transactionWeight;

	public MetalStockEntity() {
		this.metalStockUUID = UUID.randomUUID();
	}

	public MetalStockEntity(MetalEntity metal, BigDecimal closingWeight, BigDecimal openingWeight) {
		super();
		this.metalStockUUID = UUID.randomUUID();
		this.metal = metal;
		this.closingWeight = closingWeight;
		this.openingWeight = openingWeight;
	}
	
	
	
}
