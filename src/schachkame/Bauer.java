package schachkame;
import java.util.ArrayList;

import schachkame.frontend.Schachbrett;
/**
 * Diese Klasse ist eine Unterklasse von Figur
 * nutzt Grundfunktionen: Feststellen der Nachbarfelder, 
 * implementiert: Validierung der eigenen Zugmöglichkeit und mehr
 * 
 * 
 * @author Karsten Menzel
 *
 */
public class Bauer extends Figur {
	private boolean  firstMove = true;
	/**
	 * Konstruktor, der einen Bauer zusammenstellt
	 * @param farbe Figurenfarbe
	 * @param name Figurname
	 * @param initX X-Koordinate (VERTIKAL)
	 * @param initY Y-Koordinate (HORIZONTAL)
	 */
	public Bauer(String farbe, String name, int initX, int initY) {
		super(farbe, name, initX, initY);		
	}	
	/**
	 * Diese Methode ermittelt und bestimmt Zuglänge des Bauern (1 oder 2 Felder)
	 * @return ermitteltes Feld zum Auslesen von x/y
	 */
	public Feld getZuglaenge() {
		// dummy init
		Feld feld = new Feld(88, 88);
		if (isFirstMove()) {
			feld.setY(0);
			feld.setX(2);
			setFirstMove(false);
		} else {
			feld.setY(0);
			feld.setX(1);
		}
		return feld;
	}
	/**
	 * Diese Methode ermittelt, ob Bauer zum ersten Mal gezogen wurde
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isFirstMove() {
		return firstMove;
	}
	/**
	 * Diese Methode weist firstMove den boolean-Wert des Parameters zu
	 * @param firstMove ob erstmalig gezogen
	 */
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	/**
	 * Diese Methode ermittelt, ob das übergebene Feld besetzt ist
	 * @param feldTo Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean istVertikalesZielFeldBesetzt(Feld feldTo) {
		return feldTo.istBesetzt;
	}
	@Override
	public ArrayList<Feld> getKontrollierteFelder(Feld feld, Feld[][] felder, boolean mitAdd, boolean mitReturn ) { 
		ArrayList<Feld> result = new ArrayList<Feld>();

		// *************** weiss
		// **********************************************************
		if (feld.getFigur().getFarbe() == Schachbrett.WEISS) {
			// test getFeldNordWest
			if (getFeldNordWest(feld, felder) != null) {
				Schachbrett.addKontrolliertesFeld(getFeldNordWest(feld, felder), true);
				result.add(getFeldNordWest(feld, felder));
//				System.out.println(" ************** 103" + result.size() + "****************");
//				getFeldNordWest(feld, felder).zeigeFeldInfo();
			}

			// test getFeldNordOst
			if (getFeldNordOst(feld, felder) != null) {
				Schachbrett.addKontrolliertesFeld(getFeldNordOst(feld, felder), true);
				result.add(getFeldNordOst(feld, felder));
			}
		}
		// *************** schwarz
		// **********************************************************
		if (feld.getFigur().getFarbe() == Schachbrett.SCHWARZ) {
			// test getFeldSuedOst

			if (getFeldSuedOst(feld, felder) != null) {
				Schachbrett.addKontrolliertesFeld(getFeldSuedOst(feld, felder), false);
				result.add(getFeldSuedOst(feld, felder));
//				System.out.println(" ************** 111" + result.size() + "****************");
//				feld.zeigeFeldInfo();
			}

			// test getFeldSuedWest
			if (getFeldSuedWest(feld, felder) != null) {
				Schachbrett.addKontrolliertesFeld(getFeldSuedWest(feld, felder), false);
				result.add(getFeldSuedWest(feld, felder));
			}
		}
		return result;
	}	
	/**
	 * Diese Methode validiert, ob eine Bauernumwandlung möglich ist
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @param felder das Schachbrett
	 * @return Ja/Nein
	 */
	public boolean isPromotionMoeglich(Feld feldFrom, Feld feldTo,  Feld[][] felder) {
		boolean result = false;
		final int SECHSTE_REIHE = 6;
		final int ZWEITE_REIHE = 1;	
		final int PROMOTION_REIHE_SCHWARZ = 0;
		final int PROMOTION_REIHE_WEISS = 7;
		try {
			if (feldFrom.figur.getFarbe() == Schachbrett.WEISS &&
					feldFrom.getX() == SECHSTE_REIHE && feldTo.getX() == PROMOTION_REIHE_WEISS  )	{
				result = true;
			}
		} catch (Exception e) {
		}		
		try {
			if (feldFrom.figur.getFarbe() == Schachbrett.SCHWARZ &&
					feldFrom.getX() == ZWEITE_REIHE && feldTo.getX() == PROMOTION_REIHE_SCHWARZ  )	{	
				result = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return result;
	}
	
	/**
	 * Diese Methode validiert, ob enpassant (= Schlagen im Vorbeigehen) möglich ist
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @param felder das Schachbrett
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isEnPassantMoeglich(Feld feldFrom, Feld feldTo,  Feld[][] felder) {
		boolean result = false;
		final int VIERTE_REIHE_ENPASSANT = 4;
		final int DRITTE_REIHE_ENPASSANT = 3;	
		try {
			Feld enPassantWeiss = felder[feldTo.getX()-1][feldTo.getY()];
			if (feldFrom.figur.getFarbe() == Schachbrett.WEISS && !feldTo.istBesetzt() &&
					  feldTo.getX()-1 == VIERTE_REIHE_ENPASSANT && Schachbrett.getLetztenTeilzug().getFeldTo().getFigur() == enPassantWeiss.getFigur() )	{	
				felder[enPassantWeiss.getX()][enPassantWeiss.getY()].resetFeld();
				result = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		try {
			Feld enPassantSchwarz = felder[feldTo.getX() + 1][feldTo.getY()];
			if (feldFrom.figur.getFarbe() == Schachbrett.SCHWARZ && !feldTo.istBesetzt()
					&& feldTo.getX() + 1 == DRITTE_REIHE_ENPASSANT
					&& Schachbrett.getLetztenTeilzug().getFeldTo().getFigur() == enPassantSchwarz.getFigur()) {
				felder[enPassantSchwarz.getX()][enPassantSchwarz.getY()].resetFeld();
				result = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return result;
	}
	/**
	 * Diese Methode validiert die Schlagmöglichkeit von WEISS
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isSchlagmusterVonWeiss(Feld feldFrom, Feld feldTo) {
		boolean result = false;			
		if(feldTo.getX() - feldFrom.getX() ==  1 && Math.abs(feldTo.getY() - feldFrom.getY()) == 1 ) {
			result = true;
		}	
		return result;		
	}
	/**
	 * Diese Methode validiert die Schlagmöglichkeit von SCHWARZ
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isSchlagmusterVonSchwarz(Feld feldFrom, Feld feldTo) {
		boolean result = false;		
		if(feldTo.getX() - feldFrom.getX() ==  -1 && Math.abs(feldTo.getY() - feldFrom.getY()) == 1 ) {
			result = true;
		}			
		return result;		
	}
	/**
	 * Diese Methode validiert, ob Figur schlagbar ist	
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @param felder das Schachbrett
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean isFigurSchlagbar(Feld feldFrom, Feld feldTo,  Feld[][] felder) {
		boolean result = false;
		if (feldFrom.figur.getFarbe() == Schachbrett.WEISS && feldTo.figur.getFarbe() == Schachbrett.SCHWARZ && feldTo.istBesetzt()) {
			if (isSchlagmusterVonWeiss(feldFrom, feldTo)) result = true;
		}	
		  else if (feldFrom.figur.getFarbe() == Schachbrett.SCHWARZ && feldTo.figur.getFarbe() == Schachbrett.WEISS && feldTo.istBesetzt()) {
			  if (isSchlagmusterVonSchwarz(feldFrom, feldTo)) result = true;	
			} 		
		return result;
	}
	/**
	 * Diese Methode validiert ob Vorwärtszug
	 * @param feldFrom Quell-Feld
	 * @param feldTo Ziel-Feld
	 * @return boolean-Wert: Ja/Nein
	 */
	public boolean istVorwaertsZug(Feld feldFrom, Feld feldTo) {
		boolean result = false;
		if (feldFrom.figur.getFarbe() == Schachbrett.SCHWARZ) {
			if (feldTo.getX() < feldFrom.getX()) {
				result = true;
			} 			
		} else if (feldFrom.figur.getFarbe() == Schachbrett.WEISS) {
			if (feldTo.getX() > feldFrom.getX()) {
				result = true;
			} 		
		} 
		return result;
	}
	/**
	 * 	Diese Methode überschreibt die Methode der Mutterklasse
	 */
	@Override
	public boolean istZugMusterGueltig(Feld feldFrom, Feld feldTo,  Feld[][] felder) {
		boolean result = false;		
		if (!istVertikalesZielFeldBesetzt(feldTo) && istVorwaertsZug(feldFrom, feldTo))
			if (Math.abs(feldTo.getX() - feldFrom.getX()) <= this.getZuglaenge().getX() && Math.abs(feldTo.getX() - feldFrom.getX()) >= 0 &&
				Math.abs(feldTo.getY() - feldFrom.getY()) == 0  				
					) {
					result = true;
			} else setFirstMove(true);		
		if (isPromotionMoeglich(feldFrom, feldTo, felder)) {
			Schachbrett.setPromotionMoeglich();
			result = true;
		}
		if (isFigurSchlagbar(feldFrom,feldTo, felder)) {			
			result = true;
		}
		if (isEnPassantMoeglich(feldFrom, feldTo, felder)) {
			result = true;
		}
	
		return result;
	}
}
