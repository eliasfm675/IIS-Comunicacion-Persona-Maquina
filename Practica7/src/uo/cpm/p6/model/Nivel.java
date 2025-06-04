package uo.cpm.p6.model;

public enum Nivel {
	FACIL(10, 2, 0, 5, 1010), INTERMEDIO(8, 1, 1, 4, 815), DIFICIL( 6, 1, 2, 3, 610);

	private int numCasillas, numInvasores, numMeteoritos, numDisparos, numBorder;

	Nivel( int numCasillas, int numInvasores, int numMeteoritos, int numDisparos, int numBorder) {

		this.numCasillas = numCasillas;
		this.numInvasores = numInvasores;
		this.numMeteoritos = numMeteoritos;
		this.numDisparos = numDisparos;
		this.numBorder = numBorder;
	}

	public int getNumCasillas() {
		return numCasillas;
	}

	public int getNumInvasores() {
		return numInvasores;
	}

	public int getNumMeteoritos() {
		return numMeteoritos;
	}

	public int getNumDisparos() {
		return numDisparos;
	}
	public int getNumBorder() {
		return numBorder;
	}
}
