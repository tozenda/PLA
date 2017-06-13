package pla;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class GameController implements MouseListener, MouseMotionListener, KeyListener {

	static final boolean echo = false;
	Game m_game;
	GameModel m_model;
	static int haut = 'z';
	static int bas = 's';
	static int gauche = 'q';
	static int droite = 'd';
	static int up = KeyEvent.VK_UP;
	static int down = KeyEvent.VK_DOWN;
	static int left = KeyEvent.VK_LEFT;
	static int right = KeyEvent.VK_RIGHT;
	

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

	// private static final long NOPS=1000000L;

	public void keyTyped(KeyEvent e) {
		if (echo)
			System.out.println("KeyTyped: " + e);
		int memGetkeychar = e.getKeyChar();
		if ((memGetkeychar == haut) || (memGetkeychar == bas) || (memGetkeychar == gauche)
				|| (memGetkeychar == droite)) {
			m_model.heroMove(memGetkeychar);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (echo)
			System.out.println("KeyPressed: " + e);
	}

	public void keyReleased(KeyEvent e) {
		if (echo)
			System.out.println("KeyReleased: " + e);
		int memGetkeychar = e.getKeyCode();
		if ((memGetkeychar == up) || (memGetkeychar == down) || (memGetkeychar == left) || (memGetkeychar == right)) {
			System.out.println("KeyReleased: " + e.getKeyChar());
			m_model.heroMove(memGetkeychar);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (echo)
			System.out.println("MouseClicked: " + e);
		System.out.println("X is : " + e.getX() / 40 + " Y is : " + e.getY() / 40);
		if (SwingUtilities.isRightMouseButton(e)) {
			m_model.setFactXY();
			int Factx = m_model.getFactx();
			int Facty = m_model.getFacty();
			System.out.println("First" + ((Factx-1)* 20 +  (e.getX()/ 40)) + "Second :" + (((Facty-1)*12)+(e.getY() / 40)));
			m_model.Info(((Factx-1)* 20 +  (e.getX()/ 40)), (((Facty-1)*12)+(e.getY() / 40)));
		}
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
