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
/*
 * This entity stores information about any credit or debit in terms of metal of a customer.
 * */
@Entity
@Table(name = "weight_details")
public class WeightDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detail_id", length = 36)
    private UUID detailId;

	 @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private	CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "metal_id", nullable = false)
    private MetalEntity metal;

    @Column(name = "metal_weight", precision = 6, nullable = false)
    private Double metalWeight;

    @ManyToOne
    @JoinColumn(name = "uom_code", nullable = false)
    private UnitOfMeasurementEntity uom;

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
    public WeightDetailEntity() {
        // Default constructor
    	
    }
}
