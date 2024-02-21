package in.jewelx.jewelxbackend.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.user.ActivateUserDto;
import in.jewelx.jewelxbackend.dto.user.OtpRequestDto;
import in.jewelx.jewelxbackend.dto.user.SetPasswordDto;
import in.jewelx.jewelxbackend.dto.user.UserRequestDto;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.security.JwtHelper;
import in.jewelx.jewelxbackend.security.model.JwtRequest;
import in.jewelx.jewelxbackend.security.model.JwtResponse;
import in.jewelx.jewelxbackend.service.impl.UserService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/user")
//@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	// login end-point
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(request.getEmail());
		userService.login(userDetails);
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUserName())
				.email(userDetails.getEmail()).userId(userDetails.getUserId()).role(userDetails.getUserRole())
				.subsidiaryId(userDetails.getSubsidiary() == null ? null : userDetails.getSubsidiary().getIdxId())
				.brandId(userDetails.getBrand().getBrandId()).idxId(userDetails.getIdxId()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// helper method for authentication
	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto) {
		return ResponseEntity.ok(userService.createUser(userDto));
	}

	// send OTP to specified email
	@PostMapping("/send-otp/{email}")
	public ResponseEntity<?> sendOtp(@PathVariable String email) throws MessagingException {
		return ResponseEntity.ok(userService.sendOtp(email));

	}

	// verify OTP
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpRequestDto request) {
		boolean isOtpVerified = userService.verifyOtp(request.getEmail(), request.getOtp());
		if (isOtpVerified) {
			return ResponseEntity.ok("OTP verified successfully"); // Return success message
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or Expired OTP");
		}
	}

	// reset password
	@PutMapping("/reset-password")
	public ResponseEntity<?> setPassword(@RequestBody SetPasswordDto setPasswordDto) {
		return ResponseEntity.ok(userService.setPassword(setPasswordDto));
	}

	// get specific user using id
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") UUID userid) {
		return ResponseEntity.ok(userService.getUserById(userid));
	}

	// get specific user using role
	@GetMapping
	public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String role,
			@RequestParam Long brand, @RequestParam(defaultValue = "0") Long subsidiary) {
		System.out.println(role + " " + size + " " + " " + page);
		return ResponseEntity.ok(userService.getUsersByRoleAndBrand(role, size, page, brand, subsidiary));
	}

	@PutMapping("/logout/{id}")
	public ResponseEntity<String> logout(@PathVariable UUID id) {
		userService.logout(id);
		return ResponseEntity.ok("logged successfully");
	}

	@PutMapping("/activate")
	public ResponseEntity<String> setUserActive(@RequestBody ActivateUserDto dto) {
		return ResponseEntity.ok(userService.setUserActive(dto));
	}
}
