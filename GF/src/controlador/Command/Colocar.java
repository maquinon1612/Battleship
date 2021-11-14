/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Command;

import exception.CommandExecuteException;
import exception.CommandParseException;
import modelo.Game;

public class Colocar extends Command{
    int tam , fila , col ,direccion;
	
	public Colocar() {
		super("colocar", "p", "colocar<tamanio del barco><fila><columna><direccion>", "Coloca un barco en una posicion del tablero.");
	}
        
        public Colocar(int tam ,int fila ,int col,int direccion) {
		super("colocar", "p", "colocar<tamanio del barco><fila><columna><direccion>", "Coloca un barco en una posicion del tablero.");
                this.tam = tam;
                this.fila = fila;
                this.col = col;
                this.direccion = direccion; 
        }
	
	@Override
	public void execute(Game g) throws CommandExecuteException {
		if(!g.getjugadores()[g.getjactual()].colocar(tam, fila, col, direccion)){
			pasarturno(g);
        }
        g.createonColocar();
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
                    if(commandWords.length == 5) {
			return new Colocar(Integer.parseInt(commandWords[1]) ,
                                            Integer.parseInt(commandWords[2]) , 
                                            Integer.parseInt(commandWords[3]) ,
                                            Integer.parseInt(commandWords[4]));
                    }		
		}
		return null;
	}

}
