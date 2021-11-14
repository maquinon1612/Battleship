package controlador.Command;

import modelo.Game;

/*Class MostrarTablero
*dibuja los tableros de los jugadores.
*/

public class Mostrartablero extends Command{
	
	public Mostrartablero() {
		super("mostrar", "m", "[mostrar]", "Muestra los tableros que estan en juego(con las casillas ocultas claro)");
	}

	@Override
	public void execute(Game g) {
		g.getjugadores()[g.getjactual()].dibujartableros(g.getjactual());
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			return this;
		}
		return null;
	}
}
