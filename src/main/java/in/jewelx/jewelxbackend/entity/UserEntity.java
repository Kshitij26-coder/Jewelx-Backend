package in.jewelx.jewelxbackend.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * This entity store the data of different users such 'owner', 'employee', 'manager' etc.
 * */
@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id",nullable=false) 
    private BrandEntity brand;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "mobile_number", length = 15, nullable = false)
    private String mobileNumber;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "user_role", length = 1, nullable = false)
    private String userRole;

    @ManyToOne
    @JoinColumn(name = "subsidiary_id", nullable = true)
    private SubsidiaryEntity subsidiary;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "assigned_by", nullable = true)
    private UserEntity assignedBy;
    
    @Column(name = "is_active", nullable = false)
    @ColumnDefault("false")
    private boolean isActive;
  
    @Column(name = "is_logged_in", nullable = false)
    @ColumnDefault("false")
    private boolean isLoggedIn;
   
    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;
    


    public UserEntity() {
        // Default constructor
        this.userId = UUID.randomUUID();
    }

	public UserEntity(String userName, String email, String mobileNumber, String password,
			String userRole, SubsidiaryEntity subsidiary, UserEntity assignedBy) {
		super();
		this.userName = userName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.userRole = userRole;
		this.subsidiary = subsidiary;
		this.assignedBy = assignedBy;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public SubsidiaryEntity getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(SubsidiaryEntity subsidiary) {
		this.subsidiary = subsidiary;
	}

	public UserEntity getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserEntity assignedBy) {
		this.assignedBy = assignedBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
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

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", brand=" + brand + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", password=" + password + ", userRole=" + userRole
				+ ", subsidiary=" + subsidiary + ", assignedBy=" + assignedBy + ", isActive=" + isActive
				+ ", isLoggedIn=" + isLoggedIn + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+"]";
	}
	
}

