package be.mrtus.eventbus;

import be.mrtus.eventbus.TestEvent;
import be.mrtus.eventbus.Event;
import be.mrtus.eventbus.MethodNameBasedListener;

public final class TestEventListener implements MethodNameBasedListener {

	private Event event;

	public Event getLastEvent() {
		return this.event;
	}

	public void handleTestEvent(TestEvent event) {
		this.event = event;
	}
}
