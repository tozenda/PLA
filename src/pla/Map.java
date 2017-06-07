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
				if((j==5)&&(i<4)){//j is the colomn && i is the line
					Obstacles o = new Obstacles();
					c = new Case(i,j,o);
				}
				elements[i][j] = c;
			}
		}
		addBase(0, 19);
		addBase(0, 18);
		addBase(1, 19);
		addBase(1, 18);
		addBase(10, 0);
		addBase(10, 1);
		addBase(11, 0);
		addBase(11, 1);
	}
	
	public void addBase(int i, int j){
		Base base = new Base(i, j);
		Case c = new Case(i, j, base);
		elements[i][j] = c;
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
				tmp[i*width+j] = elements[i][j].toString();
			}
		}
		return tmp;
	}
	
	public void editCase(Case c){
		int w = c.getX();
		int h = c.getY();
		elements[w][h]=c;
	}
	
	public Case getCase(int x, int y){
		return elements[x][y];
	}
}
