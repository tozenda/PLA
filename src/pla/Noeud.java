package pla;

import java.util.*;

public class Noeud {
	
	int numero;
	Competence action;
	Noeud filsGauche;
	Noeud filsDroit;
	public ArrayList<Noeud> Liste=new ArrayList<Noeud>();

	public Noeud(Competence comp, int num){
		this.action=comp;
		this.numero=num;
	}
	
	public void addFilsD(Competence comp){
		if(this.filsDroit==null){
			int num = this.numero+1;
			Noeud n = new Noeud(comp, num);
			Liste.add(n);
			this.filsDroit=n;
		}
		else{
			this.filsDroit.addFilsD(comp);
		}
	} 
	
	public void addFilsG(Competence comp){
		if(this.filsDroit==null){
			int num = this.numero+1;
			Noeud n = new Noeud(comp, num);
			Liste.add(n);
			this.filsGauche=n;
		}
		else{
			this.filsGauche.addFilsG(comp);
		}
	}
	
	public void readArbre(){
	     if(this.filsGauche!=null){
	    	 filsGauche.readArbre();
	     }
	     // action.eval; //TODO eval in Competences or Competence
	     if(this.filsDroit!=null){
	    	 filsDroit.readArbre();
	     }
	}
	
	public String toString(){
	     return "noeud = "+this.numero+"  action= "+this.action;
	}
	
	public void affiche(){
	     if(this.filsGauche!=null) filsGauche.affiche();
	     System.out.println(this.toString());
	     if(this.filsDroit!=null) filsDroit.affiche();
	}

	public static void main(String[] args){
		Noeud n = new Noeud(Competence.Hit, 1);
		n.addFilsG(Competence.Boost);
		n.addFilsD(Competence.Escape);
		n.addFilsD(Competence.AugPA);
		//n.readArbre();
		n.affiche();
	}
}
