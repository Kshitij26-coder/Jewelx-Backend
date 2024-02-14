package in.jewelx.jewelxbackend.dto.customer;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
	private String name;

	private Integer pincode;

	private String address;

	private Long aadharId;

	private String panId;

	private Long mobileNumber;

	private Date dateOfBirth;

	private Date anniversaryDate;

	private Double openingBalance;

	private UUID customerID;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;
}
