package pla;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameController implements MouseListener, MouseMotionListener, KeyListener {

  static final boolean echo = true;
  Game m_game;
  GameModel m_model;
  static char haut = 'z';
  static char bas = 's';
  static char gauche = 'q';
  static char droite = 'd';

  GameController(Game game, GameModel model) {
    m_game = game;
    m_model = model;
  }

  /**
   * Simulation step. Warning: the model has already executed its step.
   * 
   * @param 1ms
   *          
   */
  void step(long now) {
  }

//  private static final long NOPS=1000000L;
  
  public void keyTyped(KeyEvent e) {
    if (echo)
      System.out.println("KeyTyped: " + e);
    if ((e.getKeyChar() == haut)||(e.getKeyChar() == bas)||(e.getKeyChar() == gauche)||(e.getKeyChar()== droite)) {
    	m_model.heroMove(e.getKeyChar());
    }
  }

  
  public void keyPressed(KeyEvent e) {
    if (echo)
      System.out.println("KeyPressed: " + e);
  }

  
  public void keyReleased(KeyEvent e) {
    if (echo)
      System.out.println("KeyReleased: " + e);
  }

  
  public void mouseClicked(MouseEvent e) {
    if (echo)
      System.out.println("MouseClicked: " + e);
  }

  
  public void mousePressed(MouseEvent e) {
    if (echo)
      System.out.println("MousePressed: " + e);
  }

  
  public void mouseReleased(MouseEvent e) {
    if (echo)
      System.out.println("MouseReleasedt: " + e);
  }

  
  public void mouseEntered(MouseEvent e) {
    if (echo)
      System.out.println("MouseEntered: " + e);
  }

  
  public void mouseExited(MouseEvent e) {
    if (echo)
      System.out.println("MouseEvent: " + e);
  }

  
  public void mouseDragged(MouseEvent e) {
    if (echo)
      System.out.println("MouseDragged: " + e);
  }


  public void mouseMoved(MouseEvent e) {
    if (echo)
      System.out.println("MouseMoved: " + e);
  }

}
