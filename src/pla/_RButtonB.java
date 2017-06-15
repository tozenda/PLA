package pla;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

class _RButtonB extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Manipulation de l'arc entourant le bouton
	// Fake shade
	private static final int ARC_WIDTH = 16;
	private static final int ARC_HEIGHT = 16;
	protected static final int FOCUS_STROKE = 2;
	protected Shape shape;
	protected Shape border;
	protected Shape base;
	protected Color color = getBackground();
	protected Color color2 = null;
	protected Color Color_Armed = new Color(230, 230, 230);
	protected Color Color_Armed2 = null;

	// super du constructeur Jbutton
	// On crée normalement un boutton
	public _RButtonB(String text) {
		super(text);
	}

	// Mise à jour de l'interface utilisateur
	@Override
	public void updateUI() {
		super.updateUI();
		//Si true le rectangle dans lequel est contenu le bouton va être colorié
		setContentAreaFilled(false);
		//Petit rectangle autoure du texte quand on arme
		setFocusPainted(false);
		initShape();
	}

	protected void initShape() {
		if (!getBounds().equals(base)) {
			base = getBounds();
			int a = FOCUS_STROKE / 2;
			int w = getWidth() - 1;
			int h = getHeight() - 1;
			shape = new RoundRectangle2D.Float(0, 0, w, h, ARC_WIDTH, ARC_HEIGHT);
			border = new RoundRectangle2D.Float(a, a, w - a, h - a, ARC_WIDTH, ARC_HEIGHT);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		initShape();
		//Cast pour pouvoir utliser les fonctions anticrénelage
		Graphics2D g2 = (Graphics2D) g.create();
		// specify whether you want objects to be rendered as quickly as
		// possible
		// or whether you prefer that the rendering quality be as high as
		// possible
		// Antialiasing = anti crénelage
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			// Couleur au clic
			if (Color_Armed2 == null)//Sans dégradé
				g2.setColor(Color_Armed);
			else {//avec dégradé
				g2.setPaint(new GradientPaint(

						new Point(0, 0), Color_Armed, new Point(0, getHeight()), Color_Armed2));
			}
			g2.fill(shape);
		} else {
			// Couleur du bouton au repos
			if (color2 == null)
				g2.setColor(color);
			else {
				g2.setPaint(new GradientPaint(

						new Point(0, 0), color, new Point(0, getHeight()), color2));
			}

			g2.fill(shape);
		}
		g2.dispose();
		super.paintComponent(g);
	}

	public void setColor_Armed(Color c) {
		Color_Armed = c;
	}

	public void setColor(Color c) {
		color = c;
	}

	public void setColors(Color c, Color armed) {
		color = c;
		Color_Armed = armed;
	}

	public void setColors(Color c, Color c2, Color armed, Color armed2) {
		color = c;
		Color_Armed = armed;
		color2 = c2;
		Color_Armed2 = armed2;
	}

	@Override

	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2f));

		Rectangle r = border.getBounds();
		r.grow(ARC_WIDTH + FOCUS_STROKE / 2, FOCUS_STROKE / 2);
		Path2D.Double lb = new Path2D.Double();
		lb.moveTo(r.x + r.width, r.y);
		lb.lineTo(r.x + r.width, r.y + r.height);
		lb.lineTo(r.x, r.y + r.height);
		lb.closePath();

		g2.setColor(Color.BLACK);
		g2.setClip(lb);
		g2.draw(border);

		g2.setColor(Color.WHITE);
		Area area = new Area(shape);
		area.subtract(new Area(lb));
		g2.setClip(area);
		g2.draw(border);

		g2.dispose();
	}

	@Override
	public boolean contains(int x, int y) {
		// Détection clic dans le bouton
		/*Equivalent
		 * if (shape==null){
			return false;
		}
		return shape.contains(x,y);*/
		return shape == null ? false : shape.contains(x, y);
	}
}
