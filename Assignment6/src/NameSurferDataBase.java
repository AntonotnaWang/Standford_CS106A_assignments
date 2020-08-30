/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	/*
	public static void main(String[] args) {
		String filename = "names-data.txt";
		NameSurferDataBase dataBase = new NameSurferDataBase(filename);
		NameSurferEntry entry = dataBase.findEntry("Anton");
		System.out.println(entry.getName());
		System.out.println(entry.getRank(2));
		System.out.println(entry.toString());
	}
	 */

	private HashMap<String, String> data = new HashMap<String, String>();
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String current_line = scanner.nextLine();
				String current_name = current_line.split("\\s+")[0];
				data.put(current_name, current_line);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if (data.containsKey(name)) {
			return new NameSurferEntry(data.get(name));
		}
		else {
			return null;
		}
	}
}

