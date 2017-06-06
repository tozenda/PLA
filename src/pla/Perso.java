package pla;

public abstract class Perso extends Actifs {
	public abstract void move (int dx, int dy);
	public abstract void attack ();
	public abstract void defend ();
	public abstract void pickUp (String dir);

}
