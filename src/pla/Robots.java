package pla;

public class Robots extends Perso{
	
	public String toString() {
		return "R";
	}

	@Override
	public void move(int dx, int dy) {
	}

	@Override
	public void attack() {
	}

	@Override
	public void defend() {
	}

	@Override
	public void ramasser() {
		
	}

	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return false;
	}

	public boolean isCompetences() {
		return false;
	}
}
