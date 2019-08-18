package be.mrtus.eventbus;

public class ListenerMethodNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ListenerMethodNotFound() {
	}

	public ListenerMethodNotFound(Throwable exception) {
		super(exception);
	}

	public ListenerMethodNotFound(String message) {
		super(message);
	}

	public static ListenerMethodNotFound forEvent(Event event) {
		return new ListenerMethodNotFound("Listener method for '" + event.getClass().getSimpleName() + "' not be found");
	}
}
