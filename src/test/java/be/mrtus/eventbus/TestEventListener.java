package be.mrtus.eventbus;

public final class TestEventListener implements MethodNameBasedListener {

	private Event event;

	public Event getLastEvent() {
		return this.event;
	}

	public void handleTestEvent(TestEvent event) {
		this.event = event;
	}
}
