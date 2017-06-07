package pla;

import java.awt.Image;

public class Base extends Actifs{
	
	private int x;
    private int y;
    private Image image;
    private int pdv;
    
	public Base(int x, int y){
		this.x = x;
		this.y = y;
		pdv = 50;
	}
	
	public String toString() {
		return "B";
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

	public boolean isHeros() {
		return false;
	}

	public boolean isBase() {
		return true;
	}

	public boolean isRobot() {
		return false;
	}
}
