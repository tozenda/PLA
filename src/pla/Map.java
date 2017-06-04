package pla;

public class Map {
	private int width=20;
	private int height=20;
	private Case elements[];
	
	public Map(){
		@SuppressWarnings("unused")
		Vide v;
		@SuppressWarnings("unused")
		Case c;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				v = new Vide(); 
				c = new Case(i,j,v);
				elements[(i*width)+j] = c;
			}
		}
		Heros h = new Heros();
		c = new Case(1,1,h);
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
		elements[]=c;
	}
}
