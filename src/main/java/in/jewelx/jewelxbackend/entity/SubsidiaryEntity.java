package in.jewelx.jewelxbackend.entity;

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
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "subsidiaries")
public class SubsidiaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "subsidiary_id")
	private UUID subsidiaryId;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private BrandEntity brand;

	@Column(name = "shop_act_number", length = 18, nullable = false)
	private String shopActNumber;

	@Column(name = "subsidiary_name", length = 255, nullable = false)
	private String subsidiaryName;

	@Column(name = "address", columnDefinition = "TEXT", nullable = false)
	private String address;

	@Column(name = "city", length = 150, nullable = false)
	private String city;

	@Column(name = "gstin", length = 15, nullable = false)
	private String gstin;

	@Column(name = "logo_url", length = 150)
	private String logoUrl;

	@Column(name = "form_header", columnDefinition = "TEXT")
	private String formHeader;

	@Column(name = "form_footer", columnDefinition = "TEXT")
	private String formFooter;

	@Column(name = "pincode")
	private Long pinCode;

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

	@Column(name = "is_Active", nullable = false)
	private boolean isActive;

	public SubsidiaryEntity() {
		this.subsidiaryId = UUID.randomUUID();
		this.isActive = false;
	}

	public SubsidiaryEntity(BrandEntity brand, String shopActNumber, String subsidiaryName, String address, String city,
			String gstin, String logoUrl, String formHeader, String formFooter, UserEntity user, Long pinCode) {
		super();
		this.subsidiaryId = UUID.randomUUID();
		this.brand = brand;
		this.shopActNumber = shopActNumber;
		this.subsidiaryName = subsidiaryName;
		this.address = address;
		this.city = city;
		this.gstin = gstin;
		this.logoUrl = logoUrl;
		this.formHeader = formHeader;
		this.formFooter = formFooter;
		this.createdBy = user;
		this.updatedBy = user;
		this.isActive = false;
		this.pinCode = pinCode;
	}

	public SubsidiaryEntity(Long idxId) {
		super();
		this.idxId = idxId;
	}

}
