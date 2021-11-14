package controlador.Command;

import modelo.Game;

public class HelpCommand extends Command{
	
	public HelpCommand() {
		super("Help", "h", "help", "Prints this help message.");
	}
	
	/*Muestra para que sirven los diferentes comandos*/

	@Override
	public void execute(Game g) {
		System.out.println(CommandGenerator.commandHelp()); 
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			return this;
		}
		return null;
	}

}
