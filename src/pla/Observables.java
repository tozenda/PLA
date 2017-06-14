package pla;

public abstract class Observables {
	public abstract String toString();	
	public abstract boolean isVide();
	public abstract boolean isObstacles();
	public abstract boolean isCompetences();
	public abstract boolean isHeros();
	public abstract boolean isBase();
	public abstract boolean isRobot();
	public int getPointdeVie(){
		return 0;
	}
	public abstract int getPic();
	public abstract String getDescription();
}
