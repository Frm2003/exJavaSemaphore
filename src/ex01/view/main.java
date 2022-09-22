package ex01.view;

import java.util.concurrent.Semaphore;
import ex01.controller.thread;

public class main {
	
	public static Semaphore s1;

	public static void main(String[] args) {
		
		s1 = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Thread t = new thread(s1);
			t.start();
		}
		
	}
}
