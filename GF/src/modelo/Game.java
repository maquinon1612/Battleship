package modelo;

import java.io.IOException;

import exception.CommandExecuteException;
import exception.FileContentsException;
import java.util.ArrayList;

import controlador.Command.Command;
import modelo.Observer.GameObserver;
import modelo.Observer.Observable;
import modelo.estrategia.Estrategia1;
import modelo.estrategia.Estrategia2;
import modelo.estrategia.Estrategia3;
import modelo.estrategia.JAuto;
import modelo.estrategia.JHumano;
import modelo.estrategia.Jugador;
import modelo.estrategia.Obstrategy;


public class Game implements Observable<GameObserver>{
	//Atributos de la clase Game
	public final static int TAM = 10;
	private Jugador[] j;
	private boolean[] vivos;
	private int jugadoract = 0;
	private int numJugadores = 0;
	private int numJugadoresvivos;
        
    private ArrayList<GameObserver> lo;

	
	public Game (){
        lo = new ArrayList<GameObserver>();
	}
	
	public void ejecutar(Command c) throws CommandExecuteException, IOException {
		c.execute(this);
		int i = 0;
	}

	public void inicializar(int num, int numJAuto , int dif[])throws IllegalArgumentException{//Inicializa el juego
		
        numJugadores = numJugadoresvivos = num;
        if(numJugadores <=4 && numJugadores > 0 && numJAuto <= numJugadores) {
        	vivos = new boolean[4];
    		for(int i = 0 ; i < 4 ; i++) {
    			vivos[i] = false;
    		}
    		
    		j = new Jugador[4];
    		for (int i = 0 ; i < numJugadores - numJAuto; i++) {
    			vivos[i] = true;
    			j[i] =  new JHumano(TAM , i);
    			for(int h = 0 ; h < i ; h++) {
    				j[i].aniadirenem(j[h]);
    				j[h].aniadirenem(j[i]);
    			}
    		}
    		for (int i = numJugadores - numJAuto ; i < numJugadores; i++) {
    			vivos[i] = true;
    			//Crea la estrategia y se la pasa al juagador automatico
    			Obstrategy estrategia = escoger(dif[i - (numJugadores - numJAuto)]);
    			j[i] =  new JAuto(estrategia , TAM , i);
    			for(int h = 0 ; h < i ; h++) {
    				j[i].aniadirenem(j[h]);
    				j[h].aniadirenem(j[i]);
    			}
    		}
                    
            createonInicializar();
        }
        else {
        	throw new IllegalArgumentException("Datos incorrectos");
        }
	}     

    public void colocarmaquina() {
        j[jugadoract].autocolocar();
        finalizarturno();
    }
    
    public boolean puedecolocar() {
		return j[jugadoract].colocados();
	}
        
        public void turno(){
            System.out.println("El jugador actual es : J" + (jugadoractual() + 1));
        }
        
    public void jugarmaquina(){
            boolean fin = false;
            while(!fin){
                try {
                    j[jugadoractual()].disparar();
                    fin = true;
		} catch (IOException|IllegalArgumentException e) {
		}
            }
	}
        
    public int jugadoractual(){
        while(!vivos[jugadoract]) {
			jugadoract = jugadoract + 1 >= numJugadores ? 0 : jugadoract + 1;
        }
        return jugadoract;
	}
	
	public void finalizarturno() {
            System.out.println("Fin de turno");
            jugadoract = jugadoract + 1 >= numJugadores ? 0 : jugadoract + 1;
            createonEndTurn();
    }
	
	public boolean isFinished() {//Comprueba si el jugador tiene algún barco vivo, si todos estan hundidos 
		for(int i = 0; i< numJugadores; i ++) {
			if(j[i].gettablero().getnumbarcos() == 0 && vivos[i] && j[i].getnocolocados() == 0) {
				System.out.println("El jugador " + (i + 1) + " ha muerto");
				vivos[i] = false;
				numJugadoresvivos -= 1;
			}
		}
		if(numJugadoresvivos == 1) {
			this.createonEnd();
			return true;
		}
		return false;
	}
	
	public int  getganador () {//Función que devuelve el ganador de la partida
		for(int i = 0; i < vivos.length ; i++) {
			if(vivos[i]) {
				return (i + 1);
			}
		}
		return 0;
	}

	public boolean exmaquina() {
		return j[jugadoract].ai();
	}
	
	public String toSave() {//Función para guardar el estado del juego
		String string = "\n";
		string = string + "G;" + jugadoract + ";" + numJugadores;
		for(int i = 0 ; i < numJugadores ; i++) {
			if(vivos[i]) string = string + ";T";
			else string = string + ";F";
		}
                
                String aux = "";
                int numAuto = 0;
                for(int i = 0 ; i < numJugadores ; i++) {
                    if(j[i].ai()){
                        aux = aux  + ";" + j[i].getdif();
                        numAuto++;
                    }
		}
                
                string = string  + ";" + numAuto;
                string = string + aux;
		
		string = string + "\n";
				
		for(int i = 0 ; i < numJugadores ; i++) {
                    if(vivos[i]){
			string = string + j[i].toSave() + "\n";
                    }
		}
		return string;
	}

        public boolean parse(String game) throws FileContentsException {
            String[] gamer = game. split (";");
            vivos = new boolean[4];
            if(game != "" && gamer[0].equalsIgnoreCase("G")) {
			jugadoract = Integer.parseInt(gamer[1]);
			numJugadores =  Integer.parseInt(gamer[2]);
                        for(int i = 3 ; i< numJugadores + 3 ; i ++){
                            if(gamer[i].equalsIgnoreCase("T")){
                                vivos[i - 3] = true;
                            }
                            else if(gamer[i].equalsIgnoreCase("F")){
                                vivos[i - 3] = false;
                            }
                            else{
                                throw new FileContentsException("invalid file, unknown line prefix");
                            }
                        }
                        
                        int numAuto = Integer.parseInt(gamer[numJugadores + 3]);
                        int dif[] = new int[3];
                        
                        for(int i = numJugadores + 3 ; i< numJugadores + 3 + numAuto; i++){
                            dif[i - (numJugadores + 3)] = Integer.parseInt(gamer[i + 1]);
                        }
                        
                        inicializar(numJugadores, numAuto, dif);
                        return true;
		}
		throw new FileContentsException("invalid file, unknown line prefix");
	}

    public void addObserver(GameObserver o) {
        if(!lo.contains(o)) {
            lo.add(o);
        }
    }

    @Override
    public void removeObserver(GameObserver o) {
        lo.remove(o);
    }
    
    public void createonInicializar() {
	for(GameObserver o : lo) {
            o.onInicializargame(j[jugadoract].getnumjugador()); 
	}	
    }

    public void createonColocar() {
    	for(GameObserver o : lo) {
            o.onColocar(toSave());
    	}
    }
    
    public void createonEndTurn() {
	for(GameObserver o : lo) {
            o.onEndTurn(j[jugadoract].getnumjugador() , toSave()); 
	}	
    }
    
    public void createonCargar() {
    	for(GameObserver o : lo) {
            o.onCargar(j[jugadoract].getnumjugador() , toSave());
    	}
    }
    
    public void createonEnd() {
    	for(GameObserver o : lo) {
            o.onEnd(getganador());
    	}
    }

    public int getj() {
        return numJugadores;
    }

	public ArrayList<GameObserver> getlo() {
		return lo;
	}

	public void setlo(ArrayList<GameObserver> lo) {
		this.lo = lo;
	}
	
	///////////////////////////////////////////////////Correccion///////////////////////////////////////////////////
	
	public Obstrategy escoger(int dificultad) {
		if(dificultad == 1) {
			return new Estrategia1();
		}
		if(dificultad == 2) {
			return new Estrategia2();
		}
		if(dificultad == 3) {
			return new Estrategia3();
		}
		return null;
	}

	public Jugador[] getjugadores() {
		return j;
	}

	public boolean[] getvivos() {
		return vivos;
	}
	
	public int getjactual() {
		return this.jugadoract;
	}

	public void setvalores(Game game2) {
		j = game2.j;
		vivos = game2.vivos;
		jugadoract = game2.jugadoract;
		numJugadores = game2.numJugadores;
		numJugadoresvivos = game2.numJugadoresvivos;
	}
}
