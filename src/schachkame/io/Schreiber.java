package schachkame.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import schachkame.Zug;
/**
 * Diese Klasse biete eine Methode zum Schreiben in eine serialisierten Datei an
 * @author Karsten Menzel
 *
 */
public class Schreiber {
	/**
	 * Diese Methode schreibt in eine serialisierte Datei die Zugliste
	 * @param ziel die Zieldatei
	 * @param zugListe die Zugliste
	 */
	public void kundenSchreiben(File ziel, ArrayList<Zug> zugListe) {
		try(ObjectOutputStream schreiber = new ObjectOutputStream(new FileOutputStream(ziel))){
			schreiber.writeObject(zugListe);
			schreiber.flush();
		}catch(FileNotFoundException fe) {
			System.out.println("Probleme mit der Datei oder Pfad");
			System.out.println(fe);
		}catch(IOException ie) {
			System.out.println("Eine andere Ausnahme beim Schreiben der Zugliste");
			System.out.println(ie);
		}
	}
}
