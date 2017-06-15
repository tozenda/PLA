package pla;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Random;

//classe permettant d'interagir entre l'affichage et notre structure de données
public class GameModel {

	String Thomas="/home/tozenda/COURS/RICM3/S6/PLA/PLA/Resources/";
	static String Najwa ="Resources/";
	String Anouar = "Resources/";
	String Jo = "/home/ferreira/Bureau/POO/PLA/Resources/";
	String Paul= "home/doublean/git/PLA/Resources/";
	static String Shoo="/Users/fathinsyuhadaabubakar/Documents/gitclean/PLA/Resources/";
	static String Path = Najwa;

	static Game m_game;
	static Map map;
	static Heros heros1;
	static Heros heros2;
	static List <Robots> robot_list = new LinkedList<Robots>();
	Robots robot;
	static Case currentCase = null;
	public static int situation = 1;
	public int clickedX = 0;
	public int clickedY = 0;
	/* 1 = Choix1
	* 2 = Action1
	* 3 = Choix2
	* 4 = Action2
	*/
	private int Factx = 1;
	private int Facty = 1;
	private boolean Labelmodified = false;
	long count = 0;
	long sum = 0;


	private BufferedImage[] walkingLeft = {getSprite(0, 1), getSprite(1, 1), getSprite(2, 1)};
	private Animation walkLeft = new Animation(walkingLeft, 1);
	private BufferedImage[] walkingRight = {getSprite(0, 2), getSprite(1, 2), getSprite(2, 2)};
	private Animation walkRight = new Animation(walkingRight, 1);
	private BufferedImage[] walkingFront = {getSprite(0, 0), getSprite(1, 0), getSprite(2, 0)}; // Gets the upper left images of sprite sheet
	private Animation walkFront = new Animation(walkingFront, 1);
	private BufferedImage[] walkingBack = {getSprite(0, 3), getSprite(1, 3), getSprite(2, 3)};
	private Animation walkBack = new Animation(walkingBack, 1);
	private Animation animation;
	private static BufferedImage iHero=null;

	Heros currentHero = heros1;
	public Heros getCurrentHero(){
		return heros1;
	}


	private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;

//loadImage
    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(Path + file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("s1.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

	public int getFactx(){
		return Factx;
	}


	public int getFacty(){
		return Facty;
	}


	void setFactXY() {

		/*
		* Factx=m_model.map.getLocation()%2;
		* Facty=(m_model.map.getLocation()/2)+1;
		*/
		switch (GameModel.map.getLocation()) {

			case (2):
			Factx = 2;
			Facty = 1;
			break;
			case (3):
			Factx = 1;
			Facty = 2;
			break;
			case (4):
			Factx = 2;
			Facty = 2;
			break;
			default:
			Factx = 1;
			Facty = 1;
			break;

		}
	}

	GameModel(Game game) {
		m_game = game;
		map = new Map();
		heros1 = new Heros(3,20,1);
		heros1.pointAction = heros1.maxPointAction;
		heros2 = new Heros(36,3,2);
		heros2.pointAction = heros2.maxPointAction;
		Case ch1 = new Case(heros1.getX(), heros1.getY(), heros1);
		Case ch2 = new Case(heros2.getX(), heros2.getY(), heros2);
		map.editCase(ch1);
		map.editCase(ch2);
		robot = new Robots(4, 4, 1); // robot en 3,3 de l equipe 1
		Case r = new Case(robot.i, robot.j, robot);
		map.editCase(r);
		//robot.editDest(29,18);
	}

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

	static Case getCurrentCase() {
		return currentCase;
	}

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
		if((Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
			if(heros1.pointAction>0){
				currentX = heros1.getX();
				currentY = heros1.getY();
				dx = currentX;
				dy = currentY;
				heros1.updatePointAction(-10);
			}
			else{
				System.out.println("Pas assez de PA pour se déplacer !");
			}
		}
		else if ((!Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
			if(heros2.pointAction>0){
				currentX = heros2.getX();
				currentY = heros2.getY();
				dx = currentX;
				dy = currentY;
				heros2.updatePointAction(-10);
			}
			else{
				System.out.println("Pas assez de PA pour se déplacer !");
			}
		}

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
		if((!Game.game.PhaseAction)&&(((Game.game.tourDe1)&&(heros1.pointAction>0))||((!Game.game.tourDe1)&&(heros2.pointAction>0)))){
			if (mvt == 'z') {
				map.setLocation(map.getHeroLocation(currentX, currentY));
				animation = walkBack;
				iHero=animation.getSprite();
			    animation.start();
			    animation.update();

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
				animation = walkLeft;
				iHero=animation.getSprite();
			    animation.start();
			    animation.update();
				if (currentX % map.getWidth() == 0 && currentX != 0) {
					map.decLocation();
					dx = (currentX - 1);
					//System.out.println("Location " + map.getLocation());
				} else if (currentX != 0) {
					dx = (currentX - 1);
				}
			} else if (mvt == 's') {
				map.setLocation(map.getHeroLocation(currentX, currentY));
				animation = walkFront;
				iHero=animation.getSprite();
			    animation.start();
			    animation.update();
				if ((currentY % map.getHeight() == map.getHeight() - 1)&&currentY!=23) {
					map.incLocation();
					map.incLocation();
				}

				if (currentY!=23){
					dy = (currentY + 1);
				}
			} else if (mvt == 'd') {
				map.setLocation(map.getHeroLocation(currentX, currentY));
				animation = walkRight;
				iHero=animation.getSprite();
			    animation.start();
			    animation.update();
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
			//System.out.println("all ok");
			if (map.getCase(dx, dy).getContenu().isVide()) {
				System.out.println("vide");
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
					heros1.move(dx - currentX, dy - currentY);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
					heros2.move(dx - currentX, dy - currentY);
				}
				Case v = new Case(currentX, currentY, new Vide());
				Case h;
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
					h = new Case(dx, dy, heros1);
					map.editCase(v);
					map.editCase(h);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
					h = new Case(dx, dy, heros2);
					map.editCase(v);
					map.editCase(h);
				}

				//System.out.println("Je déplace en (" + dx + ";" + dy + ")");
			} else if (map.getCase(dx, dy).getContenu().isCompetences()) {
				//System.out.println("else");
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
					heros1.pickUp((Competences) map.getCase(dx, dy).getContenu());
					heros1.move(dx - currentX, dy - currentY);
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)&&(!Game.game.pause)){
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


	public BufferedImage getImage()
	{
		return iHero;
	}
	/* 1 = Choix1
	* 2 = Action1
	* 3 = Choix2
	* 4 = Action2
	*/

	public static void Tour() {
		System.out.println("Tour");
		if(situation == 1){
			Game.game.compteurActionBegin();
			Game.game.tourDe1 = true;
			Game.game.PhaseAction = true;
			for(Robots r : GameModel.robot_list){
				r.courant = r.a;
			}
		}
		else if(situation == 2){
			heros2.pointAction = heros2.maxPointAction;
			Game.game.tourDe1 = false;
			Game.game.PhaseAction = false;
		}
		else if(situation == 3){
			Game.game.compteurActionBegin();
			Game.game.tourDe1 = false;
			Game.game.PhaseAction = true;
		}
		else if(situation == 4){
			Game.game.tourDe1 = true;
			heros1.pointAction = heros1.maxPointAction;
			Game.game.PhaseAction = false;
			situation = 0;
		}
		situation++;
		m_game.returnFocus();
	}

	public static void breakk() {
		//TODO
		System.out.println("Break");
		/*if(!Game.game.pause){
			Game.game.breakTimer();
		}
		else{
			Game.game.endBreak();
		}*/
		Game.game.pause = !Game.game.pause; 
		m_game.returnFocus();
	}
	
	public static void supprimerRobotList(){
		Iterator<Robots> itR = robot_list.iterator();
		while (itR.hasNext()) {
		    Robots r = itR.next();
			if(r.pdv<=0){
				itR.remove();
				break;
			}
		}
	}

	/*
	* Tente de modifier un robot, si erreur de parser, alors on ne modifie pas le robot
	*/
	public static void modifier_Robot(){
		String com = m_game.m_view.getTextField();
		if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
			if(heros1.pointAction>=2){
				Case c = getCurrentCase();
				Observables obs = c.getContenu();
				if(obs.isRobot() && (Math.abs(c.getX()-heros1.getX())+Math.abs(c.getY()-heros1.getY())<=3)){
					Robots r = (Robots) obs;
					Noeud n = null;
					Reader parser = new Reader(System.in);
					try {
						n=parser.read(com);
					} catch (pla.ParseException e) {
						n=null;
					}
					if(n!=null){
						Competences listC = new Competences();
						listC.recupListCompetence(r.a);
						heros1.pickUp(listC);
						r.a=n;
						r.supCompInventaire(heros1);
					}
				}
				else{
					System.out.println("Modification impossible : Trop loin du héros ou pas un robot");
				}
			}
			else{
				System.out.println("Pas assez de point d'actions");
			}
		}
		if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
			if(heros2.pointAction>=2){
				Case c = getCurrentCase();
				Observables obs = c.getContenu();
				if(obs.isRobot() && (Math.abs(c.getX()-heros1.getX())+Math.abs(c.getY()-heros1.getY())<=3)){
					Robots r = (Robots) obs;
					Noeud n = null;
					Reader parser = new Reader(System.in);
					try {
						n=parser.read(com);
					} catch (pla.ParseException e) {
						n=null;
					}
					if(n!=null){
						Competences listC = new Competences();
						listC.recupListCompetence(r.a);
						heros1.pickUp(listC);
						r.a=n;
						r.supCompInventaire(heros1);
					}
				}
				else{
					System.out.println("Modification impossible : Trop loin du héros ou pas un robot");
				}
			}
			else{
				System.out.println("Pas assez de point d'actions");
			}
		}
		m_game.returnFocus();
	}

	public static void Create_Robot(){
		if(!Game.game.pause){
			System.out.println("Create Robot");
			String com = "";
			if(getCurrentCase()!=null){
				if(getCurrentCase().getContenu().isRobot()){
					modifier_Robot();
				}
			}
			else{
				if((Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					if(heros1.pointAction>=2){
						com = m_game.m_view.getTextField();
						if(heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros1.getX()+1,heros1.getY()).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()+1,heros1.getY(),1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight()){
							if(GameModel.map.getCase(heros1.getX(),heros1.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX(),heros1.getY()+1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getX()>0){
							if(GameModel.map.getCase(heros1.getX()-1,heros1.getY()).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()-1,heros1.getY(),1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0){
							if(GameModel.map.getCase(heros1.getX(),heros1.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX(),heros1.getY()-1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight() && heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros1.getX()+1,heros1.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()+1,heros1.getY()+1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0 && heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros1.getX()+1,heros1.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()+1,heros1.getY()-1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight() && heros1.getX()>0){
							if(GameModel.map.getCase(heros1.getX()-1,heros1.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()-1,heros1.getY()+1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0 && heros1.getX()>0){
							if(GameModel.map.getCase(heros1.getX()-1,heros1.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros1.getX()-1,heros1.getY()-1,1,com));
								heros1.pointAction -= heros1.coupCreationRobot;
							}
						}
						else{
						}
					}
					else{
						System.out.println("Pas assez de point d'action pour créer le robot");
					}
				}
				else if((!Game.game.tourDe1)&&(!Game.game.PhaseAction)){
					if(heros2.pointAction>=2){
						com = m_game.m_view.getTextField();
						if(heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros2.getX()+1,heros2.getY()).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()+1,heros2.getY(),2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight()){
							if(GameModel.map.getCase(heros2.getX(),heros2.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX(),heros2.getY()+1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getX()>0){
							if(GameModel.map.getCase(heros2.getX()-1,heros2.getY()).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()-1,heros2.getY(),2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0){
							if(GameModel.map.getCase(heros2.getX(),heros2.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX(),heros2.getY()-1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight() && heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros2.getX()+1,heros2.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()+1,heros2.getY()+1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0 && heros1.getX()+1<map.getTotalWidth()){
							if(GameModel.map.getCase(heros2.getX()+1,heros2.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()+1,heros2.getY()-1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()+1<map.getTotalHeight() && heros1.getX()>0){
							if(GameModel.map.getCase(heros2.getX()-1,heros2.getY()+1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()-1,heros2.getY()+1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else if(heros1.getY()>0 && heros1.getX()>0){
							if(GameModel.map.getCase(heros2.getX()-1,heros2.getY()-1).getContenu().isVide()){
								robot_list.add(new Robots(heros2.getX()-1,heros2.getY()-1,2,com));
								heros2.pointAction -= heros2.coupCreationRobot;
							}
						}
						else{
							//TODO : Faire une popup qui indique que le robot ne peut être généré faute de place...
						}
					}
					else{
						System.out.println("Pas assez de point d'action pour créer le robot");
					}
				}
			}
			m_game.returnFocus();
		}
		}
}
