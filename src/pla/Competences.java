package pla;

public class Competences extends Observables {
	
	private String c;
	
	public Competences(){
		setC("");
	}
	
	public Competences(String c){
		this.setC(c);
	}
	
	public String toString() {
		return "C";
	}

	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return false;
	}

	public boolean isCompetences() {
		return true;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
