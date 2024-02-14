package in.jewelx.jewelxbackend.service;

import in.jewelx.jewelxbackend.dto.user.SetPasswordDto;
import in.jewelx.jewelxbackend.dto.user.UserDto;
import jakarta.mail.MessagingException;

public interface IUserService {

	String createUser(UserDto userDto);

	public boolean verifyOtp(String email, String otpByUser);

	String sendOtp(String email) throws MessagingException;
	
	public String setPassword(SetPasswordDto setPasswordDto);
}
