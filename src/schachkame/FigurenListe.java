package schachkame;
/**
 * Diese Enum wird verwendet, um beim SetUp der Figuren,
 * Typsicherheit/Namenssicherheit zu schaffen
 * So können nur vorgegebene Figurennamen verwendet werden
 * 
 * @author Karsten Menzel
 *
 */
public enum FigurenListe {
	BAUER("Bauer"), TURM("Turm") , SPRINGER("Springer"), LAEUFER("Laeufer"), DAME("Dame"), KOENIG("Koenig");
	   private final String stringValue;
	   /**
	    * Der Konstruktor weist jedem enum (s)einen Namen zu
	    * @param s der Figurenname
	    */
	   FigurenListe(final String s) { stringValue = s; }
	   /**
	    * Diese Methode wird produktiv nicht genutzt
	    * Stattdessen die .name()-Methode
	    */
	   public String toString() { return stringValue; }
}
