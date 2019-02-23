package be.mrtus.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public interface ReactingListener extends Listener<Event> {

	@Override
	public default void handle(Event event) {
		List<Method> methods = Arrays.asList(this.getClass().getMethods());

		Method method = methods.stream()
				.filter(m -> m.getName().equals("handle" + event.getClass().getSimpleName()))
				.filter(m -> Arrays.asList(m.getParameterTypes()).contains(event.getClass()))
				.findFirst()
				.orElseThrow(() -> ListenerMethodNotFound.forEvent(event));

		try {
			method.invoke(this, event);
		} catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			throw new RuntimeException(exception);
		}
	}
}
