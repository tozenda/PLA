package pla;

public class tmp {

	public static void main(String[] args) {
		Map map = new Map();
	    Heros h = new Heros();
	    h.setPos(0, 0);
	    affichage(map);
	}
	
	public static void affichage(Map m){
		int w = m.getWidth();
		int h = m.getHeight();
		String elemsString []=m.toStringElements();
		for(int i=0;i<h;i++){
			System.out.print("|");
			for(int j=0;j<w;j++){
				System.out.print(elemsString[i*w+j]+"|");
			}
			/*System.out.print("\n");
			for(int k=0;k<2*w;k++){
				System.out.print("-");
			}*/
			System.out.print("\n");
		}
	}

}
