package schachkame;
import java.util.ArrayList;

import schachkame.frontend.Schachbrett;
/**
 * Diese Klasse ist eine Unterklasse von Figur
 * nutzt Grundfunktionen: Feststellen der Nachbarfelder, 
 * implementiert: Validierung der eigenen Zugmöglichkeit und mehr
 * 
 * @author Karsten Menzel
 *
 */
public class Springer extends Figur {
	/**
	 * Konstruktor, der einen Läufer zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Springer(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);
	}
	
	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder, boolean mitAdd, boolean mitReturn) {
		ArrayList<Feld> result = new ArrayList<Feld>();
		Feld tempFeld = null;
		tempFeld = (Feld) feld.clone();

		// 1
		tempFeld = (Feld) feld.clone();
		if (getFeldNord(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldNord(tempFeld, felder).clone();
				
			if (getFeldNordWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordWest(tempFeld, felder).clone();
	
				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 2
		tempFeld = (Feld) feld.clone();
		if (getFeldNord(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldNord(tempFeld, felder).clone();
				
			if (getFeldNordOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordOst(tempFeld, felder).clone();
	
				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 3
		tempFeld = (Feld) feld.clone();
		if (getFeldOst(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldOst(tempFeld, felder).clone();
				
			if (getFeldNordOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordOst(tempFeld, felder).clone();
	
				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 4
		tempFeld = (Feld) feld.clone();
		if (getFeldOst(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldOst(tempFeld, felder).clone();

			if (getFeldSuedOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedOst(tempFeld, felder).clone();

				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 5
		tempFeld = (Feld) feld.clone();
		if (getFeldSued(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldSued(tempFeld, felder).clone();

			if (getFeldSuedOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedOst(tempFeld, felder).clone();

				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}				

		// 6
		tempFeld = (Feld) feld.clone();
		if (getFeldSued(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldSued(tempFeld, felder).clone();

			if (getFeldSuedWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedWest(tempFeld, felder).clone();

				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 7
		tempFeld = (Feld) feld.clone();
		if (getFeldWest(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldWest(tempFeld, felder).clone();

			if (getFeldSuedWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedWest(tempFeld, felder).clone();

				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		
		// 8
		tempFeld = (Feld) feld.clone();
		if (getFeldWest(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldWest(tempFeld, felder).clone();

			if (getFeldNordWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordWest(tempFeld, felder).clone();

				if (feld.getFigur().getFarbe() == "weiss") {
					Schachbrett.addKontrolliertesFeld(tempFeld, true);
				}
				if (feld.getFigur().getFarbe() == "schwarz") {
					Schachbrett.addKontrolliertesFeld(tempFeld, false);
				}
				result.add(tempFeld);
			}
		}
		return result;
	}
	/**
	 * Diese Methode validiert das Zugmusterintervall des Springers
	 * @param vonX Quell-Feld X-Koordinate
	 * @param bisX Ziel-Feld X-Koordinate
	 * @param vonY Quell-Feld Y-Koordinate
	 * @param bisY Ziel-Feld Y-Koordinate
	 * @return booelan-Wert: Ja/Nein
	 */	
	public boolean getZugMusterIntervallSpringer(int vonX, int bisX, int vonY, int bisY) {
		boolean result = false;
		
		if (Math.abs(bisX - (vonX)) == 2) {
			if (Math.abs(bisY - (vonY)) == 1) {
				result = true;
			}
		} else if (Math.abs(bisY - (vonY)) == 2) {
			if (Math.abs(bisX - (vonX)) == 1) {
				result = true;
			}
		} else {
		}
		return result;
	}
	@Override
	public boolean istZugMusterGueltig(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if (this.getZugMusterIntervallSpringer(from.getX(), to.getX(), from.getY(), to.getY())) {
			result = true;
		}
		return result;
	}
}
