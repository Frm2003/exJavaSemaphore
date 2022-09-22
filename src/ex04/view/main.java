package ex04.view;

import java.util.concurrent.Semaphore;
import ex04.controle.thread;

public class main {
	
	static Semaphore s1, s2;

	public static void main(String[] args) {
		s1 = new Semaphore(1);
		s2 = new Semaphore(1);
		
		for (int i = 0; i < 20; i++) {
			int id = (int) (Math.random() * 50) + 1;
			int saldo = (int) (Math.random() * 1000) + 1001;
			int val = (int) (Math.random() * 400) + 100;
			
			Thread t = new thread(id, saldo, val, s1, s2);
			t.start();
		}
	}
}
