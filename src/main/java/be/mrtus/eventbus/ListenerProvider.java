package be.mrtus.eventbus;

/**
 * An object that provides an instances of {@link Listener}
 */
public interface ListenerProvider {

	/**
	 * Provides an instance of the given {@link Listener} class
	 *
	 * @param <T>           an implementation of {@link Listener}
	 * @param listenerClass the {@link Listener} class that needs to have an instance provided for
	 *
	 * @return an instance of the given {@link Listener} class
	 */
	public <T extends Listener> T provide(Class<T> listenerClass);
}
