package in.jewelx.jewelxbackend.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.user.SetPasswordDto;
import in.jewelx.jewelxbackend.dto.user.UpdateUserDto;
import in.jewelx.jewelxbackend.dto.user.UserDto;
import in.jewelx.jewelxbackend.dto.user.UserResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.OtpEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.EmailNotFoundException;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.UserMapper;
import in.jewelx.jewelxbackend.mapper.ViewBrandUser;
import in.jewelx.jewelxbackend.repository.OtpRepository;
import in.jewelx.jewelxbackend.repository.UserRepository;
import in.jewelx.jewelxbackend.service.IUserService;
import jakarta.mail.MessagingException;

@Service
public class UserService implements IUserService, UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BrandService brandService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Autowired
	private OtpRepository otpRepo;

	@Override
	public String createUser(UserDto userDto) {
		ViewBrandUser viewBrandUser = UserMapper.mapToBrandUser(userDto);
		BrandEntity createdBrand = brandService.createBrand(viewBrandUser.getBrand());

		if (viewBrandUser.getUser() != null) {
			viewBrandUser.getUser().setBrand(createdBrand);

			if (viewBrandUser.getUser().getAssignedBy().getUserName() == null) {
				viewBrandUser.getUser().setAssignedBy(null);
			}

			if (viewBrandUser.getUser().getSubsidiary().getSubsidiaryId() == null) {
				viewBrandUser.getUser().setSubsidiary(null);
			}

			viewBrandUser.getUser().setPassword(passwordEncoder.encode(viewBrandUser.getUser().getPassword()));

			userRepo.save(viewBrandUser.getUser());
			return "Succesfully saved user data";
		}
		throw new NullObjectException("User Entity does not contains any data");
	}

	@Override
	public String sendOtp(String email) throws MessagingException {

		UserEntity userEntity = userRepo.findByEmail(email);
		// checks if the user is valid already registered user
		if (userEntity == null) {
			throw new EmailNotFoundException("email:" + email + " not found");
		}

		String otp = emailService.sendEmail(email);

		// if user is valid and email is sent successfully create an entry in otp table
		OtpEntity otpEntity = new OtpEntity(email, otp);

		otpRepo.save(otpEntity);

		return "OTP sent successfully";

	}

	// verify OTP
	@Override
	public boolean verifyOtp(String email, String otpByUser) {

		OtpEntity otpEntity;
		// check the latest record with matching email
		Optional<OtpEntity> opt = otpRepo.findLatestByEmail(email);

		// check that there is an entry corresponding to the email
		if (opt.isEmpty()) {
			throw new EmailNotFoundException("email:" + email + " not found");
		}

		otpEntity = opt.get();
		if (!otpEntity.isExpired() && otpEntity.getOtpCode().equals(otpByUser)) {
			otpEntity.setUsed(true);
			otpRepo.save(otpEntity);
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username);
		System.out.println(user);
		if (user == null) {
			// replace this with custom exception
			throw new EmailNotFoundException("email:" + username + " not found");
		}
		return user;
	}

	// reset the password
	public String setPassword(SetPasswordDto setPasswordDto) {
		UserEntity user = (UserEntity) loadUserByUsername(setPasswordDto.getEmail());
		user.setPassword(passwordEncoder.encode(setPasswordDto.getPassword()));
		userRepo.save(user);

		return "password updated successfully";
	}

	public UserEntity updateUser(UUID userId, UpdateUserDto updatedUserData) {
		// Find the user by userId
		UserEntity existingUser = userRepo.findById(userId)
				.orElseThrow(() -> new NullObjectException("User not found with userId: " + userId));

		existingUser.setUserName(updatedUserData.getUsername());
		existingUser.setMobileNumber(updatedUserData.getMobilenumber());

		// Save the updated user entity
		return userRepo.save(existingUser);

	}

	// returns a specific user by Id
	public UserEntity getUserById(UUID id) {
		UserEntity user = userRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id + " not found"));
		return user;
	}

	public Page<UserResponseDto> getUsersByRole(String role, int pageSize, int pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

		Page<UserEntity> usersPage;
		if (role == null || role.isEmpty()) {
			usersPage = userRepo.findAll(pageRequest);
		} else {
			usersPage = userRepo.findByUserRole(role, pageRequest);
		}

		return usersPage.map(UserMapper::UserToUserResponseDto);
	}

}
