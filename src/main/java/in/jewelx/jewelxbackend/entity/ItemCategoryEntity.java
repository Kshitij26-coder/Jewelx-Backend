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
@Table(name = "item_categories")
public class ItemCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Short categoryId;

    @Column(name = "category_name", length = 20, nullable = false)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "category_metal", nullable = false)
    private MetalEntity categoryMetal;

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

    public ItemCategoryEntity() {
        // Default constructor
    }

}
