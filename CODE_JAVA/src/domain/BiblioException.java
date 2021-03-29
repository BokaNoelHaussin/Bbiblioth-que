package domain;

public class BiblioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BiblioException() {
		super("Erreur : Conditions d'autorisation d'un prêt non remplie");
	}

	public BiblioException(String message) {
		super(message);
	}

	public static void main(String[] args) {
		System.out.println(new BiblioException());
		System.out.println(new BiblioException("livre non disponible"));
	}

}
