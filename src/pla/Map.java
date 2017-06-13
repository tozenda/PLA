package pla;

import java.util.LinkedList;
import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Map {
	private int width = 20;
	private int height = 12;
	private int total_width = 40;
	private int total_height = 24;
	private static int location = 1;
	static private Case elements[][];

	public Map() {
		elements = new Case[total_height][total_width];
		Vide v;
		Case c;
		for (int i = 0; i < total_height; i++) {
			for (int j = 0; j < total_width; j++) {
				// System.out.println("ligne : "+i+" colonnes : "+j);
				v = new Vide();
				c = new Case(i, j, v);
				if ((j == 2) && (i < 6) || (j == 30) && (i < 2)) {// j is the
																	// colomn &&
																	// i is the
																	// line
					Obstacles o = new Obstacles();
					c = new Case(j, i, o);
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
		
		elements[23][37] = new Case(37,23,new Obstacles());
		elements[22][37] = new Case(37,22,new Obstacles());
		elements[21][37] = new Case(37,21,new Obstacles());
		elements[20][37] = new Case(37,20,new Obstacles());
		elements[19][37] = new Case(37,19,new Obstacles());
		
		elements[18][28] = new Case(37,19,new Obstacles());
				
		elements[0][0] = new Case(0,0,new Heros());
		
		Competences c1 = new Competences();
		
		LinkedList<Competence> lc = new LinkedList <Competence>();
		
		lc.add(Competence.MoveRamasse);
		
		c1.setC(lc);
		
		elements[4][9] = new Case(9,4,c1);
		
		Competences c2 = new Competences();

		LinkedList<Competence> lc2 = new LinkedList <Competence>();
		
		lc2.add(Competence.Kamikaze);
		lc2.add(Competence.Hit);
		
		c2.setC(lc2);
		
		elements[6][17] = new Case(17,6,c2);
		
		addBase(38, 0,2);
		addBase(39, 0,2);
		addBase(38, 1,2);
		addBase(39, 1,2);
		addBase(0, 22,1);
		addBase(1, 22,1);
		addBase(0, 23,1);
		addBase(1, 23,1);

	}

	public void addBase(int i, int j, int equipe) {
		Base base = new Base(i, j, equipe);
		Case c = new Case(i, j, base);
		elements[j][i] = c;
	}

	public int getWidth() {
		return width;
	}
	
	public int getTotalWidth(){
		return total_width;
	}
	
	public int getTotalHeight(){
		return total_height;
	}

	public int getHeight() {
		return height;
	}

	public Case[][] getElems() {
		return elements;
	}
	
	private Competence choixC(int c){
		if(c<12){
			return Competence.MoveRamasse;
		}
		else if ((c>=12)&&(c<24)){
			return Competence.MoveAttack;
		}
		else if ((c>=24)&&(c<35)){
			return Competence.MoveDef;
		}
		else if((c>=35)&&(c<47)){
			return Competence.Hit;
		}
		else if((c>=47)&&(c<53)){
			return Competence.Soin;
		}
		else if((c>=53)&&(c<58)){
			return Competence.Kamikaze;
		}
		else if((c>=58)&&(c<64)){
			return Competence.AutoDestruction;
		}
		else if((c>=64)&&(c<67)){
			return Competence.Volvie;
		}
		else if((c>=67)&&(c<69)){
			return Competence.Stun;
		}
		else if ((c>=68)&&(c<75)){
			return Competence.AugDef;
		}
		else if ((c>=75)&&(c<80)){
			return Competence.DimDef;
		}
		else if ((c>=80)&&(c<83)){
			return Competence.Protect;
		}
		else if ((c>=83)&&(c<88)){
			return Competence.Contrer;
		}
		else if ((c>=88)&&(c<94)){
			return Competence.Poison;
		}
		else if ((c>=94)&&(c<100)){
			return Competence.Boost;
		}
		else{
			return null;
		}
	}
	
	public void bb(){
		
	}
	//TODO : Appeler popCompetence à chaque tick de jeu pour faire pop des competence aléatoirement sur la map. Methode à modifier pour ne pas écraser qqch qui existe déjà :/
	public void popCompetence(){
		//System.out.println("popCompetence() appelée");
		Random rnd = new Random(); //Initialisation du générateur de nombre aléatoire avec le temps comme seed
		int choix = rnd.nextInt(100); // Random sur 100 pour le choix de la compètence
		Competence c1 = choixC(choix);
		int choixI = 0;
		int choixJ = 0;
		do{//Permet de ne pas écraser autre chose lors de l'insertion des compétences dans la map.
			choixI = rnd.nextInt(total_width);
			choixJ = rnd.nextInt(total_height);
		}while((!this.getCase(choixI, choixJ).getContenu().isVide())&&(!this.getCase(choixI, choixJ).getContenu().isCompetences()));
		Competences cs1 = new Competences(c1); //Déclaration de la liste des compétences
		//if(this.getCase(choixI, choixI).is){
			
		//}
		if(this.getCase(choixI, choixJ).getContenu().isCompetences()){ //Si la case contenait déjà des compétences
			cs1 = (Competences) this.getCase(choixI, choixJ).getContenu(); //On récupère les compétences
			cs1.addCompetence(c1);//On ajoute la nouvelle
		}
		Case case1 = new Case(choixI,choixJ,cs1); //On met ça dans une case
		this.editCase(case1);//On met la case dans la map
		
		choix = rnd.nextInt(100);
		Competence c2 = choixC(choix);
		do{//Permet de ne pas écraser autre chose lors de l'insertion des compétences dans la map.
			choixI = rnd.nextInt(total_width);
			choixJ = rnd.nextInt(total_height);
		}while((!this.getCase(choixI, choixJ).getContenu().isVide())&&(!this.getCase(choixI, choixJ).getContenu().isCompetences()));
		Competences cs2 = new Competences(c2); //Déclaration de la liste des compétences
		if(this.getCase(choixI, choixJ).getContenu().isCompetences()){ //Si la case contenait déjà des compétences
			cs2 = (Competences) this.getCase(choixI, choixJ).getContenu(); //On récupère les compétences
			cs2.addCompetence(c2);//On ajoute la nouvelle
		}
		Case case2 = new Case(choixI,choixJ,cs2);
		this.editCase(case2);
		
		choix = rnd.nextInt(100);
		Competence c3 = choixC(choix);
		do{//Permet de ne pas écraser autre chose lors de l'insertion des compétences dans la map.
			choixI = rnd.nextInt(total_width);
			choixJ = rnd.nextInt(total_height);
		}while((!this.getCase(choixI, choixJ).getContenu().isVide())&&(!this.getCase(choixI, choixJ).getContenu().isCompetences()));
		Competences cs3 = new Competences(c3); //Déclaration de la liste des compétences
		if(this.getCase(choixI, choixJ).getContenu().isCompetences()){ //Si la case contenait déjà des compétences
			cs3 = (Competences) this.getCase(choixI, choixJ).getContenu(); //On récupère les compétences
			cs3.addCompetence(c3);//On ajoute la nouvelle
		}
		Case case3 = new Case(choixI,choixJ,cs3);
		this.editCase(case3);
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

	public String[] toStringElements() {
		String tmp[] = new String[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tmp[i * width + j] = elements[j][i].toString();
			}
		}
		return tmp;
	}

	public int getLocation() {
		return location;
	}

	public void incLocation() {
		if (location < 4) {
			location++;
		}
	}

	public void decLocation() {
		if (location > 1)
			location--;
	}

	public void resetLocation() {
		location = 1;
	}

	public void setLocation(int a) {
		location = a;

	}

	public int getHeroLocation(int x, int y) {
		if (x > 19) {
			if (y < 12) {
				return 2;
			}
			return 4;
		}
			if (y < 12) {
				return 1;
			}
			return 3;
		}
	

	public void editCase(Case c) {
		int w = c.getX();
		int h = c.getY();
		elements[h][w] = c;
	}

	public Case getCase(int x, int y) {
		return elements[y][x];
	}
}