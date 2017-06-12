package pla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiniMap extends JLabel {
	/**
	 * 
	 */
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
		int tailleCase = 4;

		// Vertical
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 40; j++) {
				if (map.getCase(j, i).getContenu().isBase()) {
					g.setColor(Color.ORANGE);
				} else if (map.getCase(j, i).getContenu().isCompetences()) {
					g.setColor(Color.GRAY);
				} else if (map.getCase(j, i).getContenu().isHeros()) {
					g.setColor(Color.BLUE);
				} else if (map.getCase(j, i).getContenu().isRobot()) {
					g.setColor(Color.cyan);
				} else if (map.getCase(j, i).getContenu().isVide()) {
					g.setColor(Color.GREEN.darker().darker());
				} else if (map.getCase(j, i).getContenu().isObstacles()) {
					g.setColor(Color.DARK_GRAY);
				}
				g.fillRect((j % 42) * tailleCase, (i % 42) * tailleCase, tailleCase, tailleCase);
			}
		}

	}

}
