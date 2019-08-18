package be.mrtus.eventbus;

public class ListenerMethodNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ListenerMethodNotFound(Throwable exception) {
		super(exception);
	}

	public ListenerMethodNotFound() {
	}
}
