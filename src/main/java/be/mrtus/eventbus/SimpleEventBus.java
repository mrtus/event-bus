package be.mrtus.eventbus;

import java.util.Set;

/**
 * A {@link EventBus} implementation that publishes an {@link Event} synchronous
 *
 * @see EventBus
 */
public class SimpleEventBus implements EventBus {

	private final ListenerProvider provider;
	private final Registry registry;

	public SimpleEventBus(
			Registry registry,
			ListenerProvider provider
	) {
		this.registry = registry;
		this.provider = provider;
	}

	@Override
	public <T extends Event> void publish(T event) {
		Class<? extends Event> eventClass = event.getClass();

		Set<Class<? extends Listener<? extends Event>>> listeners = this.registry.get(eventClass);

		listeners.stream()
				.forEach(listenerClass -> {
					Listener listener = this.provider.provide(listenerClass);

					listener.handle(event);
				});
	}
}
