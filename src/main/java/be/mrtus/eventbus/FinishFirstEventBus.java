package be.mrtus.eventbus;

import java.util.LinkedList;
import java.util.Queue;

public class FinishFirstEventBus implements EventBus {

	private final EventBus eventBus;
	private boolean processing;
	private final Queue<Event> queue = new LinkedList<>();

	public FinishFirstEventBus(
			EventBus eventBus
	) {
		this.eventBus = eventBus;
	}

	@Override
	public <T extends Event> void publish(T event) {
		if(this.isProcessingAnotherEvent()) {
			this.store(event);

			return;
		}

		this.handle(event);

		this.release();
	}

	public void release() {
		this.processing = false;

		if(this.queue.isEmpty()) {
			return;
		}

		this.publish(this.queue.poll());
	}

	private <T extends Event> void handle(T event) {
		this.processing = true;

		this.eventBus.publish(event);
	}

	private boolean isProcessingAnotherEvent() {
		return this.processing;
	}

	private <T extends Event> void store(T event) {
		this.queue.offer(event);
	}
}
