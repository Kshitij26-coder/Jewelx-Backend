package in.jewelx.jewelxbackend.service;

import jakarta.mail.MessagingException;

public interface IEmailService {
	String sendEmail(String to) throws MessagingException;
}
