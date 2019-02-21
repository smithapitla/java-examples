package multithreading.model;

public class LongWrapper {
	
	private Object key = new Object(); // To avoid race condition
	private long l;
	
	public LongWrapper(long l) {
		this.l = l;
	}
	
	public long getValue(){
		return l;
	}
	
	public void incrementValue(){
		synchronized(key) { // To avoid race condition
			l = l+1;
		}
	}

}
