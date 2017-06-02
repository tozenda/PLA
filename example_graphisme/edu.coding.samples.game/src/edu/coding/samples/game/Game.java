package edu.coding.samples.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Game {

  static String license = "xd";
  
  static Game game;
  public static void main(String[] args) {
    
    game = new Game();
    
    // notice that the main thread will exit here,
    // but not your program... hence the hooking
    // of the window events to System.exit(0) when
    // the window is closed. See class WindowListener.
    
    /*
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     * If you do something here, on this "main" thread,
     * you will have parallelism and thus race conditions.
     * 
     *           ONLY FOR ADVANCED DEVELOPERS
     *           
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     */
  }

  /*
   * We want to target 24 frame per seconds (fps),
   * which is the following period in milliseconds
   *   period = (1000.0 / 24.0)
   */
  static final int REPAINT_DELAY = (int) (1000.0 / 24.0);

  JFrame m_frame;
  GameView m_view;
  Timer m_timer;
  GameModel m_model;
  GameController m_controller;
  JLabel m_text;
  int m_fps;
  String m_msg;
  long m_elapsed;
  long m_lastRepaint;
  long m_lastTick;
  int m_nTicks;

  public Game() {
    // construct the game elements: model, controller, and view.
    m_model = new GameModel(this);
    m_controller = new GameController(this, m_model);
    m_view = new GameView(this, m_model, m_controller);

    // create the main window and the periodic timer
    // to drive the overall clock of the simulation.
    createWindow();
    createTimer();
  }

  private void createWindow() {
    m_frame = new JFrame();
    m_frame.setLayout(new BorderLayout());
    m_frame.setTitle("Game");

    m_frame.add(m_view, BorderLayout.CENTER);

    m_text = new JLabel();
    m_text.setText("Starting up...");
    m_frame.add(m_text, BorderLayout.NORTH);

    m_frame.setSize(800, 600);
    m_frame.doLayout();
    m_frame.setVisible(true);

    // hook window events so that we exit the Java Platform
    // when the window is closed by the end user.
    m_frame.addWindowListener(new WindowListener());
  }

  /* 
   * Let's create a timer, it is the heart of the simulation,
   * ticking periodically so that we can simulate the passing of time.
   */
  private void createTimer() {
    int tick = 1; // milliseconds
    m_timer = new Timer(tick, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        tick();
      }
    });
    m_timer.start();
  }

  /*
   * This is the period tick callback.-- -- 
   */
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
      m_text.setText(txt);
      m_text.repaint();
      m_view.repaint();
      m_lastRepaint = now;
    }
  }

  public void setFPS(int fps, String msg) {
    m_fps = fps;
    m_msg = msg;
  }

}
