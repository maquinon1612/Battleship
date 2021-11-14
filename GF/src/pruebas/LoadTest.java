package pruebas;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class LoadTest {
	@Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        String estado_tras1 = "\n" + 
        		"G;1;4;T;T;T;T;1;3\n" + 
        		"B1;02_O,00_O,10T\n" + 
        		"B1;02_O,11_O,21T\n" + 
        		"B1;02_*,22_O,32T*,22_\n" + 
        		"B1;02_O,33_O,43T\n";
        
        try {
			tester.cargar("tests/test1"); // Se carga una partida que no esposible llegar con las funciones del juego
									//pero sigue siendo posible como solo tener un barco
		} catch (CommandExecuteException | IOException e1) {}
        
        String s = tester.toSave();
        
        assertEquals(s,estado_tras1);//Se comprueba tambien que aunque falten datos en el tablero estos se rellenan 
        //es el caso del disparo en 22 que daña un barco pero este no se guarda en los datos del tablero
        //La dificultad de los jugadores automaticos se debe mantener
        
        try {
			tester.disparar(3, 0, 0);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        s = tester.toSave();
        
        try {
			tester.cargar("tests/test1_trasloadTest"); // Se carga una partida con disparos realizados y se comprueba que es igual que lo guardado tras los disparos
		} catch (CommandExecuteException | IOException e1) {}
        
        assertEquals(s,tester.toSave());
        
	}
}
