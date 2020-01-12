package pruebamobdev.breedsserver.exception;

public class ErrorServicio extends Exception {

	/**
	 * Serie.
	 */
	private static final long serialVersionUID = 6500476936200996188L;

	public ErrorServicio(Exception error) {

		super(error);

	}

	public ErrorServicio(String mensaje) {

		super(mensaje);

	}

}
