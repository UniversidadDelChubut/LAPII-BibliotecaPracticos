package ejercicioscolecciones;

import java.util.HashMap;
import java.util.Map;

public class Rango implements Comparable<Rango> {
	private char codigo;
	private String nombre;
	
	public Rango(char codigo) {
		this.codigo = codigo;
		this.nombre = "Rango " + codigo;
	}
	
	public char getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return this.getCodigo() + "";
	}
	
	@Override
	public int compareTo(Rango o) {
		return this.codigo - o.codigo;
	}
	
	public static Rango getRango(int valor) {
		if(valor < 40_000)
			return new Rango('A');
		if(valor < 100_000)
			return new Rango('B');
		return new Rango('C');
	}

	public static void main(String[] args) {
		// Este programa tiene el propósito de tomar todos
		// los valores del arreglo y luego de clasificar cada
		// uno en el rango correspondiente, informar el total
		// acumulado en cada rango, pero algo falla ¿Qué es?
		// ¿Cuál es la manera correcta de arreglarlo?
		// Corrija el programa. Explique la solución propuesta.
		int [] valores = {10_000, 35_000, 250_000, 125_000, 75_000, 62_000};
		Map<Rango, Integer> acumulados = new HashMap<>();
		for(int valor: valores) {
			Rango rango = Rango.getRango(valor);
			Integer acumulado = acumulados.get(rango);
			acumulado = (acumulado == null ? 0 : acumulado) +  valor;
			acumulados.put(rango, acumulado);
		}
		for (Rango rango: acumulados.keySet()) {
			System.out.println(rango + "  " + acumulados.get(rango));
		}
	}

}
