package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;
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
/*
 * This entity store the data of metal inventory 
 * */
import jakarta.persistence.Table;

@Entity
@Table(name = "metal_stocks")
public class MetalStockEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @ManyToOne
    @JoinColumn(name = "metal_id", nullable = false)
    private MetalEntity metal;

    @Column(name = "opening_weight", precision = 8, scale = 2, nullable = false)
    private BigDecimal openingWeight;

    @Column(name = "closing_weight", precision = 8, scale = 2, nullable = false)
    private BigDecimal closingWeight;

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

    public MetalStockEntity() {
        // Default constructor
    }
}
