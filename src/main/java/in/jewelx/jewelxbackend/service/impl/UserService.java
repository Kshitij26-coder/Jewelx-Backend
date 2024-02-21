package in.jewelx.jewelxbackend.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.user.ActivateUserDto;
import in.jewelx.jewelxbackend.dto.user.SetPasswordDto;
import in.jewelx.jewelxbackend.dto.user.UpdateUserDto;
import in.jewelx.jewelxbackend.dto.user.UserRequestDto;
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
import in.jewelx.jewelxbackend.utils.RolesEnum;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Transactional
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

	/*
	 * create a user. Brand is created first and then user is created
	 */
	@Override
	public String createUser(UserRequestDto userDto) {
		ViewBrandUser viewBrandUser = UserMapper.mapToBrandUser(userDto);
		if (RolesEnum.valueOf(userDto.getUserRole().toUpperCase()) == RolesEnum.O) {
			BrandEntity createdBrand = brandService.createBrand(viewBrandUser.getBrand());
			// owner will be by default active
			viewBrandUser.getUser().setActive(true);
			viewBrandUser.getUser().setBrand(createdBrand);
		}
		// will prevent in creating nested objects in viewBrandUserObject
		if (viewBrandUser.getUser().getAssignedBy().getIdxId() == null) {
			viewBrandUser.getUser().setAssignedBy(null);
		}

		if (viewBrandUser.getUser().getSubsidiary().getIdxId() == null
				|| viewBrandUser.getUser().getSubsidiary().getIdxId() == 0) {
			viewBrandUser.getUser().setSubsidiary(null);
		}

		if (viewBrandUser.getUser().getBrand().getBrandId() == null
				|| viewBrandUser.getUser().getBrand().getBrandId() == 0) {
			viewBrandUser.getUser().setBrand(null);
		}

		viewBrandUser.getUser().setPassword(passwordEncoder.encode(viewBrandUser.getUser().getPassword()));
		userRepo.save(viewBrandUser.getUser());
		return "Succesfully saved user data";

	}

	/*
	 * Used to change status to logged in
	 */
	public void login(UserEntity user) {
		user.setLoggedIn(true);
		userRepo.save(user);
	}

	/*
	 * Used to change status to logged out
	 */
	public void logout(UUID id) {
		UserEntity user = userRepo.findByUserId(id).orElseThrow(() -> new ObjectNotFoundException(id, ":not found"));
		user.setLoggedIn(false);
		userRepo.save(user);
	}

	public String setUserActive(ActivateUserDto dto) {
		UserEntity user = userRepo.findById(dto.getAssigneeId())
				.orElseThrow(() -> new ObjectNotFoundException(0, "User not found"));
		UserEntity assigner = userRepo.findById(dto.getAssignerId())
				.orElseThrow(() -> new ObjectNotFoundException(0, "User not found"));
		user.setActive(!user.isActive());
		user.setAssignedBy(assigner);
		userRepo.save(user);
		return user.isActive() ? "User Activated" : "User Disabled";
	}

	/*
	 * send OTP to email
	 */
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

	/*
	 * reset password a specific user by Id
	 */
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
		if (user == null) {
			// replace this with custom exception
			throw new EmailNotFoundException("email:" + username + " not found");
		}
		return user;
	}

	/*
	 * reset password a specific user by Id
	 */
	@Override
	public String setPassword(SetPasswordDto setPasswordDto) {
		UserEntity user = (UserEntity) loadUserByUsername(setPasswordDto.getEmail());
		user.setPassword(passwordEncoder.encode(setPasswordDto.getPassword()));
		userRepo.save(user);

		return "password updated successfully";
	}

	/*
	 * updates a specific user by Id
	 */
	@Override
	public UserEntity updateUser(UUID userId, UpdateUserDto updatedUserData) {
		// Find the user by userId
		UserEntity existingUser = userRepo.findByUserId(userId)
				.orElseThrow(() -> new NullObjectException("User not found with userId: " + userId));

		existingUser.setUserName(updatedUserData.getUsername());
		existingUser.setMobileNumber(updatedUserData.getMobilenumber());

		// Save the updated user entity
		return userRepo.save(existingUser);

	}

	/*
	 * returns a specific user by Id
	 */
	@Override
	public UserResponseDto getUserById(UUID id) {
		UserEntity user = userRepo.findByUserId(id).orElseThrow(() -> new IdNotFoundException(id + " not found"));

		return UserMapper.UserToUserResponseDto(user);
	}

	/*
	 * Get all user in paginated format
	 */
	@Override
	public Page<UserResponseDto> getUsersByRoleAndBrand(String role, int pageSize, int pageNumber, Long brandId) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

		Page<UserEntity> usersPage;
		if (role == null || role.isEmpty()) {
			usersPage = userRepo.findAll(pageRequest);
		} else {
			BrandEntity brand = new BrandEntity();
			brand.setBrandId(brandId);
			usersPage = userRepo.findByUserRoleAndBrand(role, brand, pageRequest);
		}
		System.out.println(usersPage);
		return usersPage.map(UserMapper::UserToUserResponseDto);
	}

}
