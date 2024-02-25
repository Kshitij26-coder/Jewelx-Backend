package in.jewelx.jewelxbackend.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
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

/*
 * This entity store the data of different users such 'owner', 'employee', 'manager' etc.
 * */
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx_id")
	private Long idxId;

	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@Column(name = "user_name", length = 100, nullable = false)
	private String userName;

	@ManyToOne()
	@JoinColumn(name = "brand_id", nullable = false)
	private BrandEntity brand;

	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "mobile_number", length = 15, nullable = false)
	private String mobileNumber;

	@Column(name = "password", length = 500, nullable = false)
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

	public UserEntity(String userName, String email, String mobileNumber, String password, String userRole,
			SubsidiaryEntity subsidiary, UserEntity assignedBy, BrandEntity brandEntity) {
		super();
		this.userId = UUID.randomUUID();
		this.userName = userName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.userRole = userRole;
		this.subsidiary = subsidiary;
		this.assignedBy = assignedBy;
		this.brand = brandEntity;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	// Note: getUsernmae returns email and getUserName return full name of the user
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	public UserEntity(Long idxId) {
		super();
		this.idxId = idxId;
	}
	
	

}
