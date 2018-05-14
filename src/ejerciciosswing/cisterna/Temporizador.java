package ejerciciosswing.cisterna;

public class Temporizador {
	
	private static final Temporizador temporizador = new Temporizador();

	public static final Temporizador getTemporizador() {
		return Temporizador.temporizador;
	}
	
	public void dormirUnMinuto() {
		try {
			Thread.sleep(unMinuto());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int unMinuto() {
		return 83;  // 1 h = 5 s => 1m = 0.083 s
	}
	
	
}
