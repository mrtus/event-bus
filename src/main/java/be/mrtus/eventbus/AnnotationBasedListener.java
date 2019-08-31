package be.mrtus.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AnnotationBasedListener implements Listener<Event> {

	private final Map<Class<? extends Event>, Method> methods = new HashMap<>();

	@Override
	public void handle(Event event) {
		Method method = this.methods.computeIfAbsent(
				event.getClass(),
				eventClass -> this.findMethodForEvent(eventClass)
		);

		try {
			method.invoke(this, event);
		} catch(IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException exception) {
			throw new ListenerMethodNotFound(exception);
		}
	}

	private Method findMethodForEvent(Class<? extends Event> eventClass) {
		List<Method> methods = Arrays.asList(this.getClass().getDeclaredMethods());

		Stream<Method> methodsWithMatchingAnnotation = methods.stream()
				.filter(m -> m.getAnnotation(ReactsTo.class) != null)
				.filter(m -> {
					ReactsTo annotation = m.getAnnotation(ReactsTo.class);

					return eventClass.equals(annotation.value());
				});

		Method method = methodsWithMatchingAnnotation.collect(
				Collectors.collectingAndThen(
						Collectors.toList(),
						list -> {
							if(list.size() > 1) {
								throw new DuplicateListenerMethodsFound();
							}

							return list.stream()
									.findFirst()
									.orElseThrow(() -> new ListenerMethodNotFound());
						}
				)
		);

		return method;
	}
}
