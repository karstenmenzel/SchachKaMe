package schachkame;
import java.io.Serializable;
/**
 * Diese Klasse modelliert den Zug
 * 
 * @author Karsten Menzel
 *
 */

public class Zug implements Serializable{
	// Testausgabe
	@Override
	public String toString() {
		return "Zug [zugVonWeiss=" + zugVonWeiss + ", zugVonSchwarz="
				+ zugVonSchwarz + ", einTeilzug=" + einTeilzug + ", feldFrom="
				+ feldFrom + ", feldTo=" + feldTo + "]";
	}
	/**
	 * Zug von WEISS
	 * wird verwendet bei "ganzen" Zügen: Weiss UND Schwarz ziehen
	 */
	private String zugVonWeiss ="";
	/**
	 * Zug von SCHWARZ
	 * wird verwendet bei "ganzen" Zügen: Weiss UND Schwarz ziehen
	 */
	private String zugVonSchwarz ="";
	/**
	 * ein Teilzug
	 * wird verwendet bei Teil-Zügen: Weiss ODER Schwarz zieht
	 */
	private String einTeilzug ="";
	/**
	 * Quell-Feld
	 */
	private Feld feldFrom = new Feld(88, 88);
	/**
	 * Ziel-Feld
	 */
	private Feld feldTo = new Feld(88, 88);
	/**
	 * Der Index beim ersten Zug wird initialisiert
	 */
	private String index = "1";
	
	/**
	 * Der Konstruktor wird beim Schreiben in die Datei verwendet
	 * mit Zug-Index
	 * @param index ZugNr
	 * @param zugVonWeiss Zug in PGNNotation (= Prefix der Figur + Notation)
	 * @param zugVonSchwarz Zug in PGNNotation (= Prefix der Figur + Notation)
	 */
	public Zug(String index, String zugVonWeiss, String zugVonSchwarz) {
		super();
		this.index = index;
		this.zugVonWeiss = zugVonWeiss;
		this.zugVonSchwarz = zugVonSchwarz;
	}
	/**
	 * Der Konstruktor wird beim Erstellen der Zugliste verwendet
	 * @param zugVonWeiss s.o.
	 * @param zugVonSchwarz s.o.
	 */
	public Zug(String zugVonWeiss, String zugVonSchwarz) {
		super();
		this.zugVonWeiss = zugVonWeiss;
		this.zugVonSchwarz = zugVonSchwarz;
	}
	public Zug() {
		
	}
	/**
	 * Diese Methode gibt das Ziel-Feld zurück
	 * @return Ziel-Feld 
	 */
	public Feld getFeldTo() {
		return feldTo;
	}
	/**
	 * Diese Methode gibt die ZugNr zurück
	 * @return index
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * Diese Methode gibt den Zug von Weiss zurück
	 * @return String-Wert 
	 */
	public String getZugVonWeiss() {
		return zugVonWeiss;
	}
	/**
	 * Diese Methode setzt
	 * @param einTeilzug notaion
	 * @param feldFrom quell-feld
	 * @param feldTo ziel-feld
	 */
	public void setTeilzug(String einTeilzug, Feld feldFrom, Feld feldTo) {
		this.einTeilzug = einTeilzug;
		this.feldFrom = feldFrom;
		this.feldTo = feldTo;
	}
	
	public void setZugVonWeiss(String zugVonWeiss) {
		this.zugVonWeiss = zugVonWeiss;
	}
	
	public String getZugVonSchwarz() {
		return zugVonSchwarz;
	}
	
	public void setZugVonSchwarz(String zugVonSchwarz) {
		this.zugVonSchwarz = zugVonSchwarz;
	}
	
	public String getZug(boolean mitIndex) {
		if(mitIndex) {
			String sZug = getIndex() + getZugVonWeiss() + " " +getZugVonSchwarz();
		}
		else {
		}
		String sZug = getZugVonWeiss() + " " +getZugVonSchwarz();
		return sZug;
	}
	public String getTeilzug() {
		return einTeilzug;
	}
}
