package rocketseat.com.passin.domain.event.exceptions;

public class EventNotFoundExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventNotFoundExceptions(String message) {
		super(message);
	}
}
