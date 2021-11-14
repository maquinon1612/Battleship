package modelo.estrategia;

import java.util.Random;
import modelo.Casilla;
import modelo.EstadoCasilla;
import modelo.Tablero;

public class Estrategia3 implements Obstrategy{
	private static boolean barcoDado=false;
	private static int numCasillasArededor=0;
	private static int x;
	private static int y;
	Random rand;
        static Casilla c;
        
	public Estrategia3() {c = new Casilla(0 , -1 , EstadoCasilla.NULO);}
        
	public  void disparar(Tablero t) {
		if(!barcoDado) {
			int i,j;
			
			i = (int) (Math.random() * (9 - 0)) + 0;
			j = (int) (Math.random() * (9 - 0)) + 0;
		
			
			c = t.getCasilla(i, j);
			t.serdisparado(i, j);
			if(t.getCasilla(i, j).getestado()==EstadoCasilla.TOCADO) {
				barcoDado=true;
				y=i;
				x=j;
			}
		}
		else {
			int i=-1,j=-1;
			if(numCasillasArededor<=3) {
				c=t.getCasilla(y, x);
				if(numCasillasArededor==0) {
					i=y-1;
					j=x;
				}
				else if(numCasillasArededor==1) {
					i=y;
					j=x-1;
				}
				else if(numCasillasArededor==2) {
					i=y;
					j=x+1;
				}
				else if(numCasillasArededor==3) {
					i=y+1;
					j=x;
				}
				
				t.serdisparado(i, j);
				if(t.getCasilla(i, j).getestado()==EstadoCasilla.TOCADO) {
					barcoDado=true;
					numCasillasArededor=0;
					y=i;
					x=j;
				}
				numCasillasArededor++;
				if(numCasillasArededor>3) {
					barcoDado=false;
					numCasillasArededor=0;
				}
			}
		}
	}

	@Override
	public int getdif() {
		return 3;
	}
}


/*Dispara a una casilla aleatoria del tablero y si da a una barco 
 * disparara en los turnos siguientes la casilla de arriba,derecha,izquierda y abajo 
 * en ese orden, si acaba de disparar alrededor disparara a una nueva casilla aleatoria
 * */
