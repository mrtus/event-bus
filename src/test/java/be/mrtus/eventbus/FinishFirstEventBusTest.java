package be.mrtus.eventbus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class FinishFirstEventBusTest {

	private EventBus delegate;
	private FinishFirstEventBus eventBus;

	@BeforeEach
	public void before() {
		this.delegate = Mockito.mock(EventBus.class);

		this.eventBus = new FinishFirstEventBus(
				this.delegate
		);
	}

	@Test
	public void testSomeMethod() {
		Event startEvent = new TestEvent();
		Event sideEffectEventA = new TestEvent();
		Event sideEffectEventB = new TestEvent();
		Event sideEffectEventC = new TestEvent();

		Mockito.doAnswer(answer -> {
			this.eventBus.publish(sideEffectEventA);
			this.eventBus.publish(sideEffectEventC);

			return null;
		})
				.when(this.delegate)
				.publish(startEvent);

		Mockito.doAnswer(answer -> {
			this.eventBus.publish(sideEffectEventB);

			return null;
		})
				.when(this.delegate)
				.publish(sideEffectEventA);

		this.eventBus.publish(startEvent);

		InOrder inOrder = Mockito.inOrder(this.delegate);

		inOrder.verify(this.delegate)
				.publish(startEvent);

		inOrder.verify(this.delegate)
				.publish(sideEffectEventA);

		inOrder.verify(this.delegate)
				.publish(sideEffectEventB);

		inOrder.verify(this.delegate)
				.publish(sideEffectEventC);
	}
}
