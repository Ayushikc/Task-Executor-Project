package edu.utdallas.taskExecutor;

import edu.utdallas.blockingFIFO.BlockingFIFO;

public class TaskRunner implements Runnable{
	private Task task;
	private BlockingFIFO fIFOQueue;
	public TaskRunner(BlockingFIFO fIFO) {
		this.fIFOQueue = fIFO;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		while (true) {
			try {
				task = fIFOQueue.dequeueTask();
			synchronized (task) {
				//task = fIFOQueue.dequeueTask();
				task.execute();
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
	}
	
}
