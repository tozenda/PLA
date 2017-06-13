package pla;
import java.util.*;

public class Competences extends Observables {
	
	private List<Competence> lc = new LinkedList<Competence>();
	
	public Competences(){
	}
	
	public Competences(Competence c){
		lc.add(c);
	}
	
	public String toString() {
		return lc.toString();
	}

	public boolean isVide() {
		return false;
	}

	public boolean isObstacles() {
		return false;
	}

	public boolean isCompetences() {
		return true;
	}

	public List<Competence> getLc() {
		return lc;
	}

	public void setC(List<Competence> c) {
		this.lc = c;
	}
	
	public void addCompetence(Competence c){
		lc.add(c);
	}

	public boolean isHeros() {
		return false;
	}

	public boolean isBase() {
		return false;
	}

	public boolean isRobot() {
		return false;
	}
	
	public void recupListCompetence(Noeud n) {
		if(n!=null){
			this.addCompetence(n.action);
			if(n.filsDroit!=null){
				this.recupListCompetence(n.filsDroit);
			}
			if(n.filsGauche!=null){
				this.recupListCompetence(n.filsGauche);
			}
		}
	}
	public String getPic() {
		switch (this.getLc().get(0)) {
		case AugDef:
			return "AugDef.png";
		case AutoDestruction:
			return "AutoDestruction.png";
		case Boost:
			return "bolt.png";
		case Contrer:
			return "Contrer.png";
		case DimDef:
			return "DimDef.png";
		case Etoile:
			return "Etoile.png";
		case Hit:
			return "Hit.png";
		case Kamikaze:
			return "Kamikaze.png";
		case MoveAttack:
			return "MoveAttack.png";
		case MoveDef:
			return "MoveDef.png";
		case MoveRamasse:
			return "MoveRamasse.png";
		case Ou:
			return "Ou.png";
		case Poison:
			return "Poison.png";
		case Protect:
			return "Protect.png";
		case Soin:
			return "Soin.png";
		case Stun:
			return "Stun.png";
		case Sup:
			return "Sup.png";
		case Volvie:
			return "Volvie.png";
		default:
			System.out.println("Skill pic error");
			return null;
		}
	}

}
