package be.mrtus.eventbus;

/**
 * An object that handles an {@link Event}
 *
 * @param <T> the type of event that needs to be handled
 */
public interface Listener<T extends Event> {

	/**
	 * Handles the {@link Event}
	 *
	 * @param event the {@link Event} which needs to be handled
	 */
	public void handle(T event);
}
