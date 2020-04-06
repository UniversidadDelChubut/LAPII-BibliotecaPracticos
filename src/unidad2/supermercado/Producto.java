package unidad2.supermercado;

import java.util.Collection;
import java.util.LinkedList;

public class Producto {
	
	private static Collection<Producto> productos;
	
	private String codigo;
	private String nombre;
	private double precio;
	
	private Producto(String codigo, String nombre, double precio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public static Collection<Producto> getProductos() {
		
		if (Producto.productos !=  null)
			return productos;
		
		Producto.productos = new LinkedList<>();
		
		productos.add(new Producto ("YERP1K", "Yerba Piporé 1 Kilo", 38.45)); 
		productos.add(new Producto ("YOGSER", "Yogurth Serenito", 12.34));
		productos.add(new Producto ("PANLAC", "Pan lactal", 52.50));
		productos.add(new Producto ("MEDGOR", "Medialunas de grasa por unidad", 9.75));
		productos.add(new Producto ("PANCAS", "Pan Casero", 25.34));
		productos.add(new Producto ("PAPABL", "Papa Blanca por kilo", 12.00));
		productos.add(new Producto ("CEBOLL", "Cebolla por kilo", 14.00));
		productos.add(new Producto ("ZUKINI", "Zuccini por kilo", 34.00));
		productos.add(new Producto ("PILASV", "Pilas Varta", 65.00));
		return productos;
	}
	
}
