package pla;

public class Map {
	private int width=20;
	private int height=12;
	private Case elements[][];
	
	public Map(){
		elements = new Case[height][width];
		Vide v;
		Case c;
		for(int i=0; i < height;i++){
			for(int j=0;j<width;j++){
//				System.out.println("ligne : "+i+" colonnes : "+j);
				v = new Vide(); 
				c = new Case(i,j,v);
				if((j==5)&&(i<6)){//j is the colomn && i is the line
					Obstacles o = new Obstacles();
					c = new Case(j,i,o);
				}
				elements[i][j] = c;
			}
		}
		elements[9][19] = new Case(9,19,new Obstacles());
		elements[9][18] = new Case(9,18,new Obstacles());
		elements[9][17] = new Case(9,17,new Obstacles());
		elements[9][16] = new Case(9,16,new Obstacles());
		
		elements[9][10] = new Case(9,10,new Obstacles());
		elements[10][10] = new Case(10,10,new Obstacles());
		elements[11][10] = new Case(11,10,new Obstacles());
		
		addBase(19, 0);
		addBase(18, 0);
		addBase(19, 1);
		addBase(18, 1);
		addBase(0, 10);
		addBase(1, 11);
		addBase(0, 11);
		addBase(1, 10);
	}
	
	public void addBase(int i, int j){
		Base base = new Base(i, j);
		Case c = new Case(i, j, base);
		elements[j][i] = c;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Case[][] getElems() {
		return elements;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setElems(Case elems[][]) {
		elements = elems;
	}
	
	public String[] toStringElements(){
		String tmp[] = new String[width*height];
		for(int i=0;i<height;i++){
			for(int j = 0 ; j < width ; j++){
				tmp[i*width+j] = elements[j][i].toString();
			}
		}
		return tmp;
	}
	
	public void editCase(Case c){
		int w = c.getX();
		int h = c.getY();
		elements[h][w]=c;
	}
	
	public Case getCase(int x, int y){
		return elements[y][x];
	}
}
