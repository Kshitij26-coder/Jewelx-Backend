package in.jewelx.jewelxbackend.entity;

import java.time.LocalDateTime;

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

@Getter
@Setter
@Entity
@Table(name = "metal_master")
public class MetalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "metal_id")
	private Long metalId;

	@Column(name = "metal_description", nullable = false, length = 100)
	private String metalDescription;

	@Column(name = "metal_rate", nullable = false, precision = 15)
	private Double metalRate;

	@Column(name = "metal_name", nullable = false, length = 20)
	private String metalName;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private BrandEntity brand;

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

	public MetalEntity() {
		// Default constructor
	}

	public MetalEntity(String metalDescription, Double metalRate, String metalName, UserEntity updatedBy,
			BrandEntity brand) {
		super();
		this.metalDescription = metalDescription;
		this.metalRate = metalRate;
		this.metalName = metalName;
		this.updatedBy = updatedBy;
		this.brand = brand;
	}

	public MetalEntity(Long metalId) {
		super();
		this.metalId = metalId;
	}

}
