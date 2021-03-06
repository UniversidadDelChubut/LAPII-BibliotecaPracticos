package unidad2.supermercado;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author sdellatorre@udc.edu.ar
 *
 */
public class PreciosYPromociones {
	
	public static String [] getProductosPromoSegundaUnidad50PorCiento() {
		String [] productosConPromo = {"YOGSER", "PILASV"}; 
		return productosConPromo;
	}
	
	private static String [][] getVentaPrueba1() {
		String [][] venta  =
			{
				{"YERP1K", "3"},
				{"YOGSER", "3"},
				{"PANLAC", "1"},
				{"MEDGOR", "6"},
				{"PANCAS", "1"},
				{"PAPABL", "1"},
				{"CEBOLL", "1"},
				{"ZUKINI", "1"},
				{"PILASV", "1"},
			};		
		
		return venta;
		
	}

	
	private static String [][] getVentaPrueba2() {
		String [][] venta  =
			{
				{"YERP1K", "2"},
				{"YOGSER", "2"},
				{"PANLAC", "3"},
				{"ZUKINI", "1.350"},
				{"PILASV", "2"},
			};		
		
		return venta;
		
	}

	private static String [][] getVentaPrueba3() {
		String [][] venta  =
			{
				{"YERP1K", "1"},
				{"YOGSER", "2"},
				{"PANLAC", "5"},
				{"MEDGOR", "12"},
				{"PANCAS", "1"},
				{"CEBOLL", "1.450"},
				{"ZUKINI", "1.350"},
				{"PILASV", "4"},
			};		
		
		return venta;
		
	}

	private static String [][] getVentaPrueba4() {
		String [][] venta  =
			{

				{"YOGSER", "34"},
				{"PANLAC", "11"},
				{"MEDGOR", "18"},
				{"PANCAS", "1"},
				{"PAPABL", "2.500"},
				{"PILASV", "1"},
			};		
		
		return venta;
		
	}
	
	public static String [][] getVentaPrueba(int numeroEjemplo) {
		if (numeroEjemplo == 1) 
			return PreciosYPromociones.getVentaPrueba1();
		if (numeroEjemplo == 2) 
			return PreciosYPromociones.getVentaPrueba2();
		if (numeroEjemplo == 3) 
			return PreciosYPromociones.getVentaPrueba3();
		return PreciosYPromociones.getVentaPrueba4();
	}
	
	
	public static String [][] getVentaPrueba() {
		Random r = new Random(new Date().getTime() );
		return PreciosYPromociones.getVentaPrueba(r.nextInt(4) + 1); 
	}
	
}
