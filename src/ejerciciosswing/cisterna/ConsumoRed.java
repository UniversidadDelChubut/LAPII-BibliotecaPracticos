package ejerciciosswing.cisterna;

import java.util.Random;

public class ConsumoRed {

	private boolean cerrado;
	private double demandaMedia;
	private Cisterna cisterna;
	private double demandaReal;
	
	public ConsumoRed(double demandaMedia) {
		this.demandaMedia = demandaMedia;
		new ConsumoFluido().start();
	}

	public void setCisterna(Cisterna cisterna) {
		this.cisterna = cisterna;
		
	}
	
	public void cerrar() {
		this.cerrado = true;
	}
	
	public void abrir() {
		this.cerrado = false;
	}
	
	public double getDemandaMedia() {
		return demandaMedia;
	}
	
	public boolean isCerrado() {
		return cerrado;
	}
	
	public double getDemandaReal() {
		return demandaReal;
	}

	private class ConsumoFluido extends Thread {
		
		Random rnd = new Random();

		@Override
		public void run() {
			for (;;) {
				Temporizador.getTemporizador().dormirUnMinuto();
				if (cerrado)
					continue;
				if (cisterna == null)
					continue;
				demandaReal = ((double)demandaMedia / 60) * (1 + (( rnd.nextBoolean() ? 1:-1)  * rnd.nextDouble() /10 )) ;
				
				cisterna.sacar( demandaReal );
			}
		}
	}
}
