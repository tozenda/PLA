package pla;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Robots extends Perso{

	int i,j;
	int di,dj;
	int pdv = 100;
	int pDefense = 0;
	int pAttaque;
	Noeud a;
	int equipe;
	boolean stun;
	boolean protection;
	boolean contre;
	int poison;
	boolean boostDegat;
	boolean choixOu;

	public Robots(int i,int j, int equipe){
		this.i = i;
		this.j = j;
		//this.a=a;
		this.equipe = equipe;
		protection = false;
		contre = false;
		poison = 0;
		stun = false;
		boostDegat = false;
		choixOu = true;
	}

	public Robots(int i,int j, int equipe, String s){
		this.i = i;
		this.j = j;
		//this.a = Reader.read(s);
		this.a = null;
        Reader parser = new Reader(System.in);
        try {
               this.a=parser.read(s);
        } catch (pla.ParseException e) {
                e.printStackTrace();
                this.a=new Noeud();
        }
		this.equipe = equipe;
		protection = false;
		contre = false;
		poison = 0;
		stun = false;
		boostDegat = false;
		choixOu = true;
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

	private Observables contenu(String dir){
		if(dir == "N" && i<GameModel.map.getTotalWidth() && j<GameModel.map.getTotalHeight()){
			return GameModel.map.getCase(i, j-1).getContenu();
		}
		else if(dir == "S" && i<GameModel.map.getTotalWidth() && j<GameModel.map.getTotalHeight()){
			return GameModel.map.getCase(i, j+1).getContenu();
		}
		else if(dir == "E" && i<GameModel.map.getTotalWidth() && j<GameModel.map.getTotalHeight()){
			return GameModel.map.getCase(i+1, j).getContenu();
		}
		else if(dir == "W" && i<GameModel.map.getTotalWidth() && j<GameModel.map.getTotalHeight()){
			return GameModel.map.getCase(i-1,j).getContenu();
		}
		else{
			return null;
		}
	}

	private boolean faceALaMerde = false;
	private String dir = "S";

	public void moveObs(Map map){
		 Random rnd = new Random();
		 rnd.setSeed(System.currentTimeMillis());
		 boolean r = false;
		if(dir == "N"){
			if((contenu("N").isVide())||(contenu("N").isCompetences())){
				faceALaMerde = false;
				if(contenu("N").isCompetences()){
					pickUp( (Competences) contenu("N"));
				}
				NSEW(map,"N");
			}
			else{
				if((contenu("E").isVide())||(contenu("E").isCompetences())||(contenu("W").isVide())||(contenu("W").isCompetences())){
					if((i<5)&&((contenu("E").isVide())||(contenu("E").isCompetences()))){
						if(contenu("E").isCompetences()){
							pickUp( (Competences) contenu("E"));
						}
						NSEW(map,"E");
					}
					else if((i>map.getTotalWidth()-5)&&((contenu("W").isVide())||(contenu("W").isCompetences()))){
						if(contenu("W").isCompetences()){
							pickUp( (Competences) contenu("W"));
						}
						NSEW(map,"W");
					}
					else{
						r = rnd.nextBoolean();
						if((r)&&((contenu("E").isVide())||(contenu("E").isCompetences()))){
							if(contenu("E").isCompetences()){
								pickUp( (Competences) contenu("E"));
							}
							NSEW(map,"E");
						}
						else if((contenu("W").isVide())||(contenu("W").isCompetences())){
							if(contenu("W").isCompetences()){
								pickUp( (Competences) contenu("W"));
							}
							NSEW(map,"W");
						}
						}
				}
			}
		}
		else if(dir == "S"){
			if((contenu("S").isVide())||(contenu("S").isCompetences())){
				faceALaMerde = false;
				if(contenu("S").isCompetences()){
					pickUp( (Competences) contenu("S"));
				}
				NSEW(map,"S");
			}
			else{
				if((contenu("E").isVide())||(contenu("E").isCompetences())||(contenu("W").isVide())||(contenu("W").isCompetences())){
					if((i<5)&&(contenu("E").isCompetences()||contenu("E").isVide())){
						if(contenu("E").isCompetences()){
							pickUp( (Competences) contenu("E"));
						}
						NSEW(map,"E");
					}
					else if((i>map.getTotalWidth()-5)&&(contenu("W").isCompetences()||contenu("W").isVide())){
						if(contenu("W").isCompetences()){
							pickUp( (Competences) contenu("W"));
						}
						NSEW(map,"W");
					}
					else{
						r = rnd.nextBoolean();
						if((r)&&(contenu("E").isCompetences()||contenu("E").isVide())){
							if(contenu("E").isCompetences()){
								pickUp( (Competences) contenu("E"));
							}
							NSEW(map,"E");
						}
						else if(contenu("W").isCompetences()||contenu("W").isVide()){
							if(contenu("W").isCompetences()){
								pickUp( (Competences) contenu("W"));
							}
							NSEW(map,"W");
						}
					}
				}
			}
		}
		else if( dir == "E"){
			if((contenu("E").isVide())||(contenu("E").isCompetences())){
				if(contenu("E").isCompetences()){
					pickUp( (Competences) contenu("E"));
				}
				faceALaMerde = false;
				NSEW(map,"E");
			}
			else{
				if((contenu("S").isVide())||(contenu("S").isCompetences())||((contenu("N").isVide())||(contenu("N").isCompetences()))){
					if((j<5)&&(contenu("S").isCompetences()||contenu("S").isVide())){
						if(contenu("S").isCompetences()){
							pickUp( (Competences) contenu("S"));
						}
						NSEW(map,"S");
					}
					else if((j>map.getTotalWidth()-5)&&(contenu("N").isCompetences()||contenu("N").isVide())){
						if(contenu("N").isCompetences()){
							pickUp( (Competences) contenu("N"));
						}
						NSEW(map,"N");
					}
					else{
						r = rnd.nextBoolean();
						if((r)&&(contenu("S").isCompetences()||contenu("S").isVide())){
							if(contenu("S").isCompetences()){
								pickUp( (Competences) contenu("S"));
							}
							NSEW(map,"S");
						}
						else if(contenu("N").isCompetences()||contenu("N").isVide()){
							if(contenu("N").isCompetences()){
								pickUp( (Competences) contenu("N"));
							}
							NSEW(map,"N");
						}
					}
				}
			}
		}
		else if(dir == "W"){
			if((contenu("W").isVide())||(contenu("W").isCompetences())){
				if(contenu("W").isCompetences()){
					pickUp( (Competences) contenu("W"));
				}
				faceALaMerde = false;
				NSEW(map,"W");
			}
			else{
				if((contenu("S").isVide())||(contenu("S").isCompetences())||(contenu("N").isVide())||(contenu("N").isCompetences())){
					if((j<5)&&(contenu("S").isCompetences()||contenu("S").isVide())){
						if(contenu("S").isCompetences()){
							pickUp( (Competences) contenu("S"));
						}
						NSEW(map,"S");
					}
					else if((j>map.getTotalWidth()-5)&&(contenu("N").isCompetences()||contenu("N").isVide())){
						if(contenu("N").isCompetences()){
							pickUp( (Competences) contenu("N"));
						}
						NSEW(map,"N");
					}
					else{
						r = rnd.nextBoolean();
						if((r)&&(contenu("S").isCompetences()||contenu("S").isVide())){
							if(contenu("S").isCompetences()){
								pickUp( (Competences) contenu("S"));
							}
							NSEW(map,"S");
						}
						else if(contenu("S").isCompetences()||contenu("S").isVide()){
							if(contenu("N").isCompetences()){
								pickUp( (Competences) contenu("N"));
							}
							NSEW(map,"N");
						}
					}
				}
			}
		}
	}

	public void move(Case c) {
		di = c.getX();
		dj = c.getY();
		Map map = GameModel.map;
		if(Game.game.PhaseAction){
			int resX = (int) Math.sqrt(Math.pow(di-i, 2));
			int resY = (int) Math.sqrt(Math.pow(dj-j, 2));
			if(((i!=di)||(j!=dj))&&(!faceALaMerde)){
				if(resX>resY){
					if(i-di>0){
						if((contenu("W").isVide())||(contenu("W").isCompetences())){
							if(contenu("W").isCompetences()){
								pickUp( (Competences) contenu("W"));
							}
							NSEW(map,"W");
						}
						else{
							faceALaMerde = true;
							dir = "W";
							moveObs(map);
						}
					}
					else{
						if((contenu("E").isVide())||(contenu("E").isCompetences())){
							if(contenu("E").isCompetences()){
								pickUp( (Competences) contenu("E"));
							}
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
						if((contenu("N").isVide())||(contenu("N").isCompetences())){
							if(contenu("N").isCompetences()){
								pickUp( (Competences) contenu("N"));
							}
							NSEW(map,"N");
						}
						else{
							faceALaMerde = true;
							dir = "N";
							moveObs(map);					}
						}
					else{
						if((contenu("S").isVide())||(contenu("S").isCompetences())){
							if(contenu("S").isCompetences()){
								pickUp( (Competences) contenu("S"));
							}
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
		}

	}

	/*Action d'attaque d'un robot
	 *	regarde si y a un robot à 1 case de lui :
	 *	si oui, il regarde si il est d'une équipe différente, si le robot esquive l'attaque et attaque
	 *	sinon on renvoie 0 pour indiquer que le robot ne peut attaquer personne
	 */
	public int attack(){
		Map map = GameModel.map;

		for(int k = i-1; k<= i+1; k++){
			for(int l = j-1; l<=j+1; l++){
				if(0<=k && k<map.getWidth() && 0<=l&& l<map.getHeight()){
					Case c = map.getCase(k, l);
					Observables obs = c.getContenu();

					if(obs.isHeros()){
						Heros h = (Heros) obs;
						if(h.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.07+h.defend()){
								h.pdv -= 35;
								h.destructionHeros();
							}
						}
					}
					if(obs.isRobot()){
						Robots r2 = (Robots) obs;
						if(r2.equipe != this.equipe){
							if(r2.contre){
								pdv -= 15;
							}
							double p = Math.random();
							if(p > 0.05+r2.defend() && !r2.protection){
								//Si non esquive du robot adverse
								if(boostDegat){
									r2.pdv -= 10;
									boostDegat = false;
								}
								r2.pdv -= 35;
								r2.destructionRobot();
							}
							return 1;
						}
					}
					
					if(obs.isBase()){
						Base b = (Base) obs;
						if(b.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.05){
								//5% de chance de rater son attaque
								if(boostDegat){
									b.pdv -= 10;
									boostDegat = false;
								}
								b.pdv -= 35;
								b.destructionBase();
							}
							return 1;
						}
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

	//inflige 30 degats, recupere 10 pdv
	public int voleVie(){
		Map map = GameModel.map;

		for(int k = i-1; k<= i+1; k++){
			for(int l = j-1; l<=j+1; l++){
				if(0<=k && k<40 && 0<=l&& l<24){
					Case c = map.getCase(k, l);
					Observables obs = c.getContenu();

					if(obs.isHeros()){
						Heros h = (Heros) obs;
						if(h.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.07+h.defend()){
								h.pdv -= 30;
								this.soin();
								h.destructionHeros();
							}
						}
					}
					if(obs.isRobot()){
						Robots r2 = (Robots) obs;
						if(r2.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.05+r2.defend() && !r2.protection){
								//Si non esquive du robot adverse
								r2.pdv -= 30;
								this.soin();
								r2.destructionRobot();
							}
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	//recupere 10 pv si inférieur à 90
	public int soin(){
		if(this.pdv <= 90){
			this.pdv +=10;
			return 1;
		}
		else{
			return 0;
		}
	}

	/*
	 * Le robot s'autodétruit et inflige 25 dégats sur les 8 cases autour de lui
	 */
	public int autoDestruction(){
		Map map = GameModel.map;
		int res = 0;
		if(this.pdv < 35){
			for(int k = i-1; k<= i+1; k++){
				for(int l = j-1; l<=j+1; l++){
					if(0<=k && k<map.getWidth() && 0<=l&& l<map.getHeight()){

						Case c = map.getCase(k, l);
						Observables obs = c.getContenu();

						if(obs.isRobot()){
							Robots r2 = (Robots) obs;
							if(this.equipe != r2.equipe){
								r2.pdv -=25;
								r2.destructionRobot();
								res++;
							}
						}
					}
				}
			}
			if(res>1){
				this.pdv=0;
				this.destructionRobot();
				return 1;
			}
		}
		else{
			return 0;
		}
		return 0;
	}

	/*
	 *
	 */
	public int kamikaze(){
		Map map = GameModel.map;

		if(this.pdv < 35){
			for(int k = i-1; k<= i+1; k++){
				for(int l = j-1; l<=j+1; l++){
					if(0<=k && k<map.getWidth() && 0<=l&& l<map.getHeight()){

						Case c = map.getCase(k, l);
						Observables obs = c.getContenu();

						if(obs.isRobot()){
							Robots r2 = (Robots) obs;
							if(this.equipe != r2.equipe){
								r2.pdv -=this.pdv;
								this.pdv = 0;
								destructionRobot();
								r2.destructionRobot();
								return 1;
							}
						}
					}
				}
			}
		}
		else{
			return 0;
		}
		return 0;
	}

	/*
	 * Immobilise un adversaire au tour suivant, 40% de chance de réussite de base
	 */
	public int stun(){
		Map map = GameModel.map;

		for(int k = i-1; k<= i+1; k++){
			for(int l = j-1; l<=j+1; l++){
				if(0<=k && k<map.getWidth() && 0<=l&& l<map.getHeight() && k+l<i+j+2){
					Case c = map.getCase(k, l);
					Observables obs = c.getContenu();

					if(obs.isRobot()){
						Robots r2 = (Robots) obs;
						if(r2.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.8+r2.defend()){
								//Si non esquive du robot adverse, le stun
								r2.stun = true;
							}
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	// augmente les pDefense du robot
	public int augDef(){
		this.pDefense += 1;
		return 1;
	}

	/*
	 * Diminue pDefense du robot adverse le plus proche, 50% de réussite de base
	 */
	public int dimDef(){
		Map map = GameModel.map;

		for(int k = i-1; k<= i+1; k++){
			for(int l = j-1; l<=j+1; l++){
				if(0<=k && k<40 && 0<=l&& l<24){
					Case c = map.getCase(k, l);
					Observables obs = c.getContenu();

					if(obs.isRobot()){
						Robots r2 = (Robots) obs;
						if(r2.equipe != this.equipe){
							double p = Math.random();
							if(p > 0.5+r2.defend()){
								//Si non esquive du robot adverse, pdefense -=1
								r2.pDefense -= 1;
							}
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Le robot ne prend aucun dégats au tour suivant, 15% de chances de réussites de bases
	 */
	public int protection(){
		double p = Math.random();
		if(p > 0.0-this.defend()){
			protection = true;
		}
		return 1;
	}

	/*
	 * Le robot renverra une partie des dégats qu'il subira au tour suivant,  15% de chances de réussites de bases
	 */
	public int contre(){
		double p = Math.random();
		if(p > 0.85-this.defend()){
			contre = true;
		}
		return 1;
	}

	/*
	 * Augmente les dégats du robot
	 */
	public int boostAttack(){
		this.boostDegat=true;
		return 1;
	}

	/*
	 * Tente d'empoisonner le robot le plus proche, 60% de chance de réussir
	 */
	public int poison(){
		Map map = GameModel.map;

		for(int k = i-1; k<= i+1; k++){
			for(int l = j-1; l<=j+1; l++){
				if(0<=k && k<40 && 0<=l&& l<24){

					Case c = map.getCase(k, l);
					Observables obs = c.getContenu();

					if(obs.isRobot()){
						Robots r2 = (Robots) obs;
						if(this.equipe != r2.equipe){
							double p = Math.random();
							if(p > 0.0){
								r2.poison += 3;
								return 1;
							}
						}
					}
				}
			}
		}
		return 0;
	}

	/* se dirige vers la compétence la plus proche :
	 * Il regarde toute la map et cherche la compétence avec la distance la plus petite de lui
	 * On apelle move vers cette case
	 */
	public int moveRamasse(){
		Map map = GameModel.map;
		int min = map.getHeight()+map.getWidth();
		int min_i = 0, min_j = 0;
		int tmp = 0;


		for(int k = 0; k<map.getTotalWidth(); k++){
			for(int l = 0; l<map.getTotalHeight(); l++){

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
			move(c);
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
		Map map = GameModel.map;
		int min = map.getTotalHeight()+map.getTotalWidth();
		int min_i = 0, min_j = 0;
		int tmp = 0;

		//recherche d'ennemie
		for(int k = 0; k<map.getTotalWidth(); k++){
			for(int l = 0; l<map.getTotalHeight(); l++){

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
				if(obs.isBase()){
					Base b = (Base) obs;
					if(this.equipe != b.equipe){
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

	/* se dirige vers la base :
	 * Il regarde toute la map et cherche la case libre la plus proche de la base
	 * On apelle move vers cette case
	 */
	public int moveDef(){
		Map map = GameModel.map;
		int min = map.getTotalHeight()+map.getTotalWidth();
		int min_i = 0, min_j = 0;
		int tmp = 0;

		for(int k = 0; k<map.getTotalWidth(); k++){
			for(int l = 0; l<map.getTotalHeight(); l++){

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
	 * Parcourt notre structure de donnée pour trouver le héros de son équipe
	 * Des qu'il le trouve se déplace vers celui ci
	 */
	public int moveHeros(){
		Map map = GameModel.map;

		for(int k = 0; k<map.getTotalWidth(); k++){
			for(int l = 0; l<map.getTotalHeight(); l++){

				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();

				if(obs.isHeros()){
					Heros h = (Heros) obs;
					if(h.equipe == this.equipe){
						move(c);
						return 1;
					}
				}
			}
		}
		return 0;
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
		List<Competence> l = c.getLc();
		for (Competence tmp : l){
			if(this.equipe == 1){
				if (GameModel.heros1.inventaire.containsKey(tmp)){
					GameModel.heros1.inventaire.put(tmp,GameModel.heros1.inventaire.get(tmp)+1);
				}
				else{
					GameModel.heros1.inventaire.put(tmp,1);
				}
				
			}
			else if(this.equipe == 2){
				if (GameModel.heros2.inventaire.containsKey(tmp)){
					GameModel.heros2.inventaire.put(tmp,GameModel.heros2.inventaire.get(tmp)+1);
				}
				else{
					GameModel.heros2.inventaire.put(tmp,1);
				}
			}
		}
		c.getLc().clear();
		System.out.println("Inventaire du heros");
		for(HashMap.Entry<Competence, Integer> e : GameModel.heros1.inventaire.entrySet()){
			System.out.println(e.getKey().toString());
		}
	}


	public void editDest(int di, int dj){
		//Map map = Game.game.m_model.map;
		//if(((di>=0)&&(di<map.getHeight()))||((dj>=0)&&(dj<map.getWidth()))){
			this.di = di;
			this.dj = dj;
		//}
	}

	/*
	 * regarde se le robot est tué, si oui le supprime
	 */
	public void destructionRobot(){
		if(pdv<=0){
			Map map = GameModel.map;
			Competences listC = new Competences();
			listC.recupListCompetence(this.a);
			Case c = new Case(i, j, listC);
			map.editCase(c);
		}
	}


	/*Méthode évaluant l'arbre
	 * Il parcourt l'arbre noeud par noeud et éxécute l'action de chaque noeud
	 * Remet à 0 l invulnérabilité, et le contre
	 * Désimobilise si stun mais n exécute pas l arbre
	 * Tick de poison
	 */
	public void eval(Noeud n){
		if((GameModel.situation==1&&this.equipe==1) || (GameModel.situation==3&&this.equipe==2)){
			protection = false;
			contre = false;
		}
		if(poison>0){
			pdv -= 15;
			poison--;
			destructionRobot();
		}
		if(n!=null){
			if(!stun){
				Competence c = n.action;
				switch(c){
					case Hit:
						this.attack();
						this.eval(n.filsDroit);
						break;
					case Protect:
						this.protection();
						this.eval(n.filsDroit);
						break;
					case Soin:
						this.soin();
						this.eval(n.filsDroit);
						break;
					case Kamikaze:
						this.kamikaze();
						this.eval(n.filsDroit);
						break;
					case Volvie:
						this.voleVie();
						this.eval(n.filsDroit);
						break;
					case Stun:
						this.stun();
						this.eval(n.filsDroit);
						break;
					case AugDef:
						this.augDef();
						this.eval(n.filsDroit);
						break;
					case DimDef:
						this.dimDef();
						this.eval(n.filsDroit);
						break;
					case Contrer:
						this.contre();
						this.eval(n.filsDroit);
						break;
					case Poison:
						this.poison();
						this.eval(n.filsDroit);
						break;
					case Boost:
						this.boostAttack();
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
						if (isPossible(n.filsGauche) == 0){
							this.eval(n.filsDroit);
						}
						else{
							this.eval(n.filsGauche);
						}
						break;
					case Etoile:
						while(GameModel.situation==2*this.equipe){
							eval(n.filsDroit);
						}
						break;
					case Ou:
						if(choixOu){
							eval(n.filsGauche);
						}
						else{
							eval(n.filsDroit);
						}
						break;
				default:
					break;
				}
				stun = false;
			}
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
				if(this.protection()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case Kamikaze:
				if(this.kamikaze()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case AutoDestruction:
				if(this.autoDestruction()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case Contrer:
				if(this.contre()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case AugDef:
				isPossible(n.filsDroit);
			case DimDef:
				isPossible(n.filsDroit);
			case Boost:
				isPossible(n.filsDroit);
			case Soin:
				isPossible(n.filsDroit);
			case Poison:
				if(this.poison()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case Stun:
				if(this.stun()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
			case Volvie:
				if(this.voleVie()==1){
				isPossible(n.filsDroit);
				}
				else{
					return 0;
				}
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
				if (isPossible(n.filsDroit) == 1){
					return 1;
				}
				else{
					return 0;
				}
			case Etoile:
				return 1;
			case Ou:
				return 1;
			default:
				return 1;
		}
		return 1;
	}

	@Override
	public String getPic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/*supprime les comp�tences utilis�es pour cr�er un robot de l'inventaire*/
	public void supCompInventaire(Heros h){
		HashMap<Competence,Integer> inventaire = new HashMap<Competence,Integer>();
		inventaire = h.inventaire;

		Competences aSuppr = new Competences();
		aSuppr.recupListCompetence(this.a);
		for(Competence c : aSuppr.getLc()){
			/*test si la competence est pr sente dans l'inventaire et est diff rente d'un constructeur*/
			if (!((c==Competence.Ou)||(c==Competence.Sup)||(c==Competence.Etoile))){
				if (inventaire.containsKey(c)){
					inventaire.remove(c);
				}
				else{
					System.out.println("Nice try but NOPE !!");
					break;
				}
			}

		}

	}

	public static void main(String[] args) {
		Robots r1 = new Robots(3, 3, 1, "(H)");
		Robots r2 = new Robots(3, 4, 1, "(S)");
		r2.pdv = 70;
		r1.pdv = 30;
		r1.attack();
		System.out.println(r2.pdv);
		r1.eval(r1.a);
		System.out.println(r2.pdv);
	}

}
