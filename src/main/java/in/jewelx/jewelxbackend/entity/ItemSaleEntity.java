package in.jewelx.jewelxbackend.entity;

import java.math.BigDecimal;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "item_sales")
public class ItemSaleEntity {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    @Column(name = "item_id")
	    private UUID itemId;

	   @ManyToOne
	    @JoinColumn(name = "sale_id", nullable = false)
	    private SaleEntity sale;

	    @ManyToMany
	    @JoinColumn(name = "tag_id", nullable = false)
	    private ArticleStockEntity tagId;

	    @Column(name = "item_amount", nullable = false)
	    private Long itemAmount;

	    @Column(name = "metal_rate", precision = 8, scale = 2, nullable = false)
	    private BigDecimal metalRate;

	    @Column(name = "total_amount", nullable = false)
	    private Long totalAmount;

	    @Column(name = "sgst", precision = 8, scale = 2, nullable = false)
	    private BigDecimal sgst;

	    @Column(name = "cgst", precision = 8, scale = 2, nullable = false)
	    private BigDecimal cgst;

	    @Column(name = "payable_amount", precision = 8, scale = 2, nullable = false)
	    private BigDecimal payableAmount;

	    @Column(name = "making_charges", precision = 8, scale = 2, nullable = false)
	    private BigDecimal makingCharges;

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

	    public ItemSaleEntity() {
	        // Default constructor
	    }
}
