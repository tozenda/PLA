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
    
    /*private void initHeros() {
        
        ImageIcon ii = new ImageIcon("hero.png");
        image = ii.getImage();
        x = 40;
        y = 60;        
    }*/


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

    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
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


	@Override
	public boolean isVide() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isObstacles() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCompetences() {
		// TODO Auto-generated method stub
		return false;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
