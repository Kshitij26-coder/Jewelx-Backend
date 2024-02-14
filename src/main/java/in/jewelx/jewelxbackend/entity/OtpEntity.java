package in.jewelx.jewelxbackend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "otps")
public class OtpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "otp_code")
	private String otpCode;

	@CreationTimestamp
	@Column(name = "generated_at")
	private LocalDateTime generatedAt;

	@Column(name = "is_used")
	private boolean used;

	@Column(name = "expiry_time")
	private LocalDateTime expiryTime;

	@Transient
	private final static int expiryTimeInMinutes = 5;
	// Constructors, getters, and setters

	// Constructor to set the expiry time 5 minutes later than the current time
	public OtpEntity() {
		this.generatedAt = LocalDateTime.now();
		this.expiryTime = this.generatedAt.plusMinutes(expiryTimeInMinutes); // Set expiry 5 minutes later
	}

	public OtpEntity(String email, String otpCode) {
		super();
		this.email = email;
		this.otpCode = otpCode;
		this.generatedAt = LocalDateTime.now();
		this.expiryTime = this.generatedAt.plusMinutes(expiryTimeInMinutes); // Set expiry 5 minutes later
		this.used = false;
	}

	public boolean isExpired() {
		LocalDateTime currentTime = LocalDateTime.now();
		return expiryTime != null && (expiryTime.isBefore(currentTime) || this.used);
	}
}
