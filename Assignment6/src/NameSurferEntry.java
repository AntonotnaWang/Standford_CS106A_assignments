/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.lang.reflect.Array;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
	/*
	public static void main(String[] args) {
		String line = "Anton 1 2 3 4 5";
		NameSurferEntry entry = new NameSurferEntry(line);
		System.out.println(entry.getName());
		System.out.println(entry.getRank(3));
		System.out.println(entry.toString());
	}
	 */
	private String name;
	private int[] ranks;
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		String[] splited_line = line.split("\\s+");
		name = splited_line[0];
		ranks = new int[splited_line.length-1];
		for (int i = 1; i < splited_line.length; i++) {
			ranks[i-1] = Integer.parseInt(splited_line[i]);
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		if (decade < 0) {
			decade = 0;
		}
		else if (decade > ranks.length - 1) {
			decade = ranks.length - 1;
		}
		return ranks[decade];
	}

	public int getRankLength() {
		return ranks.length;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String rank_str = " [";
		for (int i = 0; i < ranks.length; i++) {
			if (i < ranks.length - 1) {
				rank_str += ranks[i]+" ";
			}
			else {
				rank_str += ranks[i]+"]";
			}
		}
		return name+" "+rank_str;
	}
}

