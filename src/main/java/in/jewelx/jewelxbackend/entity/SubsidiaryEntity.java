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

@Entity
@Table(name = "subsidiaries")
public class SubsidiaryEntity {
	   @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    @Column(name = "subsidiary_id")
	    private UUID subsidiaryId;

	   @ManyToOne
	    @JoinColumn(name = "brand_id",nullable=false)
	    private BrandEntity brand;
	   
	    @Column(name = "shop_act_number", length = 18, nullable = false)
	    private String shopActNumber;

	    @Column(name = "subsidiary_name", length = 255, nullable = false)
	    private String subsidiaryName;

	    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
	    private String address;

	    @Column(name = "city", length = 20, nullable = false)
	    private String city;

	    @Column(name = "gstin", length = 15, nullable = false)
	    private String gstin;

	    @Column(name = "cash_balance", precision = 15, nullable = false)
	    private double cashBalance;

	    @Column(name = "logo_url", length = 150, nullable = false)
	    private String logoUrl;

	    @Column(name = "form_header", columnDefinition = "TEXT", nullable = false)
	    private String formHeader;

	    @Column(name = "form_footer", columnDefinition = "TEXT", nullable = false)
	    private String formFooter;
	    
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

		public UUID getSubsidiaryId() {
			return subsidiaryId;
		}

		public void setSubsidiaryId(UUID subsidiaryId) {
			this.subsidiaryId = subsidiaryId;
		}

		public BrandEntity getBrand() {
			return brand;
		}

		public void setBrand(BrandEntity brand) {
			this.brand = brand;
		}

		public String getShopActNumber() {
			return shopActNumber;
		}

		public void setShopActNumber(String shopActNumber) {
			this.shopActNumber = shopActNumber;
		}

		public String getSubsidiaryName() {
			return subsidiaryName;
		}

		public void setSubsidiaryName(String subsidiaryName) {
			this.subsidiaryName = subsidiaryName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getGstin() {
			return gstin;
		}

		public void setGstin(String gstin) {
			this.gstin = gstin;
		}

		public double getCashBalance() {
			return cashBalance;
		}

		public void setCashBalance(double cashBalance) {
			this.cashBalance = cashBalance;
		}

		public String getLogoUrl() {
			return logoUrl;
		}

		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}

		public String getFormHeader() {
			return formHeader;
		}

		public void setFormHeader(String formHeader) {
			this.formHeader = formHeader;
		}

		public String getFormFooter() {
			return formFooter;
		}

		public void setFormFooter(String formFooter) {
			this.formFooter = formFooter;
		}

		public LocalDateTime getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(LocalDateTime createdOn) {
			this.createdOn = createdOn;
		}

		public LocalDateTime getUpdatedOn() {
			return updatedOn;
		}

		public void setUpdatedOn(LocalDateTime updatedOn) {
			this.updatedOn = updatedOn;
		}

		public UserEntity getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(UserEntity createdBy) {
			this.createdBy = createdBy;
		}

		public UserEntity getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(UserEntity updatedBy) {
			this.updatedBy = updatedBy;
		}

		@Override
		public String toString() {
			return "SubsidiaryEntity [subsidiaryId=" + subsidiaryId + ", brand=" + brand + ", shopActNumber="
					+ shopActNumber + ", subsidiaryName=" + subsidiaryName + ", address=" + address + ", city=" + city
					+ ", gstin=" + gstin + ", cashBalance=" + cashBalance + ", logoUrl=" + logoUrl + ", formHeader="
					+ formHeader + ", formFooter=" + formFooter + ", createdOn=" + createdOn + ", updatedOn="
					+ updatedOn + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
		}
	    
	    
}
