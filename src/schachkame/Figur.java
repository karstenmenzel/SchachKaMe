package schachkame;
import java.io.Serializable;
import java.util.ArrayList;

import schachkame.frontend.Schachbrett;
/**
 * Diese Klasse ist die Oberklassse aller "echten" Figuren
 * bietet Grundfunktionen an: Feststellen der Nachbarfelder, 
 * sowie zum Implementieren: Validierung der eigenen Zugm�glichkeit und mehr
 * 
 * @author Karsten Menzel
 *
 */
public class Figur implements Serializable {
	/**
	 * Zeichenkette die die Farbe der Figur bezeichnet
	 */
	private String farbe = "";
	/**
	 * Zeichenkette die den Namen des Spielers bezeichnet
	 */
	private String name = "";
	/**
	 * Ganzzahl, die die X-Koordinate des Bretts bezeichnet
	 * HIER: VERTIKALE Ausrichtung
	 */
	private int initX = 0;
	/**
	 * Ganzzahl, die die Y-Koordinate des Bretts bezeichnet
	 * HIER: HORIZONTALE Ausrichtung
	 */
	private int initY = 0;	
	/**
	 * Konstante: OBERE_GRENZE_HORIZONTAL
	 */
	final protected int OBERE_GRENZE_HORIZONTAL = 7;
	/**
	 * Konstante: UNTERE_GRENZE_HORIZONTAL
	 */
	final protected int UNTERE_GRENZE_HORIZONTAL = 0;
	/**
	 * Konstante: OBERE_GRENZE_VERTIKAL
	 */
	final protected int OBERE_GRENZE_VERTIKAL = 7;
	/**
	 * Konstante: UNTERE_GRENZE_VERTIKAL
	 */
	final protected int UNTERE_GRENZE_VERTIKAL = 0;

	/**
	 * Konstruktor, der eine Figur zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL)
	 */
	public Figur(String farbe, String name, int initX, int initY) {
		setFarbe(farbe); 
		setName(name);
		this.initX = initX;
		this.initY = initY;
	}
	/**
	 * Diese Methode liefert alle Felder ausgehend vom Schachgeber/Angreifer zum K�nig
	 * wird von Unterklassen �berschrieben
	 * @param feld Feld des Koenigs
	 * @param felder ist das 2-dim. Array des Schachbretts
	 * @param feldAngreifer Feld der angreifenden Figur
	 * @return ArrayList, die den Pfad zum Koenig enth�lt oder null ist
	 */
	public ArrayList<Feld> getPfadZumKoenig(Feld feld, Feld[][] felder, Feld feldAngreifer) {
		return null;
	}
	/**
	 * Diese Methode gibt das n�chste n�rdliche Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldNord(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if (feld.getX() >= 0 && feld.getY() >= 0 && feld.getX() < 7 && feld.getY() <= 7) {
				result = felder[feld.getX() + 1][feld.getY()];
			}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste Nord-West Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldNordWest(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if (feld.getX() >= 0 && feld.getY() > 0 && feld.getX() < 7 && feld.getY() <= 7) {
				result = felder[feld.getX() + 1][feld.getY() - 1];
			}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste Nord-Ost Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldNordOst(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if (feld.getX() >= 0 && feld.getY() >= 0 && feld.getX() < 7 && feld.getY() < 7) {
				result = felder[feld.getX() + 1][feld.getY() + 1];
//				System.out.println("getFeldNordOst");
//				result.zeigeFeldInfo();
			}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste Ost Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldOst(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if (feld.getX() >= 0 && feld.getY() >= 0 && feld.getX() <= 7 && feld.getY() < 7) {
				result = felder[feld.getX()][feld.getY() + 1];
//				System.out.println("getFeldOst");
//				result.zeigeFeldInfo();
			}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste S�d-Ost Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldSuedOst(Feld feld, Feld[][] felder) {
		Feld result = null;
		
		if (feld != null)
			if(feld.getX() > 0 && feld.getY() >= 0 && feld.getX() <= 7 && feld.getY() < 7) {
			result = felder[feld.getX() - 1][feld.getY() + 1];
//			System.out.println("getFeldSuedOst");
//			result.zeigeFeldInfo();
		}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste S�d-West Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldSuedWest(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if(feld.getX() > 0 && feld.getY() > 0 && feld.getX() <= 7 && feld.getY() <= 7) {
			result = felder[feld.getX() - 1][feld.getY() - 1];
//			System.out.println("getFeldSuedWest");
//			result.zeigeFeldInfo();
		}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste S�d Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldSued(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if(feld.getX() > 0 && feld.getY() >= 0 && feld.getX() <= 7 && feld.getY() <= 7) {
			result = felder[feld.getX() - 1][feld.getY()];
//			System.out.println("getFeldSued");
//			result.zeigeFeldInfo();
		}
		return result;		
	}
	/**
	 * Diese Methode gibt das n�chste West Nachbarfeld zur�ck   			
	 * @param feld Quell-Feld
	 * @param felder das Schachbrett (mit Feldern)
	 * @return das Ziel-Feld oder null
	 */
	public Feld getFeldWest(Feld feld, Feld[][] felder) {
		Feld result = null;
		if (feld != null)
			if(feld.getX() >= 0 && feld.getY() > 0 && feld.getX() <= 7 && feld.getY() <= 7) {
			result = felder[feld.getX()][feld.getY() - 1];
//			System.out.println("getFeldWest");
//			result.zeigeFeldInfo();
		}
		return result;		
	}
	/**
	 * Diese Methode gibt die kontrollierten Felder zur�ck
	 * wird von den Unterklassen implementiert	
	 * @param feldFrom Quell-Feld
	 * @param felder das Schachbrett
	 * @param mitAdd es soll die Add-Methode in der implementierenden Methode aufgerufen werden oder nicht
	 * Dies ist der STANDARD
	 * @param mitReturn soll eine R�ckgabe erfolgen oder nicht
	 * @return 	ArrayList mit kontrolierten Felder oder null
	 */
	public ArrayList<Feld> getKontrollierteFelder(Feld feldFrom, Feld[][] felder, boolean mitAdd, boolean mitReturn ) {
		return null;
	}
	/**
	 * Diese Methode validiert, ob eine Figur ein K�nig ist
	 * @return Ja/Nein
	 */
	public boolean istFigurKoenig() {
		boolean result = false;
		if (this instanceof Koenig) {
			result = true;
		} 
		return result;
	}
	/**
	 * Diese Methode validiert, ob ein Zug g�ltig ist
	 * wird von Unterklassen implementiert
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @param felder das Schachbrett
	 * @return Ja/Nein 
	 */
	public boolean istZugMusterGueltig(Feld feldFrom, Feld feldTo, Feld[][] felder) {
		boolean result = false;				
		return result;
	}
	/**
	 * Diese Methode gibt die Farbe der Figur zur�ck (WEISS/SCHWARZ)
	 * @return String String-Wert: w/s
	 */
	public String getFarbe() {
		return farbe;
	}
	/**
	 * Diese Methode setzt die Farbe der Figur (WEISS/SCHWARZ)
	 * @param farbe w/s
	 */
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
	/**
	 * Diese Methode gibt den Figur-Namen (Bauer, K�nig, Dame,..) zur�ck 
	 * @return String-Wert des Namens
	 */
	public String getName() {
		return name;
	}
	/**
	 * Diese Methode setzt den Figur-Namen (Bauer, K�nig, Dame,..) 
	 * @param name der Figurname
	 */
	public void setName(String name) {
		this.name = name;
	}	
	
}
