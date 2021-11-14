package controlador.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.CommandExecuteException;
import exception.FileContentsException;
import modelo.Game;
import modelo.Tablero;

/*Class CargarPartida
*Carga una partida jugada anteriormente de un archivo de texto.
*El usuario introduce el archivo a cargar
*/

public class CargarPartida extends Command{
	private String name;

	public CargarPartida(String name) {
		super("Cargar","c","cargar<nombredelarchivo>","carga el estado del juego desde un archivo del tipo .txt");
		this.name = name;
	}
	
	public CargarPartida() {
		super("Cargar","c","cargar<nombredelarchivo>","carga el estado del juego desde un archivo del tipo .txt");
	}

	@Override
	public void execute(Game g) throws CommandExecuteException{
		Game game2 = new Game();
    	game2.setlo(g.getlo());
        try(BufferedReader inBytes = new BufferedReader(new FileReader(name + ".txt"));){
            inBytes.readLine().trim();
            ///////////////////////////load
            String line = inBytes.readLine().trim();
            if(game2.parse(line)) {
                for(int i = 0; i< g.getj() ; i++){
                    if(game2.getvivos()[i]){
                        line = inBytes.readLine().trim();
                        game2.getjugadores()[i].parse(Tablero.parse(line));
                    }
                }
            }
            //////////////////////////
            inBytes.close();
            System.out.println("'Game successfully load from file " + name + ".txt.'");
            g.setvalores(game2);
            g.createonCargar();
        }
        catch (IOException | FileContentsException | IllegalArgumentException io) {
            throw new CommandExecuteException("unable to load\n" , io);
        }
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 2) {
			if(matchCommandName(commandWords[0])) {
				return new CargarPartida(commandWords[1]);
			}
		}
		return null;
	}
}
