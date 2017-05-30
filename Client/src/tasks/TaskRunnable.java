package tasks;

/**
 * 
 * TaskRunnable Class implements Runnable interface
 * contains a Task data member and enables run() function
 *
 */
public class TaskRunnable implements Runnable {
	
	private Task task;

	/**
	 * TaskRunnable C'tor sets this.task to the given task
	 * @param task (Task)
	 */
	public TaskRunnable(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doTask();
	}

}
