package in.jewelx.jewelxbackend.dto.user;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
	// Brand properties
	private String brandName;
	private String brandDescription;
	private String brandImageUrl;
	private Long brandId;

	// User Properties
	private String userName;
	private String email;
	private String mobileNumber;
	private String password;
	private String userRole;
	private long subsidiaryId;
	private UUID assignedBy;

}
