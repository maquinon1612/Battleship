package controlador.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import modelo.Game;
import modelo.memento.toSave;

public class SaveCommand extends Command{
	
	private String name;

	public SaveCommand(String name) {
		super("save", "v", "save<filename>", "save the state of the game on a .dat file.");
		this.name = name;
	}
	
	public SaveCommand() {
		super("save", "v", "save<filename>", "guarda el estado de la partida en un archivo txt");
	}

	@Override
	public void execute(Game g) throws IOException{
		BufferedWriter outBytes = new BufferedWriter(new FileWriter(name + ".txt"));
        outBytes.write((new toSave(g)).toString());
        outBytes.close();
        System.out.println("'Game guardado en archivo " + name + ".txt'");	
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 2) {
				return new SaveCommand(commandWords[1]);
			}
		}
		return null;
	}

}
