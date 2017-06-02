package pla;

public class Vide extends Observables {

    
	public static final Vide VIDE = new Vide();
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "V";
	}
	@Override
	public boolean isVide() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isObstacles() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCompetences() {
		// TODO Auto-generated method stub
		return false;
	}
}
