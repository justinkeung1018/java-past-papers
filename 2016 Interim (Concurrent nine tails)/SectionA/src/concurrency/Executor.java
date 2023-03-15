package concurrency;

import java.util.LinkedList;
import java.util.List;

import concurrency.schedulers.Scheduler;

public class Executor {

	private ConcurrentProgram program;
	private Scheduler scheduler;

	public Executor(ConcurrentProgram program, Scheduler scheduler) {
		this.program = program;
		this.scheduler = scheduler;
	}

	/**
	 * Executes program with respect to scheduler
	 *
	 * @return the final state and history of execution
	 */
	public String execute() {
		List<Integer> history = new LinkedList<Integer>();
		boolean deadlockOccurred = false;

		while (!program.isTerminated() && !deadlockOccurred) {
			try {
				int threadId = scheduler.chooseThread(program);
				program.step(threadId);
				history.add(threadId);
			} catch (DeadlockException e) {
				deadlockOccurred = true;
			}
		}

		StringBuilder result = new StringBuilder();
		result.append("Final state: " + program + "\n");
		result.append("History: " + history + "\n");
		result.append("Termination status: "
				+ (deadlockOccurred ? "deadlock" : "graceful") + "\n");
		return result.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof Executor thatExecutor)) {
			return false;
		}
		return program.toString().equals(thatExecutor.program.toString());
	}

	@Override
	public int hashCode() {
		return program.toString().hashCode();
	}
}
