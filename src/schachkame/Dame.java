package schachkame;
import java.util.ArrayList;
/**
 *  * Diese Klasse ist eine Unterklasse von Figur
 * nutzt Grundfunktionen: Feststellen der Nachbarfelder, 
 * implementiert: Validierung der eigenen Zugmöglichkeit  und mehr
 * 
 * @author Karsten Menzel
 *
 */
public class Dame extends Figur {
	/**
	 * Konstruktor, der eine Dame zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Dame(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);
	}

	@Override
	public ArrayList<Feld> getPfadZumKoenig(Feld feld, Feld[][] felder, Feld angreifer) {
		ArrayList<Feld> resultPfadZumKoenig = new ArrayList<Feld>();
		resultPfadZumKoenig.addAll(new Laeufer("", "", 88, 88).getPfadZumKoenig(feld, felder, angreifer));
		resultPfadZumKoenig.addAll(new Turm("", "", 88, 88).getPfadZumKoenig(feld, felder, angreifer));
		return resultPfadZumKoenig;
	}
	
	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder,  boolean mitAdd, boolean mitReturn) {
		ArrayList<Feld> result = new ArrayList<Feld>();
		result.addAll(new Laeufer("dummy", "dummy", feld.getX(), feld.getY()).getKontrollierteFelder(feld, felder, true, false));
		result.addAll(new Turm("dummy", "dummy", feld.getX(), feld.getY()).getKontrollierteFelder(feld, felder, true, false));
		return result;
	}
	/**
	 * Diese Methode validiert das Zugmusterintervall der Dame
	 * durch Aufruf der entsprechenden Methoden von Turm und Läufer
	 * @param vonX Quell-Feld X-Koordinate
	 * @param bisX Ziel-Feld X-Koordinate
	 * @param vonY Quell-Feld Y-Koordinate
	 * @param bisY Ziel-Feld Y-Koordinate
	 * @param felder das Schachbrett
	 * @return booelan-Wert: Ja, wenn Zug ok, Nein, andernfalls
	 */
	public boolean getZugMusterIntervallDame(int vonX, int bisX, int vonY,
		int bisY, Feld[][] felder) {
		boolean result = false;

		if (new Laeufer("dummy", "dummy", vonX, vonY).getZugMusterIntervallLaeufer(vonX, bisX, vonY, bisY,
						felder) ||
				new Turm("dummy", "dummy", vonX, vonY).getZugMusterIntervall(vonX, bisX, vonY, bisY, felder)	) {
			result = true;
		} 
		return result;
	}
	/**
	 * 	Diese Methode überschreibt die Methode der Mutterklasse
	 */
	@Override
	public boolean istZugMusterGueltig(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if (this.getZugMusterIntervallDame(from.getX(), to.getX(), from.getY(), to.getY(),felder)) {
			result = true;
		}
		return result;
	}
}