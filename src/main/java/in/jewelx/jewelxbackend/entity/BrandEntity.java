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
@Table(name = "brands")
public class BrandEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "image_url", length = 255)
	private String imageUrl;

	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@ManyToOne
	@JoinColumn(name = "updated_by", nullable = true)
	private UserEntity updatedBy;

	public BrandEntity() {
		// Default constructor

	}

	public BrandEntity(String name, String description, String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "BrandEntity [brandId=" + brandId + ", name=" + name + ", description=" + description + ", imageUrl="
				+ imageUrl + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy + "]";
	}

	public BrandEntity(Long brandId) {
		super();
		this.brandId = brandId;
	}

}
