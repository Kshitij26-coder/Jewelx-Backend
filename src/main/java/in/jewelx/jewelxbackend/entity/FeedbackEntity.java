package in.jewelx.jewelxbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "feedback")
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "mobile_number", precision = 12, nullable = false)
	private String mobileNumber;

	@Column(nullable = false, length = 1000)
	private String description;

	// Constructors, getters, and setters
	
	public FeedbackEntity() {
    }

    public FeedbackEntity(String name, String email, String mobileNumber, String description) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.description = description;
    }
}
