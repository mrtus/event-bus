package be.mrtus.eventbus.simple;

import be.mrtus.eventbus.Event;
import be.mrtus.eventbus.Listener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * An object that maps a {@link Listener} to an {@link Event}
 */
public interface Registry {

	public Set<Class<? extends Listener<? extends Event>>> get(Class<? extends Event> eventClass);

	/**
	 * @Deprecated
	 * @param eventClass
	 * @param listener
	 */
	default public void register(
			Class<? extends Event> eventClass,
			Class<? extends Listener<? extends Event>> listener) {
		this.register(eventClass, Arrays.asList(listener));
	}

	public void register(
			Class<? extends Event> eventClass,
			Collection<Class<? extends Listener<? extends Event>>> listeners
	);
}
