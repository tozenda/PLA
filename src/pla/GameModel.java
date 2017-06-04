package pla;

//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;

//classe permettant d'interagir entre l'affichage et notre structure de données
public class GameModel {

  Game m_game;

  GameModel(Game game) {
    m_game = game;
    Map map = new Map();
    Heros h = new Heros();
    h.setPos(1, 1);
  }
  
  long count=0;
  long sum = 0;

  private long op(long i) {
    return i + i*i;
  }
  private void overhead() {
    // *** WARNING *** WARNING *** WARNING *** WARNING  
    // long callbacks will kill your frame-per-second performance
    // the game will get sluggish...
    // avoid as much as possible.
    for (long i=0;i<count;i++) 
      sum += op(i);
  }
  /**
   * Simulation step.
   * @param 1ms
   */
  void step(long now) {
    overhead();
  }
}
