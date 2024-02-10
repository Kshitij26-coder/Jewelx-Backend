package in.jewelx.jewelxbackend.service.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.UserDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.OtpEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
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
		throw new NullPointerException("User Entity does not contains any data");
	}

	
	
	public String sendOtp(String email) throws MessagingException {

		UserEntity userEntity = userRepo.findByEmail(email);
		// checks if the user is valid already registered user
		if (userEntity == null) {
			throw new RuntimeException("email not found");
		}

		String otp = emailService.sendEmail(email);

		// if user is valid and email is sent successfully create an entry in otp table
		OtpEntity otpEntity = new OtpEntity(email, otp);

		otpRepo.save(otpEntity);

		return "OTP sent successfully";

	}

	
	
	
	public boolean verifyOtp(String email, String otpByUser) {

		OtpEntity otpEntity;
		// check the latest record with matching email
		Optional<OtpEntity> opt = otpRepo.findLatestByEmail(email);

		// check that there is an entry corresponding to the email
		if (opt.isEmpty()) {
			throw new RuntimeException("email not found");
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
			throw new RuntimeException("id not found");
		}
		return user;
	}
}
