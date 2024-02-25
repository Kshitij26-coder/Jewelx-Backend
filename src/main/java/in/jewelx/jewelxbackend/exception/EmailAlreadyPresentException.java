package in.jewelx.jewelxbackend.exception;

public class EmailAlreadyPresentException extends RuntimeException{
	
	public EmailAlreadyPresentException(String message) {
		super(message);
	}
}
