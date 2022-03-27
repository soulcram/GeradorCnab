package br.com.m3Tech.solucoesFromtis.events;

public class Pause implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException ex) {

			System.err.println(ex.getMessage() + "\r\n");

		}
	}

}
