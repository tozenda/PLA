package pla;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("unused")
public class Game {
	
	static Game game;	
	public static void main(String[] args) {
		game = new Game();
	}
	
	JFrame m_frame;
	GameView m_view;
	Timer m_timer;
	GameModel m_model;
	GameController m_controller;
	//JLabel m_text;
	int m_fps;
	String m_msg;
	long m_elapsed;
	long m_lastRepaint;
	long m_lastTick;
	int m_nTicks;
	
	public Game() {
		m_model = new GameModel(this);
	    m_controller = new GameController(this, m_model);
	    createWindow();
	    System.out.println("Fenetre cr�e");
	

	    // create the main window and the periodic timer
	    // to drive the overall clock of the simulation.
	    
	    createTimer();
	}
	
	private void createWindow() {
		
		// creation QUE de la fenetre
	    m_frame = new JFrame();
	    m_frame.setTitle("Mighty Retarded Robot");
	    m_frame.setSize(916, 585);
	    m_frame.setResizable(false);
	    // gestion de l'affichage affichage
	    m_view = new GameView(this, m_model, m_controller);
	    m_frame.add(m_view, BorderLayout.CENTER);
	    m_frame.setVisible(true);
	    
	    
	    //permet de fermer la fenetre et arréter l'appli
	    m_frame.addWindowListener(new WindowListener());
	  }
	
	  private void createTimer() {
	    int tick = 1; // milliseconds
	    m_timer = new Timer(tick, new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	        tick();
	      }
	    });
	    m_timer.start();
	  }
	  
	  static final int REPAINT_DELAY = (int) (1000.0 / 24.0);
	  
	  // affichage des ticks de raffraichissement + fps
	  private void tick() {
		    long now = System.currentTimeMillis();
		    m_elapsed += (now - m_lastTick);
		    m_lastTick = now;
		    m_nTicks++;
		    m_model.step(now);
		    m_controller.step(now);
		    long elapsed = now - m_lastRepaint;
		    if (elapsed > REPAINT_DELAY) {
		      double tick = (double) m_elapsed / (double) m_nTicks;
		      long tmp = (long) (tick * 10.0);
		      tick = tmp / 10.0;
		      m_elapsed = 0;
		      m_nTicks = 0;
		      String txt = "Tick=" + tick + "ms";
		      while (txt.length()<15)
		        txt += " ";
		      txt = txt + m_fps + " fps   ";
		      while (txt.length()<25)
		        txt += " ";
		      if (m_msg != null)
		        txt += m_msg;
		      //m_text.setText(txt);
		      //m_text.repaint();
		      m_view.repaint();
		      m_lastRepaint = now;
		    }
		    
		  }
	  
	  public void setFPS(int fps, String msg) {
		    m_fps = fps;
		    m_msg = msg;
	  }
}
