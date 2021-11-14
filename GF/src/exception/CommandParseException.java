package exception;

public class CommandParseException extends Exception {

	/** Exception thrown when there's a problem while parsing a command */
	
	private static final long serialVersionUID = 1L;

	public CommandParseException() {super("El comando dado no es uno de las posibles");}

	public CommandParseException(String mensage) {super(mensage);}
	
	public CommandParseException(String mensage , Throwable cause) {super(mensage, cause);}
	
	public CommandParseException(Throwable cause) {super(cause);}
}
