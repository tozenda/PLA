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

}
