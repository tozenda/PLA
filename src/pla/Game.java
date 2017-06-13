package pla;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/*
 * TODO :
 * - Pause (break)
 * - Affichage inventaire
 * - Timer fin de periode choix Héros
 * - Modif des robots
 * - Information sur les étapes (où on en est, temps restant...)
 */

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
	int cmpt=0;
	int cmpt2=0;
	long debutPhaseAction;
	long DureePhaseAction = 10000;
	long tempsEcoulePhaseAction;
	public boolean tourDe1 = true;
	public boolean PhaseAction=false;
	
	public Game() {
		Sound.bgmusic.loop();
		m_model = new GameModel(this);
	    m_controller = new GameController(this, m_model);
	    createWindow();
	    System.out.println("Fenetre cr�e");
	    

	    // create the main window and the periodic timer
	    // to drive the overall clock of the simulation.
	    
	    createTimer();
	}
	public void returnFocus(){
		m_view.requestFocus();
	}
	
	private void createWindow() {
		
		// creation QUE de la fenetre
	    m_frame = new JFrame();
	    m_frame.setTitle("Mighty Retarded Robot");
	    m_frame.setSize(980, 590);
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
	  
	  public void compteurActionBegin(){
		  tempsEcoulePhaseAction = 0;
		  debutPhaseAction = System.currentTimeMillis();
	  }
	  
	  static final int REPAINT_DELAY = (int) (1000.0 / 24.0);
	  
	  private void majRobot(){
		  if(PhaseAction){
	    		for(Robots r : GameModel.robot_list){
	    			if(tourDe1){
	    				if(r.equipe==1){
	    					r.eval(r.a);
	    				}
	    			}
	    			else if(!tourDe1){
	    				if(r.equipe!=1){
	    					r.eval(r.a);
	    				}
	    			}
	    		}
	    	}
	  }
	  
	  // affichage des ticks de raffraichissement + fps
	  private void tick() {
		 // System.out.println(m_frame.getFocusOwner());
		    long now = System.currentTimeMillis();
		    m_elapsed += (now - m_lastTick);
		    tempsEcoulePhaseAction += (now - m_lastTick);
		    if((PhaseAction)&&(tempsEcoulePhaseAction>DureePhaseAction)){
		    	GameModel.Tour();
		    }
		    m_lastTick = now;
		    m_nTicks++;
		    m_model.step(now);
		    m_controller.step(now);
		    long elapsed = now - m_lastRepaint;
		    if (elapsed > REPAINT_DELAY) {
		    	majRobot();
		    	if(cmpt2 == 20){
		    		GameModel.map.popCompetence();
		    		cmpt2 = 0;
		    	}
		      double tick = (double) m_elapsed / (double) m_nTicks;
		      long tmp = (long) (tick * 10.0);
		      tick = tmp / 10.0;
		      m_elapsed = 0;
		      m_nTicks = 0;
		      m_view.repaint();
		      m_lastRepaint = now;
		      cmpt++;
		      cmpt2++;
		    }
		    
		  }
	  
	  public void setFPS(int fps, String msg) {
		    m_fps = fps;
		    m_msg = msg;
	  }
}
