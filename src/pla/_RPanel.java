package pla;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class _RPanel extends JPanel {
	String Thomas="/home/tozenda/COURS/RICM3/S6/PLA/PLA/Resources/";
	String Najwa ="Resources/";
	String Anouar = "Resources/";
	String Jo = "/home/ferreira/Bureau/POO/PLA/Resources/";
	String Paul= "home/doublean/git/PLA/Resources/";
	String Shoo=null;
	String Path = ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		BufferedImage iBande;
		try {
			iBande = ImageIO.read(new File(Path+"Bande.png"));
			g.drawImage(iBande,0,0,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
