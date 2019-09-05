package be.mrtus.eventbus.simple;

import be.mrtus.eventbus.Event;
import be.mrtus.eventbus.Listener;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @see Registry
 */
public class ListenerRegistry implements Registry {

	private final Map<Class<? extends Event>, Set<Class<? extends Listener>>> listeners = new HashMap<>();

	@Override
	public Set<Class<? extends Listener>> get(Class<? extends Event> eventClass) {
		return this.listeners.getOrDefault(eventClass, new HashSet<>());
	}

	public void register(
			Class<? extends Event> eventClass,
			Collection<Class<? extends Listener<? extends Event>>> listeners
	) {
		this.listeners.putIfAbsent(eventClass, new HashSet<>());

		this.listeners.get(eventClass)
				.addAll(listeners);
	}
}
