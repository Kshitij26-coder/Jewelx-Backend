package in.jewelx.jewelxbackend.dto.user;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
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
	private BrandShortDetailsDto brand;
	private UserShortDetailsDto assignedBy;
	private SubsidiaryShortDetailsDto subsidiary;
	private boolean isActive;
	private boolean isLoggedIn;

}
