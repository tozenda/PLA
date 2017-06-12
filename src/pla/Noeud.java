package pla;

import java.util.*;

public class Noeud {
	
	int numero;
	Competence action;
	Noeud filsGauche;
	Noeud filsDroit;
	public ArrayList<Noeud> Liste=new ArrayList<Noeud>();
	
	public Noeud(){
		this.action = null;
		this.filsGauche = null;
		this.filsDroit = null;
		this.numero = 0;
	}
	public Noeud(Competence comp, int num){
		this.action=comp;
		this.numero=num;
		this.filsGauche = null;
		this.filsDroit = null;
	}
	
	/* Ajoute un fils droit au noeud existant, 
	 * @param Competence du noeud � ajouter*/
	public void addFilsD(Competence comp){
		//si ce noeud poss�de d�j� un fils droit, ajoute la Competence au fils droit du noeud
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
	 * @param Competence du noeud � ajouter*/
	public void addFilsG(Competence comp){
		//si ce noeud poss�de d�j� un fils gauche, ajoute la Competence au fils gauche du noeud
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
	
	/* Fonction toString qui renvoie le num�ro du noeud et sa Competence*/
	public String toString(){
	     return "noeud = "+this.numero+"  action= "+this.action;
	}
	
	/* Permet d'afficher l'arbre avec les num�ros des noeuds*/
	public void affiche(){
	     if(this.filsGauche!=null) filsGauche.affiche();
	     System.out.println(this.toString());
	     if(this.filsDroit!=null) filsDroit.affiche();
	}
	
	public static Competence Symb_to_Comp(String s) {
		  switch(s){
		    case "H":
		    	return Competence.Hit;
		    case "S":
		    	return Competence.Soin;
		    case "K":
		    	return Competence.Kamikaze;
		    case "A":
		    	return Competence.AutoDestruction;
		    case "V":
		    	return Competence.Volvie;
		    case "St":
		    	return Competence.Stun;
		    case "Ad":
		    	return Competence.AugDef;
		    case "D":
		    	return Competence.DimDef;
		    case "C":
		    	return Competence.Contrer;
		    case "P":
		    	return Competence.Poison;
		    case "B":
		    	return Competence.Boost;
		    case "Mr":
		    	return Competence.MoveRamasse;
		    case "Ma":
		    	return Competence.MoveAttack;
		    case "Md":
		    	return Competence.MoveDef;
		    default:
		        return Competence.MoveRamasse;
		  }
	}

	/* on construit un arbre de test*/
	public static void main(String[] args){
		Noeud n = new Noeud(Competence.Hit, 1);
		n.addFilsG(Competence.Boost);
//		n.addFilsD(Competence.Escape);
		n.addFilsD(Competence.AugDef);
		//n.readArbre();
		n.affiche();
	}
}
