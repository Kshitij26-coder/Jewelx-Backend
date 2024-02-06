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

@Entity
@Table(name = "unit_of_measurement")
public class UnitOfMeasurementEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "uom_id")
	    private Long uomId;

	    @Column(name = "uom_code", nullable = false, length = 5,unique = true)
	    private String uomCode;

	    @Column(name = "uom_name", nullable = false, length = 50,unique=true)
	    private String uomName;

	    @Column(name = "description", columnDefinition = "TEXT")
	    private String description;
	    
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

	    // Constructors, getters, and setters

	    public UnitOfMeasurementEntity() {
	        // Default constructor
	    }
}
