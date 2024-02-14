package in.jewelx.jewelxbackend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequestDto {
	private String email;
	private String otp;
}
