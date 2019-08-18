/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.mrtus.eventbus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnnotationBasedListenerTest {

	private TestAnnotationBasedListener listener;

	@BeforeEach
	public void before() {
		this.listener = new TestAnnotationBasedListener();
	}

	@Test
	public void itShouldReactToEventWithAnnotation() {
		Event event = new TestEvent();

		this.listener.handle(event);

		Event actual = this.listener.getLastEvent();

		Assertions.assertEquals(event, actual);
	}

	@Test
	public void itShouldThrowWhenNoMethodWithReactToNotationWasFound() {
		Event event = new OtherEvent();

		Assertions.assertThrows(
				ListenerMethodNotFound.class,
				() -> this.listener.handle(event)
		);
	}

	@Test
	public void itShouldThrowWhenDuplicateListenerMethodsAreFound() {
		Event event = new EventWithDuplicateListenerMethods();

		Assertions.assertThrows(
				DuplicateListenerMethodsFound.class,
				() -> this.listener.handle(event)
		);
	}

	private class TestAnnotationBasedListener extends AnnotationBasedListener {

		private Event lastEvent;

		@ReactsTo(TestEvent.class)
		protected void handle(TestEvent event) {
			this.lastEvent = event;
		}

		@ReactsTo(EventWithDuplicateListenerMethods.class)
		protected void handlesEventWithDuplicateListenerMethods(EventWithDuplicateListenerMethods event) {
			this.lastEvent = event;
		}

		@ReactsTo(EventWithDuplicateListenerMethods.class)
		protected void handlesEventWithDuplicateListenerMethodsDuplicate(EventWithDuplicateListenerMethods event) {
			this.lastEvent = event;
		}

		private Event getLastEvent() {
			return this.lastEvent;
		}
	}

	private class EventWithDuplicateListenerMethods implements Event {
	}
}
