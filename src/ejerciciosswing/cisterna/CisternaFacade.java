package ejerciciosswing.cisterna;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ejerciciosswing.cisterna.CisternaListener.InformeCisterna;

public class CisternaFacade {
	
	private Cisterna cisterna;
	private Bomba bomba;
	private ConsumoRed consumoRed;
	
	public CisternaFacade(int capacidadMaxima, int flujoHorarioBombaEntrada, int demandaMediaSalida) {
		this.cisterna = new Cisterna(capacidadMaxima);
		this.cisterna.cargar(this.cisterna.getCapacidad() / 2);
		this.bomba = new Bomba(flujoHorarioBombaEntrada);
		this.bomba.setCisterna(cisterna);
		this.consumoRed =  new ConsumoRed(demandaMediaSalida);
		this.consumoRed.setCisterna(cisterna);
		
		new Informer().start();
	}
	
	public void setPotenciaBombeo (double porcentaje) {
		this.bomba.setPorcentajePotenciaBombeo(porcentaje);
	}
	
	public void cerrarSalida() {
		this.consumoRed.cerrar();
	}
	
	public void abrirSalida() {
		this.consumoRed.abrir();
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
			
				InformeCisterna informe = CisternaFacade.this.getInformeCisterna();
				for (CisternaListener listener: CisternaFacade.this.listeners) {
					listener.change(informe);
				}
			}
		}
	}
	
	InformeCisterna getInformeCisterna() {
		InformeCisterna informe = new CisternaListener.InformeCisterna();
		
		informe.setBombaEncendida (bomba.isEncendida());
		informe.setCapacidadTotal (CisternaFacade.this.cisterna.getCapacidad());
		informe.setContenido ( CisternaFacade.this.cisterna.getNivelActual());
		informe.setFechaInforme (new Date());
		informe.setFlujoEntrada ( bomba.getFlujoHorarioMaximo() * bomba.getPorcentajePotenciaBombeo() / 100);
		informe.setFlujoSalida ( consumoRed.getDemandaMedia());
		informe.setSalidaAbierta ( ! consumoRed.isCerrado() );
		
		return informe;
	}
	
	private List <CisternaListener> listeners = new LinkedList<CisternaListener>();
	
	public void addCisternaListener(CisternaListener cisternaListener) {
		if (!this.listeners.contains(cisternaListener))
			this.listeners.add(cisternaListener);
	}
	
	
}
