package pruebamobdev.breedsserver.exception;

public class ErrorNoEncontrado extends ErrorServicio {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = -6878455687578593936L;

	public ErrorNoEncontrado(Exception error) {

		super(error);

	}

	public ErrorNoEncontrado(String mensaje) {

		super(mensaje);

	}

}
