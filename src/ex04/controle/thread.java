package ex04.controle;

import java.util.concurrent.Semaphore;

public class thread extends Thread {
	
	int cod, saldo, val;
	private static Semaphore travaSaque, travaDeposito;
	
	public thread(int cod, int saldo, int val, Semaphore s1, Semaphore s2) {
		this.cod = cod; this.saldo = saldo; this.val = val; travaSaque = s1; travaDeposito = s2;
	}
	
	@Override
	public void run() {
		try {
			travaSaque.acquire();
			if (cod % 2 == 0) { saque(); } else { deposito(); }
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("ID conta: " + cod + " | Saldo atual: " + saldo);
			travaSaque.release();
		}
	}
	
	private void saque() {
		int delay = (int) (Math.random() * 10) + 1;
		try {
			sleep(delay * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ID conta: " + cod + " | Sacou: " + val);
		saldo -= val;
	}
	
	private void deposito() {
		int delay = (int) (Math.random() * 10) + 1;
		try {
			sleep(delay * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ID conta: " + cod + " | Depositou: " + val);
		saldo += val;
	}
}
