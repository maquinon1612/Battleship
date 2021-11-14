package modelo.estrategia;
//clase estrategia1

import modelo.Casilla;
import modelo.EstadoCasilla;
import modelo.Game;
import modelo.Tablero;

public class Estrategia1 implements Obstrategy{
	//define la variable barco de tipo Barco
    static Casilla c;
        
	//constructora sin para metros de Estrategia1
	public Estrategia1() {
            c = new Casilla(0 , -1 , EstadoCasilla.NULO);
	}
	//sobrescribe la funcion disparar que recibe un tablero por parametro
	@Override
	public void disparar(Tablero t) {
		//crea e inicializa dos variables
		int i = c.getFila();
		int j = c.getCol() + 1;
		
		if(j <Game.TAM) {
			i++;
			j = 0;
		}
		c=t.getCasilla(i, j);
		t.serdisparado(i, j);
	}
	@Override
	public int getdif() {
		return 1;
	}

}

/*Dispara desde la primera casilla del tablero
  (arriba a la izquierda) hasta la ultima
  (abajo a la derecha) en orden*/
