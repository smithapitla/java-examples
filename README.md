# java-examples

# Starting a thread
Runnable r = () -> {
  while(!Thread.currentThread().isInterrupted()){
    System.out.println("Hello World!");
  }
}
Thread t = new Thread(r);
t.start();

# Stopping a thread
t.interrup();
