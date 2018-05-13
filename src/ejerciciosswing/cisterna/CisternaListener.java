package ejerciciosswing.cisterna;

import java.util.Date;

public interface CisternaListener {
	
	public void change (InformeCisterna informeCisterna);
	
	public static class InformeCisterna {
		
		private Date fechaInforme;
		private double capacidadTotal;
		private double contenido;
		private boolean bombaEncendida;
		private boolean salidaAbierta;
		private double flujoEntrada;
		private double flujoSalida;
		
		public Date getFechaInforme() {
			return fechaInforme;
		}
		
		public double getCapacidadTotal() {
			return capacidadTotal;
		}
		
		public double getContenido() {
			return contenido;
		}
		
		public boolean isBombaEncendida() {
			return bombaEncendida;
		}
		
		public boolean isSalidaAbierta() {
			return salidaAbierta;
		}
		
		public double getFlujoEntrada() {
			return flujoEntrada;
		}

		public double getFlujoSalida() {
			return flujoSalida;
		}

		final void setFechaInforme(Date fechaInforme) {
			this.fechaInforme = fechaInforme;
		}

		final void setCapacidadTotal(double capacidadTotal) {
			this.capacidadTotal = capacidadTotal;
		}

		final void setContenido(double contenido) {
			this.contenido = contenido;
		}

		final void setBombaEncendida(boolean bombaEncendida) {
			this.bombaEncendida = bombaEncendida;
		}

		final void setSalidaAbierta(boolean salidaAbierta) {
			this.salidaAbierta = salidaAbierta;
		}

		final void setFlujoEntrada(double flujoEntrada) {
			this.flujoEntrada = flujoEntrada;
		}

		final void setFlujoSalida(double flujoSalida) {
			this.flujoSalida = flujoSalida;
		}
		
		
		
		
		
		
	}
	
}
