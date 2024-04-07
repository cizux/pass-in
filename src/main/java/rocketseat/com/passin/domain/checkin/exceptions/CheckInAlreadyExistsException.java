package rocketseat.com.passin.domain.checkin.exceptions;

public class CheckInAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CheckInAlreadyExistsException(String message) {
		super(message);
	}
}
