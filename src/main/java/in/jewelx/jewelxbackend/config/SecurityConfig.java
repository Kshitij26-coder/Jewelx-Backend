package in.jewelx.jewelxbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.jewelx.jewelxbackend.security.JwtAuthenticationEntryPoint;
import in.jewelx.jewelxbackend.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors().and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/user/test").authenticated()
						.requestMatchers("/user").permitAll().requestMatchers("/user/send-otp").permitAll()
						.requestMatchers("/user/*").permitAll().requestMatchers("/brand/*").permitAll()
						.requestMatchers("/feedback").permitAll().requestMatchers("/brand/all").permitAll()
						.requestMatchers("/subsidiary/all/*").permitAll().requestMatchers("/subsidiary/brand/*")
						.permitAll().requestMatchers("/user/send-otp/*").permitAll()
						.requestMatchers("/article-stock/barcode/*").permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//http://localhost:8080/article-stock/barcode/hjg54165
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider doDoaAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		// implement password encoder later
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

}
