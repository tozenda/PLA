package pla;

import java.awt.event.KeyEvent;

//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;

//classe permettant d'interagir entre l'affichage et notre structure de données
public class GameModel {

	static Game m_game;
	Map map;
	Heros heros1;
	Robots robot;

	GameModel(Game game) {
		m_game = game;
		map = new Map();
		heros1 = new Heros(0, 0, 1);// 1ere coord -> Ligne et 2nd coord 6>
									// Colonne de l equipe 1
		Case c = new Case(heros1.getX(), heros1.getY(), heros1);
		map.editCase(c);
		robot = new Robots(3, 3, 1); // robot en 3,3 de l equipe 1
		Case r = new Case(robot.i, robot.j, robot);
		map.editCase(r);
		robot.editDest(18, 10);
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

	void heroMove(int mvt) {
		int currentX = heros1.getX();
		int currentY = heros1.getY();
		int dx = currentX;
		int dy = currentY;
		System.out.println(mvt);
		int currentLocation = map.getHeroLocation(currentX, currentY);
		System.out.println("Map actuelle : "+ currentLocation);
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
		if (mvt == 'z') {
			map.setLocation(map.getHeroLocation(currentX, currentY));

			if (currentY % map.getHeight() == 0 && currentY != 0) {
				map.decLocation();
				map.decLocation();
				dy = (currentY - 1);
				System.out.println("Location " + map.getLocation());
			} else if (currentY != 0) {
				dy = (currentY - 1);
			}

		} else if (mvt == 'q') {
			map.setLocation(map.getHeroLocation(currentX, currentY));

			if (currentX % map.getWidth() == 0 && currentX != 0) {
				map.decLocation();
				dx = (currentX - 1);
				System.out.println("Location " + map.getLocation());
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
			heros1.move(dx - currentX, dy - currentY);
			Case v = new Case(currentX, currentY, new Vide());
			Case h = new Case(dx, dy, heros1);
			map.editCase(v);
			map.editCase(h);
			System.out.println("Je déplace en (" + dx + ";" + dy + ")");
		} else if (map.getCase(dx, dy).getContenu().isCompetences()) {
			System.out.println("else");
			heros1.pickUp((Competences) map.getCase(dx, dy).getContenu());
			heros1.move(dx - currentX, dy - currentY);
			Case v = new Case(currentX, currentY, new Vide());
			Case h = new Case(dx, dy, heros1);
			map.editCase(v);
			map.editCase(h);
			// GameView.Please();
		}
	}

	public static void Tour() {
		System.out.println("Tour");
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
