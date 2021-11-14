package modelo;


/*Clase que sirve para definir en que estado se encuentra cada casilla.*/

public enum EstadoCasilla {
	TOCADO , HUNDIDO , AGUA , BARCO , NULO;
	
	
	public String toString() {
	
		if(this == TOCADO) {
			return "*";
		}
		else if(this == HUNDIDO) {
			return "X";
		}
		else if(this == AGUA) {
			return ".";
		}
		else if(this == BARCO) {
			return "O";
		}
		else if(this == NULO) {
			return " ";
		}
		else {
			return " ";
		}
	}
	
	public static EstadoCasilla parse(String line){
		for (EstadoCasilla estado : EstadoCasilla.values()) {
                    String c = estado.toString();
			if (c.equalsIgnoreCase(line)){
				return estado;
			}
                }
                return null;
	}
	
}

