package services;

public class Printer {
	public synchronized void print(String s){
		System.out.println(s);
	}
}
