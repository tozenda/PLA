package pla;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;

public class Heros extends Perso{
	
    private int x;
    private int y;
    private Image image;
    //private List<Competence> inventaire = new ArrayList<Competence>();

    public Heros() {
        x = 0;
        y = 0;
        initHeros();
    }
    
    public Heros(int x, int y) {
        this.x = x;
        this.y = y;
        initHeros();
     }
    
    private void initHeros() {
        
        ImageIcon ii = new ImageIcon("../img/hero.png");
        image = ii.getImage();
        x = 40;
        y = 60;        
    }
    
    public void setPos(int x, int y){
    	this.x = x;
    	this.y = y;
    }


    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*public Image getImage() {
        return image;
    }*/

   	public String toString() {
		return "H";
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		
	}

	public void pickUp(Competences c) {
		List<Competence> l = c.getLc();
		for (Competence tmp : l){
			inventaire.add(tmp);
		}
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Competence> getInventaire() {
		return inventaire;
	}

	public void setInventaire(List<Competence> inventaire) {
		this.inventaire = inventaire;
	}

	public boolean isHeros() {
		return true;
	}

	public boolean isBase() {
		return false;
	}

	public boolean isRobot() {
		return false;
	}
}
