package multithreading;

public class Deadlock {
	
	private Object key1 = new Object();
	private Object key2 = new Object();
	
	public void firstMethod(){
		synchronized(key1){
			System.out.println(Thread.currentThread().getName()+"::I am in firstMethod");
			secondMethod();
		}
	}
	public void secondMethod(){
		synchronized(key2){
			System.out.println(Thread.currentThread().getName()+"::I am in secondMethod");
			thirdMethod();
		}
	}
	public void thirdMethod(){
		synchronized(key1){
			System.out.println(Thread.currentThread().getName()+"::I am in thirdMethod");
		}
	}

}
