package pla;

import java.util.List;
import java.math.*;

public class Robots extends Perso{
	
	int i,j;
	int di,dj;
	int pdv = 100;
	int pDefense = 0;
	Noeud a;
	int equipe;
	
	public Robots(int i,int j, int equipe){
		this.i = i;
		this.j = j;
//		this.a=a;
		this.equipe = equipe;
	}
	
	public String toString() {
		return "R";
	}
	
	public void NSEW(Map map,String dir){
		if(dir == "N"){
			j--;
			Case nr = new Case(i,j,this);
			Case v = new Case(i,j+1,new Vide());
			map.editCase(nr);
			map.editCase(v);
		}
		else if (dir == "S"){
			j++;
			Case nr = new Case(i,j,this);
			Case v = new Case(i,j-1,new Vide());
			map.editCase(nr);
			map.editCase(v);
		}
		else if(dir == "E"){
			i++;
			Case nr = new Case(i,j,this);
			Case v = new Case(i-1,j,new Vide());
			map.editCase(nr);
			map.editCase(v);
		}
		else if(dir == "W"){
			i--;
			Case nr = new Case(i,j,this);
			Case v = new Case(i+1,j,new Vide());
			map.editCase(nr);
			map.editCase(v);
		}
	}
	
	private boolean faceALaMerde = false;
	private String dir = "S";
	
	public void moveObs(Map map){
		if(dir == "N"){
			if((map.getCase(i, j-1).getContenu().isVide())||(map.getCase(i, j-1).getContenu().isCompetences())){
				faceALaMerde = false;
				NSEW(map,"N");
			}
			else{
				if((map.getCase(i+1, j).getContenu().isVide())||(map.getCase(i+1, j).getContenu().isCompetences())){
					if(i<map.getWidth()/2){
						NSEW(map,"W");
					}
					else{
						NSEW(map,"E");
					}
				}
			}
		}
		else if(dir == "S"){
			if((map.getCase(i, j+1).getContenu().isVide())||(map.getCase(i, j+1).getContenu().isCompetences())){
				faceALaMerde = false;
				NSEW(map,"S");
			}
			else{
				if((map.getCase(i+1, j).getContenu().isVide())||(map.getCase(i+1, j).getContenu().isCompetences())){
					if(i<map.getWidth()/2){
						NSEW(map,"W");
					}
					else{
						NSEW(map,"E");
					}				}
			}
		}
		else if( dir == "E"){
			if((map.getCase(i+1, j).getContenu().isVide())||(map.getCase(i+1, j).getContenu().isCompetences())){
				faceALaMerde = false;
				NSEW(map,"E");
			}
			else{
				if((map.getCase(i, j+1).getContenu().isVide())||(map.getCase(i, j+1).getContenu().isCompetences())){
					if(j<map.getHeight()/2){
						NSEW(map,"S");
					}
					else{
						NSEW(map,"N");
					}				}
			}
		}
		else if(dir == "W"){
			if((map.getCase(i-1, j).getContenu().isVide())||(map.getCase(i-1, j).getContenu().isCompetences())){
				faceALaMerde = false;
				NSEW(map,"W");
			}
			else{
				if((map.getCase(i, j+1).getContenu().isVide())||(map.getCase(i, j+1).getContenu().isCompetences())){
					if(j<map.getHeight()/2){
						NSEW(map,"S");
					}
					else{
						NSEW(map,"N");
					}						}
			}
		}
	}

	public void move() {
		
		//System.out.println("Move Robot appelé");
		Map map = Game.game.m_model.map;
		int resX = (int) Math.sqrt(Math.pow(di-i, 2));
		int resY = (int) Math.sqrt(Math.pow(dj-j, 2));
		if(((i!=di)||(j!=dj))&&(!faceALaMerde)){
			if(resX>resY){
				if(i-di>0){
					if((map.getCase(i-1, j).getContenu().isVide())||(map.getCase(i-1, j).getContenu().isCompetences())){
						NSEW(map,"W");
					}
					else{
						faceALaMerde = true;
						dir = "W";
						moveObs(map);
					}
				}
				else{
					if((map.getCase(i+1, j).getContenu().isVide())||(map.getCase(i+1, j).getContenu().isCompetences())){
						NSEW(map,"E");
					}
					else{
						faceALaMerde = true;
						dir = "E";
						moveObs(map);					}
				}
			}
			else{
				if(j-dj>0){
					if((map.getCase(i, j-1).getContenu().isVide())||(map.getCase(i, j-1).getContenu().isCompetences())){
						NSEW(map,"N");
					}
					else{
						faceALaMerde = true;
						dir = "N";
						moveObs(map);					}
					}
				else{
					if((map.getCase(i, j+1).getContenu().isVide())||(map.getCase(i, j+1).getContenu().isCompetences())){
						NSEW(map,"S");
					}
					else{
						faceALaMerde = true;
						dir = "S";
						moveObs(map);					}
					}
				}
			}
		else{
			if((i!=di)||(j!=dj)){
				moveObs(map);
			}
		}
		
		//di = c.getX();
		//dj = c.getY();
		/*if(i!=di){
			if(i<di){
				if(map.getCase(i+1, j).getContenu().isVide()){
					i++;
					Case nr = new Case(i,j,this);
					Case v = new Case(i-1,j,new Vide());
					map.editCase(nr);
					map.editCase(v);
				}
				else if(map.getCase(i, j).getContenu().isCompetences()){
					i++;
					Case nr = new Case(i,j,this);
					Case v = new Case(i-1,j,new Vide());
					map.editCase(nr);
					map.editCase(v);
				}
				else{
					if(i>=(map.getWidth()/2)){
						j--;
						Case nr = new Case(i,j,this);
						Case v = new Case(i,j+1,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
					else{
						j++;
						Case nr = new Case(i,j,this);
						Case v = new Case(i,j-1,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
				}
			}
			else{
				Case cas = map.getCase(i-1, j);
				if(map.getCase(i-1, j).getContenu().isVide()){
					i--;
					Case nr = new Case(i,j,this);
					Case v = new Case(i+1,j,new Vide());
					map.editCase(nr);
					map.editCase(v);
				}
				else if(map.getCase(i, j).getContenu().isCompetences()){
					
				}
				else{
					if(i>=(map.getWidth()/2)){
						j++;
						Case nr = new Case(i,j,this);
						Case v = new Case(i,j-1,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
					else{
						j--;
						Case nr = new Case(i,j,this);
						Case v = new Case(i,j+1,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
				}
			}
		}
		else if(j!=dj){
			if(j<dj){
				if(map.getCase(i, j+1).getContenu().isVide()){
					j++;
					Case nr = new Case(i,j,this);
					Case v = new Case(i,j-1,new Vide());
					map.editCase(nr);
					map.editCase(v);
				}
				else if(map.getCase(i, j).getContenu().isCompetences()){
					
				}
				else{
					if(i>=(map.getHeight()/2)){
						i--;
						Case nr = new Case(i,j,this);
						Case v = new Case(i+1,j,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
					else{
						i++;
						Case nr = new Case(i,j,this);
						Case v = new Case(i-1,j,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
				}
			}
			else{
				if(map.getCase(i, j-1).getContenu().isVide()){
					j--;
					Case nr = new Case(i,j,this);
					Case v = new Case(i,j+1,new Vide());
					map.editCase(nr);
					map.editCase(v);
				}
				else if(map.getCase(i, j).getContenu().isCompetences()){
					
				}
				else{
					if(i>=(map.getHeight()/2)){
						i--;
						Case nr = new Case(i,j,this);
						Case v = new Case(i+1,j,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
					else{
						i++;
						Case nr = new Case(i,j,this);
						Case v = new Case(i-1,j,new Vide());
						map.editCase(nr);
						map.editCase(v);
					}
				}
			}
		}
		//System.out.println("Les nouvelles coordonnées sont : ("+i+";"+j+")");*/
	}

	/*Action d'attaque d'un robot
	 *	regarde si y a un robot à 1 case de lui :
	 *	si oui, il regarde si il est d'une équipe différente, si le robot esquive l'attaque et attaque
	 *	sinon on renvoie 0 pour indiquer que le robot ne peut attaquer personne
	 */
	public int attack(){
		Map map = Game.game.m_model.map;
		
		for(int k = i-1; k<= i++; k++){
			for(int l = j-1; l<=j++; l++){
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				// TODO : est ce qu un robot peut attaquer un héros ? 
				
				if(obs.isRobot()){
					Robots r2 = (Robots) obs;
					if(r2.equipe != this.equipe){
						double p = Math.random();		
						if(p > 0.05+r2.defend()){
							//Si non esquive du robot adverse
							r2.pdv -= 35;
							if(r2.pdv<=0){
								// si pdv < 0 destruction du robot adverse
								Case v = new Case(i,j,new Vide());
								c = v;
							}
						}
						return 1;
					}
				}
			}
		}
		return 0;
	}

	//defend renvoie un double d'un valeur de 0.20 maximum, augmente avec le nbr de pDefense
	public double defend() {
		double p = 0.0;
		for(int k = 0 ; k<pDefense ; k++){
			p += (0.2-p)/3;
		}
		return p;
	}
	
	/* se dirige vers la compétence la plus proche :
	 * Il regarde toute la map et cherche la compétence avec la distance la plus petite de lui
	 * On apelle move vers cette case
	 */
	public int moveRamasse(){
		int min = 30;
		int min_i = 0, min_j = 0;
		int tmp = 0;
		Map map = Game.game.m_model.map;
		
		for(int k = 0; k<map.getHeight(); k++){
			for(int l = 0; l<map.getWidth(); l++){
				
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isCompetences()){
					//le robot trouve une compétence sur la map
					tmp = Math.abs(k-i)+Math.abs(l-j);
					if(tmp<min){
						// si elle est plus provhe que la case sauvegardé
						min = tmp;
						min_i = k;
						min_j = l;
					}
				}
			}
		}
		if(min!=30){
			Case c = map.getCase(min_i, min_j);
			//move(c); //TODO
			// le robot se rend sur la derniere case sauvegard
			return 1;
		}
		else{
			return 0;
		}	
	}
	
	/* se dirige vers l'ennemie ou la base la plus proche :
	 * Il regarde toute la map et cherche l'ennemie avec la distance la plus petite de lui
	 * Si il trouve pas l'ennemie il attaque la base adverse
	 */
	public int moveAttack(){
		int min = 30;
		int min_i = 0, min_j = 0;
		int tmp = 0;
		Map map = Game.game.m_model.map;
		
		//recherche d'ennemie
		for(int k = 0; k<map.getHeight(); k++){
			for(int l = 0; l<map.getWidth(); l++){
				
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isRobot()){
					Robots r2 = (Robots) obs;
					if(this.equipe != r2.equipe){
						tmp = Math.abs(k-i)+Math.abs(l-j);
						if(tmp<min){
							min = tmp;
							min_i = k;
							min_j = l;
						}
					}
				}
			}
		}
		
		//Cas ou il trouve pas d'ennemie il attaque la base
		if(min==30){
			//attaquer base ennemie
		}
		Case c = map.getCase(min_i, min_j);
		move(c);
		return 1;
	}

	/* se dirige vers la base :
	 * Il regarde toute la map et cherche la case libre la plus proche de la base
	 * On apelle move vers cette case
	 */
	public int moveDef(){
		int min = 30;
		int min_i = 0, min_j = 0;
		int tmp = 0;
		Map map = Game.game.m_model.map;
		
		for(int k = 0; k<map.getHeight(); k++){
			for(int l = 0; l<map.getWidth(); l++){
				
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isBase()){
					Base b = (Base) obs;
					if(b.equipe == this.equipe){
						tmp = Math.abs(k-i)+Math.abs(l-j);
						if(tmp<min){
							min = tmp;
							min_i = k;
							min_j = l;
						}
					}
				}
			}
		}
		Case c = map.getCase(min_i, min_j);
		move(c);
		return 1;
	}
	
	/*
	 * Toutes les méthoodes booléennes utilisé dans les if
	 */
	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return false;
	}

	public boolean isCompetences() {
		return false;
	}

	public boolean isHeros() {
		return false;
	}

	public boolean isBase() {
		return false;
	}

	public boolean isRobot() {
		return true;
	}
	
	public void pickUp(Competences c) {
		
	}
	
	public void editDest(int di, int dj){
		//Map map = Game.game.m_model.map; 
		//if(((di>=0)&&(di<map.getHeight()))||((dj>=0)&&(dj<map.getWidth()))){
			this.di = di;
			this.dj = dj;
		//}
	}
	
	
	/*Méthode évaluant l'arbre
	 * Il parcourt l'arbre noeud par noeud et éxécute l'action de chaque noeud
	 */
	public void eval(Noeud n){
		Competence c = n.action;
		switch(c){
			case Hit:
				this.attack();
				this.eval(n.filsDroit);
				break;
			case Protect:
//				this.protect();
				this.eval(n.filsDroit);
				break;
			case MoveRamasse:
				this.moveRamasse();
				this.eval(n.filsDroit);
				break;
			case MoveAttack:
				this.moveAttack();
				this.eval(n.filsDroit);
				break;
			case MoveDef:
				this.moveDef();
				this.eval(n.filsDroit);
				break;
			case Sup:
				if (isPossible(n.filsGauche) == -1){ //TODO cr�er cas impossible pour chaque fonction
					this.eval(n.filsDroit);
				}
				else{
					this.eval(n.filsGauche);
				}
				break;
		default:
			break;
		}
	}
	
	
	/*Méthode permettant de vérifier si un arbre est réalisable à partir du noeud racine
	 * Regarde les valeurs retournés par les méthodes d'actions de robot,
	 * si l'un d'elle renvoie 0, alors on peut pas réaliser cette arbre
	 */
	private int isPossible(Noeud n) {
		Competence c = n.action;
		switch(c){
			case Hit:
				if(this.attack()==1){
					isPossible(n.filsDroit);
				}
				else{return 0;}
				break;
			case Protect:
//				if(this.protect()==1){
				isPossible(n.filsDroit);
//				}
//				else{
					return 0;
//				}
			case MoveRamasse:
				if(this.moveRamasse()==1){
					isPossible(n.filsDroit);
				}
				else{return 0;}
			case MoveAttack:
				if(this.moveAttack()==1){
					isPossible(n.filsDroit);
				}
				else{return 0;}
			case MoveDef:
				if(this.moveDef()==1){
					isPossible(n.filsDroit);
				}
				else{return 0;}
			case Sup:
				if(isPossible(n.filsGauche)==1){
					return 1;
				}
				if (isPossible(n.filsDroit) == 1){ //TODO cr�er cas impossible pour chaque fonction
					return 1;
				}
				else{
					return 0;
				}
			default:
				return 1;
		}
		return 1;
	}
	
}
