package ejerciciosswing.cisterna;

import ejerciciosswing.cisterna.CisternaListener.InformeCisterna;

public interface CisternaView {
	
	public void setController(CisternaController controller);
	
	public void change(InformeCisterna informeCisterna);
	
	public void lanzar();
	
}
