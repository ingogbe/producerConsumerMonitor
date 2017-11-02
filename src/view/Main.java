package view;

public class Main {

	public static void main(String[] x){
		Buffer b = new Buffer();
		Printer p = new Printer();
		
		Producer p1 = new Producer(p, b, 1, 2, true);
		Producer p2 = new Producer(p, b, 2, 3, true);
		Consumer c1 = new Consumer(p, b, 1, 5, false);
		
		p1.start(); 
		p2.start();
		c1.start();
	}
	
}
