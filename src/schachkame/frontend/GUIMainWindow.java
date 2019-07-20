package schachkame.frontend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import schachkame.Zug;
import schachkame.io.DateiZugriff;
import schachkame.io.Leser;
/**
 * Diese Klasse stellt das Hauptfenster im klassischen Border-Layout
 * inkl. Menu zusammen  
 * 
 * Free PNG-Bilder von: https://icons8.com/icons/set/chess
 * 
 * @author Karsten Menzel
 *
 */
public class GUIMainWindow extends JFrame {
	Schachbrett schachbrett;
	private JPanel schachPanel; //mitte
	private JPanel nord; //spielernamen
	private JPanel ost; //zugliste
	private JPanel sued; //info-text

	
	// panel oben
	private JTextField textSpielerWeiss;
	private JTextField textSpielerSchwarz;
	
	// panel rechts
	private JTextArea textAereaZugliste;
	private JScrollPane scroll;

	
	// panel unten
	private JTextField textInfo = new JTextField();
	
	private JMenuBar menuLeiste = new JMenuBar();
	
	//Einträge in die Menuleiste
	private JMenu dateiMenu = new JMenu("Datei");
	private JMenu spielMenu = new JMenu("Spiel");
	private JMenu infoMenu = new JMenu("Info");
	
	//Einträge in Menus:
	private JMenuItem saveItem = new JMenuItem("Speichern");
	private JMenuItem openItem = new JMenuItem("Öffnen");
	private JMenuItem exitItem = new JMenuItem("Verlassen");
	
	private JMenuItem newItem = new JMenuItem("Neues Spiel");
	
	private JMenuItem infoItem = new JMenuItem("Über Schach KaMe...");
	
	private JFileChooser fc;
	private Border compound, raisedbevel, loweredbevel;
		
	public GUIMainWindow(Schachbrett schachbrett) {
		this.schachbrett = schachbrett;
		setSize(900, 800);
		setTitle("Schach KaMe - Abschluss-Projekt OOP mit Java - by Karsten_Menzel@web.de");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		menuBauen();
		panelNordBauen();
		panelOstBauen();
		panelSuedBauen();
		schachbrettBauen(schachbrett, ost, textAereaZugliste, textInfo, textSpielerSchwarz, textSpielerWeiss);	
		newItem.addActionListener(neu->{
			schachbrett.neuesSpiel();
			remove(schachPanel);
			remove(nord);
			remove(ost);
			remove(sued);
			
			panelNordBauen();
			panelOstBauen();
			panelSuedBauen();
			schachbrettBauen(schachbrett, ost, textAereaZugliste, textInfo, textSpielerSchwarz, textSpielerWeiss );	
			paintAll(getGraphics());
		});
		saveItem.addActionListener(open-> {
			schachbrett.speichernZuglisteInDatei();
			textInfo.setText("Zugliste gespeichert");
			sued.repaint();
		});
		openItem.addActionListener(open->this.oeffnen());
		
		exitItem.addActionListener(exit->System.exit(-1));
	}
	
	private void panelNordBauen() {
		nord = new JPanel();
		textSpielerSchwarz = new JTextField(schachbrett.getNameSpielerSchwarz());
		textSpielerWeiss = new JTextField(schachbrett.getNameSpielerWeiss());
		textSpielerSchwarz.setFont(new Font("Tahoma", Font.BOLD, 20));
		textSpielerWeiss.setFont(new Font("Tahoma", Font.BOLD, 20));
		textSpielerWeiss.setForeground(Color.WHITE);
		textSpielerSchwarz.setForeground(Color.BLACK);
		textSpielerWeiss.setBackground(Color.GREEN);
		textSpielerSchwarz.setBackground(Color.RED);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		textSpielerSchwarz.setBorder(compound);
		textSpielerWeiss.setBorder(compound);
		nord.add(textSpielerWeiss);
		nord.add(textSpielerSchwarz);
		add(nord,BorderLayout.NORTH);
	}
	private void panelOstBauen() {
		ost = new JPanel();
		ost.setMinimumSize(new Dimension(100, 330));
		textAereaZugliste = new JTextArea("                  ");
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		textAereaZugliste.setBorder(compound);
		scroll = new JScrollPane(textAereaZugliste);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		ost.add(scroll);
		add(ost,BorderLayout.EAST);
	}
	private void panelSuedBauen() {
		sued = new JPanel();
		textInfo = new JTextField("Info");
		textInfo.setPreferredSize(new Dimension(250, 30));
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
		textInfo.setBorder(compound);
		sued.add(textInfo);
		add(sued,BorderLayout.SOUTH);
	}
	
	private void menuBauen() {
		//Menus füllen mit MenuItems
		dateiMenu.add(saveItem);
		dateiMenu.add(openItem);
		dateiMenu.add(exitItem);
		spielMenu.add(newItem);
		infoMenu.add(infoItem);
		
		//Menuleiste füllen mit Menus
		menuLeiste.add(dateiMenu);
		menuLeiste.add(spielMenu);
		menuLeiste.add(infoMenu);
		setJMenuBar(menuLeiste);	
	}
	
	 private void oeffnen() { 
		final JFileChooser chooser = new JFileChooser("Verzeichnis wählen");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		final File file = new File("/home");
		chooser.setCurrentDirectory(file);
		chooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
						|| e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
					final File f = (File) e.getNewValue();
				}
			}
		});
		chooser.setVisible(true);
		final int result = chooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File inputVerzFile = chooser.getSelectedFile();
			String inputVerzStr = inputVerzFile.getPath();
			System.out.println("Eingabepfad:" + inputVerzStr);
			textAereaZugliste.setText("");
			textAereaZugliste.setText(": inputVerzStr");
			ArrayList<Zug> zugGelesen = null;
			if (inputVerzFile.exists()) {
				try {
					Leser leser = new Leser();
					zugGelesen = leser.zugLesen(inputVerzFile);
					if (zugGelesen != null) {
						textAereaZugliste.setText("Datei gelesen ANFANG:\n");
						for (Zug zug : zugGelesen) {
							System.out.println(zug.getZug(true));
							textAereaZugliste.append(zug.getZug(true) + "\n");
						}
						textAereaZugliste.append("Datei gelesen ENDE:\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Abbruch");
		chooser.setVisible(false);
	} 
	private void schachbrettBauen(Schachbrett schachbrett, JPanel rechts, JTextArea textArea,
			JTextField textInfo, JTextField textSpielerSchwarz, JTextField textSpielerWeiss) {
		schachPanel = new GUISchachbrett(schachbrett, rechts, textArea, textInfo, textSpielerSchwarz, textSpielerWeiss);
		schachPanel.setSize(new Dimension(550, 450));
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		schachPanel.setBorder(compound);
		add(schachPanel,BorderLayout.CENTER);
	}
}
