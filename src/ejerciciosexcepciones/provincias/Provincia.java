package ejerciciosexcepciones.provincias;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represente un aprovincia de Argentina.
 * 
 * @author lapii-udc
 *
 */
public class Provincia {

	// https://es.wikipedia.org/wiki/ISO_3166-2:AR

	static {
		Provincia.provincias.put("A", new Provincia("A", "Salta", "Salta", 1215207, 155488));
		Provincia.provincias.put("B", new Provincia("B", "Buenos Aires", "La Plata", 15594428, 307571));
		Provincia.provincias.put("C", new Provincia("C", "Ciudad Autónoma de Buenos Aires", "-", 2891082, 202));
		Provincia.provincias.put("D", new Provincia("D", "San Luis", "San Luis", 489255, 76748));
		Provincia.provincias.put("E", new Provincia("E", "Entre Ríos", "Paraná", 1235994, 78781));
		Provincia.provincias.put("F", new Provincia("F", "La Rioja", "La Rioja",  387728, 89680));
		Provincia.provincias.put("G", new Provincia("G", "Santiago del Estero", "Santiago del Estero", 896461, 136351));
		Provincia.provincias.put("H", new Provincia("H", "Chaco", "Resistencia", 1143201, 99633));
		Provincia.provincias.put("J", new Provincia("J", "San Juan", "San Juan", 680427, 89651));
		Provincia.provincias.put("K", new Provincia("K", "Catamarca", "San Fernando", 367820, 102602));
		Provincia.provincias.put("L", new Provincia("L", "La Pampa", "Santa Rosa", 316940, 143440));
		Provincia.provincias.put("M", new Provincia("M", "Mendoza", "Mendoza", 1741610, 148827));
		Provincia.provincias.put("N", new Provincia("N", "Misiones", "Posadas", 1097829, 29801));
		Provincia.provincias.put("P", new Provincia("P", "Formosa", "Formosa", 527895, 72066));
		Provincia.provincias.put("Q", new Provincia("Q", "Neuquén", "Neuquén", 550344, 94078));
		Provincia.provincias.put("R", new Provincia("R", "Río Negro", "Viedma", 633374, 203013));
		Provincia.provincias.put("S", new Provincia("S", "Santa Fe", "Santa Fé", 3300736, 133007));
		Provincia.provincias.put("T", new Provincia("T", "Tucumán", "San Miguel", 1592878, 22524));
		Provincia.provincias.put("U", new Provincia("U", "Chubut", "Rawson", 506668, 224686));
		Provincia.provincias.put("V", new Provincia("V", "Tierra del Fuego", "Ushuaia", 126190, 1002445));
		Provincia.provincias.put("W", new Provincia("W", "Corrientes", "Corrientes", 993338, 88199));
		Provincia.provincias.put("X", new Provincia("X", "Córdoba", "Córdoba", 3304825, 165321));
		Provincia.provincias.put("Y", new Provincia("Y", "Jujuy", "Jujuy", 672260, 53219));
		Provincia.provincias.put("Z", new Provincia("Z", "Santa Cruz", "Río Gallegos", 272524, 243943));
	}
	
	private static Map<String, Provincia> provincias = new LinkedHashMap<String, Provincia>();
	
	private String codigo;
	private String nombre;
	private String capital;
	private int habitantes;
	private int superficie;

	public static Provincia getProvincia(String codigo) throws ProvinciaInexistenteException {
		Provincia p = Provincia.provincias.get(codigo);
		if (p == null) {
			throw new ProvinciaInexistenteException("No existe una provincia con codigo " + codigo);
		}
		return p;
	}

	public static Collection <Provincia> getProvincias() {
		return Provincia.provincias.values();
	}

	
	public Provincia(String codigo, String nombre, String capital,
			int habitantes, int superficie) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.capital = capital;
		this.habitantes = habitantes;
		this.superficie = superficie;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCapital() {
		return capital;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public int getSuperficie() {
		return superficie;
	}
	
}
