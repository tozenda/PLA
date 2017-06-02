package pla;

public class Vide extends Observables {
<<<<<<< HEAD
    
	public static final Vide VIDE = new Vide();
=======
	
	public String ToString(){
		return "V";
	}
>>>>>>> c20557d4bfc2efcd5dc50a4fc649138eee1224e8

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
