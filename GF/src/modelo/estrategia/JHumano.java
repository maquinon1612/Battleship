package modelo.estrategia;

import exception.FileContentException;


public class JHumano extends Jugador{


	//constructor que llama al constructor de Jugador con tamano TAM y numero de jugador nu
	public JHumano(int TAM, int num){ 
		super(TAM , num);
	}
	
	// funcion que pide el numero de jugador al que se desea disparar y la posicion del tablero con fila y columna
	public void disparar(){}
        
        //metodo heredado de jugador que indica la posicion del tablero a disparar con los parametros fila y col
        @Override
        public void disparar(int enem , int fila , int col)  throws IllegalArgumentException{ 
            if(barcosnocolocados <= 0){
            int enem1 = enem - 1;
            if(enem1 <= numenem && enem1 > -1) {
		try {
                    getjenemtoshoot(enem1).gettablero().serdisparado(fila, col);//se deispara al tablero enemigo
                    getenem(enem1).actualizartablero(getjenem(enem1).gettablero());//se actualizan los tableros de todos los jugadores sobre el jugador al que se ha disparado
                    for(int i = 0 ; i< numenem ; i++){
                        if(jenem[i] != getjenem(enem1)){
                            jenem[i].getenem(enem1).actualizartablero(getjenem(enem1).gettablero());
                        }
                    }
                }
                catch(IllegalArgumentException e){ //en caso de no poderse realizar se lanzara una excepcion
                    throw e;
                }
            }
            else{
                throw new IllegalArgumentException("Jugador no existe");
            }    
            }
            else{
                throw new IllegalArgumentException("Coloca todos los barcos primero");
            }
            
	}

	@Override
	public boolean ai() {
		return false;
	}
	
	public static Jugador parse(String line) throws FileContentException {
		//char k=0;
		//Tablero t = new Tablero(TAM, TAM);
		
		throw new FileContentException("invalid file, unknown line prefix");
	}

    @Override
    public int getdif() {
        return 0;
    }

    @Override
    public void autocolocar() {}
	
}
