package pla;

import java.util.List;

public class Robots extends Perso{
	
	int i,j;
	int di,dj;
	int pdv = 100;
	int pDefense = 0;
	Noeud a;
	
	public Robots(int i,int j){
		this.i = i;
		this.j = j;
//		this.a=a;
	}
	
	public String toString() {
		return "R";
	}

	public void move(Case c) {
		//System.out.println("Move Robot appelé");
		Map map = Game.game.m_model.map; 
		//di = c.getX();
		//dj = c.getY();
		if(i!=di){
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
		//System.out.println("Les nouvelles coordonnées sont : ("+i+";"+j+")");
	}

	public int attack(){
		Map map = Game.game.m_model.map;
		
		for(int k = i-1; k<= i++; k++){
			for(int l = j-1; l<=j++; l++){
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isRobot()){
					Robots r2 = (Robots) obs;
					//if(obs.isSameTeam()){
						double p = Math.random();		
						//+ obs.defend();
						if(p > 0.05+r2.defend()){
							r2.pdv -= 35;
						}
						return 1;
					//}
				}
			}
		}
		return 0;
	}

	//defend renvoie un double ça serait bien, renvoie 0.20 au maximum
	public double defend() {
		double p = 0.0;
		for(int k = 0 ; k<pDefense ; k++){
			p += (0.2-p)/3;
		}
		return p;
	}
	
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
					tmp = Math.abs(k-i)+Math.abs(l-j);
					if(tmp<min){
						min = tmp;
						min_i = k;
						min_j = l;
					}
				}
			}
		}
		Case c = map.getCase(min_i, min_j);
		move(c);
		return 1;
	}
	
	public int moveAttack(){
		int min = 30;
		int min_i = 0, min_j = 0;
		int tmp = 0;
		Map map = Game.game.m_model.map;
		
		for(int k = 0; k<map.getHeight(); k++){
			for(int l = 0; l<map.getWidth(); l++){
				
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isRobot()){
					//if(obs.isSameTeam()){ regarder si robot equipe adverse
						tmp = Math.abs(k-i)+Math.abs(l-j);
						if(tmp<min){
							min = tmp;
							min_i = k;
							min_j = l;
						}
					//}
				}
			}
		}
		if(min==30){
			//attaquer base ennemie
		}
		Case c = map.getCase(min_i, min_j);
		move(c);
		return 1;
	}

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
					//if(obs.isSameTeam()){
						tmp = Math.abs(k-i)+Math.abs(l-j);
						if(tmp<min){
							min = tmp;
							min_i = k;
							min_j = l;
						}
					//}
				}
			}
		}
		Case c = map.getCase(min_i, min_j);
		move(c);
		return 1;
	}
	
	
	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return false;
	}

	public boolean isCompetences() {
		return false;
	}

	public void pickUp(Competences c) {
		
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
	
	public void editDest(int di, int dj){
		//Map map = Game.game.m_model.map; 
		//if(((di>=0)&&(di<map.getHeight()))||((dj>=0)&&(dj<map.getWidth()))){
			this.di = di;
			this.dj = dj;
		//}
	}
	
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
		return 0;
	}
	
}
