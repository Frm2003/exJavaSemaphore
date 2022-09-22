package ex02.view;

import ex02.threads.thread;
import java.util.concurrent.Semaphore;

public class main {
	static String dir[] = {"esquerda", "direita", "cima", "baixo"};
	private static Semaphore s1;
	
	public static void main(String[] args) {
		s1 = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Thread t = new thread(dir[i], s1);
			t.start();
		}
	}

}
