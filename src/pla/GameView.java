package pla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class GameView extends JPanel {

	private static final long serialVersionUID = 1L;

	Game m_game;
	GameModel m_model;
	Color m_background = Color.RED;
	long m_last;
	int m_npaints;
	int m_fps;
	int tailleCase = 40;
	private JPanel jp = null;
	private JPanel side = null;
	private _RButtonB Create_Robot = null;
	private _RButtonB Break = null;
	private _RButtonB Tour = null;
	private JTextField jtf = null;
	private JProgressBar Hero_HealthBar = null;
	private JProgressBar Base_HealthBar = null;
	private JProgressBar EBase_HealthBar = null;
	private JLabel logo = null;
	private JLabel joueur1 = null;

	GridBagConstraints gc = new GridBagConstraints();
	GridBagConstraints sideg = new GridBagConstraints();

	private JTextField setTextField() {
		if (jtf == null) {
			jtf = new JTextField();
			sideg.gridx = 0;
			sideg.gridy = 4;
			sideg.gridheight = 1;
			sideg.fill = GridBagConstraints.HORIZONTAL;
		}
		return jtf;
	}

	private JPanel build() {
		if (jp == null) {
			setPanel().add(setLogo(), gc);
			setPanel().add(setPlayer(), gc);
			setPanel().add(setBarHero(), gc);
			setPanel().add(setBarBase(), gc);
			setPanel().add(setEBarBase(), gc);
			setPanel().add(setBreak(), gc);
			setPanel().add(setTour(), gc);
			return setPanel();
		}
		setPanelE().add(setCreate_Robot(), sideg);
		setPanelE().add(setTextField(), sideg);
		return setPanelE();

	}

	private JPanel setPanel() {
		if (jp == null) {
			System.out.println("panel");
			jp = new JPanel();
			jp.setLayout(new GridBagLayout());
			jp.setBackground(Color.white);
			gc.ipady = gc.anchor = GridBagConstraints.CENTER;
			gc.insets = new Insets(2, 2, 2, 2);
			gc.weightx = 5;
			/* weightx définit le nombre de cases en ordonnée */
			gc.weighty = 2;
			gc.gridheight = 1;

		}
		return jp;
	}

	private JPanel setPanelE() {
		if (side == null) {
			side = new JPanel();
			side.setLayout(new GridBagLayout());
			side.setBackground(Color.WHITE);
			sideg.ipady = sideg.anchor = GridBagConstraints.FIRST_LINE_START;
			// sideg.insets = new Insets(2,2,2,2);
			/* weightx définit le nombre de cases en abscisse */
			sideg.weightx = 2;
			/* weightx définit le nombre de cases en ordonnée */
			sideg.weighty = 5;

		}
		return side;
	}

	public JProgressBar setBarHero() {
		if (Hero_HealthBar == null) {
			gc.gridx = 1;
			gc.gridy = 1;
			gc.gridheight = 1;
			System.out.println("Bar hero");
			Hero_HealthBar = new JProgressBar();
			Hero_HealthBar.setString("0/1000");
			Hero_HealthBar.setStringPainted(true);
			Hero_HealthBar.setValue(500);
			Hero_HealthBar.setMaximum(1000);
			Hero_HealthBar.setBackground(Color.red);
		}
		return Hero_HealthBar;
	}

	public JProgressBar setBarBase() {
		if (Base_HealthBar == null) {
			gc.gridx = 2;
			gc.gridy = 0;
			gc.gridheight = 1;
			System.out.println("bar base");
			Base_HealthBar = new JProgressBar();
			Base_HealthBar.setString("0/1000");
			Base_HealthBar.setStringPainted(true);
			Base_HealthBar.setValue(700);
			Base_HealthBar.setMaximum(1000);
			Base_HealthBar.setBackground(Color.darkGray);
			Base_HealthBar.setVisible(true);
		}
		return Base_HealthBar;
	}

	public JProgressBar setEBarBase() {
		if (EBase_HealthBar == null) {

			gc.gridx = 2;
			gc.gridy = 1;
			gc.gridheight = 1;
			EBase_HealthBar = new JProgressBar();
			EBase_HealthBar.setString("0/1000");
			EBase_HealthBar.setStringPainted(true);
			EBase_HealthBar.setValue(500);
			EBase_HealthBar.setMaximum(500);
			EBase_HealthBar.setBackground(Color.CYAN);
			EBase_HealthBar.setVisible(true);
		}
		return EBase_HealthBar;
	}

	public JLabel setLogo() {
		if (logo == null) {
			gc.gridx = 0;
			gc.gridy = 0;
			gc.gridheight = 2;

			ImageIcon background = new ImageIcon("PLA\\Resources\\Logo.png");
			logo = new JLabel();
			logo.setIcon(background);

			System.out.println("logo");
		}
		return logo;
	}

	public JLabel setPlayer() {
		InputStream is = GameView.class.getResourceAsStream("Supersonic.ttf");
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			joueur1 = new JLabel("Joueur 1");
			Font sizedFont = font.deriveFont(18f);
			joueur1.setFont(sizedFont);
			gc.gridheight = 1;
			gc.gridx = 1;
			gc.gridy = 0;
			System.out.println("Player");
			// j.add(joueur1, gc);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ldklfgnbldjfno");
			e.printStackTrace();
		}
		return joueur1;
	}

	public _RButtonB setBreak() {
		if (Break == null) {
			gc.gridx = 3;
			gc.gridy = 1;
			gc.gridheight = 1;
			Break = new _RButtonB("Have a kitkat");
		}
		System.out.println("Break");
		return Break;
	}

	public _RButtonB setCreate_Robot() {
		if (Create_Robot == null) {
			Create_Robot = new _RButtonB("Create Robot");
			Create_Robot.setForeground(Color.RED);
			Font font2 = new Font("American Typewriter", Font.PLAIN, 12);
			Create_Robot.setFont(font2);
			Create_Robot.setColors(Color.BLACK, Color.white, Color.white, Color.pink.darker());
		}
		System.out.println("robot");
		return Create_Robot;
	}

	public _RButtonB setTour() {
		if (Tour == null) {
			gc.gridheight = 1;
			gc.gridx = 4;
			gc.gridy = 1;
			Tour = new _RButtonB("Tour");
			System.out.println("tour");
		}

		return Tour;
	}

	GameView(Game game, GameModel model, GameController ctr) {
		super(true);
		m_game = game;
		m_model = model;
		this.setLayout(new BorderLayout());
		this.add(build(), java.awt.BorderLayout.SOUTH);
		this.add(build(), java.awt.BorderLayout.EAST);

		// permet de récupérer les événements clavier et souris
		addKeyListener(ctr);
		addMouseListener(ctr);
		addMouseMotionListener(ctr);
		// grab the focus on this JPanel, meaning keyboard events
		// are coming to our controller. Indeed, the focus controls
		// which part of the overall GUI receives the keyboard events.
		setFocusable(true);
		requestFocusInWindow();
		grabFocus();
		System.out.println("GameView cree");
	}

	// incrémente une variable à chaque raffraichissement d'écran pour calculer
	// les fps
	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, "npaints=" + m_npaints);
		// System.out.println("npaints="+m_npaints);
		m_npaints++;
	}

	// pour afficher des objets sur notre fenetre
	public void paintComponent(Graphics g) {
		System.out.println("Paint Component appel�");
		computeFPS();

		// Quadrillage de la map
		g.setColor(Color.BLACK);
		for (int i = 1; i < 21; i++) {
			g.drawLine(i * tailleCase, 0, i * tailleCase, 480);
		}
		for (int i = 1; i <= 12; i++) {
			g.drawLine(0, i * tailleCase, 800, i * tailleCase);
		}

		// Base des 2 joueurs
		g.setColor(Color.RED);
		g.fillRect(0, 10 * tailleCase, 2 * tailleCase, 2 * tailleCase);
		g.setColor(Color.BLUE);
		g.fillRect(18 * tailleCase, 0, 2 * tailleCase, 2 * tailleCase);

		// Quelques obstacles
		g.setColor(Color.BLACK);
		g.fillRect(4 * tailleCase, 3 * tailleCase, 1 * tailleCase, 3 * tailleCase);
		g.fillRect(5 * tailleCase, 3 * tailleCase, 2 * tailleCase, 1 * tailleCase);
		g.fillRect(13 * tailleCase, 8 * tailleCase, 3 * tailleCase, 1 * tailleCase);
		g.fillRect(15 * tailleCase, 6 * tailleCase, 1 * tailleCase, 2 * tailleCase);
		g.fillRect(9 * tailleCase, 5 * tailleCase, 2 * tailleCase, 2 * tailleCase);

	}
}
