package controlador.Command;

import exception.CommandParseException;

/*Class CommandGenerator 
*Devuelve un nuevo comando segun el user input.
*/

public class CommandGenerator {
	
	/*Lista de los comandos disponibles*/
	
	private static Command[] availableCommands = {
			new Disparar(),
			new Mostrartablero(),
			new SaveCommand(),
                        new CargarPartida(),
                        new HelpCommand(),
                        new Colocar()
			};
	private static int numberofcommands = 6;
	
	/** Recive el user input y crea un nuevo comando usando la funcion parse en cada uno de ellos.
	*Lanza CommandParseException si no hay ningun comando correspondiente al input.
	 *  */
	
	public static Command parse(String[] commandWords) throws CommandParseException{
		for(int i = 0 ; i < numberofcommands ; i++) {
			if(availableCommands[i].parse(commandWords) != null) {
				return availableCommands[i].parse(commandWords);
			}
		}
		throw new CommandParseException("El comando dado no es uno de las posibles");
	}
	
	/**Une todos los helpText de los comandos para ser mostrados por HelpCommand*/
	
	public static String commandHelp() {
		String helptext = "";
		for(int i = 0; i < availableCommands.length ; i++ ) {
			helptext = helptext + availableCommands[i].helpText();
		}
		return helptext;
	}
}
