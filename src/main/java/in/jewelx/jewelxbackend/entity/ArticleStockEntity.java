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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * This entity store data in regards to the stock of articles(ring, necklace, bracelet etc ) 
 * */

@Getter
@Setter
@ToString
@Entity
@Table(name = "article_stock")
public class ArticleStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;
    
    @Column(name="article_name", nullable = false)
    private String articleName;

    @Column(name = "barcode", length = 40)
    private String barcode;

    @Column(name = "gross_weight", precision = 8,  nullable = false)
    private Double grossWeight;

    @Column(name = "net_weight", precision = 8,  nullable = false)
    private Double netWeight;

    @Column(name = "purity", precision = 3, nullable = false)
    private Double purity;

    @Column(name = "stone_weight", precision = 8, nullable = false)
    private Double stoneWeight;

    @Column(name = "huid", length = 6, unique = true)
    private String huid;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "subsidiary_id", nullable = false)
    private SubsidiaryEntity subsidiary;

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
    
    @ManyToOne
    @JoinColumn(name="brand_id", nullable = false)
    private BrandEntity brand;

    // Constructors, getters, and setters

    public ArticleStockEntity() {
        // Default constructor
    }
}
