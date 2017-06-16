package pla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

public class GameView extends JPanel {

	private static final long serialVersionUID = 1L;
	Game m_game;
	GameModel m_model;
	Color m_background = Color.RED;
	long m_last;
	int m_npaints;
	int m_fps;
	int tailleCase = 40;
	int count = 0;
	private _RPanel jp = null;
	private JPanel side = null;
	private _RButtonB Create_Robot = null;
	private _RButtonB Break = null;
	private _RButtonB Tour = null;
	private JTextField jtf = null;
	private JProgressBar Hero_HealthBar = null; //TODO
	private JProgressBar Base_HealthBar = null;
	private JProgressBar EBase_HealthBar = null;
	private JProgressBar PointAction = null;
	private JLabel logo = null;
	private JLabel joueur1 = null;
	private JLabel NPointAction = null;
	private JLabel info = null;
	private String player = "Let's play";
	private BufferedImage Image[] = new BufferedImage[100];
	private BufferedImage Map[] = new BufferedImage[4];

	BufferedImage iRobot_settings = null;
	Color Gold = new Color(229, 186, 27);
	String Thomas = "/home/tozenda/COURS/RICM3/S6/PLA/PLA/Resources/";
	String Najwa = "Resources/";
	String Anouar = "Resources/";
	String Jo = "/home/ferreira/Bureau/POO/PLA/Resources/";
	String Paul = "home/doublean/git/PLA/Resources/";
	String Shoo = "/Users/fathinsyuhadaabubakar/Documents/gitclean/PLA/Resources/";
	String Path = Najwa;
	JTextArea textArea = null;
	JScrollPane scrollableTextArea = null;

	GridBagConstraints gc = new GridBagConstraints();
	GridBagConstraints sideg = new GridBagConstraints();

	public void load() {
		try {
			int i = 0;
			Image[i++] = ImageIO.read(new File(Path + "AugDef.png"));//1
			Image[i++] = ImageIO.read(new File(Path + "AutoDestruction.png"));//2
			Image[i++] = ImageIO.read(new File(Path + "Boost.png"));//3
			Image[i++] = ImageIO.read(new File(Path + "Contrer.png"));//4
			Image[i++] = ImageIO.read(new File(Path + "DimDef.png"));//5
			Image[i++] = ImageIO.read(new File(Path + "Etoile.png"));//6
			Image[i++] = ImageIO.read(new File(Path + "Hit.png"));//7
			Image[i++] = ImageIO.read(new File(Path + "Kamikaze.png"));//8
			Image[i++] = ImageIO.read(new File(Path + "MoveAttack.png"));//9
			Image[i++] = ImageIO.read(new File(Path + "MoveDef.png"));//10
			Image[i++] = ImageIO.read(new File(Path + "MoveRamasse.png"));//11
			Image[i++] = ImageIO.read(new File(Path + "Ou.png"));//12
			Image[i++] = ImageIO.read(new File(Path + "Poison.png"));//13
			Image[i++] = ImageIO.read(new File(Path + "Protect.png"));//14
			Image[i++] = ImageIO.read(new File(Path + "Soin.png"));//15
			Image[i++] = ImageIO.read(new File(Path + "Stun.png"));//16
			Image[i++] = ImageIO.read(new File(Path + "Sup.png"));//17
			Image[i++] = ImageIO.read(new File(Path + "Volvie.png"));//18
			Image[i++] = ImageIO.read(new File(Path + "obstacl.png"));//19
			Image[i++] = ImageIO.read(new File(Path + "logo.png"));//20
			Image[i++] = ImageIO.read(new File(Path + "robot_blue.png"));//joueur1//21
			Image[i++] = ImageIO.read(new File(Path + "map.png"));//22
			Image[i++] = ImageIO.read(new File(Path + "barrier.png"));//23
			Image[i++] = ImageIO.read(new File(Path + "base.png"));//24
			Image[i++] = ImageIO.read(new File(Path + "Base1.png"));//25
			Image[i++] = ImageIO.read(new File(Path + "Base2.png"));//26
			Image[i++] = ImageIO.read(new File(Path + "front.png"));//27
			Image[i++] = ImageIO.read(new File(Path + "kitkat.png"));//28
			Image[i++] = ImageIO.read(new File(Path + "tour.png"));//29
			Image[i++] = ImageIO.read(new File(Path + "robot_red.png"));//joueur2//30
			Map[0] = ImageIO.read(new File(Path + "11.png"));
			Map[1] = ImageIO.read(new File(Path + "12.png"));
			Map[2] = ImageIO.read(new File(Path + "21.png"));
			Map[3] = ImageIO.read(new File(Path + "22.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTextField setTextField() {
		if (jtf == null) {
			jtf = new JTextField();
			sideg.gridx = 0;
			sideg.gridy = 2;
			sideg.gridwidth = 3;
			sideg.gridheight = 1;
			sideg.anchor = GridBagConstraints.CENTER;
			sideg.fill = GridBagConstraints.HORIZONTAL;
			// jtf.setFont(setFont(12f));
			jtf.setBackground(Color.gray.brighter());
		}
		return jtf;
	}

	public String getTextField() {
		return jtf.getText();
	}

	private MiniMap setMinimap() {
		sideg.gridx = 1;
		sideg.gridy = 5;
		sideg.gridwidth = 1;
		sideg.gridheight = 2;
		sideg.anchor = GridBagConstraints.PAGE_END;
		sideg.fill = GridBagConstraints.BOTH;

		MiniMap minimap = new MiniMap("Heyxkjcnvfkj");
		Border border = minimap.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		minimap.setMaximumSize(new Dimension(140, 140));
		minimap.setMinimumSize(new Dimension(140, 140));

		minimap.setBorder(new CompoundBorder(border, margin));

		minimap.setBackground(Color.RED);
		return minimap;
	}

	private JPanel build() {
		if (jp == null) {
			setPanel().add(setLogo(), gc);
			setPanel().add(setPlayer(false), gc);
			setPanel().add(setBarHero(false), gc);
			setPanel().add(setBarBase(), gc);
			setPanel().add(setEBarBase(), gc);
			setPanel().add(setNPointAction(), gc);
			setPanel().add(setPointAction(false), gc);
			setPanel().add(setBreak(), gc);
			setPanel().add(setTour(), gc);
			return setPanel();
		}
		setPanelE().add(setCreate_Robot(), sideg);
		setPanelE().add(setTextField(), sideg);
		setPanelE().add(setInventory(), sideg);
		setPanelE().add(setInfo(), sideg);
		setPanelE().add(setMinimap(), sideg);
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
			sideg.weightx = 3;
			/* weightx définit le nombre de cases en ordonnée */
			sideg.weighty = 8;

		}
		return side;
	}


	public JProgressBar setBarHero(boolean GameBegan) {// TODO : à debuguer
		int vie = 0;
		int maxVie = 300;
		if (GameBegan) {
			if (Game.game.tourDe1) {
				vie = GameModel.heros1.pdv;
			}
			if (!Game.game.tourDe1) {
				vie = GameModel.heros2.pdv;
			}
		}
		if (Hero_HealthBar == null) {
			gc.gridx = 1;
			gc.gridy = 1;
			gc.gridheight = 1;
			System.out.println("Bar hero");
			Hero_HealthBar = new JProgressBar();
			Hero_HealthBar.setString("PDV Hero 300/300");
			Hero_HealthBar.setStringPainted(true);
			Hero_HealthBar.setValue(300);
			Hero_HealthBar.setMaximum(300);
			Hero_HealthBar.setFont(setFont(14f));
			Hero_HealthBar.setBackground(Color.white);
			Hero_HealthBar.setForeground(new Color(216, 40, 82));
		}
		else{
			Hero_HealthBar.setString(vie + "/" + maxVie);
			Hero_HealthBar.setStringPainted(true);
			Hero_HealthBar.setValue(vie);
			Hero_HealthBar.setMaximum(maxVie);
		}
		return Hero_HealthBar;
	}

	public JScrollPane setInventory() {
		if (scrollableTextArea == null) {
			sideg.gridx = 0;
			textArea = new JTextArea(2, 2);
			scrollableTextArea = new JScrollPane(textArea);
			sideg.gridy = 1;
			sideg.gridheight = 1;
			scrollableTextArea.setMaximumSize(new Dimension(140, 100));
			scrollableTextArea.setMinimumSize(new Dimension(140, 100));
			textArea.setEditable(false);
			textArea.setFont(setFont(12f));
			textArea.setForeground(Color.black);
			scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		}

		String Inventory = "\n         Inventaire vide \n";
		String s = null;

		if (Game.game != null) {
			if (Game.game.tourDe1) {
				for (HashMap.Entry<Competence, Integer> e : GameModel.heros1.getInventaire().entrySet()) {

					if (s == null) {
						s = "" + e.getKey() + "(" + e.getValue() + ")";
					} else {
						s = s + "\n" + "" + e.getKey() + "(" + e.getValue() + ")";
					}
				}
			}

			else {
				for (HashMap.Entry<Competence, Integer> e : GameModel.heros2.getInventaire().entrySet()) {

					if (s == null) {
						s = "" + e.getKey() + "(" + e.getValue() + ")";
					} else {
						s = s + "\n" + "" + e.getKey() + "(" + e.getValue() + ")";
					}
				}
			}
		}

		if (s != null) {
			try {
				textArea.getHighlighter().addHighlight(0, 56, (new DefaultHighlighter.DefaultHighlightPainter(Gold)));
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			textArea.setText(s);
		} else {
			textArea.setText(Inventory);
		}
		//System.out.println("Here's What I got : " + s);

		return scrollableTextArea;
	}

	public JProgressBar setBarBase() {// TODO
		if (Base_HealthBar == null) {
			gc.gridx = 2;
			gc.gridy = 0;
			gc.gridheight = 1;
			System.out.println("bar base");
			Base_HealthBar = new JProgressBar();
			int total_healthB1 = GameModel.map.getTotalHealthBase1();
			Base_HealthBar.setString("Pdv Base :" + total_healthB1 + "/1000");
			Base_HealthBar.setForeground(Color.gray);
			Base_HealthBar.setStringPainted(true);
			Base_HealthBar.setValue(total_healthB1);
			Base_HealthBar.setMaximum(1000);
			Base_HealthBar.setBackground(Color.darkGray);
			Base_HealthBar.setVisible(true);
		}
		return Base_HealthBar;
	}

	public JProgressBar setEBarBase() {// TODO
		if (EBase_HealthBar == null) {

			gc.gridx = 2;
			gc.gridy = 1;
			gc.gridheight = 1;
			EBase_HealthBar = new JProgressBar();
			int total_healthB2 = GameModel.map.getTotalHealthBase2();
			EBase_HealthBar.setString(total_healthB2 + "/1000");
			EBase_HealthBar.setStringPainted(true);
			EBase_HealthBar.setValue(total_healthB2);
			EBase_HealthBar.setMaximum(1000);
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
			try {
				// iLogo=ImageIO.read(new
				// File("C:\\Users\\Najwa\\Git_Clean\\PLA\\src\\pla\\logo.png"));
				iLogo = ImageIO.read(new File(Path + "logo.png"));

				logo = new JLabel();

				logo.setIcon(new ImageIcon(
						new ImageIcon(iLogo).getImage().getScaledInstance(160, 50, java.awt.Image.SCALE_SMOOTH)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("logo");
		}
		return logo;
	}

	public Font setFont(float size) {
		InputStream is = GameView.class.getResourceAsStream("Supersonic.ttf");
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			Font sizedFont = font.deriveFont(size);
			// joueur1 = new JLabel("Joueur 1");
			return sizedFont;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return font;
	}

	public JLabel setPlayer(boolean gameBegan) {
		if (joueur1 == null) {
			joueur1 = new JLabel();
			joueur1.setFont(setFont(18f));
			joueur1.setForeground(Color.white);
			gc.gridheight = 1;
			gc.gridx = 1;
			gc.gridy = 0;
			System.out.println("Player");

		} else {
			if(gameBegan){
				switch (Game.game.getState()) {
				case (1):
					player = "Joueur 1";
					break;
				case (3):
					player = "Joueur 2";
					break;
				default:
					player = "Phase d'action";
				}
			}
		}
		joueur1.setText(player);
		return joueur1;
	}

	public _RButtonB setBreak() {
		if (Break == null) {
			gc.gridx = 4;
			gc.gridy = 1;
			gc.gridheight = 1;
			Break = new _RButtonB("Have a kitkat");
			Break.setForeground(Color.WHITE);
			Break.setFont(setFont(12f));
			Break.setColors(new Color(250, 27, 47), Color.CYAN.darker());
			Break.setIcon(new ImageIcon(
					new ImageIcon(Image[27]).getImage().getScaledInstance(30, 20, java.awt.Image.SCALE_SMOOTH)));
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
			sideg.gridx = 0;
			sideg.gridy = 0;
			sideg.gridwidth = 3;
			Create_Robot = new _RButtonB("Create Robot");
			Create_Robot.setForeground(Color.WHITE);
			Create_Robot.setFont(setFont(16f));
			Create_Robot.setColors(new Color(2, 27, 47), Gold);
			try {
				iRobot_settings = ImageIO.read(new File(Path + "robot_settings.png"));
				Create_Robot.setIcon(new ImageIcon(new ImageIcon(iRobot_settings).getImage().getScaledInstance(30, 20,
						java.awt.Image.SCALE_SMOOTH)));

			} catch (IOException e1) {
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

	public JLabel setInfo() {
		if (info == null) {
			info = new JLabel(
					"<html>Right click on the <font color='rgb(128, 128, 0)'><br/>map </font> to discover what you have to deal with</html> ");
			info.setIcon(new ImageIcon(
					new ImageIcon(Path + "map.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
			Border border = info.getBorder();
			Border margin = new EmptyBorder(10, 10, 10, 10);
			// info.setBorder(new CompoundBorder(border, margin));
			info.setFont(setFont(12f));
			sideg.gridx = 0;
			sideg.gridy = 3;
			sideg.gridwidth = 2;
			sideg.gridheight = 1;
			sideg.fill = GridBagConstraints.PAGE_END;
			info.setPreferredSize(new Dimension(140, 150));
			info.setMaximumSize(new Dimension(140, 140));
			info.setMinimumSize(new Dimension(140, 140));

			// String s = "Line1 Line2 <br/> Line3";

			// jLabel1.setText (sText);
			// info = new JLabel(s);

		}
		if (GameModel.getCurrentCase() != null) {
			String s = "<html> <font color='rgb(255, 0, 0)'><br/>PDV: </font>"
					+ GameModel.getCurrentCase().getContenu().getPointdeVie();

			switch (GameModel.getCurrentCase().getContenu().toString()) {

			case ("H"):
				info.setIcon(new ImageIcon(new ImageIcon(Path + "hero.png").getImage().getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH)));
				s = "<html><font size ='5'><font color='rgb(229, 186, 27)'>Hero</font></font><br/>" + s
						+ "<br/>This is you...<br/>Ever considered plastic surgery? <br/>How sad.</html>";
				break;
			case ("O"):
				info.setIcon(new ImageIcon(new ImageIcon(Path + "barrier.png").getImage().getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH)));
				s = "<html><font color='rgb(31, 178, 163)'>Obstacle</font>" + s
						+ "<br/><br/>Oh, no...<br/>there's something <br/>on your way... <br/><br/><font color='rgb(185, 90, 148)'>How sad</font></html>";

				break;

			case ("B"):
				info.setIcon(new ImageIcon(new ImageIcon(Path + "base.png").getImage().getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH)));
				s = "<html><font color='rgb(31, 178, 163)'>Base</font>" + s
						+ "<br/>No <font color='rgb(216, 40, 82)'>princess</font>, but you <br/> still have to protect it...</html>";
				break;
			case ("R"):
				info.setIcon(new ImageIcon(new ImageIcon(Path + "robot.png").getImage().getScaledInstance(50, 50,
						java.awt.Image.SCALE_SMOOTH)));
				s = "<html><font color='rgb(31, 178, 163)'>Robot</font>" + s
						+ "<br/>This is a robot <br/>A stupid one.</html>";
				break;
			case ("Vide"):
				info.setIcon(new ImageIcon(new ImageIcon(Path + "base.png").getImage().getScaledInstance(1, 1,
						java.awt.Image.SCALE_SMOOTH)));
				s = "<html><font color='rgb(213, 178, 94)'>Emptiness</font>" + s
						+ "<br/>Nah, there's nothing<br/> here <br/> Drugs I guess?</html>";
				break;
			// TODO

			// <span style\"color: red\">" + message + "</span>

			default:
				info.setIcon(new ImageIcon(new ImageIcon(Image[GameModel.getCurrentCase().getContenu().getPic()])
						.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

				s = "<html><font color='rgb(31, 178, 163)'>" + GameModel.getCurrentCase().getContenu().toString()
						+ "</font>" + "<br/>" + GameModel.getCurrentCase().getContenu().getDescription();
				break;

			}
			info.setText(s);
			// info.setForeground(fg);

			info.setFont(setFont(12f));

		}
		GameModel.currentCase = null;
		return info;
	}

	public _RButtonB setTour() {
		if (Tour == null) {
			gc.gridheight = 1;
			gc.gridx = 4;
			gc.gridy = 0;
			Tour = new _RButtonB("Tour");
			System.out.println("tour");
			Tour.setForeground(Color.WHITE);
			Tour.setFont(setFont(12f));
			Tour.setColors(Color.CYAN.darker(), new Color(47, 27, 250));
			Tour.setIcon(new ImageIcon(
					new ImageIcon(Image[27]).getImage().getScaledInstance(30, 20, java.awt.Image.SCALE_SMOOTH)));
			Tour.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					GameModel.Tour();
				}
			});
		}
		System.out.println("Tour");
		return Tour;
	}

	public JLabel setNPointAction() {
		if (NPointAction == null) {
			NPointAction = new JLabel("Points d'action : ");
			NPointAction.setFont(setFont(12f));
			NPointAction.setForeground(Color.WHITE);
			gc.gridx = 3;
			gc.gridy = 0;
		}
		return NPointAction;
	}

	public JProgressBar setPointAction(boolean GameBegan) {
		int pda = 1000;
		int max = 0;
		if (GameBegan) {
			if (Game.game.tourDe1) {
				max = GameModel.heros1.maxPointAction;
				pda = GameModel.heros1.pointAction;
			}
			if (!Game.game.tourDe1) {
				max = GameModel.heros2.maxPointAction;
				pda = GameModel.heros2.pointAction;
			}
		}
		if (PointAction == null) {
			PointAction = new JProgressBar();
			PointAction.setString(pda + "/" + max);
			PointAction.setStringPainted(true);
			PointAction.setValue(pda);
			PointAction.setMaximum(max);
			PointAction.setForeground(Color.ORANGE);
			PointAction.setFont(setFont(14f));
			PointAction.setBackground(Color.white);
			gc.gridx = 3;
			gc.gridy = 1;
		} else {
			PointAction.setString(pda + "/" + max);
			PointAction.setStringPainted(true);
			PointAction.setValue(pda);
			PointAction.setMaximum(max);
		}
		return PointAction;
	}

	GameView(Game game, GameModel model, GameController ctr) {
		super(true);
		m_game = game;
		m_model = model;
		load();
		this.setLayout(new BorderLayout());
		this.add(build(), java.awt.BorderLayout.SOUTH);
		this.add(build(), java.awt.BorderLayout.EAST);
		this.requestFocus();

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
		m_npaints++;
	}

	// pour afficher des objets sur notre fenêtre
	public void paintComponent(Graphics g) {
		setPlayer(true);
		/*
		 * On réaffiche le JLabel info (right click) après modification
		 *
		 */

		if (m_model.getLabelmodified()) {
			setInfo();
			m_model.setLabelmodified(false);
		}
		if (count == 30) {
			setInventory();
			count = 0;
		} else {
			count++;
		}
		int nbrCaseX = 20;
		int nbrCaseY = 12;
		computeFPS();
		// Quadrillage de la map
		g.drawImage(Map[GameModel.map.getLocation() - 1], 0, 0, this);
		for (int i = 0; i < nbrCaseX; i++) {
			for (int j = 0; j < nbrCaseY; j++) {
				m_model.setFactXY();
				int Factx = m_model.getFactx();
				int Facty = m_model.getFacty();
				Case c = GameModel.map.getCase(((Factx - 1) * nbrCaseX) + i, (Facty - 1) * nbrCaseY + j);
				Observables obs = c.getContenu();
				if (obs.isHeros()) {
					Heros h = (Heros) obs;
					if (m_model.getImage(h.equipe) == null) {

						g.drawImage(Image[obs.getPic()], (i % GameModel.map.getWidth()) * tailleCase + 1,
								(j % GameModel.map.getHeight()) * tailleCase + 1, 39, 39, this);
					}

					g.drawImage(m_model.getImage(h.equipe), (i % GameModel.map.getWidth()) * tailleCase + 1,
							(j % GameModel.map.getHeight()) * tailleCase + 1, 39, 39, this);

				} else if (obs.isObstacles()) {
				} else if (obs.isVide()) {
					g.setColor(Color.white);

				} else {
					g.drawImage(Image[obs.getPic()], (i % GameModel.map.getWidth()) * tailleCase + 1,
							(j % GameModel.map.getHeight()) * tailleCase + 1, 39, 39, this);

				}
			}
		}

	}
}
