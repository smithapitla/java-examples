package multithreading;

public class FirstThread {

	public static void main(String[] args) {
		
		Runnable runnable = () -> {
			System.out.println("Running thread::"+Thread.currentThread().getName());
		};
		
		Thread t = new Thread(runnable);
		t.setName("FirstThread");
		t.start(); //Output:: Running thread::FirstThread
		t.run(); // Output:: Running thread::main
		
		/* Output if both start and run are called
		 Running thread::main
		 Running thread::FirstThread
		*/

	}

}
