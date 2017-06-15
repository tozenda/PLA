package pla;

public class Obstacles extends Observables{

	public String toString() {
		return "O";
	}

	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return true;
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
		return 18;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
