package pla;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameView extends JPanel {

  private static final long serialVersionUID = 1L;

  Game m_game;
  GameModel m_model;
  Color m_background = Color.RED;
  long m_last;
  int m_npaints;
  int m_fps;
  int tailleCase = 40;

  GameView(Game game, GameModel model, GameController ctr) {
    super(true);
    m_game = game;
    m_model = model;
    
    // permet de récupérer les événements clavier et souris
    addKeyListener(ctr);
    addMouseListener(ctr);
    addMouseMotionListener(ctr);
    // grab the focus on this JPanel, meaning keyboard events
    // are coming to our controller. Indeed, the focus controls
    // which part of the overall GUI receives the keyboard events.
    setFocusable(true);
    requestFocusInWindow();
    grabFocus();
    System.out.println("GameView cree");
  }
  
  // incrémente une variable à chaque raffraichissement d'écran pour calculer les fps
  private void computeFPS() {
    long now = System.currentTimeMillis();
    if (now - m_last > 1000L) {
      m_fps = m_npaints;
      m_last = now;
      m_npaints = 0;
    }
    m_game.setFPS(m_fps, "npaints="+m_npaints);
    // System.out.println("npaints="+m_npaints);
    m_npaints++;
  }


  //pour afficher des objets sur notre fenetre
  public void paintComponent(Graphics g) {
	  	System.out.println("Paint Component appel�");
	  	computeFPS();
    
	  	//Quadrillage de la map
  		g.setColor(Color.BLACK);
  		for(int i = 1; i < 21 ; i++){
  			g.drawLine(i*tailleCase, 0, i*tailleCase, 480);
  		}
  		for(int i = 1; i <= 12 ; i++){
  			g.drawLine(0, i*tailleCase, 800, i*tailleCase);
  		}
  		
  		//Base des 2 joueurs
  		g.setColor(Color.RED);
  		g.fillRect(0, 10*tailleCase, 2*tailleCase, 2*tailleCase);
  		g.setColor(Color.BLUE);
    	g.fillRect(18*tailleCase, 0, 2*tailleCase, 2*tailleCase);
    		
    	//Quelques obstacles
    	g.setColor(Color.BLACK);
   		g.fillRect(4*tailleCase, 3*tailleCase, 1*tailleCase, 3*tailleCase);
    	g.fillRect(5*tailleCase, 3*tailleCase, 2*tailleCase, 1*tailleCase);
    	g.fillRect(13*tailleCase, 8*tailleCase, 3*tailleCase, 1*tailleCase);
   		g.fillRect(15*tailleCase, 6*tailleCase, 1*tailleCase, 2*tailleCase);
    	g.fillRect(9*tailleCase, 5*tailleCase, 2*tailleCase, 2*tailleCase);
    		
  }
}
