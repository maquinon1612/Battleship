package pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class InicializaryJugadorAutomaticoTest {//Se prueba la funcion inicializar y los jugadores automaticos
	@Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        tester.inicializargame(5, 0, null);//no inicializa el juego no puede haber mas de 4 jugadores
        tester.inicializargame(4, 5, null);//no inicializa el juego no puede haber mas jAutomaticos que jugadores hay
        int a[] = {1,2,3};
        tester.inicializargame(4, 3, a);
        
        String salida = tester.toSave();
        
        assertEquals('4',salida.split("\n")[1].charAt(4));//Se comprueba que hay cuatro jugadores 
        assertEquals('3',salida.split("\n")[1].charAt(14));//Se comprueba que hay 3 jugadores automaticos
        assertEquals('1',salida.split("\n")[1].charAt(16));//Se comprueba que el jAuto 1 es de dificultad 1 
        assertEquals('2',salida.split("\n")[1].charAt(18));//Se comprueba que el jAuto 2 es de dificultad 2 
        assertEquals('3',salida.split("\n")[1].charAt(20));//Se comprueba que el jAuto 3 es de dificultad 3 
        
        try {
			tester.colocar(2, 0, 0, 1);
			tester.colocar(4, 3, 3, 1);
	        tester.colocar(3, 7, 0, 2);
	        tester.colocar(3, 8, 0, 2);
	        tester.colocar(5, 9, 0, 2);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        salida = tester.toSave();
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());//Se debe haber colocado todos los barcos aleatoriamente
        salida = tester.toSave();
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());
        salida = tester.toSave();
        
        assertNotEquals(salida.split("\n")[3], salida.split("\n")[4]);//Se comprueba que como se colocan aleatoriamente son diferentes posiciones entre diferentes jugadores
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());
        salida = tester.toSave();
        
        assertNotEquals(salida.split("\n")[4], salida.split("\n")[3]);//Se comprueba que como se colocan aleatoriamente son diferentes posiciones entre diferentes jugadores
        
        try {
			tester.disparar(2, 0, 0);
		} catch (IllegalArgumentException | CommandExecuteException | IOException e) {}
        
        salida = tester.toSave();
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());//Se debe haber disparado algun jugador en una posicion concreta segun la estrategia 1
        salida = tester.toSave();
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());//Se debe haber disparado algun jugador en una posicion concreta segun la estrategia 2
        salida = tester.toSave();
        
        tester.automatico();
        assertNotEquals(salida, tester.toSave());//Se debe haber disparado algun jugador en una posicion concreta segun la estrategia 3
        
	}
}
