package ejerciciosswing.cisterna;

public class Bomba {
	
	private boolean encendida = false;
	
	private double porcentajePotenciaBombeo = 50;
	
	private double flujoHorarioMaximo;
		
	private Cisterna cisterna;
	
	public Bomba(double flujoHorarioMaximo) {
		super();
		this.flujoHorarioMaximo = flujoHorarioMaximo;
		new HiloBombeo().start();
	}
	
	public void setCisterna(Cisterna cisterna) {
		this.cisterna = cisterna;
	}
	
	public void setPorcentajePotenciaBombeo(double porcentajePotenciaBombeo) {
		if (porcentajePotenciaBombeo < 0 || porcentajePotenciaBombeo > 100)
			return;
		this.porcentajePotenciaBombeo = porcentajePotenciaBombeo;
		this.encendida = this.porcentajePotenciaBombeo > 0; 
	}
	
	public void encender() {
		this.encendida = true;
	}
	
	public void apagar() {
		this.encendida = false;
	}
	
	public Cisterna getCisterna() {
		return cisterna;
	}
	
	public double getFlujoHorarioMaximo() {
		return flujoHorarioMaximo;
	}
	
	public double getPorcentajePotenciaBombeo() {
		return porcentajePotenciaBombeo;
	}
	
	public boolean isEncendida() {
		return encendida;
	}
	
	private class HiloBombeo extends Thread {
		@Override
		public void run() {
			for (;;){
				Temporizador.getTemporizador().dormirUnMinuto();
				if (cisterna == null)
					continue;
				if (! encendida) 
					continue;
				cisterna.cargar(flujoHorarioMaximo * porcentajePotenciaBombeo / 100 / 60  );
			}
		}
	}
	
}
