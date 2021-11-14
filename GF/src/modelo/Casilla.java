package modelo;

public class Casilla {
	int fila;
	int colum;
	EstadoCasilla estado;
	
	public Casilla(){  // crea una casilla
		fila = -1;
		colum = -1;
	}
	
	public Casilla(int f , int c , EstadoCasilla est){ // crea la clase casilla en una f y c y con un estado.
		fila = f;
		colum = c;
		estado = est;
	}
	
	public int getFila() { // devuelve la fila de la casilla
		return fila;
	}
	
	public EstadoCasilla getestado() {  // devuelve el estado de la casilla
		return estado;
	}
	
	void setestado(EstadoCasilla estado) { // inicializa el estado de una casilla
		this.estado = estado;
	}
	
	public int getCol() { // devuelve la columna de la casilla
		return colum;
	}
	
	void delete() { // cambia el estado de la casilla a TOCADO 
		estado = EstadoCasilla.TOCADO;
	}
	
	void deletefinal() { // cambia el estado de la casilla a HUNDIDO
		estado = EstadoCasilla.HUNDIDO;
	}
        
        static Casilla parse(String casilla) {
            String[] lines = casilla.split(",");
            Casilla c = new Casilla();
            c.setestado(EstadoCasilla.parse(lines[0]));
            c.fila = Character.getNumericValue(lines[1].charAt(0));
            c.colum = Character.getNumericValue(lines[1].charAt(1));
            return c;
        }
}

