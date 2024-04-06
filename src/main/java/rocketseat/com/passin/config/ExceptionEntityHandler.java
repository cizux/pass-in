package rocketseat.com.passin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rocketseat.com.passin.domain.event.exceptions.EventNotFoundExceptions;

@ControllerAdvice
public class ExceptionEntityHandler {

	@ExceptionHandler(EventNotFoundExceptions.class)
	public ResponseEntity handleEventnotFound(EventNotFoundExceptions exception) {
		return ResponseEntity.notFound().build();
	}
	
}
