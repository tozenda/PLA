package pla;

public class Case {
	private int x;
	private int y;
	private Observables contenu;
	
	public Case(){
		
	}
	
	public Case(int x, int y, Observables contenu){
		this.x = x;
		this.y = y;
		this.contenu = contenu;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setContenu(Observables contenu){
		this.contenu = contenu;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Observables getContenu(){
		return contenu;
	}
	
	public String toString(){
		return contenu.toString();
	}
}
