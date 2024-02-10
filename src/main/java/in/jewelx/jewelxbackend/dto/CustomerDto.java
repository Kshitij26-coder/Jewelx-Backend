package in.jewelx.jewelxbackend.dto;

import java.sql.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private String name;

	private Integer pincode;

	private String address;

	private Long aadharId;

	private String panId;

	private Long mobileNumber;

	private Date dateOfBirth;

	private Date anniversaryDate;

	private Double openingBalance;

	private UUID userID;

}
