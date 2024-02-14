package in.jewelx.jewelxbackend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
	//username is email
	private String username;
	private String mobilenumber;
}
