package in.jewelx.jewelxbackend.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<String> handleDataIntegrityViolationException(SQLIntegrityConstraintViolationException ex) {
		String errorMessage = "Error: Foreign key constraint violation. Please check your input data.";
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String errorMessage = "Duplicate key or Key not found";
		System.out.println(ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	}

	@ExceptionHandler({ NullObjectException.class })
	public ResponseEntity<String> handleObjectNotFoundException(NullObjectException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler({ EmailNotFoundException.class })
	public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler({ IdNotFoundException.class })
	public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	// to handle bas credentials error
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> exceptionHandler() {
		String errorMessage = "Credentials Invalid !!";
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
	}
	@ExceptionHandler(HuidExistedException.class)
	public ResponseEntity<String> handleHuidException(HuidExistedException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	@ExceptionHandler
	public ResponseEntity<String> handleEmailAlreadyPresentException(EmailAlreadyPresentException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	
}
