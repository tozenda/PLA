package pla;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;

public class Heros extends Perso{
	
    private int x;
    private int y;
    private Image image;
    List<Competence> inventaire = new ArrayList<Competence>();
    int equipe;
    int pdv;
    int PointAction;

    public Heros() {
        x = 0;
        y = 0;
        pdv = 300;
        equipe = 1;
        PointAction = 20;
        initHeros();
    }
    
    public Heros(int x, int y, int equipe) {
        this.x = x;
        this.y = y;
        initHeros();
        pdv = 300;
        PointAction = 20;
      this.equipe = equipe;
     }
    
    private void initHeros() {
        
        ImageIcon ii = new ImageIcon("../img/hero.png");
        image = ii.getImage();
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

	public int attack() {
		return 1;
	}

	public double defend() {
		return 0.0;
	}

	public void pickUp(Competences c) {
		System.out.println("Oh, j'ai qqch à ramasser !");
		List<Competence> l = c.getLc();
		for (Competence tmp : l){
			inventaire.add(tmp);
		}
		System.out.println("Inventaire : ");
		for(Competence cc : inventaire){
			System.out.println(cc.toString());
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

	public void destructionHeros() {
		if(pdv<=0){
			Map map = GameModel.map;
			List<Competence> listC = this.inventaire;
			Competences lComp = new Competences();
			for(int i = 0; i < listC.size(); i++){
				lComp.addCompetence(listC.get(0));
				listC.remove(0);
		    }
			Case c = new Case(x, y, lComp);
			map.editCase(c);
			Case h=null;
			if(equipe==1){
				int i = 23;
				Case tmp = map.getCase(3, i);
				while(!tmp.getContenu().isVide()){
					h = new Case(3, i,this);
					i--;
					tmp = map.getCase(3, i);
				}
			}
			else{
				int i = 0;
				Case tmp = map.getCase(37, i);
				while(!tmp.getContenu().isVide()){
					h = new Case(37, i,this);
					i++;
					tmp = map.getCase(37, i);
				}
			}
			pdv = 300;
			map.editCase(h);
		}
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
