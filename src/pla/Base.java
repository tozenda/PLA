package pla;

import java.awt.Image;

public class Base extends Actifs{
	
	private int x;
    private int y;
    private Image image;
    private int pdv;
    int equipe;
    
	public Base(int x, int y, int equipe){
		this.x = x;
		this.y = y;
		pdv = 50;
		this.equipe = equipe;
	}
	
	public String toString() {
		return "B";
	}
public int getEquipe(){
	return this.equipe;
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

	@Override
	public String getPic() {
		if (getEquipe()==1){
			return "Base1.png";
		}
		return "Base2.png";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
