package modelo;



public class Tablero {
	
	int LARGO;
	int ANCHO;
	Casilla tablero[][];
	Barco naves[];
	int numbarcos , numbarcoscolocados;
	
	
	public Tablero (int largo , int ancho){
		LARGO = largo;
		ANCHO = ancho;
		tablero = new Casilla[largo][ancho];
		naves = new Barco[5];
		numbarcoscolocados = numbarcos = 0;
	}
	
	public void aniadirbarco(Barco b) {
            try{
                for( int i=0; i< b.numCasillas; i++) {
                    if(tablero[b.getCasilla(i).getFila()][b.getCasilla(i).getCol()].getestado() == EstadoCasilla.BARCO){
			throw new IllegalArgumentException("No puedes colocar el barco donde existe otro barco");
                    }
		}
		for( int i=0; i< b.numCasillas; i++) {
                    tablero[b.getCasilla(i).getFila()][b.getCasilla(i).getCol()] = b.getCasilla(i);
		}
                naves[numbarcos] = b;
		numbarcos ++;
		numbarcoscolocados++;
            }
            catch(ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException("No puedes colocar el barco fuera del tablero");
            }
	}

	public void iniciaTablero() {
		for (int i =0; i<LARGO; i++) {
			for (int j =0; j<LARGO; j++) {
				tablero[i][j] = new Casilla(i ,j , EstadoCasilla.NULO);
			}
		}
	}
	
	public void serdisparado(int x , int y) throws IllegalArgumentException{
		boolean vivo = false;
		if(x < LARGO && y < ANCHO) {
			if(tablero[x][y].getestado() == EstadoCasilla.BARCO) {
				for(int i = 0 ; i < numbarcoscolocados ; i++) {
					if(naves[i].getVida() != 0) {
						vivo = true;
					}
					naves[i].restarvida(x, y);
					if(naves[i].getVida() == 0 && vivo) {
						numbarcos--;
					}
					vivo = false;
	                        }
			}
	                else if(tablero[x][y].getestado() == EstadoCasilla.NULO) {
	                    tablero[x][y].setestado(EstadoCasilla.AGUA);
	                }
	                else {
				throw new IllegalArgumentException("Casilla ya disparada");}
		}
		else {
			throw new IllegalArgumentException("Fuera de tablero");
		}
	}
	
	public int getnumbarcos() {
		return numbarcos;
	}

	public String positionToString(int fila, int col) {
		return tablero[fila][col].getestado().toString();
	}
	
	public String toSave(){
		String string = "";
                
		string = string + "B" + numbarcos;
		for(int i = 0 ; i < numbarcoscolocados ; i++) {
			string = string + naves[i].toSave();
		}
		
		string = string + "T" ;
		
		for(int i = 0 ; i < ANCHO ; i++) {
			for(int j = 0 ; j < LARGO ; j++) {
				if(tablero[i][j].getestado() == EstadoCasilla.NULO || tablero[i][j].getestado() == EstadoCasilla.BARCO) {}
				else string = string +  tablero[i][j].getestado().toString() + "," + i + j + "_";
			}
		}
		
		return string;
	}
	
	
	public Casilla getCasilla(int i,int j) {
		return tablero[i][j];
	}
	public static Tablero parse(String line) {
		String[] separador = line. split ("T");
		String[] naves = separador[0]. split (";");
                Tablero t = new Tablero(Game.TAM ,Game.TAM);
                t.iniciaTablero();
                int num = Character.getNumericValue(line.charAt(1));
                if(num != naves.length - 1) throw new IllegalArgumentException("archivo incorrecto");
                for(int i = 0 ; i < num ;i++){
                    t.aniadirbarco(Barco.parse(naves[i+1]));
                }
                if(separador.length != 1){
                    String[] casillas = separador[1].split("_");
                    for(int i = 0 ; i < casillas.length ; i++){
                        t.setCasilla(Casilla.parse(casillas[i]));
                    }
                }
                return t;
        }       
        
        
        public void actualizartablero(Tablero t) throws IllegalArgumentException{
		for(int i = 0 ; i < t.LARGO ; i++){
                    for(int j = 0 ; j < t.ANCHO ; j++){
                        if(t.getCasilla(i, j).getestado() != EstadoCasilla.BARCO){
                            tablero[i][j] = t.getCasilla(i, j);
                        }
                    }
                }	
	}

    private void setCasilla(Casilla casilla) {
        tablero[casilla.getFila()][casilla.getCol()] = casilla;
    }
}
