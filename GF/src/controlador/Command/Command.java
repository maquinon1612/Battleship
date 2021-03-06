package controlador.Command;

import java.io.IOException;

import exception.CommandExecuteException;
import exception.CommandParseException;
import modelo.Game;

/*
De esta clase heredan todos los comandos.
Cada subclase de Command busca en el input el texto del comando que la subclase representa.
*/

public abstract class Command {
	
	protected final String name;
	protected final String shortcut;
	private final String details ;
	private final String help;
	
	
	protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
	protected static final String incorrectArgsMsg = "Incorrect argument format";
	
	
	public Command(String name, String shortcut, String details, String help){
		this.name = name;
		this. shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	public abstract void execute(Game g) throws CommandExecuteException, IOException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
		
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) ||
				this.name.equalsIgnoreCase(name);
	}
	public String helpText(){
	return details + " : " + help + "\n";
	}
	
	public void pasarturno(Game g) throws IllegalArgumentException{
    	if(g.isFinished()) {
    		g.getganador();
    	}
    	else {
    		try{
                g.finalizarturno();
            }
            catch(IllegalArgumentException e){
            	throw new IllegalArgumentException(e.getMessage());
            }
    	}
    }

}
