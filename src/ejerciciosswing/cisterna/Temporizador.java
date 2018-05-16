package ejerciciosswing.cisterna;

/**
 * Clase que sirve para marcar el tiempo al simular situaciones. Permite 
 * cambiar la cadencia de la simulación.      
 * 
 * @author sadellatorre
 *
 */
public class Temporizador {
	
	private static final Temporizador temporizador = new Temporizador();
	
	private Reloj reloj = new Reloj(); 
	private int duracionHora = 5;
	

	public static final Temporizador getTemporizador() {
		return Temporizador.temporizador;
	}
	
	private Temporizador() {
		reloj.start();
	}
	
	/**
	 * Detiene el hilo durente un minuto en tiempos de simulación.  
	 */
	public void dormirUnMinuto() {
		try {
			Thread.sleep(unMinuto());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve la cantidad de milisegundos que dura un minuto simulado.
	 * 
	 * @return La cantidad de milisegundos que dura un minuto simulado
	 */
	private int unMinuto() {
		return (duracionHora * 1000) / 60;  // 1 h = 5 s => 1m = 0.083 s
	}
	
	/**
	 * Devuelve la cantidad de segundos que se tarda para la sumulación de una hora.
	 * 
	 * @return La cantidad de segundos que se tarda para la sumulación de una hora.
	 */
	public int getDuracionHora() {
		return duracionHora;
	}
	
	/**
	 * Establece cantidad de segundos que se tarda para la sumulación de una hora.
	 * 
	 * @param duracionHora La cantidad de segundos que se definen para la duración de una hora en esta simulación 
	 */
	public void setDuracionHora(int duracionHora) {
		if (duracionHora < 1 || duracionHora > 25)
			return;
		this.duracionHora = duracionHora;
	}
	
	/**
	 * Devuelve la cantidad de días que lleva la sumulación 
	 * 
	 * @return Cero o un número entero positivo.
	 */
	public int getDias() {
		return reloj.minutos / (60 * 24);
	}
	
	/**
	 * Devueleve la cantidad de horas simuladas dentro del día actual.  
	 * 
	 * @return Un valor entre 0 y 24.
	 */
	public int getHoras() {
		return (reloj.minutos % (60 * 24)) / 60;
	}
	
	/**
	 * Devueleve la cantidad de minutos simulados dentro de la hora actual.
	 * 
	 * @return Un valor entre 0 y 59.
	 */
	public int getMinutos() {
		return (reloj.minutos % (60 * 24)) % 60;
	}
	
	/**
	 * Devualve la hora del día en la simulación actual.
	 * 
	 * @return
	 */
	public String getHoraString() {
		return String.format("%2d:%2d", this.getHoras(), this.getMinutos());
	}
	
	private class Reloj extends Thread {
		
		private int minutos; 
		
		@Override
		public void run() {
			for (;;) {
				dormirUnMinuto();
				minutos++;
				System.out.println(Temporizador.this.getHoraString());
			}
		}
		
		
	}
	
}
