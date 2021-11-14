package modelo.memento;

import modelo.Game;

public class toSave{
	Game game;
	
	public toSave(Game game) {
		this.game = game;
	}
	
	public toSave() {}
	
	public String toString() {
		return game.toSave();
	}
}
