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

# Producer/Consumer

package capone.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileUtils {

	public static Collection listFiles(File dir, boolean recursive, Collection files) {
		
		File[] filesAndDirectories = dir.listFiles();
		for(File file: filesAndDirectories){
			if(!file.isDirectory())
				files.add(file);
			else
				listFiles(file, recursive, files);				
		}
		return files;
	}

}

package capone.io;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(".java");
	}

}

package capone.io;

import java.io.File;
import java.io.FilenameFilter;

public class JavaFileNameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return false;
	}

}

package capone.io;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyMain {

	public static void main(String[] args) {
		File dir = new File("/Users/kui987/CapitalOne/Repos/Git/java-examples");
		printFilesLenghtUsingCustomImplementation(dir);
		printFilesLengthUsingConcreteImplementation(dir);
		printFilesLengthUsingAnonymousClass(dir);
		printFilesLengthUsingLambdas(dir);
	}
	
	private static void printFilesLenghtUsingCustomImplementation(File dir) {
		try {
	        boolean recursive = true;
	        Collection files = FileUtils.listFiles(dir, recursive, new ArrayList<Files>());
	        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
	            File file = (File) iterator.next();
	            if (file.getName().endsWith(".java"))
	                System.out.println(file.getAbsolutePath());
	        }
	        System.out.println("Number of Java files when current directory is scanned recursively is::"+files.size());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }	
	}

	public static void printFilesLengthUsingConcreteImplementation(File dir){
		JavaFileFilter fileFilter = new JavaFileFilter();
		File[] javaFiles = dir.listFiles(fileFilter);
		if(javaFiles != null)
			System.out.println("Number of Java files when current directory is scanned non-recursively is::"+javaFiles.length);
	}
	
	public static void printFilesLengthUsingAnonymousClass(File dir){
		FileFilter fileFilter = new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".js");
			};
		};
		File[] jsFiles = dir.listFiles(fileFilter);
		if(jsFiles != null)
			System.out.println("Number of JavaScript files is::"+jsFiles.length);
	}
	
	public static void printFilesLengthUsingLambdas(File dir){
		FileFilter fileFilter = (File pathname) -> pathname.getName().endsWith(".css");

		File[] cssFiles = dir.listFiles(fileFilter);
		if(cssFiles != null)
			System.out.println("Number of CSS files is::"+cssFiles.length);
	}

}

package capone.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaMain {

	public static void main(String[] args) throws InterruptedException {
		runnableLambda();
		comparatorLambda();
	}
	
	public static void runnableAnonymous() throws InterruptedException{
		Runnable runnable = new Runnable(){
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println("Hello from current thread"+Thread.currentThread().getName());
				}				
			}			
		};		
		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();
	}
	
	public static void runnableLambda() throws InterruptedException{
		Runnable runnable = () -> {
				for (int i = 0; i < 3; i++) {
					System.out.println("Hello from current thread"+Thread.currentThread().getName());
				}
		};		
		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();
	}
	
	public static void comparatorAnonymous(){
		
		Comparator<String> comparator = new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
			
		};
		
		List<String> list = Arrays.asList("**","******", "****");
		Collections.sort(list,comparator);
		
		for (String s:list) {
			System.out.println(s);
		}
		
	}
	
public static void comparatorLambda(){
		
		Comparator<String> comparator = (String o1, String o2) -> Integer.compare(o1.length(), o2.length());
		
		List<String> list = Arrays.asList("**","******", "****");
		Collections.sort(list,comparator);
		
		for (String s:list) {
			System.out.println(s);
		}
		
	}

}

package capone.lang;

import capone.util.PrintUtil;

public class StringExamples {

	public static void main(String[] args) {
		
		indexOfExample1();
		indexOfExample2();
		
		splitExample();
		equalityExmaple();
		valueExamples();
		charAtExamples();
		compareToExamples();
		codePointExamples();
	}

	private static void indexOfExample1() {
		String s;
		int a;
		s = "Foolish boy.";
		a = s.indexOf("fool");
		
		System.out.println(a);
		
		String text = "Practice, practice, practice!";
		int index = text.indexOf("prac");
		System.out.println(index);
		
	}
	
	private static void indexOfExample2() {
		String s = "one two one";
		int i = s.indexOf("one");
		int j = s.lastIndexOf("one");
		
		System.out.println(i);
		System.out.println(j);
		
	}
	
	private static void splitExample() {
		String text2 = "Hurray!!#! It's Friday! finally...";
		String[] words = text2.split("!+");
		PrintUtil.printElementsInAnArray(words);
		
	}
	
	private static void equalityExmaple() {
		String a = "hello";
		String b = "hello";
		boolean x = (a == b); // True because of string interning,  reference can be same
		
		System.out.println(x);
		
		String a1 = new String("hello");
		String b1 = new String("hello");
		boolean x1 = (a1 == b1); // False because of new 
		boolean x2 = a1.equals(b1); // True because values are equal
		
		System.out.println(x1);
		System.out.println(x2);
		
	}
	
	private static void valueExamples() {
		// TODO Auto-generated method stub
		
	}

	private static void charAtExamples() {
		// TODO Auto-generated method stub
		
	}

	private static void compareToExamples() {
		// TODO Auto-generated method stub
		
	}

	private static void codePointExamples() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	      System.out.println("String = " + str);

	      // codepoint at index 1 i.e A
	      int retval = 0;
	      for(int i=0; i< str.length(); i++){
	    	  retval = str.codePointAt(i);
	    	  System.out.println("Character(unicode point) = " + retval);
	      }
	      // prints character before index1 in string
	      
		
	}
}

package capone.lang;

public class SwitchExamples {

	public static void main(String[] args) {
		
		defaultFirstWithMatch();
		defaultLast();
		defaultLastWithMatch();
		defaultFirst();

	}

	private static void defaultLastWithMatch() {
		int i=9; 
		System.out.println("***********************");
		switch (i) { 
		        case 0: 
		        System.out.println("zero "); 
		                break;
		        case 9: 
			        System.out.println("nine "); 
			                break;
		        default: 
			        System.out.println("default "); 
		   }
		System.out.println("***********************");
		
	}

	private static void defaultLast() {
		int i=9; 
		System.out.println("***********************");
		switch (i) { 
		        case 0: 
		        System.out.println("zero "); 
		                break;
		        default: 
			        System.out.println("default "); 
		   }
		System.out.println("***********************");
		
	}

	private static void defaultFirstWithMatch() {
		int i=9; 
		System.out.println("***********************");
		switch (i) { 
		        default: 
		        System.out.println("default "); 
		        case 0: 
		        System.out.println("zero "); 
		                break;
		        case 9: 
			        System.out.println("nine "); 
			                break;
		   }
		System.out.println("***********************");
		
	}

	private static void defaultFirst() {
		System.out.println("***********************");
		int i=9; 
		switch (i) { 
		        default: 
		        System.out.println("default "); 
		        case 0: 
		        System.out.println("zero "); 
		                break;
		        case 1: 
			        System.out.println("one "); 
			                break;
		   }
		System.out.println("***********************");
		
	}
	
	
	

}
package capone.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorUses {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("Smitha");
		list.add("Pitla");
		list.add("Smitha");
		list.add("Suresh");
		list.add("Thouti");
		list.add("Harshika");
		list.add("Thouti");
		list.add(null);

		removeElementsFromList(list);
		demonstrateUsesOfForEachRemaining(list);
		

	}

	private static void demonstrateUsesOfForEachRemaining(List<String> list) {
		for(String  name: list){
			// TODO
		}
		
	}

	private static void removeElementsFromList(List<String> list) {
		
		
		//System.out.println("Size of list before modification::"+list.size());
		
		for(String name : list){
			//System.out.println(name);
			/*if(name != null && name.equals("Pitla"))
				list.remove(name);*/
			// Above code will throw ConcurrentModificationException  so we use iterator ie Iteration and Modification of collections is not allowed without an iterator. 
		}
		
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			String name =  (String) iter.next();
			if(name != null && name.equals("Pitla"))
				iter.remove();
			
		}
		
		//System.out.println("Size of list after modification::"+list.size());
		System.out.println("Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics");
		System.out.println("Methods in iterator are::");
		Class c = Iterator.class;
		Method[] methods = c.getDeclaredMethods();
		for(Method m : methods){
			//System.out.println(m.toGenericString());
			System.out.println(m.getName().toString());
		}
		
		
	}

}
package capone.util;

public class ListIteratorUses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
package capone.util;

import java.util.ArrayList;

public class PrintUtil {
	
	public static void printElementsInAnArrayList(ArrayList<String> list){
		for(String str : list){
			System.out.println(str);
		}
	}
	
	public static void printElementsInAnArray(String[] array){
		for(String str : array){
			System.out.println(str);
		}
	}

}

package kafka;

import java.util.concurrent.Callable;
//Java program to illustrate Callable 
	// to return a random number 
import java.util.Random; 
import java.util.concurrent.Callable; 
import java.util.concurrent.FutureTask; 
	  
public class CallableExample implements Callable {

	  
	    public Object call() throws Exception 
	    { 
	        // Create random number generator 
	        Random generator = new Random(); 
	  
	        Integer randomNumber = generator.nextInt(5); 
	  
	        // To simulate a heavy computation, 
	        // we delay the thread for some random time 
	        Thread.sleep(randomNumber * 1000); 
	  
	        return randomNumber; 
	    } 

}
