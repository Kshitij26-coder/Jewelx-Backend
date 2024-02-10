package in.jewelx.jewelxbackend.service.impl;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import in.jewelx.jewelxbackend.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String defaultSender;

	
	@Override
	public String sendEmail(String to) throws MessagingException  {

		String subject ="One Time Password(OTP) to reset profile password";
		// validation for input arguments
		if (!isValidEmail(to) || !StringUtils.hasText(subject)) {
			throw new IllegalArgumentException("Invalid input parameters");
		}
		 MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		  MimeMessageHelper mailMessage =
			        new MimeMessageHelper(mimeMessage, true, "UTF-8");

		Context context = new Context();
		String otp  =generateOtp(6);
		context.setVariable("otp", otp);
		// Process the Thymleaf template to generate the email body
		String emailBody = templateEngine.process("email-template", context);

		mailMessage.setFrom(defaultSender);
		mailMessage.setTo(to);
		mailMessage.setText(emailBody,true);
		mailMessage.setSubject(subject);

		// sending...
		mailSender.send(mimeMessage);
		
		return otp;
	}

	
	
	// email validation
	private boolean isValidEmail(String email) {
		if (StringUtils.hasText(email)) {
			return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		}
		return false;
	}

	
	// generate random OTP
	public static String generateOtp(int length) {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		for (int i = 0; i < length; i++) {
			otp.append(random.nextInt(10)); // Generates random digits between 0 and 9
		}

		return otp.toString();
	}

}
