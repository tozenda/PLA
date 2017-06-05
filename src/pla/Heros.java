package pla;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Heros extends Perso{
	
    private int x;
    private int y;
    private Image image;

    public Heros() {
        
       // initHeros();
    }
    
    public Heros(int x, int y) {
        this.x = x;
        this.y = y;
        // initHeros();
     }
    
    /*private void initHeros() {
        
        ImageIcon ii = new ImageIcon("hero.png");
        image = ii.getImage();
        x = 40;
        y = 60;        
    }*/
    
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

	@Override
	public void ramasser() {
		// TODO Auto-generated method stub
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
}
