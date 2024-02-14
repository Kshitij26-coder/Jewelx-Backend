package in.jewelx.jewelxbackend.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<String> handleDataIntegrityViolationException(SQLIntegrityConstraintViolationException ex) {
        String errorMessage = "Error: Foreign key constraint violation. Please check your input data.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    
    @ExceptionHandler({NullObjectException.class})
    public ResponseEntity<String> handleObjectNotFoundException(NullObjectException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
	  @ExceptionHandler({EmailNotFoundException.class})
	    public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	  
	  @ExceptionHandler({IdNotFoundException.class})
	    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
    

}
