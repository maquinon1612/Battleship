package modelo.estrategia;

public class JAuto extends Jugador{

	Obstrategy est;
        
	public JAuto(Obstrategy estrategia , int TAM , int num){
		super(TAM , num);
		est = estrategia;
	}
	
	/*
	*Dispara a un jugador enemigo escogido aleatoriamente.
	*Segun la dificultad escogida atacara utilizando una de las tres estrategias.
	*/
	
	
	public void disparar(){
		int al=(int) (Math.random() * ((numenem-1) - 0)) + 0;
		if(est.getClass() == Estrategia3.class) {
			est.disparar(jenem[0].gettablero());
		}
		else {
			est.disparar(jenem[al].gettablero());
		}
	}

        
        public void autocolocar(){
            if(colocados()){
                for(int n = 0; n < 5; n++) {
                    int tam;
                    if(n > 1) {
                        tam = n+1;
                    }
                    else {
                       tam = n+2;
                    }
                    int fila = (int) (Math.random() * (TAM - 1));
                    int col = (int) (Math.random() * (TAM - 1));
                    int direccion = (int) (Math.random() + 1);	        
                    try{
                    colocar(tam,fila,col,direccion);
                    }
                    catch(IllegalArgumentException e){
                        n--;
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
	/*
	*Sirve para saber si un jugador es automatico o no.
	*/
	
	@Override
	public boolean ai() {
		return true;
	}
        
    public int getdif(){
        return est.getdif();
    }

    @Override
    public void disparar(int enem, int fila, int col) {}
	
}
