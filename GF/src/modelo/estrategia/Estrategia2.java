package modelo.estrategia;

import java.util.Random;
import modelo.Casilla;
import modelo.EstadoCasilla;
import modelo.Tablero;

public class Estrategia2 implements Obstrategy{ 
	//clase Estrategia2
		//definir variable barco de tipo Barco
		//definir variable rand de tipo Random
		Random rand;
                static Casilla c;
                
	//constructora de la clase estrategia 2 sin parametros
		public Estrategia2() { 
                    c = new Casilla(0 , -1 , EstadoCasilla.NULO);
		}
	//funcion disparar que recibe por parametro un tablero
		public  void disparar(Tablero t) {
			//define dos enteros
			int i,j;
			//asigna los valores de las funciones a los enteros
				i = (int) (Math.random() * (9 - 0)) + 0;
				j = (int) (Math.random() * (9 - 0)) + 0;
			
			c=t.getCasilla(i, j);
			t.serdisparado(i, j);
		}
		@Override
		public int getdif() {
			return 2;
		}
}

// Dispara a una casilla aleatoria del tablero
