package schachkame;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Diese Klasse modelliert ein Feld auf dem Schachbrett
 * Implementiert Cloneable, um "echtes" / tiefes Kopieren/Navigieren zu ermöglichen
 * Implementiert Serializable, um Zugliste persistent zu speichern
 * Überschreibt die Equals-Methode, um Feldvergleiche zu ermöglichen
 * Jede Figur ist einem Feld zugeordnet:
 * erstellen über addFigur()-Methode 
 * umfangreiche Feld-Manipulationen finden hier statt
 * 
 * 
 * @author Karsten Menzel
 *
 */
public class Feld implements Cloneable, Serializable {
	// ToDo: Feldfarbe definieren?
	Figur figur = new Figur("farbe_dummy", "name_dummy", 0, 0);
	
	private int x = 0;
	private int y = 0;
	String notation ="";
	boolean istBesetzt = false;
		
	/**
	 * Der Kontruktoraufruf initialisiert das Figur-Objekt (beim ersten SetUp)  
	 * und übergibt dem "Feld" seine Koordinaten
	 * @param figur Referenz der Figur auf dem Feld
	 * @param color Farbe des Feldes bisher nicht genutzt
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Feld(Figur figur, String color, int initX, int initY) {
		this.figur = figur;
		setX(initX);
		setY(initY);
		istBesetzt = true;
	}
	/**
	 * Der abgespeckte Konstruktor: wenn keine Figur hinterlegt wird
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Feld(int initX, int initY) {
		setX(initX);
		setY(initY);
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}	
	/**
	 * Testausgabe
	 */
	public void zeigeFeldInfo() {
		System.out.println("zeigeFeldInfo: " + "--- ANFANG ---");
		System.out.println("x: " + getX() + " y: " + getY() + " notation: " + getNotation() +
				" " +figur.getName() + "-" + figur.getFarbe() + " besetzt: " + istBesetzt + " | " + figur.getClass().toString());
		System.out.println("zeigeFeldInfo: " + "--- ENDE ---");
	}
	/**
	 * Diese Methode initialisert: 
	 * das Feld als "unbesetzt" und
	 * die Figur mit dem Figur-Objekt der Mutterklasse
	 */
	public void resetFeld() {
		this.setFeldFrei();
		figur = new Figur("farbe_dummy", "name_dummy", 0, 0);
	}
	/**
	 * Diese Methode gibt zurück, ob Feld besetzt ist
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean istBesetzt() {
		return istBesetzt;		
	}
	/**
	 * Diese Methode markiert das Feld als besetzt
	 */
	public void setFeldBesetzt() {
		istBesetzt = true;
	}
	/**
	 * Diese Methode markiert das Feld als unbesetzt
	 */
	public void setFeldFrei() {
		istBesetzt = false;
	}
	/**
	 * Diese Methode gibt die Objekt-Referenz der Figur zurück 
	 * @return Figur-Referenz (sollte NIE null sein!)
	 */
	public Figur getFigur() {
		return figur;
	}
	/**
	 * Diese Methode gibt eine Referenz von diesem Feld / "sich selbst" zurück
	 * @return Feld-Referenz
	 */
	public Feld getFeld() {
		return this;
	}
	/**
	 * Diese Methode fügt dem Feld eine Figur hinzu
	 * wird beim Schlagen und Ziehen verwendet 
	 * @param figur Figur-Referenz
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL)
	 */
	public void addFigur(Figur figur, int initX, int initY) {
		this.figur = figur;
		setX(initX);
		setY(initY);
		istBesetzt = true;
	}
	/**
	 * Diese Methode fügt dem Feld eine Figur hinzu 
	 * @param figur eine figur
	 */
	public void addFigur(Figur figur) {
		this.figur = figur;
		istBesetzt = true;
	}
	/**
	 * Diese Methode fügt eine Schach-Notation hinzu
	 * @param notation String-Wert
	 */
	public void addNotation(String notation) {
		this.notation = notation;
	}	
	/**
	 * Diese Methode gibt den ersten Buchstaben des Figur-Namens zurück
	 * wird beim zusammenbauen der PGNNotation verwendet
	 * @return Prefix
	 */
	public String getFigurPrefix() {
		String prefix = "";
		String s = figur.toString();
		prefix = s.substring(11,12 );
		// Bauer und Figur sollen nicht betrachtet werden
		if (prefix.equals("B") || prefix.equals("F") ) {
			prefix = "";
		} 
		return prefix;
	}
	/**
	 * Diese Methode gibt den Prefix zurück
	 * @return Prefix oder null
	 */
	public String getNotation() {
		return notation;
	}	
	/**
	 * Diese Methode gibt die Notation inkl. Prefix zurück
	 * @return String-Wert PGNNotation
	 */
	public String getPGNNotation() {
		 String figurPrefix = getFigurPrefix(); 	        	        
		return figurPrefix + getNotation(); 
	}
	
	/**
	 * Wahrheitswert zur Bestimmung, ob Feld leer ist
	 * @return Object gibt eine Object-Kopie des Feldes zurück  
	 */		
	@Override
	public Object clone() {
	      try {
	         Feld feld = (Feld) super.clone();
	         return feld;
	      } catch (CloneNotSupportedException e) { 
	         return null;
	      }
	   }
	/**
	 * Testausgabe
	 */
	@Override
	public String toString() {
		return "Feld [figur=" + figur + ", x=" + getX() + ", y=" + getY() + ", notation="
				+ notation + ", istBesetzt=" + istBesetzt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getX();
		result = prime * result + getY();
		return result;
	}
	/**
	 * Um Feldvergleiche zu ermöglichen
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feld other = (Feld) obj;
		if (getX() != other.getX())
			return false;
		if (getY() != other.getY())
			return false;
		return true;
	}
}
