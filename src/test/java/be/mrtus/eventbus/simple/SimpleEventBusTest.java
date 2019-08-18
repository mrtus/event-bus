package be.mrtus.eventbus.simple;

import be.mrtus.eventbus.OtherEventListener;
import be.mrtus.eventbus.TestEventListener;
import be.mrtus.eventbus.OtherEvent;
import be.mrtus.eventbus.TestEvent;
import be.mrtus.eventbus.Event;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SimpleEventBusTest {

	private SimpleEventBus eventBus;
	private ListenerProvider provider;

	@BeforeEach
	public void before() {
		ListenerRegistry registry = new ListenerRegistry();
		registry.register(TestEvent.class, Arrays.asList(TestEventListener.class));
		registry.register(OtherEvent.class, Arrays.asList(OtherEventListener.class));

		this.provider = mock(ListenerProvider.class);

		this.eventBus = new SimpleEventBus(
				registry,
				this.provider
		);
	}

	@Test
	public void itShouldNotPublishEventToOtherListenersThatAreNotRegisteredForEvent() {
		OtherEvent otherEvent = new OtherEvent();
		OtherEventListener otherListener = new OtherEventListener();

		when(this.provider.provide(OtherEventListener.class))
				.thenReturn(otherListener);

		assertThrows(
				ListenerMethodNotFound.class,
				() -> {
					this.eventBus.publish(otherEvent);
				}
		);
	}

	@Test
	public void itShouldPublishEvent() {
		Event event = new TestEvent();
		TestEventListener listener = new TestEventListener();

		when(this.provider.provide(TestEventListener.class))
				.thenReturn(listener);

		this.eventBus.publish(event);

		assertEquals(event, listener.getLastEvent());
	}
}
