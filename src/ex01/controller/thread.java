package ex01.controller;

import java.util.concurrent.Semaphore;

public class thread extends Thread {
	
	int vel = 0, disTotal = 200, disAtual = 0;
	private static Semaphore s1;
	
	public thread(Semaphore s1) {
		this.s1 = s1;
	}
	
	public void andando() {
		vel = (int) (Math.random() * 2) + 4;
		if (disAtual < disTotal) { disAtual += vel; } else { disTotal = 200; }
	}
	
	public void sitAtual() { 
		System.out.println("pessoa: " + getId() + " | Dist. percorrida: " + disAtual); 
	}
	
	public void delayPasso() {
		int delay = (int) (Math.random() * 5) + 1;
		try {
			sleep(delay * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("pessoa: " + getId() + " | descansou por: " + delay);
	}
	
	public void run() {
		while (disAtual < disTotal) {
			andando();
			delayPasso();
			sitAtual();
		}
		try {
			s1.acquire();
			chegouNaPorta();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			s1.release();
		}
	}

	private void chegouNaPorta() {
		System.out.println("pessoa: " + getId() + " entrou na porta");
		delayAbriPorta();
		System.out.println("pessoa: " + getId() + " passou pela porta");
	}

	private void delayAbriPorta() {
		int delay = (int) (Math.random() * 2) + 1;
		try {
			sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
