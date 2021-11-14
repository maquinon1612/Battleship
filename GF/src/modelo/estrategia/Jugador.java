package modelo.estrategia;

import java.io.IOException;

import modelo.Barco;
import modelo.Casilla;
import modelo.EstadoCasilla;
import modelo.Game;
import modelo.Tablero;

import vista.consola.GamePrinter;

public abstract class Jugador {  //definir variables del jugador
	protected Tablero t; 
	private int numjugador; 
	protected Jugador[] jenem;
	protected Tablero[] enem;
	protected int numenem;
	protected int TAM;
    int disparado;
    private int tamanios[] = {0,0,1,2,1,1};
    protected int barcosnocolocados = 5;
	
	public Jugador(int TAM , int num) { //constructora de jugador que recibe dos paramentros con el tamano y el numero
		//inicializar todos los valores de las variables
		numenem = 0;  
		numjugador = num;  
		this.TAM = TAM;
		t =  new Tablero(TAM, TAM);
		t.iniciaTablero();
		enem =  new Tablero[3];
		jenem =  new Jugador[3];
	}
	
	public void aniadirenem(Jugador j) { // nuevo jugador para la partida que se anade y se inicializa
		jenem[numenem] = j;
		enem[numenem] =  new Tablero(TAM, TAM);
		enem[numenem].iniciaTablero(); //se inicia tablero del nuevo contrincante
		numenem++;
	}
	
	public Tablero gettablero() { //get del tablero
		return t; 
	}
	
	public Tablero getenem(int i) { //funcion que devuelve un tablero
					//recibe por parametros el entero que determina el enemigo
		if( i <= numenem && i >= 0) { //comprobar que el entero es valido
			if(i < numjugador) {
				return enem[i]; //devuelve el tablero enemigo
			}
			else {
				return enem[i - 1]; //devuelve el tablero enemigo
            }
		}
		else {
			throw new IllegalArgumentException("No existe ese jugador enemigo"); //se lanza excepcion
		}
	}
	
	public Jugador getjenem(int i) {
		if(i <= numenem && i >= 0) { //comprobar que el entero es valido
			if(i < numjugador) {
				return jenem[i]; //devuelve el jugador enemigo
			}
			else {
				return jenem[i - 1]; //devuelve el jugador enemigo
			}
		}
		else {
			throw new IllegalArgumentException("No existe ese jugador enemigo"); //lanza excepcion
		}
	}
	
	public Jugador getjenemtoshoot(int i) { //funcion que devuelve un jugador 
					//recibe por parametros el entero que determina el enemigo
		if( i <= numenem && i >= 0) { //comprobar que el entero es valido
			if(i < numjugador) {
				if(jenem[i].gettablero().getnumbarcos() == 0) {
					throw new IllegalArgumentException("No puedes disparar , ya esta muerto"); //si el enemigo no tiene barcos lanza excepciom
				}
				return jenem[i]; //devuelve el jugador enemigo
			}
			else if(i > numjugador) {
				if(jenem[i - 1].gettablero().getnumbarcos() == 0) {
					throw new IllegalArgumentException("No puedes disparar , ya esta muerto"); //si el enemigo no tiene barcos lanza excepciom
				}
				return jenem[i - 1]; //devuelve el jugador enemigo
			}
			else {
				throw new IllegalArgumentException("No puedes dispararte a ti mismo"); //lanza excepcion
			}
		}
		else {
			throw new IllegalArgumentException("No existe ese jugador enemigo"); //lanza excepcion
		}
	}
	
	public void dibujartableros(int i) { //funcion que dibuja el tablero
	
		System.out.println("Tu tablero"); //muestra por pantalla "tu tablero"
		GamePrinter printer = new GamePrinter(t, Game.TAM, Game.TAM); //inicializa el valor de printer a un  nuevo gamePrinter
		System.out.println(printer); // imprime printer
		
		for(int j = 0 ; j < numenem ; j++) {
			if(j < i) {
				System.out.println("Tablero del jugador" + (j+1));
			}
			else {
				System.out.println("Tablero del jugador" + (j+2));
			}
			printer = new GamePrinter(enem[j], Game.TAM, Game.TAM);
			System.out.println(printer);
		}
	}
	
	public abstract void disparar() throws IOException; //funcion abstracta para que se implemente disparar
    public abstract void disparar(int enem , int fila , int col) throws IllegalArgumentException; //funcion abstracta que dispara a contrincante enem en el 
	public abstract int getdif();						//tablero con fila fila y columna col
	public abstract void autocolocar();
	public abstract boolean ai(); //funcion abstracta
	
	public String toSave(){ //Funcion que devuelve un String para poder guardar el juego en un fichero .txt
		String string = "";
		
		string = string + t.toSave();
		
		return string;
	}

        public void parse(Tablero t) {
            this.t = t;
            barcosnocolocados = 0;
        }
        
        public void setdisparado(int d){
            disparado = d;
        }
        
        public int getdisparado(){
            return disparado;
        }
        
        public boolean colocar(int tam, int fila, int col, int direccion) throws IllegalArgumentException{
            if(tamanios[tam] > 0){
                Casilla[] c = new Casilla[tam];
		if(direccion==1)//Si la dirección escogida es vertical
                    for (int j =0; j<tam; j++) {
                    c[j] = new Casilla(fila + j , col , EstadoCasilla.BARCO);
		}
		else if(direccion==2) {//Si la dirección escogida es horizontal
			for (int j =0; j<tam; j++) {
                            c[j] = new Casilla(fila , col + j , EstadoCasilla.BARCO);
		}
		}
			Barco b= new Barco(tam, 001, c);
                t.aniadirbarco(b);
                barcosnocolocados --;
                tamanios[tam] = tamanios[tam] - 1;
            }
            else{
                throw new IllegalArgumentException("No te quedan barcos de tamaño:" + tam);
            }
            return colocados();
        }

    public int getnocolocados() {
        return barcosnocolocados;
    }

    public boolean colocados() {
        return (barcosnocolocados > 0);
    }
    
    public int getnumjugador(){
        return numjugador;
    }
}

