package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class ShootingTest1 {
	@Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        try {
			tester.cargar("tests/test1");
		} catch (CommandExecuteException | IOException e1) {}
        
        String entrada = tester.toSave();
        
        try {
			tester.disparar(3, 0, 0);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e1) {}
        
        String salida = tester.toSave();
        
        try {
			tester.cargar("tests/test1_salida1");
		} catch (CommandExecuteException | IOException e) {}
        
        String salidaejemplo = tester.toSave();
        
        assertEquals(salida,salidaejemplo);
        assertNotEquals(salida,entrada);
    }
}
