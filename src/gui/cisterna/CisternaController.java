package gui.cisterna;

public class CisternaController {

	public CisternaView vista;
	public CisternaFacade modelo;
	
	public CisternaController(CisternaFacade modelo, CisternaView vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setController(this);
		this.vista.lanzar();
		
	}
	
	public void setPotenciaBombeo(int porcentaje) {
		this.modelo.setPotenciaBombeo(porcentaje);
	}
	
}
