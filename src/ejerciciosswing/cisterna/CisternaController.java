package ejerciciosswing.cisterna;

import ejerciciosswing.cisterna.CisternaListener.InformeCisterna;

public class CisternaController {

	public CisternaView vista;
	public CisternaFacade modelo;
	
	public CisternaController(CisternaFacade modelo, CisternaView vista) {
		this.modelo = modelo;
		
		this.vista = vista;
		this.modelo.addCisternaListener(vista);
		this.vista.setController(this);
		this.vista.lanzar();
		
	}
	
	public void setPotenciaBombeo(int porcentaje) {
		this.modelo.setPotenciaBombeo(porcentaje);
	}
	
	public void abrirSalida() {
		this.modelo.abrirSalida();
	}
	
	public void cerrarSalida() {
		this.modelo.cerrarSalida();
	}
	
	public InformeCisterna getInformeCisterna() {
		return this.modelo.getInformeCisterna();
	}
	
}
