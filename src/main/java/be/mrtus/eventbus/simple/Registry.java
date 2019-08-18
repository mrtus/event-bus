package be.mrtus.eventbus.simple;

import be.mrtus.eventbus.Event;
import be.mrtus.eventbus.Listener;
import java.util.Set;

/**
 * An object that maps a {@link Listener} to an {@link Event}
 */
public interface Registry {

	public Set<Class<? extends Listener<? extends Event>>> get(Class<? extends Event> eventClass);
}
