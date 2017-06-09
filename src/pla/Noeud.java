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
	
	/* Ajoute un fils droit au noeud existant, 
	 * @param Competence du noeud à ajouter*/
	public void addFilsD(Competence comp){
		//si ce noeud possède déjà un fils droit, ajoute la Competence au fils droit du noeud
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
	
	/* Ajoute un fils gauche au noeud existant, 
	 * @param Competence du noeud à ajouter*/
	public void addFilsG(Competence comp){
		//si ce noeud possède déjà un fils gauche, ajoute la Competence au fils gauche du noeud
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
	
	/* Fonction toString qui renvoie le numéro du noeud et sa Competence*/
	public String toString(){
	     return "noeud = "+this.numero+"  action= "+this.action;
	}
	
	/* Permet d'afficher l'arbre avec les numéros des noeuds*/
	public void affiche(){
	     if(this.filsGauche!=null) filsGauche.affiche();
	     System.out.println(this.toString());
	     if(this.filsDroit!=null) filsDroit.affiche();
	}

	/* on construit un arbre de test*/
	public static void main(String[] args){
		Noeud n = new Noeud(Competence.Hit, 1);
		n.addFilsG(Competence.Boost);
		n.addFilsD(Competence.Escape);
		n.addFilsD(Competence.AugPA);
		//n.readArbre();
		n.affiche();
	}
}
