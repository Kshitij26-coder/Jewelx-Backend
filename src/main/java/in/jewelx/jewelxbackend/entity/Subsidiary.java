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

@Entity
public class Subsidiary {
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "subsidiary_id")
	    private int subsidiaryId;

	   @ManyToOne
	    @JoinColumn(name = "brand_id",nullable=false)
	    private Brand brand;
	   
	    @Column(name = "shop_act_number", length = 18, nullable = false)
	    private String shopActNumber;

	    @Column(name = "subsidiary_name", length = 255, nullable = false)
	    private String subsidiaryName;

	    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
	    private String address;

	    @Column(name = "city", length = 20, nullable = false)
	    private String city;

	    @Column(name = "gstin", length = 15, nullable = false)
	    private String gstin;

	    @Column(name = "cash_balance", precision = 15, nullable = false)
	    private double cashBalance;

	    @Column(name = "logo_url", length = 150, nullable = false)
	    private String logoUrl;

	    @Column(name = "form_header", columnDefinition = "TEXT", nullable = false)
	    private String formHeader;

	    @Column(name = "form_footer", columnDefinition = "TEXT", nullable = false)
	    private String formFooter;
	    
	    @CreationTimestamp
	    @Column(name = "created_on", nullable = false, updatable = false)
	    private LocalDateTime createdOn;

	    @UpdateTimestamp
	    @Column(name = "updated_on", nullable = false,updatable = false)
	    private LocalDateTime updatedOn;
	    
	    @ManyToOne
	    @JoinColumn(name = "created_by", nullable = false, updatable = false)
	    private User createdBy;

	    @ManyToOne
	    @JoinColumn(name = "updated_by", nullable = false,updatable = false)
	    private User updatedBy;
	    
	  
	    
	    
}
