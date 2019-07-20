package schachkame.io;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import schachkame.Zug;
import schachkame.frontend.Schachbrett;

/**
 * Diese Klasse regelt den Schreib- und Lesezugriff
 * der Zugliste in/aus einer Datei
 * 
 * @author Karsten Menzel *
 */
public class DateiZugriff {
	/**
	 * Diese Methode schreibt die Zugliste in eine Datei
	 */
	public static void schreibeListeInDatei() {
		File zugliste = new File("Zugliste");
		zugliste.mkdir();
		File zuglisteSave = new File(zugliste, "Zugliste.ser");
		if(!zuglisteSave.exists()) {
			try {
				zuglisteSave.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Schreiber schreiber = new Schreiber();
		Leser leser = new Leser();
		ArrayList<Zug> alleZuege = new ArrayList<>();
		int i = 1;
		for(Zug zug : Schachbrett.getZugliste()) {
			alleZuege.add(new Zug(i+".",zug.getZug(false)));
			i++;
		}
		schreiber.kundenSchreiben(zuglisteSave, alleZuege); 
		ArrayList<Zug> zugGelesen = leser.zugLesen(zuglisteSave);
		for(Zug zug : zugGelesen) {
			System.out.println(zug.getZug(true));
		}
	}
	/**
	 * Diese Methode liest die Zugliste aus einer Datei
	 * @return gibt die Zugliste als ArrayList zurueck
	 */
	public static ArrayList<Zug> leseListeAusDatei() {
		File zugliste = new File("Zugliste");
		ArrayList<Zug> zugGelesen = null;
		File zuglisteSave = new File(zugliste, "Zugliste.ser");
		if(zuglisteSave.exists()) {
			try {
				Leser leser = new Leser();
				zugGelesen = leser.zugLesen(zuglisteSave);
				for(Zug zug : zugGelesen) {
					System.out.println(zug.getZug(true));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return zugGelesen;	
	}
}