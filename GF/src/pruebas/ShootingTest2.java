package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class ShootingTest2 {
	@Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        try {
			tester.cargar("tests/test2");
		} catch (CommandExecuteException | IOException e1) {}
        
        String entrada = tester.toSave();
        
        try {
			tester.disparar(1, 0, 0);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e1) {}//pasa del jugador 4 al 1 correctamente
        
        try {
            tester.disparar(1, 0, 0);//No se puede disparar a si mismo
        }catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        
        try {
        tester.disparar(2, 16, 16);//No puedes disparar fuera del tablero
		}catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        
        try {
        tester.disparar(31, 110, 10);//No puedes disparar a un jugador que no hay
		}catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        //Se comprueba varias veces para saber que no pasa de turno
        
        String salida = tester.toSave();
        
        try {
			tester.cargar("tests/test1_salida2");
		} catch (CommandExecuteException | IOException e) {}
        
        String salidaejemplo = tester.toSave();
        
        assertEquals(salida,salidaejemplo); // se comprueba que no se ha podido realizar el segundo disparo y no se pasa de turno
        assertNotEquals(salida,entrada);
    }
}
