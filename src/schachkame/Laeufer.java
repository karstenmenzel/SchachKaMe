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
public class Laeufer extends Figur {
	/**
	 * Konstruktor, der einen Läufer zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL) 
	 */
	public Laeufer(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);
	}
	/**
	 * Diese Methode prüft, ob ein  Feld besetzt ist
	 * @param feld Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isFeldBesetzt(Feld feld) {
		boolean result = false;
		if (feld.istBesetzt())
			result = true;
		return result;
	}
	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder, boolean mitAdd, boolean mitReturn) {
		ArrayList<Feld> result = new ArrayList<Feld>();	
		boolean isHindernisGefunden = false;
		Feld tempFeld = null;
		tempFeld = (Feld) feld.clone();
		{

			while (getFeldNordWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordWest(tempFeld, felder).clone();

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
			while (getFeldNordOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordOst(tempFeld, felder).clone();

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
			while (getFeldSuedOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedOst(tempFeld, felder).clone();

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
			while (getFeldSuedWest(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedWest(tempFeld, felder).clone();

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
		}
		return result;
	}
	/* Hier werden die Felder geklont, da echtes Kopieren erforderlich ist */
	@Override	
	public ArrayList<Feld> getPfadZumKoenig(Feld feld, Feld[][] felder, Feld angreifer) {
		ArrayList<Feld> resultPfadZumKoenig = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadNW = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadNO = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadSO = new ArrayList<Feld>();
		ArrayList<Feld> resultPfadSW = new ArrayList<Feld>();
		boolean isKoenigGefunden = false;
		Feld tempFeld = null;
		tempFeld = (Feld) angreifer.clone();

		while (getFeldNordWest(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldNordWest(tempFeld, felder).clone();
			if (tempFeld.getFigur() instanceof Koenig
					&& !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
				isKoenigGefunden = true;
				resultPfadZumKoenig.addAll(resultPfadNW);
				break;
			}
			resultPfadNW.add(tempFeld);
		}
		tempFeld = (Feld) angreifer.clone();
		if (!isKoenigGefunden)
			while (getFeldNordOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldNordOst(tempFeld, felder).clone();
				if (tempFeld.getFigur() instanceof Koenig
						&& !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
					isKoenigGefunden = true;
					resultPfadZumKoenig.addAll(resultPfadNO);
					break;
				}
				resultPfadNO.add(tempFeld);
			}
		tempFeld = (Feld) angreifer.clone();
		if (!isKoenigGefunden)
			while (getFeldSuedOst(tempFeld, felder) != null) {
				tempFeld = (Feld) getFeldSuedOst(tempFeld, felder).clone();
				if (tempFeld.getFigur() instanceof Koenig
						&& !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
					isKoenigGefunden = true;
					resultPfadZumKoenig.addAll(resultPfadSO);
					break;
				}
				resultPfadSO.add(tempFeld);
			}

		tempFeld = (Feld) angreifer.clone();
		while (getFeldSuedWest(tempFeld, felder) != null) {
			tempFeld = (Feld) getFeldSuedWest(tempFeld, felder).clone();
			if (tempFeld.getFigur() instanceof Koenig
					&& !angreifer.getFigur().getFarbe().equals(feld.getFigur().getFarbe())) {
				isKoenigGefunden = true;
				resultPfadZumKoenig.addAll(resultPfadSW);
				break;
			}
			resultPfadSW.add(tempFeld);
		}
		return resultPfadZumKoenig;
	}
	/**
	 * Diese Methode validiert das Zugmusterintervall des Läufers
	 * @param vonX Quell-Feld X-Koordinate
	 * @param bisX Ziel-Feld X-Koordinate
	 * @param vonY Quell-Feld Y-Koordinate
	 * @param bisY Ziel-Feld Y-Koordinate
	 * @param felder das Schachbrett
	 * @return booelan-Wert: Ja/Nein
	 */
	public boolean getZugMusterIntervallLaeufer(int vonX, int bisX,
		int vonY, int bisY, Feld[][] felder) {
		boolean result = false;
		boolean isHindernisGefunden = false;

		ArrayList<Feld> moeglicheZuege = new ArrayList<>();
		ArrayList<Feld> moeglicheHindernisse = new ArrayList<>();
		int testX = vonX;
		testX += 1;
		int testY = vonY;
		testY -= 1;

		// laufrichtung nach links oben
		while (testX <= OBERE_GRENZE_VERTIKAL
				&& testY >= UNTERE_GRENZE_HORIZONTAL) {

			if (isFeldBesetzt(felder[testX][testY])) {
				// besetztes Feld In Diagonale?
				if (testX > vonX && testY < vonY && testX < bisX
						&& testY > bisY) {
					moeglicheHindernisse.add(felder[testX][testY]);
					isHindernisGefunden = true;
				}
			}
			moeglicheZuege.add(new Feld(testX, testY));
			testX += 1;
			testY -= 1;
		}

		testX = vonX;
		testX -= 1;
		testY = vonY;
		testY -= 1;
		// laufrichtung nach links unten
		while (testX >= UNTERE_GRENZE_VERTIKAL
				&& testY >= UNTERE_GRENZE_HORIZONTAL) {
			if (isFeldBesetzt(felder[testX][testY])) {
				// besetztes Feld In Diagonale?
				if (testX < vonX && testY < vonY && testX > bisX
						&& testY > bisY) {
					moeglicheHindernisse.add(felder[testX][testY]);
					isHindernisGefunden = true;
				}

			}
			moeglicheZuege.add(new Feld(testX, testY));
			testX -= 1;
			testY -= 1;
		}

		testX = vonX;
		testX += 1;
		testY = vonY;
		testY += 1;
		// laufrichtung nach rechts oben
		while (testX <= OBERE_GRENZE_VERTIKAL
				&& testY <= OBERE_GRENZE_HORIZONTAL) {
			if (isFeldBesetzt(felder[testX][testY])) {
				// besetztes Feld In Diagonale?
				if (testX > vonX && testY > vonY && testX < bisX
						&& testY < bisY) {
					moeglicheHindernisse.add(felder[testX][testY]);
					isHindernisGefunden = true;
				}
			}
			moeglicheZuege.add(new Feld(testX, testY));
			testX += 1;
			testY += 1;
		}

		testX = vonX;
		testX -= 1;
		testY = vonY;
		testY += 1;
		// laufrichtung nach rechts unten
		while (testX >= UNTERE_GRENZE_VERTIKAL
				&& testY <= OBERE_GRENZE_HORIZONTAL) {
			if (isFeldBesetzt(felder[testX][testY])) {
				// besetztes Feld In Diagonale?
				if (testX < vonX && testY > vonY && testX > bisX
						&& testY < bisY) {
					moeglicheHindernisse.add(felder[testX][testY]);
					isHindernisGefunden = true;
				}
			}
			moeglicheZuege.add(new Feld(testX, testY));
			testX -= 1;
			testY += 1;
		}
		// moegliche zuege werden gegen zugabsicht verglichen
		for (int i = 0; i < moeglicheZuege.size(); i++) {
			// beabsichtiger Zug stimmt mit einer Auswahl überein...
			if ((moeglicheZuege.get(i).getX() == bisX)
					&& (moeglicheZuege.get(i).getY() == bisY)) {
				result = true;
			}
		}
		return result && !isHindernisGefunden;
	}
	@Override
	public boolean istZugMusterGueltig(Feld from, Feld to, Feld[][] felder) {
		boolean result = false;
		if (this.getZugMusterIntervallLaeufer(from.getX(), to.getX(), from.getY(), to.getY(),
				felder)) {
			result = true;
		}
		return result;
	}
}
