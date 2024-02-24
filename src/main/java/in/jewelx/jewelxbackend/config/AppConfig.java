package in.jewelx.jewelxbackend.config;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloudinary.Cloudinary;

@Configuration
public class AppConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder().
//                username("DURGESH")
//                .password(passwordEncoder().encode("DURGESH")).roles("ADMIN").
//                build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true)
				.setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE).setSkipNullEnabled(true);

		return modelMapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	//Bean created to use cloudianry object and its method
	@Bean
	public Cloudinary getCloudinary() {
		//setting cloudinary server configuration 
		Map config = new HashMap();
		config.put("cloud_name","dqpof2sxy");
		config.put("api_key", "414357913281979");
		config.put("api_secret", "sqHpzeimxE4aLIgQ2EKDUDA1G94");
		config.put("secure", true);
		return new Cloudinary(config);
	}
}
