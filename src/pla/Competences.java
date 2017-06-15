package pla;

import java.util.*;

public class Competences extends Observables {

	private List<Competence> lc = new LinkedList<Competence>();

	public Competences() {
	}

	public Competences(Competence c) {
		lc.add(c);
	}

	public String toString() {
		switch (this.getLc().get(0)) {
		case AugDef:
			return "Augmenter Defense";
		case AutoDestruction:
			return "Auto destruction";
		case Boost:
			return "Boost";
		case Contrer:
			return "Contrer";
		case DimDef:
			return "DimDef";
		case Etoile:
			return "Etoile";
		case Hit:
			return "Hit";
		case Kamikaze:
			return "Kamikaze";
		case MoveAttack:
			return "MoveAttack";
		case MoveDef:
			return "MoveDef";
		case MoveRamasse:
			return "MoveRamasse";
		case Ou:
			return "Ou";
		case Poison:
			return "Poison";
		case Protect:
			return "Protect";
		case Soin:
			return "Soin";
		case Stun:
			return "Stun";
		case Sup:
			return "Sup";
		case Volvie:
			return "Volvie";
		default:
			System.out.println("Skill pic error");
			return null;
		}
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

	public void addCompetence(Competence c) {
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
		if (n != null) {
			this.addCompetence(n.action);
			if (n.filsDroit != null) {
				this.recupListCompetence(n.filsDroit);
			}
			if (n.filsGauche != null) {
				this.recupListCompetence(n.filsGauche);
			}
		}
	}

	public int getPic() {
		switch( this.getLc().get(0)){
		case AugDef:
			return 0;
		case AutoDestruction:
			return 1;
		case Boost:
			return 2;
		case Contrer:
			return 3;
		case DimDef:
			return 4;
		case Etoile:
			return 5;
		case Hit:
			return 6;
		case Kamikaze:
			return 7;
		case MoveAttack:
			return 8;
		case MoveDef:
			return 9;
		case MoveRamasse:
			return 10;
		case Ou:
			return 11;
		case Poison:
			return 12;
		case Protect:
			return 13;
		case Soin:
			return 14;
		case Stun:
			return 15;
		case Sup:
			return 16;
		case Volvie:
			return 17;
		default:
			System.out.println("Skill pic error");
			return -1;
		}
	}

	@Override
	public String getDescription() {
		switch (this.getLc().get(0)) {
		case AugDef:
			return "Augmenter resistance robot";
		case AutoDestruction:
			return "Le robot se fait exploser et inflige des degats aux robots sur les 8 cases autour x";
		case Boost:
			return "Augmenter degats Hit";
		case Contrer:
			return "L'attaquant subit sa propre attaque";
		case DimDef:
			return "Diminuer resistance de la cible";
		case Etoile:
			return "pendant tout le tour on va eval l'arbre du robot";
		case Hit:
			return "La cible perd des pv x";
		case Kamikaze:
			return "Le robot perd tout ses pdv et le perso se trouvant a proximité perd le meme nb de pv x";
		case MoveAttack:
			return "Se déplace vers le robot ennemi le plus proche x";
		case MoveDef:
			return "Se déplace vers le robot ennemi le plus proche x";
		case MoveRamasse:
			return "Se déplace vers la pièce la plus proche x";
		case Ou:
			return "a | b va faire a ou b";
		case Poison:
			return "Permet d'empoisonner un adversaire";
		case Protect:
			return "Resister, la cible subit moins de degats que prevu";
		case Soin:
			return "Gain de PV x";
		case Stun:
			return "Immobiliser la cible";
		case Sup:
			return "a > b va effectuer a en priorité";
		case Volvie:
			return "Vol de vie adversaire, Hit+Soin x";
		default:
			System.out.println("Skill pic error");
			return null;
		}
	}

}
