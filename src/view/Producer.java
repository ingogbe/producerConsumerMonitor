package view;

public class Producer extends Thread {
	private Buffer buffer;
	private int number;
	private Printer printer;
	private int production;
	private boolean waitForNextProduction;
	
	public Producer(Printer printer, Buffer buffer, int number, int production, boolean waitForNextProduction) {
		this.buffer = buffer;
		this.number = number;
		this.printer = printer;
		this.production = production;
		this.waitForNextProduction = waitForNextProduction;
	} 
	
	public void run() {
		for (int i = 0; i < this.production; i++) {
			long milisecondsBegin = System.currentTimeMillis();
			Content c = new Content(i+1, this);
			this.buffer.put(c);
			long milisecondsEnd = System.currentTimeMillis();
			
			synchronized(this.printer) {
				this.printer.print(
					"Producer #" + this.number + " put: " + (c.getValue()) + ". " + 
					"Wait time to put: " + (milisecondsEnd - milisecondsBegin) + ". "
				);
			}
			
			if(waitForNextProduction){
				try {
					long timeToProduceAgain = (int)(Math.random() * 100);
					this.printer.print("Producer #" + this.number + " produce again in " + timeToProduceAgain + " miliseconds");
					sleep(timeToProduceAgain);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	public int getNumber() {
		return number;
	}
	
	
} 