package schachkame.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import schachkame.Bauer;
import schachkame.Dame;
import schachkame.Feld;

/**
 * Diese Klasse repraesentiert das grafische Schachbrett
 * 
 * @author Karsten Menzel
 *
 */
public class GUISchachbrett extends JPanel implements ActionListener {
	/**
	 * Auf diesem Objekt werden Methoden des Objekts aufgerufen - s. Konstruktor  
	 */
	private Schachbrett schachbrett = null;
	/**
	 * In diesem Button-Array werden identische Button-Objekte, die dem Grid-Layout
	 * hinzugefuegt wurden gespeichert
	 * KONZEPT:
	 * 1. Gib mir den Index des Buttons aus dem Array, das dem geklickten Button-Objekt
	 * im Grid.Layout entspricht
	 * 2. transformiere X/Y in notation
	 * 3. ziehe Figur(vonNotation, nachNotation) auf dem funktionalen Schachbrett: felder[][] in der Schachbrett-Klasse
	 * 4. ziehe Figur im Button-Array
	 * 5. ziehe Figur im Grid-Layout
	 *   
	 */
	private JButton[][] buttons = new JButton[8][8];
	/**
	 * 64 Buttons mit Namen der Anfangsnotation
	 */
	private JButton h1;
	private JButton g1;
	private JButton f1;
	private JButton e1;
	private JButton d1;
	private JButton c1;
	private JButton b1;
	private JButton a1;
	private JButton h2;
	private JButton g2;
	private JButton f2;
	private JButton e2;
	private JButton d2;
	private JButton c2;
	private JButton b2;
	private JButton a2;
	private JButton h3;
	private JButton g3;
	private JButton f3;
	private JButton e3;
	private JButton d3;
	private JButton c3;
	private JButton b3;
	private JButton a3;
	private JButton h4;
	private JButton g4;
	private JButton f4;
	private JButton e4;
	private JButton d4;
	private JButton c4;
	private JButton b4;
	private JButton a4;
	private JButton h5;
	private JButton g5;
	private JButton f5;
	private JButton e5;
	private JButton d5;
	private JButton c5;
	private JButton b5;
	private JButton a5;
	private JButton h6;
	private JButton g6;
	private JButton f6;
	private JButton e6;
	private JButton d6;
	private JButton c6;
	private JButton b6;
	private JButton a6;
	private JButton h8;
	private JButton g8;
	private JButton f8;
	private JButton e8;
	private JButton d8;
	private JButton c8;
	private JButton b8;
	private JButton a8;
	private JButton h7;
	private JButton g7;
	private JButton f7;
	private JButton e7;
	private JButton d7;
	private JButton c7;
	private JButton b7;
	private JButton a7;
	
	// ANFANG dieser Block wird beim Auswerten der evaluateClicks()-Methode verwendet 
	private boolean quelleGueltig;
	private JButton tempButtonSrc = new JButton();
	private JButton tempButtonTrg = new JButton();
	private int count = 0;
	private Point indexXY = null;
	private int x;
	private int y;
	private Icon iconLeer = null;	
	private String zugVon = "";
	private String zugNach = "";
	private Feld feldFrom;
	private Feld feldTo;
	// ENDE
	
	 // Um die Zugliste und weitere Componenten zu modfizieren brauchen wir die Refs aus der GuiMAinWindow-Klasse
	/**
	 * das östliche Panel mit dem Textfeld als Zugliste
	 */
	private JPanel rechts;
	/**
	 * beherbergt die das Textfeld mit der Zugliste
	 */
	private JTextArea teZugliste;
	/**
	 * Textfeld zur Ausgabe von Ereignissen
	 */
	private JTextField tfInfo;
	/**
	 * Ausgabe Spielername von schwarz
	 */
	private JTextField textSpielerSchwarz;
	/**
	 * Ausgabe Spielername von weiss
	 */
	private JTextField textSpielerWeiss;
	
	@Deprecated
	public GUISchachbrett() {
		setLayout(new GridLayout(8,8,5,5));
		zusammenstellen();
	}
	/**
	 * 	 * Der Konstruktor bekommt ein Objekt in dem verschiedene Methoden
	 * u.a. zum Abgleich und zum ziehen der Figur
	 * @param schachbrett 2 dim felder-array
	 * @param rechts panel mit zugliste
	 * @param teZugliste zugliste
	 * @param tfInfo info-feld
	 * @param textSpielerSchwarz text-feld  
	 * @param textSpielerWeiss text-feld
	 */
	public GUISchachbrett(Schachbrett schachbrett, JPanel rechts, JTextArea teZugliste, JTextField tfInfo,
			JTextField textSpielerSchwarz, JTextField textSpielerWeiss) {
		this.textSpielerSchwarz = textSpielerSchwarz;
		this.textSpielerWeiss = textSpielerWeiss;
		this.schachbrett = schachbrett;
		this.rechts = rechts;
		this.teZugliste = teZugliste;
		this.tfInfo = tfInfo;
		setLayout(new GridLayout(8,8,1,1));
		zusammenstellen();
	}
	private void zusammenstellen() {
		ImageIcon icon = null;
		// weisse Figuren aufstellen
		try {
		  Image image = ImageIO.read(new File("res/img/turm-weiss.png"));
		  icon = new ImageIcon(image);
		}
		catch(IOException e) {
		  e.printStackTrace();
		}		
		h1 = new JButton("H1",icon);
		try {
			  Image image = ImageIO.read(new File("res/img/springer-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}			
		g1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/laeufer-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}			
		f1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/koenig-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
		e1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/dame-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
		d1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/laeufer-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
		c1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/springer-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
		b1 = new JButton(icon);
		try {
			  Image image = ImageIO.read(new File("res/img/turm-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
		a1 = new JButton("A1",icon);
		try {
			  Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			  icon = new ImageIcon(image);
			}
			catch(IOException e) {
			  e.printStackTrace();
			}		
		h2 = new JButton("H2",icon);//0
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		f2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		e2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		d2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		b2 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-weiss.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a2 = new JButton("A2", icon);

		// leere Felder
		h3 = new JButton("H3");
		g3 = new JButton();
		f3 = new JButton();
		e3 = new JButton();
		d3 = new JButton();
		c3 = new JButton();
		b3 = new JButton();
		a3 = new JButton("A3");
		h4 = new JButton("H4");
		g4 = new JButton();
		f4 = new JButton();
		e4 = new JButton();
		d4 = new JButton();
		c4 = new JButton();
		b4 = new JButton();
		a4 = new JButton("A4");
		h5 = new JButton("H5");
		g5 = new JButton();
		f5 = new JButton();
		e5 = new JButton();
		d5 = new JButton();
		c5 = new JButton();
		b5 = new JButton();
		a5 = new JButton("A5");
		h6 = new JButton("H6");
		g6 = new JButton();
		f6 = new JButton();
		e6 = new JButton();
		d6 = new JButton();
		c6 = new JButton();
		b6 = new JButton();
		a6 = new JButton("A6");
		
		// schwarze Figuren aufstellen
		try {
			Image image = ImageIO.read(new File("res/img/turm-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		h8 = new JButton("H8", icon);// 0
		try {
			Image image = ImageIO.read(new File("res/img/springer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/laeufer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		f8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/koenig-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		e8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/dame-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		d8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/laeufer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/springer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		b8 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/turm-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a8 = new JButton("A8", icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		h7 = new JButton("H7", icon);// 0
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		f7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		e7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		d7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		b7 = new JButton(icon);
		try {
			Image image = ImageIO.read(new File("res/img/bauer-schwarz.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		a7 = new JButton("A7", icon);
		
		//buttons dem GRID-LAYOUT hinzufügen
		add(a8);
		add(b8);
		add(c8);
		add(d8);
		add(e8);
		add(f8);
		add(g8);
		add(h8);
		add(a7);
		add(b7);
		add(c7);
		add(d7);
		add(e7);
		add(f7);
		add(g7);
		add(h7);
		add(a6);
		add(b6);
		add(c6);
		add(d6);
		add(e6);
		add(f6);
		add(g6);
		add(h6);
		add(a5);
		add(b5);
		add(c5);
		add(d5);
		add(e5);
		add(f5);
		add(g5);
		add(h5);
		add(a4);
		add(b4);
		add(c4);
		add(d4);
		add(e4);
		add(f4);
		add(g4);
		add(h4);
		add(a3);
		add(b3);
		add(c3);
		add(d3);
		add(e3);
		add(f3);
		add(g3);
		add(h3);	
		add(a2);
		add(b2);
		add(c2);
		add(d2);
		add(e2);
		add(f2);
		add(g2);
		add(h2);	
		add(a1);
		add(b1);
		add(c1);
		add(d1);
		add(e1);
		add(f1);
		add(g1);
		add(h1);
		
		//buttons dem buttons-Array hinzufügen zur Lokalisierung des Buttons vom Grid-Layout im Array
		// und als Grundlage zum Ziehen einer Figur!
		buttons[0][7] = h1;
		buttons[0][6] = g1;
		buttons[0][5] = f1;
		buttons[0][4] = e1;
		buttons[0][3] = d1;
		buttons[0][2] = c1;
		buttons[0][1] = b1;
		buttons[0][0] = a1;

		buttons[1][7] = h2;
		buttons[1][6] = g2;
		buttons[1][5] = f2;
		buttons[1][4] = e2;
		buttons[1][3] = d2;
		buttons[1][2] = c2;
		buttons[1][1] = b2;
		buttons[1][0] = a2;
		
		buttons[2][7] = h3;
		buttons[2][6] = g3;
		buttons[2][5] = f3;
		buttons[2][4] = e3;
		buttons[2][3] = d3;
		buttons[2][2] = c3;
		buttons[2][1] = b3;
		buttons[2][0] = a3;
		
		buttons[3][7] = h4;
		buttons[3][6] = g4;
		buttons[3][5] = f4;
		buttons[3][4] = e4;
		buttons[3][3] = d4;
		buttons[3][2] = c4;
		buttons[3][1] = b4;
		buttons[3][0] = a4;
		
		buttons[4][7] = h5;
		buttons[4][6] = g5;
		buttons[4][5] = f5;
		buttons[4][4] = e5;
		buttons[4][3] = d5;
		buttons[4][2] = c5;
		buttons[4][1] = b5;
		buttons[4][0] = a5;
		
		buttons[5][7] = h6;
		buttons[5][6] = g6;
		buttons[5][5] = f6;
		buttons[5][4] = e6;
		buttons[5][3] = d6;
		buttons[5][2] = c6;
		buttons[5][1] = b6;
		buttons[5][0] = a6;
		
		buttons[6][7] = h7;
		buttons[6][6] = g7;
		buttons[6][5] = f7;
		buttons[6][4] = e7;
		buttons[6][3] = d7;
		buttons[6][2] = c7;
		buttons[6][1] = b7;
		buttons[6][0] = a7;
		
		buttons[7][7] = h8;
		buttons[7][6] = g8;
		buttons[7][5] = f8;
		buttons[7][4] = e8;
		buttons[7][3] = d8;
		buttons[7][2] = c8;
		buttons[7][1] = b8;
		buttons[7][0] = a8;
		
		setBackgroundOnButtons();
		
		h1.addActionListener(this);
		g1.addActionListener(this);
		f1.addActionListener(this);
		e1.addActionListener(this);	
		d1.addActionListener(this);
		c1.addActionListener(this);
		b1.addActionListener(this);
		a1.addActionListener(this);
		h2.addActionListener(this);
		g2.addActionListener(this);
		f2.addActionListener(this);
		e2.addActionListener(this);	
		d2.addActionListener(this);
		c2.addActionListener(this);
		b2.addActionListener(this);
		a2.addActionListener(this);
		h3.addActionListener(this);
		g3.addActionListener(this);
		f3.addActionListener(this);
		e3.addActionListener(this);	
		d3.addActionListener(this);
		c3.addActionListener(this);
		b3.addActionListener(this);
		a3.addActionListener(this);
		h4.addActionListener(this);
		g4.addActionListener(this);
		f4.addActionListener(this);
		e4.addActionListener(this);	
		d4.addActionListener(this);
		c4.addActionListener(this);
		b4.addActionListener(this);
		a4.addActionListener(this);
		h5.addActionListener(this);
		g5.addActionListener(this);
		f5.addActionListener(this);
		e5.addActionListener(this);	
		d5.addActionListener(this);
		c5.addActionListener(this);
		b5.addActionListener(this);
		a5.addActionListener(this);
		h6.addActionListener(this);
		g6.addActionListener(this);
		f6.addActionListener(this);
		e6.addActionListener(this);	
		d6.addActionListener(this);
		c6.addActionListener(this);
		b6.addActionListener(this);
		a6.addActionListener(this);
		h7.addActionListener(this);
		g7.addActionListener(this);
		f7.addActionListener(this);
		e7.addActionListener(this);	
		d7.addActionListener(this);
		c7.addActionListener(this);
		b7.addActionListener(this);
		a7.addActionListener(this);
		h8.addActionListener(this);
		g8.addActionListener(this);
		f8.addActionListener(this);
		e8.addActionListener(this);	
		d8.addActionListener(this);
		c8.addActionListener(this);
		b8.addActionListener(this);
		a8.addActionListener(this);
		
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				addMouseOverAufButtons(this.buttons[i][j]);
			}
			System.out.println(" ");
		}
	}

	/**
	 * Diese Methode wertet die Clicks aus und veranlasst das Ziehen der Figuren
	 * 
	 * @param component der geklickte Button
	 */
	public void evaluateClicks(JComponent component) {
		System.out.println("ANZEIGE BEIM ersten Klick");
		if (schachbrett.getPartieEnde()) {

		} else {
			// test-ausgabe
			schachbrett.zeigeSchachbrett();
			if (count % 2 == 1) {
				tempButtonSrc = (JButton) component;

				indexXY = new Point(getIndexFromButton(tempButtonSrc));
				zugVon = schachbrett.transformiereXYInNotation((int) indexXY.getX(), (int) indexXY.getY());
				if (!zugVon.equals("")) {
					// zur Bestimmung ob, zB Promotion
					feldFrom = schachbrett.getFeld((int) indexXY.getX(), (int) indexXY.getY());
					quelleGueltig = true;
				}
			}
			if (count % 2 == 0) {
				tempButtonTrg = (JButton) component;
				System.out.println("count gerade: " + count);
				System.out.println("component " + tempButtonTrg);

				if (quelleGueltig) {
					indexXY = new Point(getIndexFromButton(tempButtonTrg));
					zugNach = schachbrett.transformiereXYInNotation((int) indexXY.getX(), (int) indexXY.getY());
					if (!zugNach.equals("")) {
						System.out.println("VORHER zugVon: " + zugVon + "/ zugNach: " + zugNach);
						if (schachbrett.zieheFigur(zugVon, zugNach)) {
							// zur Bestimmung ob, zB Promotion
							feldTo = schachbrett.getFeld((int) indexXY.getX(), (int) indexXY.getY());
							// Promotion von Schwarz erkannt: Bauer wird in Dame verwandelt
							if (feldFrom.getFigur() instanceof Bauer && feldTo.getFigur() instanceof Dame
									&& feldTo.getFigur().getFarbe().equals(Schachbrett.SCHWARZ)) {
								Icon icon = null;
								try {
									Image image = ImageIO.read(new File("res/img/dame-schwarz.png"));
									icon = new ImageIcon(image);
									((AbstractButton) component).setIcon(icon);
								} catch (IOException e) {
									e.printStackTrace();
								}

							} else if (feldFrom.getFigur() instanceof Bauer && feldTo.getFigur() instanceof Dame
									&& feldTo.getFigur().getFarbe().equals(Schachbrett.WEISS)) {
								Icon icon = null;
								try {
									Image image = ImageIO.read(new File("res/img/dame-weiss.png"));
									icon = new ImageIcon(image);
									((AbstractButton) component).setIcon(icon);
								} catch (IOException e) {
									e.printStackTrace();
								}

							} else
								// Zielfeld bekommt Icon vom Quellfeld
								((AbstractButton) component).setIcon(tempButtonSrc.getIcon());
							// Quellfeld bekommt leeres Icon
							tempButtonSrc.setIcon(iconLeer);
							// testausgabe auf console
							schachbrett.zeigeSchachbrett();
							if (Schachbrett.getAnzahlTeilzuege() % 2 == 1) {
								if (Schachbrett.getAnzahlZuege() == 0) {
									teZugliste.setText("1. " + Schachbrett.getLetztenTeilzug().getTeilzug());
								} else {
									String tempString = teZugliste.getText();

									teZugliste.setText(tempString + (Schachbrett.getAnzahlTeilzuege() / 2 + 1) + ". "
											+ Schachbrett.getLetztenTeilzug().getTeilzug());
								}
							} else {
								teZugliste.setText(schachbrett.getZuglisteZeilenweise());

							}
							if (schachbrett.isSchach())//
								tfInfo.setText(schachbrett.getSchachMeldung());
							if (!schachbrett.isSchach())
								tfInfo.setText("");
							rechts.repaint();
							// Große Rochade von Weiss erkannt und den Turm noch bewegen
							if (zugNach.equals("c1") && zugVon.equals("e1")) {
								d1.setIcon(a1.getIcon());
								a1.setIcon(iconLeer);

							}
							// Kleine Rochade von Weiss erkannt und den Turm noch bewegen
							if (zugNach.equals("g1") && zugVon.equals("e1")) {
								f1.setIcon(h1.getIcon());
								h1.setIcon(iconLeer);

							}
							// Große Rochade von Schwarz erkannt und den Turm noch bewegen
							if (zugNach.equals("c8") && zugVon.equals("e8")) {
								d8.setIcon(a8.getIcon());
								a8.setIcon(iconLeer);

							}
							// Kleine Rochade von Schwarz erkannt und den Turm noch bewegen
							if (zugNach.equals("g8") && zugVon.equals("e8")) {
								f8.setIcon(h8.getIcon());
								h8.setIcon(iconLeer);
							}
							// enPassant erkennen und umsetzten
							// da schon der Zug in der Klasse Bauer schon "validiert " wurde ist hier nur
							// eine verkürzte Prüfung nötig
							if (feldFrom.getFigur() instanceof Bauer && feldTo.getFigur() instanceof Bauer) {
								if (feldTo.getFigur().getFarbe().equals(schachbrett.WEISS) && feldTo.getX() == 5) {
									buttons[feldTo.getX() - 1][feldTo.getY()].setIcon(iconLeer);
								}
								if (feldTo.getFigur().getFarbe().equals(schachbrett.SCHWARZ) && feldTo.getX() == 2) {
									buttons[feldTo.getX() + 1][feldTo.getY()].setIcon(iconLeer);
								}
							}
							// markiere farblich das TextFeld des betreffenden Spielers
							if (schachbrett.getWerIstAmZug().getFigurenFarbe().equals(schachbrett.WEISS)) {
								textSpielerWeiss.setBackground(Color.GREEN);
								textSpielerSchwarz.setBackground(Color.RED);
							} else if (schachbrett.getWerIstAmZug().getFigurenFarbe().equals(schachbrett.SCHWARZ)) {
								textSpielerWeiss.setBackground(Color.RED);
								textSpielerSchwarz.setBackground(Color.GREEN);
							}
							if (schachbrett.getPartieEnde()) {
								Feld koenigFeld;
								koenigFeld = schachbrett.getKoenigStehtImSchach();
								tfInfo.setText(schachbrett.getSchachMattMeldung());
								textSpielerSchwarz.setBackground(Color.GRAY);
								textSpielerWeiss.setBackground(Color.GRAY);
								JOptionPane.showMessageDialog(this.getParent(), schachbrett.getSchachMattMeldung()
										+ "\n" + schachbrett.endeMeldung(koenigFeld));
							}
//							
						} else {//test-ausgabe
							schachbrett.zeigeSchachbrett();
						}
					}
				}
				quelleGueltig = false;
				tempButtonSrc = null;
				tempButtonTrg = null;
				zugVon = "";
				zugNach = "";
			}
		}
	}

	/**
	 * Diese Methode ermittelt die Position/Index des Grid-Layout-Buttons im Array
	 * Dient als Eingabe/Uebergabe im Backend(felder-Array)
	 * 
	 * @param button Button-Referenz aus dem Grid-Layout
	 * @return Punkt-Koordinate des Buttons (x = i, y = j)
	 */
	public Point getIndexFromButton(JButton button) {
		Point result = null;
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if (this.buttons[i][j].equals(button)) {
					result = new Point(i, j);
				}
			}
			System.out.println(" ");
		}
		return result;
	}

	/**
	 * Diese Methode markiert die kontrollierten Felder einer Figur farblich (Cyan)
	 * 
	 * @param button Quell-Button
	 */
	public void addMouseOverAufButtons(JButton button) {
		button.addMouseListener(new MouseAdapter() {
			ArrayList<Feld> kontrollierteFelder;
			
			public void mouseEntered(MouseEvent me) {
				Point indexXY = new Point(getIndexFromButton(button));
				Feld[][] felder = schachbrett.getSpielfelder();
				kontrollierteFelder = felder[(int) indexXY.getX()][(int) indexXY.getY()].getFigur()
						.getKontrollierteFelder(felder[(int) indexXY.getX()][(int) indexXY.getY()], felder, false, true);
				if (kontrollierteFelder != null)
					for (Feld feld : kontrollierteFelder) {
						buttons[feld.getX()][feld.getY()].setBackground(Color.CYAN);
					}
			}
			public void mouseExited(MouseEvent me) {
				setBackgroundOnButtons();
			}
		});
	}
	/**
	 * Diese Methode setzt die Feldfarbe (der Buttons)
	 */
	public void setBackgroundOnButtons() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if (i % 2 == 0 && j % 2 == 1) {
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
				}
				if (i % 2 == 0 && j % 2 == 0) {
					buttons[i][j].setBackground(Color.ORANGE);
				}
				if (i % 2 == 1 && j % 2 == 1) {
					buttons[i][j].setBackground(Color.ORANGE);
				}
				if (i % 2 == 1 && j % 2 == 0) {
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
			System.out.println(" ");
		}
	}
	@Override
	public void actionPerformed(ActionEvent klick) {
		count++;
		evaluateClicks((JComponent) klick.getSource());
	}

}
