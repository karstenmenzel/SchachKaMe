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

public class Koenig extends Figur {
	/**
	 * Konstruktor, der einen König zusammenstellt
	 * ruft Konstruktor der Mutterklasse auf
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL)
	 */
	public Koenig(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Diese Methode überschreibt die Methode in der Oberklasse und
	 * ermittelt die kontrollierten Felder von WEISS und SCHWARZ und 
	 * übergibt diese an die aufrufende statische Methode 'addKontrolliertesFeld'
	 * @return 	ArrayList mit kontrolierten Felder oder null
	 * 			wird NUR HIER in der Klasse Koenig genutzt!
	
	 */
	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder, boolean mitAdd, boolean mitReturn) { 
		ArrayList<Feld> result = new ArrayList<Feld>();
		
		if (feld.getFigur().getFarbe() == Schachbrett.WEISS) {
			// test getFeldNord
			if (getFeldNord(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldNord(feld, felder), true);
				if(mitReturn)
					result.add(getFeldNord(feld, felder));
			}
			// test getFeldOst
			if (getFeldOst(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldOst(feld, felder), true);
				if(mitReturn)
					result.add(getFeldOst(feld, felder));
			}
			// test getFeldSued
			if (getFeldSued(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldSued(feld, felder), true);
				if(mitReturn)
					result.add(getFeldSued(feld, felder));
			}
			// test getFeldWest
			if (getFeldWest(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldWest(feld, felder), true);
				if(mitReturn)
					result.add(getFeldWest(feld, felder));
			}

			// test getFeldNordWest
			if (getFeldNordWest(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldNordWest(feld, felder), true);
				if(mitReturn)
					result.add(getFeldNordWest(feld, felder));
			}
			// test getFeldNordOst
			if (getFeldNordOst(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldNordOst(feld, felder), true);
				if(mitReturn)
					result.add(getFeldNordOst(feld, felder));
			}
			// test getFeldSuedOst
			if (getFeldSuedOst(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldSuedOst(feld, felder), true);
				if(mitReturn)	
					result.add(getFeldSuedOst(feld, felder));
			}
			// test getFeldSuedWest
			if (getFeldSuedWest(feld, felder) != null) {
				if(mitAdd)
					Schachbrett.addKontrolliertesFeld(getFeldSuedWest(feld, felder), true);
				if(mitReturn)
					result.add(getFeldSuedWest(feld, felder));
			}
		} else
			if (feld.getFigur().getFarbe() == Schachbrett.SCHWARZ) {
				// test getFeldNord
				if (getFeldNord(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldNord(feld, felder), false);
					if(mitReturn)
						result.add(getFeldNord(feld, felder));
				}
				// test getFeldOst
				if (getFeldOst(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldOst(feld, felder), false);
					if(mitReturn)
						result.add(getFeldOst(feld, felder));
				}
				// test getFeldSued
				if (getFeldSued(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldSued(feld, felder), false);
					if(mitReturn)
						result.add(getFeldSued(feld, felder));
				}
				// test getFeldWest
				if (getFeldWest(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldWest(feld, felder), false);
					if(mitReturn)
						result.add(getFeldWest(feld, felder));
				}

				// test getFeldNordWest
				if (getFeldNordWest(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldNordWest(feld, felder), false);
					if(mitReturn)
						result.add(getFeldNordWest(feld, felder));
				}
				// test getFeldNordOst
				if (getFeldNordOst(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldNordOst(feld, felder), false);
					if(mitReturn)
						result.add(getFeldNordOst(feld, felder));
				}
				// test getFeldSuedOst
				if (getFeldSuedOst(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldSuedOst(feld, felder), false);
					if(mitReturn)
						result.add(getFeldSuedOst(feld, felder));
				}
				// test getFeldSuedWest
				if (getFeldSuedWest(feld, felder) != null) {
					if(mitAdd)
						Schachbrett.addKontrolliertesFeld(getFeldSuedWest(feld, felder), false);
					if(mitReturn)
						result.add(getFeldSuedWest(feld, felder));
				}
			}
//		System.out.println("Koenig result.size: " + result.size());
		return result;
	}
	/**
	 * Diese Methode validiert, ob Zug-Wunsch eine Rochade ist	
	 * @param from Quell-Feld König
	 * @param to Ziel-Feld König
	 * @param felder das Schachbrett
	 * @return Ja/Nein
	 */
	public boolean istZugRochade(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if (from.getFigur().getFarbe() == Schachbrett.WEISS) {
			if(from.getFigur() instanceof Koenig  && felder[0][7].getFigur() instanceof Turm && to.getNotation().equals("g1")) {
				//das erste UND zweite Feld östlich vom König frei??? 
				if(!from.getFigur().getFeldOst(from, felder).istBesetzt()
					&& !from.getFigur().getFeldOst(from.getFigur().getFeldOst(from, felder), felder).istBesetzt()) {
					Schachbrett.setRochadeWeissKlein();
					result = true;
				}
			} 
		if(from.getFigur() instanceof Koenig  && felder[0][0].getFigur() instanceof Turm  && to.getNotation().equals("c1")) {
				//das erste UND zweite UND dritte Feld westlich vom König frei??? 
				if(!from.getFigur().getFeldWest(from, felder).istBesetzt()
					&& !from.getFigur().getFeldWest(from.getFigur().getFeldWest(from, felder), felder).istBesetzt()
					&& !from.getFigur().getFeldWest(from.getFigur().getFeldWest(from.getFigur().getFeldWest(from, felder), felder), felder).istBesetzt()) {
					Schachbrett.setRochadeWeissGross();
					result = true;
				}
			}			
		}
		if (from.getFigur().getFarbe() == Schachbrett.SCHWARZ) {
				if(from.getFigur() instanceof Koenig  && felder[7][7].getFigur() instanceof Turm  && to.getNotation().equals("g8")) {
					//das erste UND zweite Feld östlich vom König frei??? 
					if(!from.getFigur().getFeldOst(from, felder).istBesetzt()
						&& !from.getFigur().getFeldOst(from.getFigur().getFeldOst(from, felder), felder).istBesetzt()) {
						Schachbrett.setRochadeSchwarzKlein();
						result = true;
					}
				}
		if(from.getFigur() instanceof Koenig  && felder[0][7].getFigur() instanceof Turm  && to.getNotation().equals("c8")) {
				//das erste UND zweite UND dritte Feld westlich vom König frei??? 
				if(!from.getFigur().getFeldWest(from, felder).istBesetzt()
					&& !from.getFigur().getFeldWest(from.getFigur().getFeldWest(from, felder), felder).istBesetzt()
					&& !from.getFigur().getFeldWest(from.getFigur().getFeldWest(from.getFigur().getFeldWest(from, felder), felder), felder).istBesetzt()) {
					Schachbrett.setRochadeSchwarzGross();
					result = true;
				}
			}	
		}
		return result;
	}
	public boolean isZielfeldKontrolliert(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if(this.getFarbe().equals(Schachbrett.WEISS)) {
			result = Schachbrett.getKontrollierteFelderSchwarz().contains(to);
		} else if(this.getFarbe().equals(Schachbrett.SCHWARZ)) {
			result = Schachbrett.getKontrollierteFelderWeiss().contains(to);
		} 
		return result;
	}
	/**
	 * Diese Methode validiert das Zugmuster des Königs
	 * @param vonX Quell-Feld X
	 * @param bisX Ziel-Feld X
	 * @param vonY Quell-Feld Y
	 * @param bisY Ziel-Feld Y
	 * @return Ja/Nein
	 */
	public boolean getZugMusterIntervallKoenig(int vonX, int bisX, int vonY, int bisY) {
		boolean result = false;
//		System.out.println("----- ANFANG ZUG-Process------ getZugMusterIntervall Koenig : " + this.getClass().toString());
		
		if (vonX == bisX) {
			if ( Math.abs(bisY - (vonY)) ==  1) {
				result = true;
//				System.out.println("----- pVonX == pBisX && pBisY - (pVonY)) ==  1 :  pBisY +\"/\"+pVonY" + bisY +"/"+vonY );
//				System.out.println("----- Math.abs(pBisY - (pVonY)): " + Math.abs(bisY - (vonY))); 
			}
		} else if (vonY == bisY) {
				if (Math.abs(bisX - (vonX)) ==  1) {
					result = true;
//					System.out.println("----- pVonY == pBisY && pBisX - (pVonX)) ==  1");
				}
			} else if (Math.abs(bisX - (vonX)) ==  1 && Math.abs(bisY - (vonY)) ==  1) {
				result = true;
				} 		
		return result;
	}
	@Override
	public boolean istZugMusterGueltig(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		
		if(isZielfeldKontrolliert(from, to, felder)) {
		} else if(istZugRochade(from, to, felder)) {
			result = true;
		} else
		if (this.getZugMusterIntervallKoenig(from.getX(), to.getX(), from.getY(), to.getY())) {
			result = true;
		}
		return result;
	}
}
