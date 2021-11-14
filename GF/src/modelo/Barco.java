package modelo;


public class Barco {
	//Atributos de la clase barco
	protected int vida;
	protected int numCasillas;
	protected int id;
	protected Casilla[] casilla;

	/*
	Constructor con parametros vida del barco, el dentificador del mismo y la casilla
	*/
	public Barco(int vida, int id, Casilla[] casilla) { 
		this.vida = vida;
		this.numCasillas = casilla.length;
		this.id = id;
		this.casilla = casilla;
	}
	
	///Función encargada de restar la vida de cada casilla de la que está compuesta un barco
	public void restarvida(int fila, int col) {
                int i = 0;
		boolean b = false;
		while(i<numCasillas && !b){//Mientras qque el contador sea menor que el número de casillas
			//Si la fila y la casilla coinciden con las que se pasan por parámetro
			if(casilla[i].getFila() == fila&&casilla[i].getCol()==col) {
				this.vida--;//Restamos vida
				if(this.vida==0) {//Si la vida de esa casilla ya es 0
					for (int j=0; j < numCasillas; j++) casilla[j].deletefinal();//Pone el barco en estado HUNDIDO
				}
				else casilla[i].delete();//Pone la casilla en estado TOCADO
			}	
			 i++;
		}
	}
	
	//Get que devuelve el id del barco
	public int getId() {
		return id;
	}
	
	//Modifica el id del barco
	public void setId(int id) {
		this.id = id;
	}
	
	//Devuelve la vida del barco
	public int getVida() {
		return vida;
	}

	//Modifica la vida del barco
	public void setVida(int vida) {
		this.vida = vida;
	}

	//Devuelve la casilla correspondiente a la posición del array
	public Casilla getCasilla(int i) {
		return casilla[i];
	}

	//Modifica la casilla
	public void setCasilla(Casilla[] casilla) {
		this.casilla = casilla;
	}
	
	//Devuelve el un string con los datos del barco (id, numero total de casillas, con sus correspondientes posiciones y estados)
	public String toSave(){
		String string = ";"+id + numCasillas;
		for(Casilla c : casilla) {
			string = string + "_" + c.getestado().toString() + "," + c.getFila() + c.getCol();
		}
		
		return string;
	}

	
	public static Barco parse(String line) {
            
            int id = Character.getNumericValue(line.charAt(0));
            int num = Character.getNumericValue(line.charAt(1));
            int vida = 0;
            
            String[] naves = line.split("_");
            Casilla[] c = new Casilla[num];
            
            for(int i = 0 ; i < num ; i++){
            	if(naves.length != num + 1) throw new IllegalArgumentException("Archivo da�ado o incorrecto");
                c[i] = Casilla.parse(naves[i+1]);
                if(c[i] == null) throw new IllegalArgumentException("Archivo da�ado o incorrecto");
                if(c[i].getestado() == EstadoCasilla.BARCO){
                    vida++;
                }
            }
            return new Barco(vida, id, c);
	}
}
