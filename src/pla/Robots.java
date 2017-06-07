package pla;

import java.util.List;

public class Robots extends Perso{
	
	int i,j;
	int di,dj;
	
	public Robots(int i,int j){
		this.i = i;
		this.j = j;
	}
	
	public String toString() {
		return "R";
	}

	public void move(Case c) {
		System.out.println("Move Robot appelé");
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
			}
		}
		System.out.println("Les nouvelles coordonnées sont : ("+i+";"+j+")");
	}

	public int attack(){
		Map map = Game.game.m_model.map;
		
		for(int k = i-1; k<= i++; k++){
			for(int l = j-1; l<=j++; l++){
				Case c = map.getCase(k, l);
				Observables obs = c.getContenu();
				
				if(obs.isRobot()){
					//if(obs.isSameTeam()){
						double p = Math.random();		
						//+ obs.defend();
						if(p > 0.05+obs.defend){
							obs.pdv -= 50;
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
		this.di = di;
		this.dj = dj;
	}
}
