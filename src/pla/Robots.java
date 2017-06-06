package pla;

public class Robots extends Perso{
	
	int i,j;
	
	public String toString() {
		return "R";
	}

	public void move(Case c) {
		
		Game.game.m_model.map.editCase(c);
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
		// TODO Auto-generated method stub
		
	}
}
