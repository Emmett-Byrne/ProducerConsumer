public class BurgerJoint {
	static int buf;
	static int p = 0;
	static int c = 0;
	static int n = 10;
	
		
	
	public static class Producer implements Runnable{
		Thread thread;
		private String threadName;
		Producer(String name) {
			threadName = name;
		}
		int a = 0;
		public void run(){
			System.out.println("Producer Starting");
			while(p < n){
				while(p != c){
					System.out.println("Short order cook spinning");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("Thread has been interrupted");
					}
				}
				a = (int)Math.random() * 100;
				System.out.println("Making a Burger: " + p);
				buf = a;
				p = p + 1;
			}
			System.out.println("Finished Making Burgers");
		}
		public void start() {
			System.out.println("Thread started");
			if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
			}
		}
	}
	public static class Consumer implements Runnable{
		Thread thread;
		private String threadName;
		Consumer(String name) {
			threadName = name;
		}
		int b = 0;
		public void run(){
			System.out.println("Consumer Starting");
			while(c < n){
				while(p <= c){
					System.out.println("Starving waiting on a burger!");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("Thread has been interrupted");
					}
				}
				System.out.println("Eating Burger: " + c);
				b = buf;
				c = c + 1;
				
			}
			System.out.println("Finished Eating Burgers");
		}
		public void start() {
			System.out.println("Thread started");
			if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
			}
		}
	}
	public static void main(String[] args) {		
		Producer producer = new Producer("producer");
		Consumer consumer = new Consumer("consumer");
		producer.start();
		consumer.start();
	}
}
