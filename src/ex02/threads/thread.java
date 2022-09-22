package ex02.threads;

import java.util.concurrent.Semaphore;

public class thread extends Thread {
	String dir;
	
	private static Semaphore s1;
	
	public thread(String dir, Semaphore s1) {
		this.dir = dir;
		this.s1 = s1;
	}
	
	@Override
	public void run() {
		try {
			s1.acquire();
			chegaNoFarol();
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			s1.release();
		}
		
	}

	private void chegaNoFarol() {
		System.out.println("Carro: " + getId() + " chegou no cruzamento");
		delay();
		System.out.println("Carro: " + getId() + " terminou de cruzar");
	}

	private void delay() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Carro: " + getId() + " passou o cruzamento no sentido: " + dir);
	}
}
