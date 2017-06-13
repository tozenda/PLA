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

	public boolean isHeros() {
		return false;
	}

	public boolean isBase() {
		return false;
	}

	public boolean isRobot() {
		return false;
	}

	@Override
	public String getPic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
