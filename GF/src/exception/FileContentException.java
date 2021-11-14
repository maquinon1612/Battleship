package exception;

public class FileContentException extends Exception {
	
	/** 
	Excepcion lanzada si hay algun problema mientras se intenta cargar una partida.
	No es un CommandExecuteException porque hace referencia a problemas con archivos erroneos.
	*/
	
	private static final long serialVersionUID = 1L;
	

	public FileContentException() {super("El comando no se ha podido ejecutar");}
	
	public FileContentException(String mensage) {super(mensage);}
	
	public FileContentException(String mensage , Throwable cause) 
	{super(mensage, cause);}
	
	public FileContentException(Throwable cause) {super(cause);}
}
