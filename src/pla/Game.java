package pla;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	// JLabel m_text;
	int m_fps;
	String m_msg;
	long m_elapsed;
	long m_lastRepaint;
	long m_lastTick = System.currentTimeMillis() - 4;
	int m_nTicks;
	int cmpt = 0;
	int cmpt2 = 0;
	long debutPhaseAction;
	long DureePhaseAction = 100000;
	long tempsEcoulePhaseAction;
	long DureePhaseChoix = 60000;
	long tempsEcoulePhaseChoix;
	public boolean tourDe1 = true;
	public boolean PhaseAction = false;
	public boolean pause = false;

	public int getState() {
		if (tourDe1 == true) {
			if (PhaseAction == false) {
				return 1;
			}
			return 2;
		} else {
			if (PhaseAction == false) {
				return 3;
			}
			return 4;
		}
	}

	public Game() {
//		Sound.bgmusic.loop();
		m_model = new GameModel(this);
		m_controller = new GameController(this, m_model);
		createWindow();
		System.out.println("Fenetre cr�e");

		// create the main window and the periodic timer
		// to drive the overall clock of the simulation.

		createTimer();
	}

	public void returnFocus() {
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

		// permet de fermer la fenetre et arréter l'appli
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

	public void compteurActionBegin() {
		tempsEcoulePhaseAction = 0;
		debutPhaseAction = System.currentTimeMillis();
	}

	static final int REPAINT_DELAY = (int) (1000.0 / 24.0);

	
	private void majRobot(){
		if(PhaseAction){
			if(!pause){
				System.out.println("m_nTicks : " + m_nTicks);
				Iterator<Robots> iter = GameModel.robot_list.iterator();
				while (iter.hasNext()) {
					Robots r = iter.next();
					if(tourDe1){
						if(r.equipe==1){
							r.eval(r.courant);
						}
					}
					else if(!tourDe1){
						if(r.equipe!=1){
							r.eval(r.courant);
						}
					}
				}
				GameModel.robot_list.removeAll(GameModel.toRemove);
				
			}
		}
	}

	// affichage des ticks de raffraichissement + fps
	private void tick() {
		// System.out.println(m_frame.getFocusOwner());
		long now = System.currentTimeMillis();
		m_elapsed += (now - timeElapsedBreak - m_lastTick);
		if (!pause) {
			if (PhaseAction) {
				tempsEcoulePhaseChoix = 0;
				tempsEcoulePhaseAction += (now - timeElapsedBreak - m_lastTick);
			} else {
				tempsEcoulePhaseAction = 0;
				tempsEcoulePhaseChoix += (now - timeElapsedBreak - m_lastTick);
			}
		}
		if ((PhaseAction) && (tempsEcoulePhaseAction > DureePhaseAction)) {
			GameModel.Tour();
		}
		if ((!(PhaseAction)) && (tempsEcoulePhaseChoix > DureePhaseChoix)) {
			GameModel.Tour();
		}
		m_lastTick = now;
		m_nTicks++;
		m_model.step(now);
		m_controller.step(now);
		long elapsed = (now - timeElapsedBreak) - m_lastRepaint;
		if (elapsed > 2*REPAINT_DELAY) {
			if(cmpt >= 5){
				if(m_model.partie()!=0){
					System.out.println("VICTOIRE DU JOUEUR "+m_model.partie());
					JOptionPane.showMessageDialog(null,"Victoire du joueur "+m_model.partie());
				}
				majRobot();
				cmpt = 0;
			}
			//majRobot();
			if ((cmpt2 == 20) && (!pause)) {
				if (PhaseAction) {
					GameModel.map.popCompetence();
				}
				cmpt2 = 0;
			}
			double tick = (double) m_elapsed / (double) m_nTicks;
			long tmp = (long) (tick * 10.0);
			tick = tmp / 10.0;
			m_elapsed = 0;
			m_nTicks = 0;
			m_view.repaint();
			m_lastRepaint = now;
			if (!pause) {
				cmpt2++;
				cmpt++;
			}
		}
	}

	long timeBeginBreak;
	long timeElapsedBreak;

	public void breakTimer() {
		timeBeginBreak = System.currentTimeMillis();
	}

	public void endBreak() {
		timeElapsedBreak = System.currentTimeMillis() - timeBeginBreak;
	}

	public void setFPS(int fps, String msg) {
		m_fps = fps;
		m_msg = msg;
	}
}
