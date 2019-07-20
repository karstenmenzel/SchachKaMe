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
public class Turm extends Figur{
	/**
	 * Konstruktor, der einen Turm zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Turm(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);
	}
	/**
	 * Diese Methode prüft, ob ein  Feld besetzt ist
	 * @param feld Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isFeldBesetzt(Feld feld) {
		boolean result = false;
		if (feld.istBesetzt()) result = true;
		return result;
	}
		
	/* Hier werden die Felder geklont, da echtes Kopieren erforderlich ist */
	@Override
	public ArrayList<Feld> getPfadZumKoenig(Feld feld, Feld[][] felder, Feld angreifer) {
		ArrayList<Feld> resultPfadZumKoenig = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadN = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadO = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadS = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadW = new ArrayList<Feld>();
			boolean isKoenigGefunden = false;
			
			Feld tempFeld = null;
				tempFeld = (Feld) angreifer.clone();
			{
				
				while (getFeldNord(tempFeld, felder) != null) {
					tempFeld = (Feld) getFeldNord(tempFeld, felder).clone();
					
					if(tempFeld.getFigur() instanceof Koenig && !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
						isKoenigGefunden = true;
						resultPfadZumKoenig.addAll(resultPfadN);
						break;
					}					
					resultPfadN.add(tempFeld);
				}
				
				tempFeld = (Feld) angreifer.clone();
				if(!isKoenigGefunden)
				while (getFeldOst(tempFeld, felder) != null) {
					tempFeld = (Feld) getFeldOst(tempFeld, felder).clone();

					if(tempFeld.getFigur() instanceof Koenig && !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
						System.out.println("49");
						isKoenigGefunden = true;
						resultPfadZumKoenig.addAll(resultPfadO);
						break;
					}					
					resultPfadO.add(tempFeld);
				}
				tempFeld = (Feld) angreifer.clone();
				if(!isKoenigGefunden)
				while (getFeldSued(tempFeld, felder) != null) {
					tempFeld = (Feld) getFeldSued(tempFeld, felder).clone();
					
					if(tempFeld.getFigur() instanceof Koenig && !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
						isKoenigGefunden = true;
						resultPfadZumKoenig.addAll(resultPfadS);
						break;
					}					
					resultPfadS.add(tempFeld);
				}
				tempFeld = (Feld) angreifer.clone();
				while (getFeldWest(tempFeld, felder) != null) {
					tempFeld = (Feld) getFeldWest(tempFeld, felder).clone();

					if(tempFeld.getFigur() instanceof Koenig && !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
						isKoenigGefunden = true;
						resultPfadZumKoenig.addAll(resultPfadW);
						break;
					}					
					resultPfadW.add(tempFeld);
				}				
		}
		return resultPfadZumKoenig;
	}

	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder, boolean mitAdd, boolean mitReturn) {
		ArrayList<Feld> result = new ArrayList<Feld>();	
		boolean isHindernisGefunden = false;
		Feld tempFeld = null;
		tempFeld = (Feld) feld.clone();
		Feld refFeld = null;

		while (getFeldNord(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldNord(tempFeld, felder).clone();

			// weitere Felder muessen nicht mehr betrachtet werden, da "Schach"unterbrochen
			if (isHindernisGefunden)
				break;

			if (feld.getFigur().getFarbe() == "weiss") {
				Schachbrett.addKontrolliertesFeld(tempFeld, true);
			}
			if (feld.getFigur().getFarbe() == "schwarz") {
				Schachbrett.addKontrolliertesFeld(tempFeld, false);
			}
			result.add(tempFeld);
			// besetztes Feld In Diagonale?
			if (tempFeld.istBesetzt)
				isHindernisGefunden = true;
		}

		tempFeld = (Feld) feld.clone();
		isHindernisGefunden = false;
		while (getFeldOst(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldOst(tempFeld, felder).clone();

			// weitere Felder muessen nicht mehr betrachtet werden, da "Schach"unterbrochen
			if (isHindernisGefunden)
				break;

			if (feld.getFigur().getFarbe() == "weiss") {
				Schachbrett.addKontrolliertesFeld(tempFeld, true);
			}
			if (feld.getFigur().getFarbe() == "schwarz") {
				Schachbrett.addKontrolliertesFeld(tempFeld, false);
			}
			result.add(tempFeld);
			// besetztes Feld In Diagonale?
			if (tempFeld.istBesetzt)
				isHindernisGefunden = true;
		}

		tempFeld = (Feld) feld.clone();
		isHindernisGefunden = false;
		while (getFeldSued(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldSued(tempFeld, felder).clone();

			// weitere Felder muessen nicht mehr betrachtet werden, da "Schach"unterbrochen
			if (isHindernisGefunden)
				break;

			if (feld.getFigur().getFarbe() == "weiss") {
				Schachbrett.addKontrolliertesFeld(tempFeld, true);
			}
			if (feld.getFigur().getFarbe() == "schwarz") {
				Schachbrett.addKontrolliertesFeld(tempFeld, false);
			}
			result.add(tempFeld);
			// besetztes Feld In Diagonale?
			if (tempFeld.istBesetzt)
				isHindernisGefunden = true;
		}

		tempFeld = (Feld) feld.clone();
		isHindernisGefunden = false;
		while (getFeldWest(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldWest(tempFeld, felder).clone();

			// weitere Felder muessen nicht mehr betrachtet werden, da "Schach"unterbrochen
			if (isHindernisGefunden)
				break;

			if (feld.getFigur().getFarbe() == "weiss") {
				Schachbrett.addKontrolliertesFeld(tempFeld, true);
			}
			if (feld.getFigur().getFarbe() == "schwarz") {
				Schachbrett.addKontrolliertesFeld(tempFeld, false);
			}
			result.add(tempFeld);
			// besetztes Feld In Diagonale?
			if (tempFeld.istBesetzt)
				isHindernisGefunden = true;
		}
		return result;
	}
	/**
	 * Diese Methode validiert das Zugmusterintervall des Turms
	 * @param vonX Quell-Feld X-Koordinate
	 * @param bisX Ziel-Feld X-Koordinate
	 * @param vonY Quell-Feld Y-Koordinate
	 * @param bisY Ziel-Feld Y-Koordinate
	 * @param felder das Schachbrett
	 * @return booelan-Wert: Ja/Nein
	 */	
	public boolean getZugMusterIntervall(int vonX, int bisX, int vonY, int bisY, Feld[][] felder) {
		boolean result = false;
		int zaehlerY = vonY;
		int zaehlerX = vonX;

		if (vonX == bisX) {
			boolean lastFeldLeer = false;
			if (vonY >= 0 && bisY < 8 && bisY > vonY) {
				for (zaehlerY = vonY + 1; zaehlerY < 8; zaehlerY++) {
					if (!isFeldBesetzt(felder[vonX][zaehlerY])
							&& (!felder[vonX][zaehlerY].equals(felder[bisX][bisY]))) {
						result = true;
					}
					if (isFeldBesetzt(felder[vonX][zaehlerY]) && (!felder[vonX][zaehlerY].equals(felder[bisX][bisY]))) {
						result = false;
						break;
					} else if (felder[vonX][zaehlerY].equals(felder[vonX][zaehlerY])) {
						result = true;
						break;
					}
				}
			} else if (vonY >= 0 && bisY < 8 && bisY < vonY) {
				for (zaehlerY = vonY - 1; zaehlerY >= 0; zaehlerY--) {
					if (!isFeldBesetzt(felder[vonX][zaehlerY])
							&& (!felder[vonX][zaehlerY].equals(felder[bisX][bisY]))) {
						result = true;

					}
					if (isFeldBesetzt(felder[vonX][zaehlerY]) && (!felder[vonX][zaehlerY].equals(felder[bisX][bisY]))) {
						result = false;
						break;

					} else if (felder[vonX][zaehlerY].equals(felder[vonX][zaehlerY])) {
						result = true;
						break;
					}

				}
			}
		} else if (vonY == bisY) {
			boolean lastFeldLeer = false;
			if (vonX >= 0 && bisX < 8 && bisX > vonX) {
				for (zaehlerX = vonX + 1; zaehlerX < 8; zaehlerX++) {
					if (!isFeldBesetzt(felder[zaehlerX][vonY])
							&& (!felder[zaehlerX][vonY].equals(felder[bisX][bisY]))) {
						result = true;

					}
					if (isFeldBesetzt(felder[zaehlerX][vonY]) && (!felder[zaehlerX][vonY].equals(felder[bisX][bisY]))) {
						result = false;
						break;

					} else if (felder[zaehlerX][vonY].equals(felder[bisX][bisY])) {
						result = true;
						break;

					}

				}
			} else if (vonX > 0 && bisX < 8 && bisX < vonX) {
				for (zaehlerX = vonX - 1; zaehlerX >= 0; zaehlerX--) {
					if (!isFeldBesetzt(felder[zaehlerX][vonY])
							&& (!felder[zaehlerX][vonY].equals(felder[bisX][bisY]))) {
						result = true;
					} else if (isFeldBesetzt(felder[zaehlerX][vonY])
							&& (!felder[zaehlerX][vonY].equals(felder[bisX][bisY]))) {
						result = false;
						break;

					} else if (felder[zaehlerX][vonY].equals(felder[bisX][bisY])) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
	@Override
	public boolean istZugMusterGueltig(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if (this.getZugMusterIntervall(from.getX(), to.getX(), from.getY(), to.getY(), felder)) {
			result = true;
		}
		return result;
	}
}
