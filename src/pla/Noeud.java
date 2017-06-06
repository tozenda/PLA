package pla;

import java.util.*;

public class Noeud extends Robots {
	
	int numero;
	int numCourant = 1;
	Competences action;
	Noeud filsGauche;
	Noeud filsDroit;
	Noeud Racine;
	public ArrayList<Noeud> Liste=new ArrayList<Noeud>();

	public Noeud(Competences comp){
		this.numero=numCourant;
		this.action=comp;
		numCourant++;
	}
	
	public void addFilsD(Competences comp){
		Noeud n = new Noeud(comp);
		Liste.add(n);
		this.filsDroit=n;
	}
	
	public void addFilsG(Competences comp){
		Noeud n = new Noeud(comp);
		Liste.add(n);
		this.filsGauche=n;
	}

}
