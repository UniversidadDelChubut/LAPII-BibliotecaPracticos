package gui.cisterna;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CisternaFacade {
	
	private Cisterna cisterna;
	private int flujoHorarioBombaEntrada;     // En metros cúbicos / hora
	private int demandaMediaSalida;           // En metros cúbicos / hora
	private double potenciaBombeo = 0;        // 0 a 100
	private boolean salidaCerrada = false;
	
	public CisternaFacade(int capacidadMaxima, int flujoHorarioBombaEntrada, int demandaMediaSalida) {
		this.cisterna = new Cisterna(capacidadMaxima);
		this.cisterna.cargar(this.cisterna.getCapacidad() / 2);
		this.flujoHorarioBombaEntrada = flujoHorarioBombaEntrada;
		this.demandaMediaSalida = demandaMediaSalida;
		new BombaCarga().start();
		new ConsumoFluido().start();
		new Informer().start();
		
		
	}
	
	public void setPotenciaBombeo (double porcentaje) {
		if (porcentaje < 0  || porcentaje > 100)
			return;
		this.potenciaBombeo =  porcentaje;
	}
	
	public void cerrarSalida() {
		this.salidaCerrada = true;
	}
	
	public void abrirSalida() {
		this.salidaCerrada = false;
	}
	
	
	private int unMinuto() {
		return 83;  // 1 h = 5 s => 1m = 0.083 s
	}
	
	private class BombaCarga extends Thread {
		
		@Override
		public void run() {
			for (;;){
				try {
					Thread.sleep(unMinuto());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cisterna.cargar(flujoHorarioBombaEntrada * potenciaBombeo / 100 / 60  );
			}
		}
	}
	
	
	private class ConsumoFluido extends Thread {
		
		@Override
		public void run() {
			for (;;){
				try {
					Thread.sleep(unMinuto());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!salidaCerrada) {
					cisterna.sacar(CisternaFacade.this.demandaMediaSalida / 60);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new CisternaFacade(500, 200, 100);
	}
	
	private class Informer extends Thread {
		@Override
		public void run() {
			for (;;){
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//				System.out.println(cisterna.getNivelActual());
				CisternaListener.InformeCisterna informe = new CisternaListener.InformeCisterna();
				
				informe.setBombaEncendida (CisternaFacade.this.potenciaBombeo != 0);
				informe.setCapacidadTotal (CisternaFacade.this.cisterna.getCapacidad());
				informe.setContenido ( CisternaFacade.this.cisterna.getNivelActual());
				informe.setFechaInforme (new Date());
				informe.setFlujoEntrada ( CisternaFacade.this.flujoHorarioBombaEntrada * CisternaFacade.this.potenciaBombeo / 100);
				informe.setFlujoSalida ( CisternaFacade.this.demandaMediaSalida);
				informe.setSalidaAbierta ( ! CisternaFacade.this.salidaCerrada);
			
				for (CisternaListener listener: CisternaFacade.this.listeners) {
					listener.change(informe);
				}
			}
		}
	}
	
	private List <CisternaListener> listeners = new LinkedList<CisternaListener>();
	
	public void addCisternaListener(CisternaListener cisternaListener) {
		if (!this.listeners.contains(cisternaListener))
			this.listeners.add(cisternaListener);
	}
	
	
}
