package in.jewelx.jewelxbackend.dto.customer;

import java.sql.Date;
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

	private Long userID;

	private Long subsidiaryId;

	private Long brandId;

}
