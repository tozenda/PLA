package pla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private _RPanel jp = null;
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
	private BufferedImage iHero = null;
	private BufferedImage iObstacle = null;
	private BufferedImage iTree = null;
	private BufferedImage iBase = null;
	private BufferedImage iSkill = null;
	BufferedImage iRobot_settings= null;
	Color Gold = new Color(229,186,27);

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
		setPanelE().add(setInventory(),sideg);
		return setPanelE();

	}

	private _RPanel setPanel() {
		if (jp == null) {
			System.out.println("panel");
			jp = new _RPanel();
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
			Hero_HealthBar.setBackground(new Color(216,40,82));
		}
		return Hero_HealthBar;
	}
	
	public JScrollPane setInventory(){
		sideg.gridx=0;
		sideg.gridy=2;
		sideg.gridheight=2;
		JTextArea textArea = new JTextArea(5, 5);
		JScrollPane scrollableTextArea = new JScrollPane(textArea);

		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scrollableTextArea;
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
			BufferedImage iLogo = null;
			try{
				//iLogo=ImageIO.read(new File("C:\\Users\\Najwa\\Git_Clean\\PLA\\src\\pla\\logo.png"));
				iLogo=ImageIO.read(new File("Resources/Logo.png"));
				
				logo = new JLabel();
				
				logo.setIcon(new ImageIcon(new ImageIcon(iLogo).getImage().getScaledInstance(160,50, java.awt.Image.SCALE_SMOOTH)));

			}
			catch (Exception e){
				e.printStackTrace();
			}
			ImageIcon background = new ImageIcon("PLA\\src\\pla\\logo.png");
			
			System.out.println("logo");
		}
		return logo;
	}

	public Font setFont(float size){
		InputStream is = GameView.class.getResourceAsStream("Supersonic.ttf");
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			Font sizedFont = font.deriveFont(size);
			//joueur1 = new JLabel("Joueur 1");
			return sizedFont;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return font;
	}
	
	public JLabel setPlayer() {
			joueur1 = new JLabel("Joueur1");
			joueur1.setFont(setFont(18f));
			joueur1.setForeground(Color.white);
			gc.gridheight = 1;
			gc.gridx = 1;
			gc.gridy = 0;
			System.out.println("Player");
			// j.add(joueur1, gc);
		return joueur1;
	}

	public _RButtonB setBreak() {
		if (Break == null) {
			gc.gridx = 3;
			gc.gridy = 1;
			gc.gridheight = 1;
			Break = new _RButtonB("Have a kitkat");
			Break.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					GameModel.breakk();
				}
			});
		}
		System.out.println("Break");
		return Break;
	}

	public _RButtonB setCreate_Robot() {
		if (Create_Robot == null) {
			Create_Robot = new _RButtonB("Create Robot");
			Create_Robot.setForeground(Color.WHITE);
			//Font font2 = new Font("American Typewriter", Font.PLAIN, 12);
			Create_Robot.setFont(setFont(16f));
			Create_Robot.setColors(new Color(2,27,47),Gold);
			try {
				iRobot_settings=ImageIO.read(new File("Resources/robot_settings.png"));
				Create_Robot.setIcon(new ImageIcon(new ImageIcon(iRobot_settings).getImage().getScaledInstance(30,20, java.awt.Image.SCALE_SMOOTH)));
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Create_Robot.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					GameModel.Create_Robot();
				}
			});
		}
		return Create_Robot;
	}

	public _RButtonB setTour() {
		if (Tour == null) {
			gc.gridheight = 1;
			gc.gridx = 4;
			gc.gridy = 1;
			Tour = new _RButtonB("Tour");
			System.out.println("tour");
			Tour.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					GameModel.Tour();
				}
			});
		}
		System.out.println("Break");
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

		int nbrCaseX = 20;
		int nbrCaseY = 12;
		computeFPS();
		/*Image image;
		ImageIcon ii = new ImageIcon("../img/hero.png");
		//image = ii.getImage();
		g.drawImage(image, 0, 0, null);*/
		// Quadrillage de la map
		BufferedImage iBande;
		try {
			iBande = ImageIO.read(new File("Resources/Bande.png"));
			g.drawImage(iBande,0,0,this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		g.setColor(Color.BLACK);
		for (int i = 0; i <= 21; i++) {
			g.drawLine(i * tailleCase, 0, i * tailleCase, 480);
		}
		for (int i = 0; i <= 12; i++) {
			g.drawLine(0, i * tailleCase, 800, i * tailleCase);
		}

		for (int i = 0; i < nbrCaseX; i++) {
			for (int j = 0; j < nbrCaseY; j++) {
				Case c = m_model.map.getCase(i, j);
				Observables obs = c.getContenu();
				if (obs.isObstacles()) {
					
					try {
					    iObstacle = ImageIO.read(new File("Resources/obstacl.png"));
					    g.drawImage(iObstacle, i * tailleCase + 1, j * tailleCase + 1, this);
					} catch (IOException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
					//g.setColor(Color.BLACK);
					//g.fillRect(i * tailleCase + 1, j * tailleCase + 1, tailleCase - 1, tailleCase - 1);
				}
				else if (obs.isHeros()) {
					try {
					    iHero = ImageIO.read(new File("Resources/hero.png"));
					    g.drawImage(iHero, i * tailleCase + 1, j * tailleCase + 1, 39,39, this);
					    //g.drawImage(iHero, i * tailleCase + 1, j * tailleCase + 1, this);
					} catch (IOException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
					//g.setColor(Color.RED);
					//g.fillRect(i * tailleCase + 1, j * tailleCase + 1, tailleCase - 1, tailleCase - 1);
				}
				else if (obs.isBase()) {
					g.setColor(Color.ORANGE);
					g.fillRect(i * tailleCase + 1, j * tailleCase + 1, tailleCase - 1, tailleCase - 1);
				}
				else if (obs.isRobot()) {
					g.setColor(Color.cyan);
					g.fillRect(i * tailleCase + 1, j * tailleCase + 1, tailleCase - 1, tailleCase - 1);
				}
				else {
					g.setColor(Color.white);
					//g.fillRect(i * tailleCase + 1, j * tailleCase + 1, tailleCase - 1, tailleCase - 1);
				}
			}
		}

		// // Base des 2 joueurs
		// g.setColor(Color.RED);
		// g.fillRect(0, 10 * tailleCase, 2 * tailleCase, 2 * tailleCase);
		// g.setColor(Color.BLUE);
		// g.fillRect(18 * tailleCase, 0, 2 * tailleCase, 2 * tailleCase);
		//
		// // Quelques obstacles
		// g.setColor(Color.BLACK);
		// g.fillRect(4 * tailleCase, 3 * tailleCase, 1 * tailleCase, 3 *
		// tailleCase);
		// g.fillRect(5 * tailleCase, 3 * tailleCase, 2 * tailleCase, 1 *
		// tailleCase);
		// g.fillRect(13 * tailleCase, 8 * tailleCase, 3 * tailleCase, 1 *
		// tailleCase);
		// g.fillRect(15 * tailleCase, 6 * tailleCase, 1 * tailleCase, 2 *
		// tailleCase);
		// g.fillRect(9 * tailleCase, 5 * tailleCase, 2 * tailleCase, 2 *
		// tailleCase);

	}
	/*public static void Please(){
		revalidate();
		repaint();
	}*/
}
