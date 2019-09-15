package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.blockingFIFO.*;
import edu.utdallas.taskExecutor.TaskRunner;

import edu.utdallas.taskExecutor.TaskExecutor;

@SuppressWarnings("unused")
public class TaskExecutorImpl implements TaskExecutor
{
	private BlockingFIFO queue = new BlockingFIFO(100);
	private TaskRunner threadPool[];
	
	public TaskExecutorImpl(int i) {
		this.threadPool = new TaskRunner[i];
		for(int count = 0; count < i ; count++) {
			String taskName = "TaskThread" + count;
			TaskRunner newThread = new TaskRunner(this.queue);
			threadPool[count] = newThread;
			Thread th = new Thread(newThread);
			th.setName(taskName);
			th.start();
			Thread.yield();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTask(Task task)
	{
		this.queue.enqueueTask(task);
		// TODO Complete the implementation
	}

}


