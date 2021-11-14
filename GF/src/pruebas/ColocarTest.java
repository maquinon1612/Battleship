package pruebas;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class ColocarTest {
	@Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        tester.inicializargame(2, 0, null);
        
        try {
        tester.disparar(2, 0, 0);
        }catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        String salida = tester.toSave();
        
        assertEquals('0',salida.split("\n")[1].charAt(2));//Se comprueba que no se permite disparar hasta colocar los barcos
        
        try {
			tester.colocar(2, 0, 0, 1);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e1) {}//colocar barco de tamaño 2 en posicion 0 0 en vertical(1);
        
        salida = tester.toSave();
        
        String comprobacion= "1" //numero de barcos colocados
        					+ ";12" //; separador ente barcos id generico y tamaño de barco
        					+ "_O,00" // O estado barco en posicion 00 (_ es el separador de casillas)
        					+ "_O,10"; // siguiente casilla en 10 por colocar verticalmente
        assertEquals(comprobacion ,salida.split("\n")[2].split("T")[0].split("B")[1]);
    
        try {
        tester.colocar(2, 3, 3, 1);//colocar barco de tamaño 2 no se puede porq ya esta colocado
        }catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        assertEquals(comprobacion ,salida.split("\n")[2].split("T")[0].split("B")[1]);//no cambia porque no se puede colocar
        
        try {
			tester.colocar(4, 3, 3, 1);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e1) {}//colocar barco
        salida = tester.toSave();
        
        try {
        tester.colocar(3, 4, 1, 2);//No se coloca porque coincide con el otro barco
        }catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        comprobacion = tester.toSave();
        
        assertEquals(comprobacion ,salida);//no cambia porque no se puede colocar
        
        try {
        tester.colocar(3, 9, 9, 2);//No se coloca porque sale del tablero
		}catch(IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        comprobacion = tester.toSave();
        
        assertEquals(comprobacion ,salida);//no cambia porque no se puede colocar
        
        try {
			tester.colocar(3, 7, 0, 2);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        try {
			tester.colocar(3, 8, 0, 2);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        try {
			tester.colocar(5, 9, 0, 2);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        salida = tester.toSave();
        
        assertEquals('1',salida.split("\n")[1].charAt(2));//Se comprueba que se cambia de turno al colocar todos los barcos
	}
}
