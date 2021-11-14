package controlador;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

import controlador.Command.CargarPartida;
import controlador.Command.Colocar;
import controlador.Command.Command;
import controlador.Command.CommandGenerator;
import controlador.Command.Disparar;
import controlador.Command.SaveCommand;
import exception.CommandExecuteException;
import exception.CommandParseException;
import vista.interfazgrafica.MainWindow;
import javax.swing.SwingUtilities;
import modelo.Game;
import modelo.Observer.GameObserver;
import modelo.Observer.Observable;
import modelo.memento.toSave;

/** Class "Controller":
 * 
 *		Controla la ejecucion del juego.
 *		recibe los comandos del usuario y ejecuta la correspondiente accion.
 *
 *  */

public class controller implements Observable<GameObserver>{
	
	private Game juego;
	private static final String comando = "Comando > ";
	protected static final String Comandodesconocido = "Comando desconocido";
	private Scanner in;
	
	public controller(Game game , Scanner in) {
		this.in = in; 
		juego = new Game();
	}
        
        
        public void init(){
            SwingUtilities.invokeLater(() -> new MainWindow(this));
        }
	
	/** Recive el comando correspondiente y comprueba si es valido.
	*	Si es valido ejecuta la accion correspondiente.	
	*	Imprime el juego.
	*	Comprueba si el juego tiene que continuar o tiene que finalizar.
	*  */
	
	public void run() throws IOException {
		System.out.println("numjugadores :");
		int num = in.nextInt();
		System.out.println("numjugadores Automaticos :");
		int numJAuto = in.nextInt();
		int dif[] = new int[4];
		for(int i = 0; i< numJAuto ;i++) {
			System.out.println("Dificultad del Jugador Automatico" + i + " :");
			dif[i] = in.nextInt();
		}
		juego.inicializar( num,  numJAuto ,  dif);
		
            while (!juego.isFinished()){
                juego.turno();
                if(juego.exmaquina()) {
                	automatico();
                }
                else {
                    System.out.print(comando);
                    String[] words = in.nextLine().trim().split("\\s+");
                    try {
                        Command command = CommandGenerator.parse(words);
                        if (command != null) {
                        	juego.ejecutar(command);
                        }
                        else System.out.println(Comandodesconocido);
                    }
                    catch (CommandParseException | CommandExecuteException | IllegalArgumentException ex) {
                        if(ex.getCause() != null) {
                            System.err.format(ex.getMessage() + "Cause of exception:" + ex.getCause().getMessage() + " %n %n");
                        }
                        else {
                            System.err.format(ex.getMessage() + " %n %n");
                        }
                    }
                }
            }
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    
    public void disparar(int enem , int fila , int col) throws IllegalArgumentException, CommandExecuteException, IOException{
    	juego.ejecutar(new Disparar(enem ,fila ,col));
    }
    

    public void colocar(int tam ,int fila ,int col ,int direccion) throws IllegalArgumentException, CommandExecuteException, IOException{
    	juego.ejecutar(new Colocar(tam , fila , col , direccion));
    }
    
    public void guardar(String name) throws IOException, CommandExecuteException{
    	juego.ejecutar(new SaveCommand(name));
    }
    
    public void cargar(String name) throws CommandExecuteException, IOException{
    	juego.ejecutar(new CargarPartida(name));
    }
    
    @Override
    public void addObserver(GameObserver o) {
        juego.addObserver(o);
    }


    @Override
    public void removeObserver(GameObserver o) {
        juego.removeObserver(o);
    }

    public int getj() {
        return juego.getj();
    }

    public int jugadoractual() {
        return juego.jugadoractual();
    }

    public void inicializargame(int num, int numauto, int[] difs) {
        try {
        	juego.inicializar(num, numauto, difs);
        }catch(IllegalArgumentException e){
        	System.out.println(e.getMessage());
        }
        //run();
    }


	public boolean ai(int enem) {
		return juego.exmaquina();
	} 
	
	
	public String toSave() {
		return new toSave(juego).toString();
	}


	public void automatico() {
		if(juego.exmaquina()) {
			if(juego.puedecolocar()) {
				juego.colocarmaquina();
			}
			else {
	            juego.jugarmaquina();
	            juego.finalizarturno();
			}
        }
	}
}
