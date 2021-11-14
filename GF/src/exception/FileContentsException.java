package exception;

public class FileContentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileContentsException(String name) {super(name);}

	public FileContentsException(String mensage , Throwable cause) {super(mensage, cause);}
}
