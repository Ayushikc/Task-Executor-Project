package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockingFIFO {
	private Task fIFOQueue[];
	private int first, last, count, queueMaxSize;
	private Object notFull, notEmpty;
	
	public BlockingFIFO(int size) {
		this.queueMaxSize = size;
		this.fIFOQueue = new Task[this.queueMaxSize];
		this.first = 0;
		this.last = 0;
		this.count = 0;
		this.notFull = new Object();
		this.notEmpty = new Object();
	}
	
	public void enqueueTask (Task newTask) {
		
		if (this.count == this.queueMaxSize) {
			synchronized (notFull) {
				try {
					notFull.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		fIFOQueue[last] = newTask;
		last = (last+1)% this.queueMaxSize;
		this.count++;
		
		synchronized (notEmpty) {
			notEmpty.notify();
		}
	}
	
	public Task dequeueTask() {
		if(this.count==0) {
			synchronized (notEmpty) {
				try {
					notEmpty.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Task dequeueTask = this.fIFOQueue[first];
		this.first = (this.first+1)%this.queueMaxSize;
		this.count--;
		synchronized (notFull) {
			notFull.notify();
		}
		return dequeueTask;
	}
}
