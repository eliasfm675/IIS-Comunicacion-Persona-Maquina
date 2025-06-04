package uo.cpm.modulo.model;

public class Casilla {
	public enum Tipo{CORAZON, PICAS, TREBOL, DIAMANTE, OROS, COPAS, BASTOS, ESPADAS}
	private String image;
	private Tipo type;
	private boolean matched;
	public Casilla(String image, Tipo type) {
		this.image = image;
		this.type = type;
		setMatched(false);
	}
	public String getImage() {
		return image;
	}
	private void setImage(String image) {
		this.image = image;
	}
	public Tipo getType() {
		return type;
	}
	private void setType(Tipo type) {
		this.type = type;
	}
	 public boolean isMatched() {
	        return matched;
	    }

	 public void setMatched(boolean matched) {
	       this.matched = matched;
	  }
	 @Override
	 public String toString() {
		 switch(getType()) {
		 case CORAZON: return "CO";
		 case PICAS: return "PI";
		 case TREBOL: return "TR";
		 case DIAMANTE: return "DI";
		 case OROS: return "OR";
		 case COPAS: return "CP";
		 case BASTOS: return "BA";
		 case ESPADAS: return "ES";
		 default: return "ERROR";
		 }
	 }
}
