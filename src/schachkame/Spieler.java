package schachkame;

import schachkame.frontend.Schachbrett;

/**
 * Diese Klasse modelliert die Spieler
 * 
 * @author Karsten Menzel
 * 
 */
public class Spieler {
	/**
	 * Name des Spielers
	 */
	private String name ="";
	/**
	 * Figurenname
	 */
	private String figurenFarbe = "";
	/**
	 * gibt zurück, ob Spieler ist am Zug
	 */
	private boolean isAmZug = false;
	
	/**
	 * Dieser Kontruktor initialisiert den Spieler
	 * Der Spieler mit den WEISSEN Steinen zieht zuerst
	 * @param figurenFarbe Fiurenfarbe
	 * @param name Name des Spielers
	 */
	public Spieler(String figurenFarbe, String name) {
		this.figurenFarbe = figurenFarbe;
		this.name = name;
		if (figurenFarbe.equals(Schachbrett.WEISS)) isAmZug = true;
	}
	/**
	 * Diese Methode Kehrt den Zugstatus um
	 */
	public void toggleIsAmZug() {
		isAmZug = !isAmZug;
	}
	/**
	 * Diese Methode ist selbsterklärend
	 * @return Ja/Nein
	 */
	public boolean isAmZug() {
		return isAmZug;
	}
	/**
	 * Diese Methode ist selbsterklärend
	 * @return Ja/Nein
	 */
	public boolean hatWeiss() {
		return figurenFarbe.equals(Schachbrett.WEISS);
	}
	/**
	 * Diese Methode ist selbsterklärend
	 * @return Firgurenfarebe
	 */
	public String getFigurenFarbe() {
		return figurenFarbe;
	}
	/**
	 * Diese Methode ist selbsterklärend
	 * @return Name
	 */
	public String getName() {
		return name;
	}
}
