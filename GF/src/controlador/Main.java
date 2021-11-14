package controlador;

import java.io.IOException;
import java.util.Scanner;

import modelo.Game;

public class Main {
	private static Game juego;
	private static Scanner in;
	private static controller controler;

	/** Función main - Crea el controller y ejecuta su función Run 
	 * @throws IOException */
	
	public static void main(String[] args) throws IOException{
		in = new Scanner (System.in);
		controler = new controller(juego , in);
		if(args.length > 0) {
			if(args[0].equals("consola")) {
				controler.run();
			}
			else if(args[0].equals("gui")) {
				controler.init();
			}
		}
		else {
			controler.init();
		}
	}

}
