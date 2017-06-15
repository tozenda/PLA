package pla;

import java.util.LinkedList;
import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

@SuppressWarnings("unused")
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
				elements[i][j] = c;
			}
		}
		
		for(int w=0;w<total_width;w++){
			elements[0][w] = new Case(w,0,new Obstacles());
			elements[total_height-1][w] = new Case(w,total_height-1,new Obstacles());
		}
		for(int h=0;h<total_height;h++){
			elements[h][0] = new Case(0,h,new Obstacles());
			elements[h][total_width-1] = new Case(total_width-1,h,new Obstacles());
		}
		elements[4][6] = new Case(4,6,new Obstacles());
		elements[4][12] = new Case(4,12,new Obstacles());
		elements[4][13] = new Case(4,13,new Obstacles());
		elements[10][12] = new Case(10,12,new Obstacles());
		elements[10][13] = new Case(10,13,new Obstacles());
		elements[10][14] = new Case(10,14,new Obstacles());
		elements[10][19] = new Case(10,19,new Obstacles());
		elements[10][4] = new Case(10,4,new Obstacles());
		elements[8][17] = new Case(8,17,new Obstacles());
		elements[12][8] = new Case(12,8,new Obstacles());
		elements[12][9] = new Case(12,9,new Obstacles());
		elements[13][8] = new Case(13,8,new Obstacles());
		elements[13][9] = new Case(13,9,new Obstacles());
		elements[14][8] = new Case(14,8,new Obstacles());
		elements[14][9] = new Case(14,9,new Obstacles());
		elements[15][8] = new Case(15,8,new Obstacles());
		elements[15][9] = new Case(15,9,new Obstacles());
		elements[16][8] = new Case(16,8,new Obstacles());
		elements[16][9] = new Case(16,9,new Obstacles());
		elements[17][8] = new Case(17,8,new Obstacles());
		elements[17][9] = new Case(17,9,new Obstacles());
		elements[17][11] = new Case(17,11,new Obstacles());
		elements[20][13] = new Case(20,13,new Obstacles());
		elements[20][14] = new Case(20,14,new Obstacles());
		elements[15][15] = new Case(15,15,new Obstacles());
		elements[15][19] = new Case(15,19,new Obstacles());
		
		elements[18][21] = new Case(18,21,new Obstacles());
		elements[18][22] = new Case(18,22,new Obstacles());
		elements[18][25] = new Case(18,25,new Obstacles());
		elements[18][26] = new Case(18,26,new Obstacles());
		elements[19][28] = new Case(19,28,new Obstacles());
		elements[19][29] = new Case(19,29,new Obstacles());
		elements[19][31] = new Case(19,31,new Obstacles());
		elements[19][32] = new Case(19,32,new Obstacles());
		elements[19][33] = new Case(19,33,new Obstacles());
		elements[17][28] = new Case(17,28,new Obstacles());
		elements[17][29] = new Case(17,29,new Obstacles());
		elements[17][31] = new Case(17,31,new Obstacles());
		elements[17][32] = new Case(17,32,new Obstacles());
		elements[17][33] = new Case(17,33,new Obstacles());
		elements[13][28] = new Case(13,28,new Obstacles());
		elements[13][29] = new Case(13,29,new Obstacles());
		elements[13][31] = new Case(13,31,new Obstacles());
		elements[13][32] = new Case(13,32,new Obstacles());
		elements[13][33] = new Case(13,33,new Obstacles());
		elements[12][25] = new Case(12,25,new Obstacles());
		elements[12][26] = new Case(12,26,new Obstacles());
		elements[10][25] = new Case(10,25,new Obstacles());
		elements[10][26] = new Case(10,26,new Obstacles());
		elements[7][25] = new Case(7,25,new Obstacles());
		elements[7][26] = new Case(7,26,new Obstacles());
		elements[7][32] = new Case(7,32,new Obstacles());
		elements[3][32] = new Case(3,32,new Obstacles());
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
		
		addBase(37, 1,2);
		addBase(38, 1,2);
		addBase(37, 2,2);
		addBase(38, 2,2);
		addBase(1, 21,1);
		addBase(2, 21,1);
		addBase(1, 22,1);
		addBase(2, 22,1);

	}

	public void addBase(int i, int j, int equipe) {
		Base base = new Base(i, j, equipe);
		Case c = new Case(i, j, base);
		elements[j][i] = c;
	}
	
	public int getTotalHealthBase1(){
		int h=0;
		if(getCase(1,21).getContenu().isBase()){
			h += getCase(1,21).getContenu().getPointdeVie();
		}
		if(getCase(2,21).getContenu().isBase()){
			h += getCase(2,21).getContenu().getPointdeVie();
		}
		if(getCase(1,22).getContenu().isBase()){
			h += getCase(1,21).getContenu().getPointdeVie();
		}
		if(getCase(2,22).getContenu().isBase()){
			h += getCase(1,21).getContenu().getPointdeVie();
		}
		return h;
	}
	
	public int getTotalHealthBase2(){
		int h=0;
		if(getCase(37,1).getContenu().isBase()){
			h += getCase(37,1).getContenu().getPointdeVie();
		}
		if(getCase(37,2).getContenu().isBase()){
			h += getCase(37,2).getContenu().getPointdeVie();
		}
		if(getCase(1,22).getContenu().isBase()){
			h += getCase(38,1).getContenu().getPointdeVie();
		}
		if(getCase(2,22).getContenu().isBase()){
			h += getCase(38,2).getContenu().getPointdeVie();
		}
		return h;
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