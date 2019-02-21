package multithreading;

import multithreading.model.LongWrapper;

public class RaceCondition {

	public static void main(String[] args) throws InterruptedException {
		
		LongWrapper longWrapper = new LongWrapper(0L);
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				longWrapper.incrementValue();
			}			
		};
		Thread[] threads = new Thread[1000];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		System.out.println("Before join::"+longWrapper.getValue());
		for (int i = 0; i < threads.length; i++) {
			threads[i].join(); // Waiting for thread to finish running.
		}
		System.out.println("After join::"+longWrapper.getValue());		
	}

}
