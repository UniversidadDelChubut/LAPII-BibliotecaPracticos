package ejerciciosswing.cisterna;

/**
 * Representa una cisterna
 * 
 * @author sadellatorre
 * 
 */
public class Cisterna {

	private double capacidad; // La capacidad de la cisterna medida en metros
								// cúblicos
	private double nivelActual; // La cantidad de fluido contenidos en la
								// cisterna medida en metros cúbicos
	/**
	 * 
	 * @param capacidad
	 *            la capacidad máxima de la cisterna medida en metros cúbicos
	 */
	public Cisterna(double capacidad) {
		this.capacidad = capacidad;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public double getNivelActual() {
		return nivelActual;
	}

	synchronized void cargar(double metros) {
		System.out.println("Carga " + metros);
		
		if (this.nivelActual + metros <= this.capacidad) {
			this.nivelActual += metros;
		} else {
			this.nivelActual = this.capacidad;
		}
	}

	synchronized void sacar(double metros) {
		if (this.nivelActual - metros < 0) {
			this.nivelActual = 0;
		} else {
			this.nivelActual -= metros;
		}
	}

	public boolean isNivelCritico() {
		return this.nivelActual < this.capacidad / 10;
	}

}
