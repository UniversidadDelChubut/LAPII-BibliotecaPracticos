package ejerciciosexcepciones.provincias;

@SuppressWarnings("serial")
public class ProvinciaInexistenteException extends RuntimeException {

	public ProvinciaInexistenteException(String message) {
		super(message);
	}

}
