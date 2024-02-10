package in.jewelx.jewelxbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.OtpRequestDto;
import in.jewelx.jewelxbackend.dto.UserDto;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.security.JwtHelper;
import in.jewelx.jewelxbackend.security.model.JwtRequest;
import in.jewelx.jewelxbackend.security.model.JwtResponse;
import in.jewelx.jewelxbackend.service.impl.UserService;

@RestController
@RequestMapping("/user")
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

	
	//login endpoint
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUserName())
				.email(userDetails.getEmail()).userId(userDetails.getUserId()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	//helper method for authentication
	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	
	//to handle bas crendentials error
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		try {
		return ResponseEntity.ok(userService.createUser(userDto));
		}catch(Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in creating user: " + err.getMessage());
		}
	}

	
	//send otp to specified email
	@PostMapping("/send-otp/{email}")
	public ResponseEntity<?> sendOtp(@PathVariable String email) {
		try {
			System.out.println(email);
			return ResponseEntity.ok(userService.sendOtp(email));
		} catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in sending email: " + err.getMessage());
		}
	}
	
	
	//verify otp
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpRequestDto request) {
	    try {
            boolean isOtpVerified = userService.verifyOtp(request.getEmail(), request.getOtp());
            if (isOtpVerified) {
                return ResponseEntity.ok("OTP verified successfully"); // Return success message
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Expired OTP"); // Return error message for invalid OTP
            }
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err.getMessage()); // Return error message for unexpected errors
        }	
	}

	@RequestMapping("/test")
	public String test() {
		this.logger.warn("This is working message");
		return "Testing message";
	}


}
