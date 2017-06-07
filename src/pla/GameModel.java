package pla;

//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;

//classe permettant d'interagir entre l'affichage et notre structure de données
public class GameModel {

  Game m_game;
  Map map;
  Heros heros1;

  GameModel(Game game) {
    m_game = game;
    map = new Map();
    heros1 = new Heros(0,0);
	Case c = new Case(0,0,heros1);
	map.editCase(c);
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

  void heroMove(char mvt) {
	  int currentX = heros1.getX();
	  int currentY = heros1.getY();
	  int dx = currentX;
	  int dy = currentY;
	  System.out.println(mvt);
	  if(mvt == 'z'){
		  if(currentY>=1){
			  dy = (currentY-1)%20;  
		  }
	  }
	  else if(mvt == 'q'){
		  if(currentX>=1){
			  dx = (currentX -1)%12;
		  }
	  }
	  else if(mvt == 's'){
		  if(currentY<map.getHeight()-1){
			  dy = (currentY+1)%20;  
		  }
	  }
	  else if(mvt == 'd'){
		  if(currentX<map.getWidth()-1){
			  dx = (currentX +1)%12;
		  }
	  }
	  System.out.println("all ok");
	  if(map.getCase(dx, dy).getContenu().isVide()){
		  System.out.println("vide");
		  heros1.move(dx-currentX, dy-currentY);
		  Case v = new Case(currentX,currentY,new Vide());
		  Case h = new Case(dx,dy,heros1);
		  map.editCase(v);
		  map.editCase(h);
		  System.out.println("Je déplace en ("+dx+";"+dy+")");
	  }
	  else if(map.getCase(dx, dy).getContenu().isCompetences()){
		  System.out.println("else");
		  heros1.pickUp((Competences) map.getCase(dx, dy).getContenu());
		  heros1.move(dx-currentX, dy-currentY);
		  Case v = new Case(currentX,currentY,new Vide());
		  Case h = new Case(dx,dy,heros1);
		  map.editCase(v);
		  map.editCase(h);
		// GameView.Please();
	  }
  }
  public static void Tour(){
	  System.out.println("Tour");
  }
  public static void breakk(){
	  System.out.println("Break");
  }
  public static void Create_Robot(){
	  System.out.println("Create Robot");
  }
  
}
