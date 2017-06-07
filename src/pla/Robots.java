package pla;

public class Robots extends Perso{
	
	int i,j;
	
	public String toString() {
		return "R";
	}

	public void move(Case c) {
		Map map = Game.game.m_model.map; 
	}

	@Override
	public void attack() {
	}

	@Override
	public void defend() {
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

	public void pickUp(Competences c) {
		
	}

	public boolean isHeros() {
		return false;
	}

	public boolean isBase() {
		return false;
	}

	public boolean isRobot() {
		return true;
	}
}
