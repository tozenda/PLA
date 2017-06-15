package pla;
import java.awt.Image;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

public class Heros extends Perso{
	
    private int x;
    private int y;
    private Image image;
    HashMap<Competence, Integer> inventaire = new HashMap<Competence,Integer>() ;
    int equipe;
    int pdv;
    int pointAction;
    int maxPointAction = 1000;
	public int coupCreationRobot = 20;
	public int coupDeplacementHeros = 1;

    public Heros() {
        x = 0;
        y = 0;
        pdv = 300;
        equipe = 1;
        pointAction = maxPointAction;
        inventaire.put(Competence.MoveRamasse, 10);
        initHeros();
    }
    
    
    public Heros(int x, int y, int equipe) {
        this.x = x;
        this.y = y;
        initHeros();
        pdv = 300;
        inventaire.put(Competence.MoveRamasse, 10);
        pointAction = maxPointAction;
      this.equipe = equipe;
     }
    
    public int getPointdeVie(){
    	return pdv;
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
			if(!inventaire.containsKey(tmp)){
				inventaire.put(tmp, 1);
			}
			else {
				inventaire.replace(tmp, (inventaire.get(tmp)+1));
				
			}
		}
		for (HashMap.Entry<Competence,Integer> e : inventaire.entrySet()){
			System.out.println(e.getKey().toString());
		}
		/*System.out.println("Inventaire : ");
		for(Competence cc : inventaire){
			System.out.println(cc.toString());
		}*/
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

	public HashMap<Competence,Integer> getInventaire() {
		return inventaire;
	}

	public void setInventaire(HashMap<Competence,Integer> inventaire) {
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
			HashMap<Competence, Integer> listC = this.inventaire;
			Competences lComp = new Competences();
			Iterator<Entry<Competence, Integer>> it = listC.entrySet().iterator();
			while (it.hasNext())
			{
			   Entry<Competence, Integer> item = it.next();
			   lComp.addCompetence(item.getKey());
			   it.remove();
			}

			Case c = new Case(x, y, lComp);
			map.editCase(c);
			Case h=null;
			if(equipe==1){
				int i = 21;
				Case tmp = map.getCase(3, i);
				while(!tmp.getContenu().isVide()){
					i--;
					tmp = map.getCase(3, i);
				}
				h = new Case(3, i,this);
			}
			else{
				int i = 2;
				Case tmp = map.getCase(36, i);
				while(!tmp.getContenu().isVide()){
					i++;
					tmp = map.getCase(36, i);
				}
				h = new Case(36, i,this);
			}
			pdv = 300;
			this.inventaire.put(0, Competence.MoveRamasse);
			this.x = h.getX();
			this.y = h.getY();
		}
	}


	@Override
	public int getPic() {
		return 26;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updatePointAction(int value){ //La valeur en positif/negatif à ajouter aux pda
		pointAction += value; 
		Game.game.m_view.setPointAction(true);
	}
}
