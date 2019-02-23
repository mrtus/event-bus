package be.mrtus.eventbus;

public class OtherEventListener implements ReactingListener {

	private Event event;

	public Event getLastEvent() {
		return this.event;
	}

	public void handleTestEvent(TestEvent event) {
		this.event = event;
	}

	public boolean hasReactedToEvents() {
		return this.getLastEvent() != null;
	}
}
