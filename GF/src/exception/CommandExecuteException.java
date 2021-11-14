package exception;


public class CommandExecuteException extends Exception {
	
	//Excepcion lanzada cuando hay un problema curante la ejecucion del comando
	
	private static final long serialVersionUID = 1L;

	public CommandExecuteException() {super("El comando no se ha podido ejecutar");}

	public CommandExecuteException(String mensage) {super(mensage);}
	
	public CommandExecuteException(String mensage , Throwable cause) {super(mensage, cause);}
	
	public CommandExecuteException(Throwable cause) {super(cause);}
}
