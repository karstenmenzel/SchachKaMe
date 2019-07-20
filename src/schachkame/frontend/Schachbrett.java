package schachkame.frontend;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import schachkame.Bauer;
import schachkame.Dame;
import schachkame.Feld;
import schachkame.Figur;
import schachkame.FigurenListe;
import schachkame.Koenig;
import schachkame.Laeufer;
import schachkame.Spieler;
import schachkame.Springer;
import schachkame.Turm;
import schachkame.Zug;
import schachkame.io.DateiZugriff;

/**
 * Diese Klasse modelliert das Schachbrett: Stellt Eingabe/Ausgabe zur
 * Interaktion per Konsole bereit
 * ToDO: Alle unnötigen statics entfernen, stattdessen Referenz auf das Brett weitergeben
 * 
 * @author Karsten Menzel
 *
 */
public class Schachbrett {
	/**
	 * Dieses 2-dim. Array repräsentiert das Schachbrett vom Typ Feld
	 */
	private Feld[][] felder = new Feld[8][8];
	/**
	 * Dieses 2-dim. Array repräsentiert eine Kopie des Schachbretts, zurzeit
	 * UNGENUTZT
	 */
	private Feld[][] felderKopie = new Feld[8][8];
	/**
	 * Diese 2-dim. Array-List repräsentiert eine Kopie des Schachbretts,
	 * zurzeit UNGENUTZT
	 */
	private ArrayList<Feld[][]> aufzeichnungStellungen = new ArrayList<>();
	/**
	 * Dieses ArrayList wird gefüllt mit Figuren wird bei setUpFiguren verwendet
	 */
	private ArrayList<Figur> figuren = new ArrayList<>();
	/**
	 * In diese Array-List werden die Züge der Partie aufgenommen
	 */
	private static ArrayList<Zug> zugliste = new ArrayList<>();
	/**
	 * In diese Array-List werden die Teil-Züge der Partie aufgenommen
	 */
	private static ArrayList<Zug> teilZugliste = new ArrayList<>();
	/**
	 * In diese Array-List werden die kontrollierten Felder von Weiss
	 * aufgenommen
	 */
	private static ArrayList<Feld> kontrollierteFelderWeiss = new ArrayList<>();
	/**
	 * In diese Array-List werden die kontrollierten Felder von Schwarz
	 * aufgenommen
	 */
	private static ArrayList<Feld> kontrollierteFelderSchwarz = new ArrayList<>();
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String KOENIG_STEHT_IM_SCHACH = "Ein König steht im Schach: ";
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String KOENIG_IST_MATT = "Ein König ist Schach und Matt! ";
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String ZUG_UNGUELTIG = "Ich befürchte der Zug ist ungültig! ";
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String WILLKOMMEN = "********* Willkommen beim ultimativen Chess-Game 'Schach KaMe' *********";
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String INFO = "INFO: Zum (vorzeitigen) Beenden des Spiels: tippe 'ende' ein\nHave fun!";
	/**
	 * Konstanten-String dient zur Ausgabe
	 */
	private static final String DIALOG = "Welche Form der spielerischen Auseinandersetzung willst Du probieren:\n"
			+ "CONSOLE oder GUI ?\n" + "\t\t Tippe 'CON', 'CONAUTO' oder 'GUI' ein!\n"
			+ "Bei 'CONAUTO' spielt der Rechner gegen sich selbst!\n"
			+ "Um diesen Dialog ohne Auswahl zu beenden: tippe 'ende' ein.";
	/**
	 * Konstanten-String dient als Farbwert der Figuren oder Zuweisung der
	 * Figuren-Farbe beim Spieler - s.u.
	 */
	public static final String WEISS = "weiss";
	/**
	 * Konstanten-String dient als Farbwert der Figuren oder Zuweisung der
	 * Figuren-Farbe beim Spieler - s.u.
	 */
	public static final String SCHWARZ = "schwarz";
	/**
	 * Diese Methode liefert die kontr. Felder von Schwarz zurück
	 * @return ArrayList (kann null sein)
	 */
	public static ArrayList<Feld> getKontrollierteFelderSchwarz() {
		return kontrollierteFelderSchwarz;
	}
	/**
	 * Diese Methode liefert die kontr. Felder von Weiss zurück
	 * @return ArrayList (kann null sein)
	 */
	public static ArrayList<Feld> getKontrollierteFelderWeiss() {
		return kontrollierteFelderWeiss;
	}
	/**
	 * Feld des letzten Zugs
	 */
	private Feld feldLetzterZug = null;
	/**
	 * Spieler WEISS wird initialisiert
	 */
	private Spieler spielerWeiss = new Spieler(WEISS, "Spieler WEISS");
	/**
	 * Spieler SCHWARZ wird initialisiert
	 */
	private Spieler spielerSchwarz = new Spieler(SCHWARZ, "Spieler SCHWARZ");
	/**
	 * Boolean-Wert ob Schach besteht
	 */
	private boolean isschach;
	/**
	 * Boolean-Wert ob Partie zu Ende
	 */
	private boolean isPartieEnde;
	/**
	 * Index der Teilzüge
	 */
	private int meineTeilZugNr = 0;
	/**
	 * Boolean-Wert ob Schwarz groß rochiert besteht
	 */
	private static boolean rochadeSchwarzGross;
	/**
	 * Boolean-Wert ob Weiss groß rochiert besteht
	 */
	private static boolean rochadeWeissGross;
	/**
	 * Boolean-Wert ob Schwarz rochiert besteht
	 */
	private static boolean rochadeSchwarzKlein;
	/**
	 * Boolean-Wert ob Weiss rochiert besteht
	 */
	private static boolean rochadeWeissKlein;

	private static boolean promotionMoeglich;
	
	// lokale Variablen der Methode 'nimmZugInZuglisteAuf'
	private Zug teilZug = new Zug();
	private int teilZugNr = 0;
	private int zugNr = 0;

	/**
	 * Der Konstruktor initialisiert das Schachbrett und startet das Spiel im
	 * Konsolen-Modus
	 */
	public Schachbrett() {
		setUpBoard();
		setUpFiguren();
		getKontrollierteFelder();
//		outputKontrollierteFelder(); // testOutput
		starteDialogConsoleOderGUI();
	}
	/**
	 * Diese Methode gibt die Schachmeldung zurück
	 * @return String_Meldung
	 */
	public String getSchachMeldung() {
		return KOENIG_STEHT_IM_SCHACH;
	}
	/**
	 * Diese Methode gibt die Schach-Mattmeldung zurück
	 * @return String_Meldung
	 */
	public String getSchachMattMeldung() {
		return KOENIG_IST_MATT;
	}
	/**
	 * Liefert die Spielfelder / das Schachbrett zurück
	 * @return das Array mit den Feldern
	 */
	public Feld[][] getSpielfelder() {
		return felder;
	}
	/**
	 * Diese Methode startet ein neues Spiel
	 */
	public void neuesSpiel() {
		setPartieAnfang();
		setUpBoard();
		setUpFiguren();
		getKontrollierteFelder();
//		outputKontrollierteFelder(); // testOutput
		zeigeSchachbrett();
		spielerWeiss = new Spieler(WEISS, "Spieler WEISS");
		spielerSchwarz = new Spieler(SCHWARZ, "Spieler SCHWARZ");
		Schachbrett.teilZugliste = new ArrayList<>();
		Schachbrett.zugliste = new ArrayList<>();
		// starteDialogConsoleOderGUI();
	}
	/**
	 * Diese Methode gibt das gewuenschte Feld als Kopie zurueck
	 * @param x VERTIKALE Koordinate
	 * @param y HORIZONTALE Koordinate
	 * @return das Feld
	 */
	public Feld getFeld(int x, int y) {
		Feld result = null;
		return  result = (Feld) felder[x][y].clone();
	}
	/**
	 * Diese Methode speichert die Zugliste im Workspace
	 */
	public void speichernZuglisteInDatei() {
		DateiZugriff.schreibeListeInDatei();
	}
	/**
	 * Index der Teilzüge wird inkrementiert
	 */
	public void incrementTeilzugNr() {
		meineTeilZugNr++;
	}
	/**
	 * Diese Methode setzt das Spielende
	 */
	public void setPartieEnde() {
		isPartieEnde = true;
	}
	/**
	 * Diese Methode markiert ein neues Spiel (= das PartieEnde wird auf false
	 * gesetzt)
	 */
	public void setPartieAnfang() {
		isPartieEnde = false;
	}
	/**
	 * Diese Methode erfragt das Spielende und gibt die Antwort im return zurück
	 * 
	 * @return JA/Nein
	 */
	public boolean getPartieEnde() {
		return isPartieEnde;
	}
	/**
	 * Diese Methode gibt zurück, ob der auszuführende Zug eine Rochade von
	 * Schwarz ist
	 * 
	 * @return Ja/Nein
	 */
	public static boolean isRochadeSchwarzKlein() {
		return rochadeSchwarzKlein;
	}
	/**
	 * Diese Methode gibt zurück, ob der auszuführende Zug eine Rochade von
	 * Weiss ist
	 * 
	 * @return Ja/Nein
	 */
	public static boolean isRochadeWeissKlein() {
		return rochadeWeissKlein;
	}
	/**
	 * Diese Methode setzt fest, dass der auszuführende Zug eine Rochade von
	 * Schwarz ist
	 *
	 */
	public static void setRochadeSchwarzKlein() {
		rochadeSchwarzKlein = true;
	}
	/**
	 * Diese Methode resettet eine Rochade von Schwarz
	 *
	 */
	public static void resetRochadeSchwarzKlein() {
		rochadeSchwarzKlein = false;
	}
	/**
	 * Diese Methode setzt fest, dass der auszuführende Zug eine Rochade von
	 * Weiss ist
	 * 
	 */
	public static void setRochadeWeissKlein() {
		rochadeWeissKlein = true;
	}
	/**
	 * Diese Methode resettet eine Rochade von Weiss
	 * 
	 */
	public static void resetRochadeWeissKlein() {
		rochadeWeissKlein = false;
	}
	/**
	 * Diese Methode gibt zurück, ob der auszuführende Zug eine große Rochade
	 * von Schwarz ist
	 * 
	 * @return Ja/Nein
	 */
	public static boolean isRochadeSchwarzGross() {
		return rochadeSchwarzGross;
	}
	/**
	 * Diese Methode gibt zurück, ob der auszuführende Zug eine große Rochade
	 * von Weiss ist
	 * 
	 * @return Ja/Nein
	 */
	public static boolean isRochadeWeissGross() {
		return rochadeWeissGross;
	}
	/**
	 * Diese Methode setzt fest, dass der auszuführende Zug eine große Rochade
	 * von Schwarz ist
	 *
	 */
	public static void setRochadeSchwarzGross() {
		rochadeSchwarzGross = true;
	}
	/**
	 * Diese Methode resettet eine große Rochade von Schwarz
	 *
	 */
	public static void resetRochadeSchwarzGross() {
		rochadeSchwarzGross = false;
	}
	/**
	 * Diese Methode setzt fest, dass der auszuführende Zug eine große Rochade
	 * von Weiss ist
	 * 
	 */
	public static void setRochadeWeissGross() {
		rochadeWeissGross = true;
	}
	/**
	 * Diese Methode resettet eine große Rochade von Weiss
	 * 
	 */
	public static void resetRochadeWeissGross() {
		rochadeWeissGross = false;
	}
	/**
	 * Diese Methode prüft, ob die Bauernumwandlung in DAME,SPRINGER,LAEUFER
	 * oder TURM moeglich ist
	 * 
	 * @return Ja/Nein
	 */
	public boolean isPromotionMoeglich() {
		return promotionMoeglich;
	}
	/**
	 * Diese Methode setzt den Wert auf true
	 */
	public static void setPromotionMoeglich() {
		promotionMoeglich = true;
	}
	/**
	 * Diese Methode resettet den Wert
	 */
	public static void resetPromotionMoeglich() {
		promotionMoeglich = false;
	}
	/**
	 * Diese Methode erfragt, ob ein Schach besteht
	 * 
	 * @return JA/Nein
	 */
	public boolean isSchach() {
		return isschach;
	}
	/**
	 * Diese Methode setzt ein Schach
	 */
	public void setSchach() {
		isschach = true;
	}
	/**
	 * Diese Methode widerruft ein Schach
	 */
	public void resetIsSchach() {
		isschach = false;
	}
	/**
	 * Diese Methode zeigt die Ende-Meldung an
	 * 
	 * @param feld  Feld zum Bestimmen, wer weisse/schwarze Steine hat
	 * @return Rückgabe-String der Meldung
	 */
	public String endeMeldung(Feld feld) {
		String result ="";
		if (feld.getFigur().getFarbe().equals(WEISS)) {
			System.out.print(result = spielerSchwarz.getName() + " hat gewonnen. Great work!\n");
			System.out.println(result += "Du warst ein würdiger Gegner!");
			
		} else {
			System.out.println(result = spielerWeiss.getName() + " hat gewonnen. Great work!\n");
			System.out.println(result += "Du warst ein würdiger Gegner!");
		}
		return result;
	}
	/**
	 * Diese Methode prüft, ob ein König im Schach steht
	 * 
	 * @return gibt das Feld des betreffenden Königs zurück oder null
	 */
	public Feld getKoenigStehtImSchach() {
		Feld resultKingFeld = null;
		Feld kingWantedWeiss = null;
		Feld kingWantedSchwarz = null;

		deleteKontrollierteFelder();
		getKontrollierteFelder();

		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder.length; j++) {
				if (felder[i][j].getFigur() instanceof Koenig && felder[i][j].getFigur().getFarbe().equals(WEISS)) {
					kingWantedWeiss = felder[i][j];
				}
				if (felder[i][j].getFigur() instanceof Koenig && felder[i][j].getFigur().getFarbe().equals(SCHWARZ)) {
					kingWantedSchwarz = felder[i][j];
				}
			}
		}
		if (kingWantedSchwarz != null && kingWantedSchwarz.getFigur().getFarbe().equals(SCHWARZ))
			for (Feld feld : kontrollierteFelderWeiss) {
				if (feld.getFigur() instanceof Koenig && kingWantedSchwarz.getFigur() instanceof Koenig
						&& feld.getFigur().getFarbe().equals(kingWantedSchwarz.getFigur().getFarbe())) {
					resultKingFeld = kingWantedSchwarz;
				}
			}
		if (kingWantedWeiss != null && kingWantedWeiss.getFigur().getFarbe().equals(WEISS))
			for (Feld feld : kontrollierteFelderSchwarz) {
				if (feld.getFigur() instanceof Koenig && kingWantedWeiss.getFigur() instanceof Koenig
						&& feld.getFigur().getFarbe().equals(kingWantedWeiss.getFigur().getFarbe())) {
					resultKingFeld = kingWantedWeiss;
				}
			}
		return resultKingFeld;
	}
	/**
	 * Diese Methode beendet das Spiel bei Schach und Matt
	 */
	public void beendeSpielWennSchachMatt() {
		// ermittele Schach/Matt
		Feld feld = null;
		if (!isSchach()) {
			feld = getKoenigStehtImSchach();
			if (feld != null) {
				setSchach();
				System.out.println(KOENIG_STEHT_IM_SCHACH + feld.getPGNNotation());
			}
		} // zweites oder n-tes Mal, der selbe König immernoch im Schach!
		else if (isSchach()) {
			feld = getKoenigStehtImSchach();
			if (feld != null) {
				System.out.println(KOENIG_STEHT_IM_SCHACH + feld.getPGNNotation());
			} else
				resetIsSchach();
		}

		// Gibt es ein Matt?
		if (feld != null && isSchachMatt(felder[feld.getX()][feld.getY()], feldLetzterZug)) {
			System.out.println(KOENIG_IST_MATT);
			setPartieEnde();
			endeMeldung(feld);
		}
	}
	/**
	 * Diese Methode prüft, ob ein König Schach Matt ist
	 * 
	 * @param feldKoenig  Feld des Königs
	 * @param feldAngreifer  Feld des Angreifers
	 * @return Ja/Nein: König ist Schach Matt
	 */
	public boolean isSchachMatt(Feld feldKoenig, Feld feldAngreifer) {
		boolean result = false;
		List<Feld> verteidiger = new ArrayList<Feld>();
		List<Feld> barriereFelder = feldAngreifer.getFigur().getPfadZumKoenig(feldKoenig, felder, feldAngreifer);

		/****************************
		 * Fall: SCHWARZER Koenig im Schach
		 ****************************/
		// suche verteidiger, die den angreifer SCHLAGEN können (= Feld des
		// Angreifers)
		if (feldKoenig.getFigur().getFarbe().equals(SCHWARZ)) {
			// suche verteidiger, die den angreifer schlagen können
			for (int i = felder.length - 1; i >= 0; i--) {
				for (int j = 0; j < felder.length; j++) {
					if (felder[i][j].getFigur().getFarbe().equals(SCHWARZ)
//							&& !(felder[i][j].getFigur() instanceof Koenig)
							&& felder[i][j].getFigur().istZugMusterGueltig(felder[i][j], feldAngreifer, felder)) {
						verteidiger.add(felder[i][j]);
					} 
				}
			}
			// suche verteidiger, die den angreifer BLOCKIEREN können (= Pfad
			// vom
			// Angreifer(auschließend dessen Feld) zum König)
			barriereFelder = feldAngreifer.getFigur().getPfadZumKoenig(feldKoenig, felder, feldAngreifer);
			if (barriereFelder != null)
				for (Feld barriere : barriereFelder) {
					for (int i = felder.length - 1; i >= 0; i--) {
						for (int j = 0; j < felder.length; j++) {
							if (felder[i][j].getFigur().getFarbe().equals(SCHWARZ)
									&& !(felder[i][j].getFigur() instanceof Koenig)
									&& felder[i][j].getFigur().istZugMusterGueltig(felder[i][j], barriere, felder)) {
								verteidiger.add(felder[i][j]);
							} 
						}
					}
				}
		}
		/************************
		 * Fall: WEISSER Koenig im Schach
		 ****************************/
		// suche verteidiger, die den angreifer SCHLAGEN können (= Feld des
		// Angreifers)
		if (feldKoenig.getFigur().getFarbe().equals(WEISS)) {
			// suche verteidiger, die den angreifer schlagen können
			for (int i = felder.length - 1; i >= 0; i--) {
				System.out.print(" ");
				for (int j = 0; j < felder.length; j++) {
					if (felder[i][j].getFigur().getFarbe().equals(WEISS) 
//							&& !(felder[i][j].getFigur() instanceof Koenig)
							&& felder[i][j].getFigur().istZugMusterGueltig(felder[i][j], feldAngreifer, felder)) {
						verteidiger.add(felder[i][j]);
					} 
				}
			}
			// suche verteidiger, die den angreifer BLOCKIEREN können (= Pfad
			// vom
			// Angreifer(auschließend dessen Feld) zum König)
			barriereFelder = feldAngreifer.getFigur().getPfadZumKoenig(feldKoenig, felder, feldAngreifer);
			if (barriereFelder != null)
				for (Feld barriere : barriereFelder) {
					for (int i = felder.length - 1; i >= 0; i--) {
						System.out.print(" ");
						for (int j = 0; j < felder.length; j++) {
							if (felder[i][j].getFigur().getFarbe().equals(WEISS)
									&& !(felder[i][j].getFigur() instanceof Koenig)
									&& felder[i][j].getFigur().istZugMusterGueltig(felder[i][j], barriere, felder)) {
								verteidiger.add(felder[i][j]);
							} 
						}
						System.out.println("");
					}
				}
		}
		/***********************************************************
		 * FLUCHTWEGE des Koenigs ermitteln
		 ***********************************************************/
		try {
			ArrayList<Feld> kontrollierteFelderKoenig = new ArrayList<>();
			ArrayList<Feld> freieFelderKoenig = new ArrayList<>();

			kontrollierteFelderKoenig
					.addAll(feldKoenig.getFigur().getKontrollierteFelder(feldKoenig, felder, false, true));
			// ermittelt zunächst Flucht-Felder des Königs ohne Berücksichtigung
			// auf Kontrolle durch den Gegner
			for (Feld feld : kontrollierteFelderKoenig) {
				if (!feld.istBesetzt()) {
					freieFelderKoenig.add(feld);
				}
			}
			// ermittelt Flucht-Felder des Königs unter Berücksichtigung der
			// kontrollierten Felder des Gegners
			if (feldKoenig != null && feldKoenig.getFigur().getFarbe().equals(SCHWARZ)) {
				if (kontrollierteFelderWeiss.containsAll(freieFelderKoenig) && verteidiger.isEmpty()) {
					// System.out.println("Der Koenig ist Schach und Matt! ");
					result = true;
				}
			} else if (feldKoenig != null && feldKoenig.getFigur().getFarbe().equals(WEISS))
				if (kontrollierteFelderSchwarz.containsAll(freieFelderKoenig) && verteidiger.isEmpty()) {
					result = true;
				}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return result;
	}

	/**
	 * Diese Methode fügt kontrollierte Felder in Liste von WEISS/SCHWARZ hinzu
	 * 
	 * @param feld
	 *            Feld, das kontrolliert wird
	 * @param iswhite
	 *            Ja/Nein gibt an, ob Feld zu den schwarzen oder weissen Figuren
	 *            gehört
	 */
	public static void addKontrolliertesFeld(Feld feld, boolean iswhite) {
		if (iswhite)
			kontrollierteFelderWeiss.add(feld);
		if (!iswhite)
			kontrollierteFelderSchwarz.add(feld);
	}
	// Test-Ausgabe
	public void outputKontrollierteFelder() {
		System.out.println(" ***********************************************************************");
		System.out.println(" *** ANFANG ANZEIGE SCHACHBRETT - kontrollierte Felder WEISS ***");
		for (Feld feld : kontrollierteFelderWeiss) {
			feld.zeigeFeldInfo();
		}
		System.out.println(" *** ANZEIGE SCHACHBRETT - kontrollierte Felder WEISS ENDE ***");

		System.out.println(" *** ANFANG ANZEIGE SCHACHBRETT - kontrollierte Felder SCHWARZ ***");
		for (Feld feld : kontrollierteFelderSchwarz) {
			feld.zeigeFeldInfo();
		}
		System.out.println(" *** ANZEIGE SCHACHBRETT - kontrollierte Felder SCHWARZ ENDE ***");
	}
	/**
	 * Diese Methode löscht die Listen kontrollierter Felder von WEISS und
	 * SCHWARZ
	 */
	public void deleteKontrollierteFelder() {
		kontrollierteFelderWeiss.clear();
		kontrollierteFelderSchwarz.clear();
	}

	/**
	 * Diese Methode zeichnet Spielbrett-Situationen auf ! Noch ungenutzt ! *
	 */
	public void saveKopieFelderSchachbrett() {
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; ++j) {
				felderKopie[i][j] = (Feld) felder[i][j].clone();
			}
		}
	}
	/**
	 * Diese Methode zeichnet Spielbrett-Situationen auf ! Noch ungenutzt ! *
	 */
	public void restoreKopieFelderSchachbrett() {
		for (int i = 0; i < felderKopie.length; i++) {
			for (int j = 0; j < felderKopie[i].length; ++j) {
				felder[i][j] = (Feld) felderKopie[i][j].clone();
			}
		}
	}
	/**
	 * Diese Methode prüft, ob Benutzer den Dialog abbrechen will
	 * 
	 * @param eingabe
	 *            Prüf-String
	 * @return Ja/Nein ob Kriterium erfüllt
	 */
	public boolean isConsoleDialogOver(String eingabe) {
		boolean result = false;
		if (eingabe.toLowerCase().equals("ende")) {
			System.out.println("Du hast das Spiel beendet. Bis zum naechsten Spiel. See you...");
			result = true;
		}
		return result;
	}

	/**
	 * Diese Methode startet den Dialog zur Entscheidung GUI-/ oder
	 * Konsolen-Modus
	 */
	private void starteDialogConsoleOderGUI() {
		System.out.println(WILLKOMMEN);
		System.out.println(DIALOG + "\n");
		String eingabe = "";
		Scanner sc;
		while (true) {
			sc = new Scanner(System.in);
			System.out.println("");
			try {
				eingabe = sc.next();
				if (isConsoleDialogOver(eingabe)) {
					sc.close();
					break;
				}
				if (eingabe.toLowerCase().equals("gui")) {
					{
						starteGUI();
					}
					sc.close();
					break;
				}
				if (eingabe.toLowerCase().equals("con")) {
					{
						starteConsoleDialog();
					}
					sc.close();
					break;
				}
				if (eingabe.toLowerCase().equals("conauto")) {
					{
						starteConsoleAutoPlay();
					}
					sc.close();
					break;
				}
			} catch (Exception e) {
			}

		}
	}

	/**
	 * Diese Methode startet den Konsolen-Dialog
	 */
	private void starteConsoleDialog() {
		System.out.println(WILLKOMMEN);
		System.out.println(INFO + "\n");
		zeigeSchachbrett();
		System.out.println("");
		System.out.println("");
		String von = "";
		String nach = "";
		Scanner sc;
		while (!getPartieEnde()) {
			sc = new Scanner(System.in);
			System.out.println("\n" + getWerIstAmZug().getName() + " ist am Zug: ");
			System.out.println("");
			System.out.println("VON: (Bsp.: e2)");
			von = sc.next();
			if (isConsoleDialogOver(von)) {
				sc.close();
				break;
			}
			System.out.println("NACH: (Bsp: e4)");
			nach = sc.next();
			if (isConsoleDialogOver(nach)) {
				sc.close();
				break;
			}
			// --------------------
			if (!zieheFigur(von, nach)) {
				System.err.println(ZUG_UNGUELTIG);
			}

			zeigeSchachbrett();
			System.out.println("\nDu hast gezogen: " + von + " -> " + nach + "\n");
			printZugliste();
		}
	}
	
	/**
	 * Diese Methode startet den AutoPlay-Modus in der Konsole
	 * Der Computer spielt gegen sich selbst
	 */
	private void starteConsoleAutoPlay() {
		System.out.println(WILLKOMMEN);
		System.out.println(INFO + "\n");
		zeigeSchachbrett();
		System.out.println("");
		System.out.println("");
		while (!getPartieEnde()) {
			try {
		          Thread.sleep(1);
		          starteComputerAutoPlay();
		        }
		        catch(InterruptedException e) {
		        }
		        System.out.println("Demo-Thread");
		}
		zeigeSchachbrett();
		printZugliste();
	}
	/**
	 * Diese Methode startet das Programm mit der Benutzeroberfläche
	 */
	private void starteGUI() {
		System.out.println("*************** starte GUI 'Schach KaMe' by Karsten Menzel ***************");
		System.out.println("*************** einen Moment bitte... ***************");
		new GUIMainWindow(this).setVisible(true);
	}

	/**
	 * Diese Methode iteriert über die Felder des Bretts, um kontrollierte
	 * Felder über die namensgleiche Methode in Listen(WEISS/SCHWARZ)
	 * abzuspeichern
	 */
	public void getKontrollierteFelder() {
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder.length; j++)
				felder[i][j].getFigur().getKontrollierteFelder(felder[i][j], felder, true, false);
		}
	}

	/**
	 * Diese Methode zeigt die aktuelle Spielsituation das Schachbretts an
	 */
	public void zeigeSchachbrett() {
		System.out.println(
				" ----------------------------------------- ANZEIGE SCHACHBRETT - aktuelle Spielsituation ---------------------------------------\n");
		for (int i = felder.length - 1; i >= 0; i--) {
			System.out.print("| ");
			for (int j = 0; j < felder.length; j++) {
				// System.out.print("| ");
				if (felder[i][j].istBesetzt()) {
					System.out.print(felder[i][j].getPGNNotation() + " " + felder[i][j].getFigur().getFarbe());
				} else
					System.out.print(felder[i][j].getPGNNotation() + " FREI");
				System.out.print(" \t| ");
			}
			System.out.println("");
		}
	}

	/**
	 * Diese Methode zeigt eine Schachbrett-Kopie an
	 */
	public void zeigeSchachbrettKopie() {
		System.out.println(" ***********************************************************************");
		System.out.println(" *** ANZEIGE SCHACHBRETT KOPIE - OHNE lETZTEN ZUG ***");
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felderKopie.length; j++) {
				if (felderKopie[i][j] != null) {
					if (felderKopie[i][j].istBesetzt()) {
						System.out.print(
								felderKopie[i][j].getPGNNotation() + " " + felderKopie[i][j].getFigur().getFarbe());
					} else
						System.out.print(felderKopie[i][j].getPGNNotation() + " FREI");
					System.out.print(" \t| ");
				}
			}
			System.out.println(" ***********************************************************************");
		}
	}
	/**
	 * Diese Methode stellt das Brett auf
	 */
	public void setUpBoard() {
		// 64 felder dem brett hinzufügen
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder.length; j++) {
				this.felder[i][j] = new Feld(i, j);
				felder[i][j].addNotation(this.transformiereXYInNotation(i, j));
			}
			System.out.println(" ");
		}
	}
	/**
	 * Diese Methode stellt die Figuren auf das Brett
	 */
	public void setUpFiguren() {
		final int BAUERN = 8;
		// zur Sicherheit initialisieren
		figuren.clear();
		/* Figuren-Liste mit weissen Figuren bestücken */
		figuren.add(new Turm(WEISS, FigurenListe.TURM.name(), 0, 0));
		figuren.add(new Springer(WEISS, FigurenListe.SPRINGER.name(), 0, 1));
		figuren.add(new Laeufer(WEISS, FigurenListe.LAEUFER.name(), 0, 2));
		figuren.add(new Dame(WEISS, FigurenListe.DAME.name(), 0, 3));
		figuren.add(new Koenig(WEISS, FigurenListe.KOENIG.name(), 0, 4));
		figuren.add(new Laeufer(WEISS, FigurenListe.LAEUFER.name(), 0, 5));
		figuren.add(new Springer(WEISS, FigurenListe.SPRINGER.name(), 0, 6));
		figuren.add(new Turm(WEISS, FigurenListe.TURM.name(), 0, 7));
		for (int i = 0; i < BAUERN; i++) {
			figuren.add(new Bauer(WEISS, FigurenListe.BAUER.name(), 1, i));
		}
		// weisse figuren aufstellen
		for (int i = 0; i < felder.length - 6; i++) {
			for (int j = 0; j < felder.length; j++) {
				this.felder[i][j] = new Feld(i, j);
				felder[i][j].addNotation(this.transformiereXYInNotation(i, j));

				// add "offiziere"
				if (i == 0)
					felder[i][j].addFigur(figuren.get(j), i, j);
				// add bauern
				if (i == 1)
					felder[i][j].addFigur(figuren.get(j + 8), i, j);
			}
		}
		// Figuren.Liste mit schwarzen Figuren bestücken
		// vorher liste löschen
		figuren.clear();
		figuren.add(new Turm(SCHWARZ, FigurenListe.TURM.name(), 0, 0));
		figuren.add(new Springer(SCHWARZ, FigurenListe.SPRINGER.name(), 0, 1));
		figuren.add(new Laeufer(SCHWARZ, FigurenListe.LAEUFER.name(), 0, 2));
		figuren.add(new Dame(SCHWARZ, FigurenListe.DAME.name(), 0, 3));
		figuren.add(new Koenig(SCHWARZ, FigurenListe.KOENIG.name(), 0, 4));
		figuren.add(new Laeufer(SCHWARZ, FigurenListe.LAEUFER.name(), 0, 5));
		figuren.add(new Springer(SCHWARZ, FigurenListe.SPRINGER.name(), 0, 6));
		figuren.add(new Turm(SCHWARZ, FigurenListe.TURM.name(), 0, 7));
		for (int i = 0; i < BAUERN; i++) {
			figuren.add(new Bauer(SCHWARZ, FigurenListe.BAUER.name(), 1, i));
		}

		// schwarze figuren aufstellen
		for (int i = 7; i >= felder.length - 2; i--) {
			for (int j = 0; j < felder.length; j++) {
				this.felder[i][j] = new Feld(i, j);
				felder[i][j].addNotation(this.transformiereXYInNotation(i, j));

				// add "offiziere"
				if (i == 7)
					felder[i][j].addFigur(figuren.get(j), i, j);
				// add bauern
				if (i == 6)
					felder[i][j].addFigur(figuren.get(j + 8), i, j);

			}
			System.out.println(" ");
		}
	}
	/**
	 * Diese Methode veranlasst die Bauernumwandlung / Promotion hier: fest
	 * verdrahtet als eine Dame
	 * 
	 * @param feldFrom
	 *            Quell-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 * @return Ja/Nein ob erfolgreich
	 */
	public boolean fuehrePromotionAus(Feld feldFrom, Feld feldTo) {
		boolean result = false;
		if (feldFrom.getFigur().getFarbe().equals(WEISS))
			this.felder[feldTo.getX()][feldTo.getY()]
					.addFigur(new Dame(WEISS, FigurenListe.DAME.name(), feldTo.getX(), feldTo.getY()));
		else
			this.felder[feldTo.getX()][feldTo.getY()]
					.addFigur(new Dame(SCHWARZ, FigurenListe.DAME.name(), feldTo.getX(), feldTo.getY()));
		this.felder[feldFrom.getX()][feldFrom.getY()].resetFeld();
		result = true;
		return result;
	}
	/**
	 * Diese Methode veranlasst das Schlagen einer Figur, falls möglich
	 * 
	 * @param feldFrom
	 *            Quell-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 * @return Ja/Nein ob erfolgreich
	 */
	public boolean fuehreZugAus(Feld feldFrom, Feld feldTo) {
		boolean result = false;
		this.felder[feldTo.getX()][feldTo.getY()].addFigur(this.felder[feldFrom.getX()][feldFrom.getY()].getFigur());
		this.felder[feldFrom.getX()][feldFrom.getY()].resetFeld(); // <----------------------------
		result = true;
		return result;
	}

	/**
	 * Diese Methode prüft, ob ein feld von Gegner besetzt ist
	 * 
	 * @param feldFrom
	 *            Quell-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 * @return Ja/Nein ob erfolgreich
	 */
	public boolean istFeldVomGegnerBesetzt(Feld feldFrom, Feld feldTo) {
		boolean result = false;
		if (this.felder[feldTo.getX()][feldTo.getY()].istBesetzt()
				&& this.felder[feldFrom.getX()][feldFrom.getY()].getFigur()
						.getFarbe() != this.felder[feldTo.getX()][feldTo.getY()].getFigur().getFarbe()
				&& !this.felder[feldTo.getX()][feldTo.getY()].getFigur().getFarbe().equals("farbe_dummy")) {
			result = true;
		}
		return result;
	}

	/**
	 * Diese Methode prüft, ob ein Feld von einer eigenen Figur besetzt ist
	 * 
	 * @param feldFrom
	 *            Quel-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 * @return Ja/Nein ob erfolgreich
	 */
	public boolean istFeldVonEigenerFigurBesetzt(Feld feldFrom, Feld feldTo) {
		boolean result = false;

		if (this.felder[feldTo.getX()][feldTo.getY()].istBesetzt() && this.felder[feldFrom.getX()][feldFrom.getY()]
				.getFigur().getFarbe().equals(this.felder[feldTo.getX()][feldTo.getY()].getFigur().getFarbe())) {
			result = true;
		} else {
		}

		return result;
	}

	/**
	 * Diese Methode führt einen Zug aus, falls möglich
	 * 
	 * @param notationFrom
	 *            Quell-Feld
	 * @param notationTo
	 *            Ziel-Feld
	 * @return Ja/Nein ob erfolgreich
	 */
	public boolean zieheFigur(String notationFrom, String notationTo) {
		boolean result = false;

		Feld feldFrom = transformiereSchachnotationInXY(notationFrom);
		Feld feldTo = transformiereSchachnotationInXY(notationTo);

		if (feldFrom == null || feldTo == null) {

		} else {
			feldFrom.addFigur(this.felder[feldFrom.getX()][feldFrom.getY()].getFigur());

			feldTo = this.felder[feldTo.getX()][feldTo.getY()];
			Feld restoreFeldFrom = (Feld) this.felder[feldFrom.getX()][feldFrom.getY()].clone();
			Feld restoreFeldTo = (Feld) this.felder[feldTo.getX()][feldTo.getY()].clone();

			// test: ist der richtige spieler am zug
			if (isFalscherSpielerAmZug(feldFrom)) {
			} else

			// wenn 'istZugMusterGueltig' positiv: zug ausfuehren
			if (this.felder[feldFrom.getX()][feldFrom.getY()].getFigur().istZugMusterGueltig(feldFrom, feldTo,
					this.felder))
				// Rochiert jemand?
				if (isRochadeSchwarzKlein()) {
					result = this.fuehreZugAus(felder[7][4], felder[7][6])
							&& this.fuehreZugAus(felder[7][7], felder[7][5]);
					resetRochadeSchwarzKlein();
				} else if (isRochadeWeissKlein()) {
					result = this.fuehreZugAus(felder[0][4], felder[0][6])
							&& this.fuehreZugAus(felder[0][7], felder[0][5]);
					resetRochadeWeissKlein();
				} else if (isRochadeSchwarzGross()) {
					result = this.fuehreZugAus(felder[7][4], felder[7][2])
							&& this.fuehreZugAus(felder[7][0], felder[7][3]);
					resetRochadeSchwarzGross();
				} else if (isRochadeWeissGross()) {
					result = this.fuehreZugAus(felder[0][4], felder[0][2])
							&& this.fuehreZugAus(felder[0][0], felder[0][3]);
					resetRochadeWeissGross();
				} else if (isPromotionMoeglich()) {
					result = fuehrePromotionAus(feldFrom, feldTo);
					System.out.println("Promotion ausgeführt feldTo/result: " + feldTo.getX() + "/" + feldTo.getY()
							+ ": " + result);
					resetPromotionMoeglich();
				} else

				// eigene Figuren und Könige werden NICHT geschlagen
				// kann wegen schach nicht passieren, wird aber
				// sicherheitshalber unterbunden
				if (!istFeldVonEigenerFigurBesetzt(feldFrom, feldTo) && !feldTo.getFigur().istFigurKoenig()) {
					result = this.fuehreZugAus(feldFrom, feldTo);
				}
			// erstes Schach
			if (!isSchach()) {
				Feld feld = getKoenigStehtImSchach();

				if (feld != null) {
					if (feld.getFigur().getFarbe().equals(feldFrom.getFigur().getFarbe())) {
						// UNDO letzten Zug, da EIGENER Koenig im Schach
						this.felder[feldFrom.getX()][feldFrom.getY()] = restoreFeldFrom;
						this.felder[feldTo.getX()][feldTo.getY()] = restoreFeldTo;
						result = false;
					} else {
						setSchach();
						System.out.println(KOENIG_STEHT_IM_SCHACH + feld.getPGNNotation() + " (erstes Schach)");
					}
				}
			} // zweites oder n-tes Mal, der selbe König immernoch im Schach!
			else if (isSchach()) {
				Feld feld = getKoenigStehtImSchach();
				beendeSpielWennSchachMatt();
				if (feld != null) {
					System.out.println(KOENIG_STEHT_IM_SCHACH + feld.getPGNNotation() + " (immer noch)");
					// UNDO letzten Zug, da immernoch Schach
					this.felder[feldFrom.getX()][feldFrom.getY()] = restoreFeldFrom;
					this.felder[feldTo.getX()][feldTo.getY()] = restoreFeldTo;
					result = false;
				} else
					resetIsSchach();
			}
			// Teilzug (s oder w)
			if (result) {
				feldLetzterZug = this.felder[feldTo.getX()][feldTo.getY()];
				beendeSpielWennSchachMatt();
				this.toggleSpielerIsAmZug();
				nimmZugInTeilzugListeAuf(this.felder[feldFrom.getX()][feldFrom.getY()],
						this.felder[feldTo.getX()][feldTo.getY()]);
				nimmZugInZuglisteAuf(this.felder[feldFrom.getX()][feldFrom.getY()],
						this.felder[feldTo.getX()][feldTo.getY()]);
			}
		}
		return result;
	}

	/**
	 * Diese Methode kehrt die Zug-Ansage um
	 */
	public void toggleSpielerIsAmZug() {
		spielerSchwarz.toggleIsAmZug();
		spielerWeiss.toggleIsAmZug();
	}
	/**
	 * Diese Methode prüft die Zug-Reihenfolge
	 * 
	 * @return entprechender Spieler
	 */
	public Spieler getWerIstAmZug() {
		if (spielerSchwarz.isAmZug()) {
			return spielerSchwarz;
		} else
			return spielerWeiss;
	}
	/**
	 * Diese Methode gibt den Namen des Spieler mit den schwarzen Steinen zurück
	 * 
	 * @return Name des Spielers
	 */
	public String getNameSpielerSchwarz() {
		return spielerSchwarz.getName();
	}
	/**
	 * Diese Methode gibt den Namen des Spieler mit den weissen Steinen zurück
	 * 
	 * @return Name des Spielers
	 */
	public String getNameSpielerWeiss() {
		return spielerWeiss.getName();
	}
	/**
	 * Diese Methode prüft, ob ein Spieler nicht an der Reihe ist
	 * 
	 * @param feldFrom
	 *            Feld des Spielers
	 * @return Ja/Nein
	 */
	public boolean isFalscherSpielerAmZug(Feld feldFrom) {
		return !((getWerIstAmZug().getFigurenFarbe()) == feldFrom.getFigur().getFarbe());
	}
	/**
	 * Diese Methode nimmt den Zug in die Zugliste auf Hinweis: Ein (ganzer) Zug
	 * besteht aus den Teil-Zügen von WEISS/SCHWARZ
	 * 
	 * @param feldFrom
	 *            Quell-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 */
	public void nimmZugInZuglisteAuf(Feld feldFrom, Feld feldTo) {
		teilZugNr += 1;
		if (teilZugNr % 2 == 1) {
			String sTeilZug = feldTo.getFigurPrefix() + feldFrom.getPGNNotation() + "-" + feldTo.getNotation();
			teilZug = new Zug();
			teilZug.setZugVonWeiss(sTeilZug);
		} else {
			String sTeilZug = feldTo.getFigurPrefix() + feldFrom.getPGNNotation() + "-" + feldTo.getNotation();
			teilZug.setZugVonSchwarz(sTeilZug);
			zugliste.add(teilZug);
		}
		zugNr += 1;
	}
	/**
	 * Diese Methode gibt den letzten Teil-Zug zurück *
	 * 
	 * @return letzter Teil-Zug
	 */
	public static Zug getLetztenTeilzug() {
		if (!teilZugliste.isEmpty())
			return teilZugliste.get(teilZugliste.size() - 1);
		return null;
	}
	/**
	 * Diese Methode gibt die Anzahl der Teil-Züge/Elemente zurück *
	 * 
	 * @return Anzahl Elemente/Teilzuege
	 */
	public static int getAnzahlTeilzuege() {
		int result = 0;
		if (!teilZugliste.isEmpty())
			return teilZugliste.size();
		return result;
	}
	/**
	 * Diese Methode gibt die Anzahl der Züge/Elemente zurück *
	 * 
	 * @return Anzahl Elemente/Zuege
	 */
	public static int getAnzahlZuege() {
		int result = 0;
		if (!zugliste.isEmpty())
			return zugliste.size();
		return result;
	}
	/**
	 * Diese Methode gibt den letzten Zug zurück *
	 * 
	 * @return letzter Zug
	 */
	public static Zug getLetztenZug() {
		if (!zugliste.isEmpty())
			return zugliste.get(zugliste.size() - 1);
		return null;
	}
	/**
	 * Diese Methode gibt die Zugliste zurück statisch ist sinnvoll, da es nur
	 * eine geben kann Zugriff von Klasse DateiZugriff
	 * 
	 * @return Zugliste
	 */
	public static ArrayList<Zug> getZugliste() {
		return zugliste;
	}
	/**
	 * Diese Methode nimmt den Teilzug in die Teilzug-Liste auf
	 * 
	 * @param feldFrom
	 *            Quell-Feld
	 * @param feldTo
	 *            Ziel-Feld
	 */
	public void nimmZugInTeilzugListeAuf(Feld feldFrom, Feld feldTo) {
		Zug meinTeilZug;
		String sTeilZug = feldTo.getFigurPrefix() + feldFrom.getPGNNotation() + "-" + feldTo.getNotation();
		meinTeilZug = new Zug();
		meinTeilZug.setTeilzug(sTeilZug, feldFrom, feldTo);
		teilZugliste.add(meinTeilZug);
		incrementTeilzugNr();
	}
	/**
	 * Diese Methode gibt die Zugliste aus
	 */
	public void printZugliste() {
		System.out.println("Zugliste:");
		for (int i = 0; i < zugliste.size(); i++) {
			System.out.print(" " + (i + 1) + ". " + zugliste.get(i).getZug(false));
		}
		System.out.print("\n");
	}


	/**
	 * 	 * Diese Methode gibt die Zugliste zeilenweis aus
	 * @return Zugliste oder null
	 */
	public String getZuglisteZeilenweise() {
		String result = "";
		System.out.println("Zugliste:");
		for (int i = 0; i < zugliste.size(); i++) {
				result += (i + 1) + ". " + zugliste.get(i).getZug(true) + "\n";
		}
		System.out.print("\n");
		return result;
	}
	
	/**
	 *  Diese Methode veranlasst den Computer gegen sich selbst zu spielen
	 */
		public void starteComputerAutoPlay() {
		ArrayList<Feld> felderVonWeissOderSchwarz = new ArrayList<Feld>();
		SecureRandom zufall = new SecureRandom();
		ArrayList<Feld> tempKontrollFelder = new ArrayList<Feld>();
		Feld feldFrom;
		Feld feldTo = null;
		String zugVon = "";
		String zugNach = "";
		int zufallsZahlVon;	
		int zufallsZahlNach;		
		
		if(this.getWerIstAmZug().getFigurenFarbe().equals(WEISS)) {
			for (int i = 0; i < felder.length; i++) {
				for (int j = 0; j < felder.length; j++) {
					if(felder[i][j].getFigur().getFarbe().equals(WEISS)) {
						felderVonWeissOderSchwarz.add(felder[i][j]);
					}					
				}
			}			
		} else if(this.getWerIstAmZug().getFigurenFarbe().equals(SCHWARZ)) {
			for (int i = 0; i < felder.length; i++) {
				for (int j = 0; j < felder.length; j++) {
					if(felder[i][j].getFigur().getFarbe().equals(SCHWARZ)) {
						felderVonWeissOderSchwarz.add(felder[i][j]);
					}					
				}
			}	
		}
		zufallsZahlVon = zufall.nextInt(felderVonWeissOderSchwarz.size());
		zugVon = felderVonWeissOderSchwarz.get(zufallsZahlVon).getNotation();
		feldFrom = felderVonWeissOderSchwarz.get(zufallsZahlVon);
		
		//Sonderhandling fuer die Bauern:
		//die Methode 'getKontrollierteFelder' liefert nur die Felder bei SCHLAGmoeglichkeit - nicht das NORMALE ziehen!
		if( felderVonWeissOderSchwarz.get(zufallsZahlVon).getFeld().getFigur() instanceof Bauer) {
			int zugLaengeBauerX = 1;

			if (feldFrom.getFigur().getFarbe() == Schachbrett.WEISS) {
				if (feldFrom.getX() == 1) {
					zugLaengeBauerX = 2;
				}
				feldTo = new Feld(feldFrom.getX() + zugLaengeBauerX, feldFrom.getY());
				zugNach = transformiereXYInNotation(feldTo.getX(), feldTo.getY());
				System.out.println("BAUER 114 Weiss ");
				System.out.println("BAUER zugVon: " + zugVon);
				System.out.println("BAUER zugNach: " + zugNach);

//				zieheFigur(zugVon, zugNach);
			} else if (feldFrom.getFigur().getFarbe() == Schachbrett.SCHWARZ) {
				if (feldFrom.getX() == 6) {
					zugLaengeBauerX = 2;
				}
				feldTo = new Feld(feldFrom.getX() - zugLaengeBauerX, feldFrom.getY());
				zugNach = transformiereXYInNotation(feldTo.getX(), feldTo.getY());
				System.out.println("BAUER 114 Schwarz");
				System.out.println("BAUER zugVon: " + zugVon);
				System.out.println("BAUER zugNach: " + zugNach);
			}
		} else {
			tempKontrollFelder.addAll(feldFrom.getFigur().getKontrollierteFelder(feldFrom, felder, false, true));
			zufallsZahlNach = zufall.nextInt(tempKontrollFelder.size());
			zugNach = tempKontrollFelder.get(zufallsZahlNach).getNotation();
		}
		zieheFigur(zugVon, zugNach);
		zeigeSchachbrett();
	}
	/**
	 * Diese Methode transformiert die Koordinaten in Notation
	 * 
	 * @param y
	 *            der horizontale Wert des Feldes (wegen äußere Schleife = y)
	 * @param x
	 *            der vertikale Wert des Feldes (wegen innere Schleife = x)
	 * @return die Notation: z.B. a2, b5, h7,.. oder leerer String: ""
	 */
	public String transformiereXYInNotation(int y, int x) {
		String result = "";

		switch (x) {
		case 0:
			result += "a";
			break;
		case 1:
			result += "b";
			break;
		case 2:
			result += "c";
			break;
		case 3:
			result += "d";
			break;
		case 4:
			result += "e";
			break;
		case 5:
			result += "f";
			break;
		case 6:
			result += "g";
			break;
		case 7:
			result += "h";
			break;
		default:
			System.out.println("ERROR");
		}

		switch (y) {
		case 0:
			result += "1";
			break;
		case 1:
			result += "2";
			break;
		case 2:
			result += "3";
			break;
		case 3:
			result += "4";
			break;
		case 4:
			result += "5";
			break;
		case 5:
			result += "6";
			break;
		case 6:
			result += "7";
			break;
		case 7:
			result += "8";
			break;
		default:
			System.out.println("ERROR");
		}
		return result;
	}

	/**
	 * Diese Methode transformiert die Koordinaten die Notation in Koordinaten
	 * 
	 * @param notation
	 *            Schach-Notation des Feldes, z.B. e3,c2,f8,...
	 * @return gibt die Koordinate des Feldes zurück oder null
	 */
	public Feld transformiereSchachnotationInXY(String notation) {
		Feld result = null;
		int x = -1;
		int y = -1;

		if (notation.length() == 2) {

			switch (String.valueOf(notation.charAt(0))) {
				case ("a"):
					y = 0;
					break;
				case ("b"):
					y = 1;
					break;
				case ("c"):
					y = 2;
					break;
				case ("d"):
					y = 3;
					break;
				case ("e"):
					y = 4;
					break;
				case ("f"):
					y = 5;
					break;
				case ("g"):
					y = 6;
					break;
				case ("h"):
					y = 7;
					break;
				default:
					// System.out.println(" transformiereSchachnotationInXY ERROR");
			}

			switch (String.valueOf(notation.charAt(1))) {
				case ("1"):
					x = 0;
					break;
				case ("2"):
					x = 1;
					break;
				case ("3"):
					x = 2;
					break;
				case ("4"):
					x = 3;
					break;
				case ("5"):
					x = 4;
					break;
				case ("6"):
					x = 5;
					break;
				case ("7"):
					x = 6;
					break;
				case ("8"):
					x = 7;
					break;
				default:
			}
			if (x >= 0 && y >= 0)
				result = new Feld(x, y);
		}
		return result;
	}
}
