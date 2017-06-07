package pla;

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

	@Override
	public void attack() {
	}

	@Override
	public void defend() {
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
