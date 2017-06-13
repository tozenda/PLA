package pla;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;

//classe permettant d'interagir entre l'affichage et notre structure de données
public class GameModel {

	static Game m_game;
	static Map map;
	Heros heros1;
	Heros heros2;
	//List <Robots> robot = new LinkedList<Robots>();
	Robots robot;
	Case currentCase = null;
	public static int situation = 1;
	/* 1 = Choix1 
	 * 2 = Action1
	 * 3 = Choix2
	 * 4 = Action2
	 */


	GameModel(Game game) {
		m_game = game;
		map = new Map();
		heros1 = new Heros(2,23,1);
		heros2 = new Heros(37,2,2);
		Case ch1 = new Case(heros1.getX(), heros1.getY(), heros1);
		Case ch2 = new Case(heros2.getX(), heros2.getY(), heros2);
		map.editCase(ch1);
		map.editCase(ch2);
		robot = new Robots(4, 4, 1); // robot en 3,3 de l equipe 1
		Case r = new Case(robot.i, robot.j, robot);
		map.editCase(r);
		robot.editDest(29,18);
	}
	private boolean Labelmodified = false;

	public boolean getLabelmodified(){
		return Labelmodified;
	}
	public void setLabelmodified(boolean a) {
		Labelmodified = a;
	}
	
	void Info(int x, int y) {
		System.out.println("info called");
		currentCase = map.getCase(x, y);
		setLabelmodified(true);
	}

	Case getCurrentCase() {
		return currentCase;
	}

	long count = 0;
	long sum = 0;

	private long op(long i) {
		return i + i * i;
	}

	private void overhead() {
		// *** WARNING *** WARNING *** WARNING *** WARNING
		// long callbacks will kill your frame-per-second performance
		// the game will get sluggish...
		// avoid as much as possible.
		for (long i = 0; i < count; i++)
			sum += op(i);
	}

	/**
	 * Simulation step.
	 * 
	 * @param 1ms
	 */
	void step(long now) {
		overhead();
	}

	void heroMove(int mvt){ 
		int currentX = 0;
		int currentY = 0;
		int dx = 0;
		int dy = 0;
		if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
			currentX = heros1.getX();
			currentY = heros1.getY();
			dx = currentX;
			dy = currentY;
		}
		else if ((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
			currentX = heros2.getX();
			currentY = heros2.getY();
			dx = currentX;
			dy = currentY;
		}
		
		//System.out.println(mvt);
		int currentLocation = map.getHeroLocation(currentX, currentY);
		//System.out.println("Map actuelle : "+ currentLocation);
		// remplacer par switch
		if (mvt == KeyEvent.VK_LEFT && map.getLocation() != 3) {
			map.decLocation();
		}
		if (mvt == KeyEvent.VK_UP&&map.getLocation()!=2) {
			map.decLocation();
			map.decLocation();
		}
		if (mvt == KeyEvent.VK_DOWN&&map.getLocation()!=4&&map.getLocation()!=3) {
			map.incLocation();
			map.incLocation();
		}
		if (mvt == KeyEvent.VK_RIGHT && map.getLocation() != 4 && map.getLocation() != 2) {
			map.incLocation();
		}
		if(!Game.game.PhaseAction){
			if (mvt == 'z') {
				map.setLocation(map.getHeroLocation(currentX, currentY));

				if (currentY % map.getHeight() == 0 && currentY != 0) {
					map.decLocation();
					map.decLocation();
					dy = (currentY - 1);
					//System.out.println("Location " + map.getLocation());
				} else if (currentY != 0) {
					dy = (currentY - 1);
				}

			} else if (mvt == 'q') {
				map.setLocation(map.getHeroLocation(currentX, currentY));

				if (currentX % map.getWidth() == 0 && currentX != 0) {
					map.decLocation();
					dx = (currentX - 1);
					//System.out.println("Location " + map.getLocation());
				} else if (currentX != 0) {
					dx = (currentX - 1);
				}
			} else if (mvt == 's') {
				map.setLocation(map.getHeroLocation(currentX, currentY));

				if ((currentY % map.getHeight() == map.getHeight() - 1)&&currentY!=23) {
					map.incLocation();
					map.incLocation();
				}
				
				if (currentY!=23){
					dy = (currentY + 1);
				}
			} else if (mvt == 'd') {
				map.setLocation(map.getHeroLocation(currentX, currentY));

				if (currentX % map.getWidth() == 19&&currentX!=39) {
					map.incLocation();

					/*
					 * } if(currentX<map.getWidth()-1){ dx = (currentX +1);
					 */
				}
				 if (currentX!=39){
					dx = (currentX + 1);
				}
			}
			System.out.println("all ok");
			if (map.getCase(dx, dy).getContenu().isVide()) {
				System.out.println("vide");
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					heros1.move(dx - currentX, dy - currentY);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					heros2.move(dx - currentX, dy - currentY);
				}
				Case v = new Case(currentX, currentY, new Vide());
				Case h;
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					h = new Case(dx, dy, heros1);
					map.editCase(v);
					map.editCase(h);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					h = new Case(dx, dy, heros2);
					map.editCase(v);
					map.editCase(h);
				}
				
				//System.out.println("Je déplace en (" + dx + ";" + dy + ")");
			} else if (map.getCase(dx, dy).getContenu().isCompetences()) {
				//System.out.println("else");
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					heros1.pickUp((Competences) map.getCase(dx, dy).getContenu());
					heros1.move(dx - currentX, dy - currentY);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					heros2.pickUp((Competences) map.getCase(dx, dy).getContenu());
					heros2.move(dx - currentX, dy - currentY);
				}
				Case v = new Case(currentX, currentY, new Vide());
				Case h = new Case(dx, dy, heros1);
				map.editCase(v);
				map.editCase(h);
				// GameView.Please();
			}
		}
	}
	
	public static void Tour() {
		System.out.println("Tour");
		if(situation == 1){
			Game.game.tourDe1 = true;
			Game.game.PhaseAction = true;
		}
		else if(situation == 2){
			Game.game.tourDe1 = false;
			Game.game.PhaseAction = false;
		}
		else if(situation == 3){
			Game.game.tourDe1 = false;
			Game.game.PhaseAction = true;
		}
		else if(situation == 4){
			Game.game.tourDe1 = true;
			Game.game.PhaseAction = false;
			situation = 0;
		}
		situation++;
		m_game.returnFocus();
	}

	public static void breakk() {
		System.out.println("Break");
		m_game.returnFocus();
	}

	public static void Create_Robot() {
		System.out.println("Create Robot");
		m_game.returnFocus();
	}
}