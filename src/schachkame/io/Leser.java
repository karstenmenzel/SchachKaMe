package schachkame.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import schachkame.Zug;
/**
 * Diese Klasse biete eine Methode zum Einlesen einer serialisierten Datei an
 * 
 * @author Karsten Menzel
 */
public class Leser {
	public ArrayList<Zug> zugLesen(File quelle) {
		ArrayList<Zug> zugListe = null;
		try(ObjectInputStream leser = new ObjectInputStream(new FileInputStream(quelle))){
			Zug bestandskunde;
			zugListe = (ArrayList<Zug>) leser.readObject();
			return zugListe;
		}catch(FileNotFoundException fe) {
			System.out.println("Zugliste: Probleme mit Datei");
		}catch(IOException ie) {
			System.out.println("Zugliste: andere Probleme");
		}catch(ClassNotFoundException ce) {
			System.out.println("Klasse und Objekt Zugliste passen nicht zusammen");
		}
		return null;
	}
}
