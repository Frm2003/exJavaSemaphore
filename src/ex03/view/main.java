package ex03.view;

import ex03.controller.thread;
import java.util.concurrent.Semaphore;

public class main {
	
	private static Semaphore s1, s2;
	public static String nome[] = {"Rerrari", "Chevrolet", "Porche", "Missan", "Neep", "Citrozen", "Subaru"};
	
	public static void main(String[] args) {
		s1 = new Semaphore(5); 
		s2 = new Semaphore(1);
		
		for (int i = 0; i < 7; i++) {
			Thread t = new thread(nome[i], i, s1, s2);
			t.start();
		}
	}

}
