package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.user.SetPasswordDto;
import in.jewelx.jewelxbackend.dto.user.UpdateUserDto;
import in.jewelx.jewelxbackend.dto.user.UserRequestDto;
import in.jewelx.jewelxbackend.dto.user.UserResponseDto;
import in.jewelx.jewelxbackend.entity.UserEntity;
import jakarta.mail.MessagingException;

public interface IUserService {

	String createUser(UserRequestDto userDto);

	public boolean verifyOtp(String email, String otpByUser);

	String sendOtp(String email) throws MessagingException;

	public String setPassword(SetPasswordDto setPasswordDto);

	UserEntity updateUser(UUID userId, UpdateUserDto updatedUserData);

	UserEntity getUserById(UUID id);

	Page<UserResponseDto> getUsersByRole(String role, int pageSize, int pageNumber);

}
