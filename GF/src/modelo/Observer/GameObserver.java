package modelo.Observer;

public interface GameObserver {
	public void onInicializargame(int numjugador);
	void onColocar(String memento);
    void onEndTurn(int numjugador , String memento);
    void onCargar(int numjugador , String memento);
    void onEnd(int numjugador);
}
