package in.jewelx.jewelxbackend.dto.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {


	private UUID userId;
	private String userName;
	private String email;
	private String mobileNumber;
	private String userRole;
	private UserBrandResponseDto brand;
	private UserAssignedByResponseDto assignedBy;
	private UserSubsidiaryResponseDto subsidiary;
	private boolean isActive;
	private boolean isLoggedIn;

}
