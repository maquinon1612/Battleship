package pruebas;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class ShootingTestAsiMismo {
	 @Test
	    public void prueba(){
	    	
	    	Game juego = null;
	        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
	        
	        try {
				tester.cargar("tests/test1");
			} catch (CommandExecuteException | IOException e1) {}
	        try {
		        tester.disparar(2, 0, 0);
	        }catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
	        
	        
	        String salida = tester.toSave();
	        
	        try {
				tester.cargar("tests/test1");
			} catch (CommandExecuteException | IOException e) {}
	        
	        String entrada = tester.toSave();
	        
	        assertEquals(salida,entrada);//Se comprueba que no se permite disparars a si mismo
	        
	    }
}
