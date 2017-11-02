package view;

public class Buffer {
	private Content content;
	private boolean available;
	   
	public Buffer(){
		this.content = null;
		this.available = false;
	}
	
	public synchronized Content get() {
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = false;
		notifyAll();
		return this.content;
	}
	
	public synchronized void put(Content content) {
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		this.content = content;
		available = true;
		notifyAll();
	}
	
}
