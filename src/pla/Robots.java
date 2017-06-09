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
	
	boolean[][] wasHere;
	boolean [][] correctPath; // The solution to the maze
	int startX, startY; // Starting X and Y values of maze
	int endX, endY;     // Ending X and Y values of maze

	public void solveMaze(Map map) {
		wasHere = new boolean[map.getHeight()][map.getWidth()];
		correctPath = new boolean [map.getHeight()][map.getWidth()];
	    for (int row = 0; row < map.getHeight(); row++)  
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < map.getWidth(); col++){
	            wasHere[row][col] = false;
	            correctPath[row][col] = false;
	        }
	    boolean b = recursiveSolve(map,startX, startY);
	    // Will leave you with a boolean array (correctPath) 
	    // with the path indicated by true values.
	    // If b is false, there is no solution to the maze
	}
	public boolean recursiveSolve(Map map, int x, int y) {
	    if (x == endX && y == endY) return true; // If you reached the end
	    if (map.getCase(x, y).getContenu().isObstacles() || wasHere[y][x]) return false;  
	    // If you are on a wall or already were here
	    wasHere[x][y] = true;
	    if (x != 0) // Checks if not on left edge
	        if (recursiveSolve(map,x-1, y)) { // Recalls method one to the left
	            correctPath[x][y] = true; // Sets that path value to true;
	            return true;
	        }
	    if (x != map.getWidth() - 1) // Checks if not on right edge
	        if (recursiveSolve(map,x+1, y)) { // Recalls method one to the right
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != 0)  // Checks if not on top edge
	        if (recursiveSolve(map,x, y-1)) { // Recalls method one up
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != map.getHeight()- 1) // Checks if not on bottom edge
	        if (recursiveSolve(map,x, y+1)) { // Recalls method one down
	            correctPath[x][y] = true;
	            return true;
	        }
	    return false;
	}


	public void move(Case c) {
		//System.out.println("Move Robot appelé");
		Map map = Game.game.m_model.map;
		/*startX = i;
		startY = j;
		endX = this.di;
		endY = this.dj;
		solveMaze(map);
		for(int i=0;i<map.getWidth();i++){
			System.out.println("");
			for(int j=0;j<map.getHeight();j++){
				System.out.print(" "+correctPath[i][j]+ " ");
			}
		}
		System.out.println("");*/
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
