package pl.misern.taskm.event;

import pl.misern.taskm.event.command.Command;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EventHandler {

	protected static final Queue<Command> queue = new LinkedList<>();

	public static void pushEvent(Command event) {
		queue.add(event);
	}

	public void registerQueueExecutor() {
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
			if (!queue.isEmpty()) {
				Command command = queue.poll();

				if (command != null) {
					command.execute();
				}
			}
		}, 0, 50, TimeUnit.MILLISECONDS);
	}
}
