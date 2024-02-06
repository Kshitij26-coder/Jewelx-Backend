package in.jewelx.jewelxbackend.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "brand_id",nullable=false)
    private Brand brand;

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
    private Subsidiary subsidiary;
    
	@Column(name = "assigned_by", length = 36, nullable = true)
    private String assignedBy;
    
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
    @Column(name = "updated_on", nullable = false,updatable = true)
    private LocalDateTime updatedOn;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false,updatable = false)
    private User updatedBy;

   




}

