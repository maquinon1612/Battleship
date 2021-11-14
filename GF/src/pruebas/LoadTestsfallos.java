package pruebas;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

import controlador.controller;
import exception.CommandExecuteException;
import modelo.Game;

public class LoadTestsfallos {

    @Test
    public void prueba(){
    	
    	Game juego = null;
        controller tester = new controller(juego ,new Scanner (System.in)); // MyClass is tested
        
        try {
			tester.cargar("tests/test1");
		} catch (CommandExecuteException | IOException e1) {}
        
        String s = tester.toSave();
        
        try {
			tester.cargar("tests/testfallo1");
		} catch ( CommandExecuteException | IOException e) {
			System.out.println(e.getMessage());
		}
        
        assertEquals(s,tester.toSave());//Se comprueba que se mantiene el mismo estado si se carga un archivo dañado y se puede continuar la partida
    }
}


