package in.jewelx.jewelxbackend.entity;

import java.sql.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "pincode", nullable = false)
    private Integer pincode;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "aaddhar_id", precision = 12, unique=true)
    private Long aadharId;

    @Column(name = "pan_id", length = 10,unique =true)
    private String panId;

    @Column(name = "mobile_number", precision = 12, nullable = false)
    private Long mobileNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "anivarsary_date")
    private Date anniversaryDate;

    @Column(name = "opening_balance", precision = 15, nullable = false)
    private Double openingBalance;

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

    public CustomerEntity() {
        // Default constructor
    }
}
