package ejerciciosswing.cisterna;

import ejerciciosswing.cisterna.CisternaListener.InformeCisterna;

public interface CisternaView {
	
	/**
	 * A través de este método se da a conocer el controlador a la vista.  
	 * 
	 * @param controller
	 */
	public void setController(CisternaController controller);
	
	/**
	 * A través de este método el controlador informa a la vista de los cambios en el modelo.
	 *  
	 * @param informeCisterna
	 */
	public void change(InformeCisterna informeCisterna);
	
	/**
	 * Da incio a la vista de usuario. Es invocada por el controlador una única vez por proceso. 
	 */
	public void lanzar();
	
}
