package pla;

public class Vide extends Observables {

    
	//public static final Vide VIDE = new Vide();
	
	public Vide(){
		
	}
	
	public String toString() {
		return "Vide";
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
	public int getPic() {
		return -1;
	}

	@Override
	public String getDescription() {
		return "<html><font color='rgb(213, 178, 94)'>Emptiness</font><br/>Nah, there's nothing<br/> here <br/> Drugs I guess?</html>";
		// TODO Auto-generated method stub
	}
}
