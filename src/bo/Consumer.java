package bo;

import services.Buffer;
import services.Printer;

public class Consumer extends Thread {
	private Buffer buffer;
	private int number;
	private Printer printer;
	private int consumption;
	private boolean waitForNextConsumption;
   
	public Consumer(Printer printer, Buffer buffer, int number, int consumption, boolean waitForNextConsumption) {
		this.buffer = buffer;
		this.number = number;
		this.printer = printer;
		this.consumption = consumption;
		this.waitForNextConsumption = waitForNextConsumption;
	}

	public void run() {
		Content content = null;
		
		for (int i = 0; i < this.consumption; i++) {
			long milisecondsBegin = System.currentTimeMillis();
			content = this.buffer.get();
			long milisecondsEnd = System.currentTimeMillis();
			
			synchronized(this.printer) {
				this.printer.print(
					"Consumer #" + this.number + " got: " + content.getValue() + " from Producer #" + content.getProducer().getNumber() + ". " + 
					"Wait time to get: " + (milisecondsEnd - milisecondsBegin)
				);
			}
			
			if(waitForNextConsumption){
				try {
					long timeToConsumeAgain = (int)(Math.random() * 100);
					this.printer.print("INFO> Consumer #" + this.number + " consume again in " + timeToConsumeAgain + " miliseconds");
					sleep(timeToConsumeAgain);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}