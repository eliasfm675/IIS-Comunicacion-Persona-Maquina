package uo.cpm.modulo.model;

public class Juego {
	private int tries;
	private Tablero tablero;
	private Casilla casillaSeleccionada_1;
	private Casilla casillaSeleccionada_2;
	private boolean finJuego;
	public Juego() {
		setTries(5);
		setFinJuego(false);
		casillaSeleccionada_1 = null;
		casillaSeleccionada_2 = null;
		setTablero(new Tablero());
	}
	public int getTries() {
		return tries;
	}
	public void setTries(int tries) {
		this.tries = tries;
	}
	public Tablero getTablero() {
		return tablero;
	}
	private void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	public Casilla getCasillaSeleccionada_1() {
		return casillaSeleccionada_1;
	}
	public void setCasillaSeleccionada_1(Casilla casillaSeleccionada) {
		this.casillaSeleccionada_1 = casillaSeleccionada;
	}
	public Casilla getCasillaSeleccionada_2() {
		return casillaSeleccionada_2;
	}
	public void setCasillaSeleccionada_2(Casilla casillaSeleccionada) {
		this.casillaSeleccionada_2 = casillaSeleccionada;
	}
	private boolean isFinJuego() {
		return finJuego;
	}
	private void setFinJuego(boolean finJuego) {
		this.finJuego = finJuego;
	}

	public void choose(int i) {
	    if (casillaSeleccionada_1 == null) {
	        setCasillaSeleccionada_1(tablero.getTablero()[i]);
	    } else if (casillaSeleccionada_2 == null) {
	        setCasillaSeleccionada_2(tablero.getTablero()[i]);
	        boolean isMatch = choose_final();
	        if (isMatch) {
	            // Marco las casillas como emparejadas
	            casillaSeleccionada_1.setMatched(true);
	            casillaSeleccionada_2.setMatched(true);
	        }

	        tries--;
	    }
	    if (tries < 0) {
	        tries = 0;
	    }
	    finJuego = isGameFinished();
	}
	public boolean choose_final() {
		return getCasillaSeleccionada_1().getType().equals(getCasillaSeleccionada_2().getType());
	}
	public boolean isGameFinished() {
		return tries==0;
	}
	public String getImage(int i) {
		return tablero.getImage(i);
	}
	public String getReward() {
		switch(getTries()) {
		case 4: 
			return "/img/Reward-pizza.png";
		case 3:
			return "/img/Reward-entrante.png";
		case 2:
			return "/img/Reward-ensalada.png";
		case 1:
			return "/img/Reward-postre.png";
		case 0:
			return "/img/Reward-bebida.png";
		default:
			return "Error";
		}
	}
	
	 public int searchCasilla(Casilla c) {
		 Casilla[] t = getTablero().getTablero();
		 for(int i=0; i<t.length; i++) {
			 if(t[i].equals(c)) {
				 return i;
			 }
		 }
		 return -1;
	 }
}
