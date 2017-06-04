package pla;

public class Vide extends Observables {

    
	//public static final Vide VIDE = new Vide();
	
	public Vide(){
		
	}
	
	public String toString() {
		return "V";
	}
	public boolean isVide() {
		return true;
	}
	public boolean isObstacles() {
		return false;
	}
	public boolean isCompetences() {
		return false;
	}
}
