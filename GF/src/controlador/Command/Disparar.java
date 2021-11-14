package controlador.Command;

import exception.CommandExecuteException;
import exception.CommandParseException;
import modelo.Game;

/*Class Disparar
*Dispara a un jugador enemigo.
*Muestra los tableros.
*Finaliza el turno del jugador actual.
*/

public class Disparar extends Command{
    
        int enem , fila , col;
	
	public Disparar() {
		super("dispara", "D", "dispara<juagdor a disparar><fila><columna>", "Disparar a una posicion del tablero enemigo.");
	}
        
        public Disparar(int enem ,int fila ,int col) {
		super("dispara", "D", "dispara<juagdor a disparar><fila><columna>", "Disparar a una posicion del tablero enemigo.");
                this.enem = enem;
                this.fila = fila;
                this.col = col; 
        }
	
	@Override
	public void execute(Game g) throws CommandExecuteException {
		g.getjugadores()[g.jugadoractual()].disparar(enem, fila , col);
		pasarturno(g);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
                    if(commandWords.length == 4) {
			return new Disparar(Integer.parseInt(commandWords[1]) ,
                                            Integer.parseInt(commandWords[2]) , 
                                            Integer.parseInt(commandWords[3]));
                    }		
		}
		return null;
	}

}
