package pla;

public class Map {
	private int width=20;
	private int height=20;
	private Case elements[];
	
	public Map(){
		elements = new Case[width*height];
		Vide v;
		Case c;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				v = new Vide(); 
				c = new Case(i,j,v);
				if((j==2)&&(i!=0)){//j is the colomn && i is the line
					Obstacles o = new Obstacles();
					c = new Case(i,j,o);
				}
				elements[(i*width)+j] = c;
			}
		}
		Heros h = new Heros(0,width-1);
		c = new Case(0,width-1,h);
		this.editCase(c);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Case[] getElems() {
		return elements;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setElems(Case elems[]) {
		elements = elems;
	}
	
	public String[] toStringElements(){
		String tmp[] = new String[width*height];
		for(int i=0;i<width*height;i++){
			tmp[i] = elements[i].toString();
		}
		return tmp;
	}
	
	public void editCase(Case c){
		int w = c.getX();
		int h = c.getY();
		elements[w*width+h]=c;
	}
	
	public Case getCase(int x, int y){
		return elements[y*width+x];
	}
}
