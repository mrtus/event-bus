package be.mrtus.eventbus.simple;

import be.mrtus.eventbus.Event;
import be.mrtus.eventbus.Listener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListenerRegistryTest {

	private ListenerRegistry registry;

	@BeforeEach
	public void before() {
		this.registry = new ListenerRegistry();
	}

	@Test
	public void itShouldRegisterListeners() {
		Set<Class<? extends Listener<? extends Event>>> expected = new HashSet();
		expected.add(TestEventListener.class);

		this.registry.register(TestEvent.class, Arrays.asList(TestEventListener.class));

		Set<Class<? extends Listener>> actual = this.registry.get(TestEvent.class);

		assertEquals(expected, actual);
	}
}
