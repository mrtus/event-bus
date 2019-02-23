package be.mrtus.eventbus;

/**
 * An object that notifies a {@link Listener} for an {@link Event}
 */
public interface EventBus {

	/**
	 * Publishes an {@link Event}
	 *
	 * @param <T>   an implementation of an {@link Event}
	 * @param event the {@link Event} that needs to be published
	 */
	public <T extends Event> void publish(T event);
}
