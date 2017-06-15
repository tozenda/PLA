package pla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class MiniMap extends JLabel {
	
	//La minimap est un JLabel modifié
	private static final long serialVersionUID = 1L;
	Map map = new Map();

	public MiniMap(String s) {
		super(s);
	}

	public MiniMap() {
		super();
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		int tailleCase =3;
		Observables obs;
		/*On parcourt la map.
		 * à chaque observable croisé on dessine un carré de telle ou telle couleur 
		 * d'une taille définie dans la variable tailleCase.
		 */
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 40; j++) {
				obs = map.getCase(j, i).getContenu();
				if (obs.isBase()) {
					g.setColor(Color.ORANGE);
				} else if (obs.isCompetences()) {
					g.setColor(Color.GRAY);
				} else if (obs.isHeros()) {
					Heros h = (Heros) obs;
					if(h.getEquipe()==1){
						g.setColor(Color.BLUE.darker());
					}
					else{
						g.setColor(Color.RED);
					}
				} else if (obs.isRobot()) {
					Robots r = (Robots) obs;
					if(r.equipe==1){
						g.setColor(Color.BLUE);
					}
					else{
						g.setColor(Color.RED.brighter());
					}
				} else if (obs.isVide()) {
					g.setColor(Color.GREEN.darker().darker());
				} else if (obs.isObstacles()) {
					g.setColor(Color.DARK_GRAY);
				}
				g.fillRect((j ) * tailleCase, (i) * tailleCase, tailleCase, tailleCase);
			}
		}

	}

}
