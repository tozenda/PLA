package pla;

import java.util.*;

public class Noeud extends Robots {
	
	int numero;
	int numCourant = 1;
	Competences action;
	Noeud filsGauche;
	Noeud filsDroit;
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
	
	public void readArbre(){
	     if(this.filsGauche!=null){
	    	 filsGauche.readArbre();
	     }
	     action.eval; //TODO eval in Competences or Competence
	     if(this.filsDroit!=null){
	    	 filsDroit.readArbre();
	     }
	   }

	public void main(){
		Noeud n = new Noeud(//Competence);
		n.addFilsG(//Competence);
		n.addFilsD(//Competence);
		n.readArbre();
	}
}
