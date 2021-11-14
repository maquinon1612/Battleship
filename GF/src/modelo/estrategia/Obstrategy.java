package modelo.estrategia;

import modelo.Tablero;

public interface Obstrategy{
	public abstract void disparar(Tablero t );//cada uno crea una estrategia q hereda de esta clase e implementa como disparar
	//el tablero a disparar es t

	public abstract int getdif();
}