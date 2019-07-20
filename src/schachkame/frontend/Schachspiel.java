package schachkame.frontend;
/** 
 * Diese Klasse beherbergt den Einstiegspunkt zum Starten des Schachspiels
 * Das Spiel hat folgende Funktionen:
 * + Begr��ungsmeldung
 * + MODUS
 *   CONSOLE: Player-Player, Computer-Computer
 *   GUI: Player-Player
 * + Zug-Interaktion per Konsole
 * + Anzeige der aktuellen Spielsituation auf der Konsole
 * + Anzeige Zugliste nach jedem Zug
 * + Speichern der Partie in eine Datei im Pfad des Workspace
 * + Laden einer Partie aus einer Datei im Pfad des Workspace
 * + Endemeldung mit Bekanntgabe des Siegers und lobenden Worten
 * 
 * + Validierung des Zuges auf G�ltigkeit
 * + Beachtung der Spieler-Reihenfolge ("Richtiger" Spieler am Zug?!)
 * + KI, die ...
 * ...Schach erkennt
 * ...kontrollierte Felder von WEISS und SCHWARZ f�hrt und
 * ...damit einher ung�ltige Z�ge des K�nigs erkennt und
 * ...damit einher m�gliche Verteidiger finden kann
 * ...schlie�lich ein Schach-Matt (=Partie-Ende) feststellen kann
 * 
 * KNOWN BUGS / CHALLENGES - Feel free to develop/implement:
 * ...isStalemate/isPatt Method
 * ...wenn Rochade: fehlender Test: fremder K�nig auf den Rochadefeldern? K�nig wird sonst geschlagen! 
 * ...GUI: Partie vor / zur�ck navigieren
 * ...Spracheingabe
 * ...Sprachausgabe
 * ...Zugvorschlag zum Unterbinden eines Schachs
 * ...Stellungsbewertung per Punktesystem - Summe der Figurenpunkte im Vergleich
 * ... and more...
 * 
 * RESSOURCEN: Free PNG-Bilder von: https://icons8.com/icons/set/chess
 * 
 *  @author Karsten Menzel
 *
 */
public class Schachspiel {
	/**
	 * Diese Methode startet das Spiel, indem ein anonymes Objekt 
	 * von 'Schachbrett' erstellt wird
	 */
	public void starteSpiel() {
		new Schachbrett();
	}
	/**
	 * 	 * Die Main-Methode ruft eine Start-Methode zum Starten des Spiels auf.
	 * @param args optionale Argumente hier NICHT vorgesehen
	 */
	public static void main(String[] args) {
		new Schachspiel().starteSpiel();
	}
}
