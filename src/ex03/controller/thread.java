package ex03.controller;

import java.util.concurrent.Semaphore;

public class thread extends Thread {

	String marca;
	static String marcas[] = new String[14];
	
	int pos;
	static int cont = 0;
	
	double tmpVolta;
	static double vetTmp[] = { 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0 };
	
	private static Semaphore s1, s2;

	public thread(String marca, int pos, Semaphore s1, Semaphore s2) {
		this.marca = marca; this.pos = pos; this.s1 = s1; this.s2 = s2;
	}

	public void run() {
		for (int vlt = 0; vlt < 3; vlt++) {
			for (int id = 0; id < 2; id++) {
				try {
					s1.acquire();
					volta(id, vlt, pos);
				} catch (InterruptedException e) {
					System.out.println(e);
				} finally {
					s1.release();
				}
			}
		}
		if (cont >= 14) {  
			ordenaGrid();
		}
	}

	private void volta(int id, int vlt, int pos) {
		tmpVolta = (double) (Math.random() * 10) + 60;
		// delay
		System.out.println("Carro: " + marca + " " + (id + 1) + " entrou na pista");
		try {
			sleep((int) tmpVolta * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// SEMAPHORE
		try {
			s2.acquire();
			if (tmpVolta < vetTmp[pos * 2 + id]) {
				vetTmp[pos * 2 + id] = tmpVolta;
				marcas[pos * 2 + id] = (String) marca + " " + (id + 1);
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("Carro: " + marca + " " + (id + 1) + " | saiu da pista | tempo: " + String.format("%.2f", tmpVolta));
			if (vlt >= 2) { cont++; }
			s2.release();
		}
	}
	
	private void ordenaGrid() {
		double auxd; String auxs;
		for (int i = 0; i < 14; i++) {
			for (int j = i + 1; j < 14; j++) {
				if (vetTmp[i] > vetTmp[j]) {
					// Numeros
					auxd = vetTmp[i];
					vetTmp[i] = vetTmp[j];
					vetTmp[j] = auxd;
					// Texto
					auxs = marcas[i];
					marcas[i] = marcas[j];
					marcas[j] = auxs;
				}
			}
		}
		
		for (int j = 0; j < 14; j++) {
			System.out.println(marcas[j] + " | " + String.format("%.2f", vetTmp[j]));
		}
	}
}