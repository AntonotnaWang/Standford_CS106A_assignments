/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

	private static final int NUMBER_OF_WORDS = 10000;

/** Returns the number of words in the lexicon. */
	public int getWordCount(String word) {
		return word.length();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	}

	public String getWordFromFile(String file_name) {
		try {
			File lexicon_file = new File(file_name);
			Scanner input = new Scanner(lexicon_file);
			int number_of_words = 0;
			String[] lexicon = new String[NUMBER_OF_WORDS];
			while (input.hasNextLine()) {
				lexicon[number_of_words]=input.nextLine();
				number_of_words += 1;
			}
			RandomGenerator rgen = new RandomGenerator();

			System.out.println("Got the word from file.");

			return lexicon[rgen.nextInt(0, number_of_words-1)];
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return "FileNotFoundException";
		}
	}

	public String[] getLexiconFromFile(String file_name) {
		try {
			File lexicon_file = new File(file_name);
			Scanner input = new Scanner(lexicon_file);
			int number_of_words = 0;
			String[] lexicon = new String[NUMBER_OF_WORDS];
			while (input.hasNextLine()) {
				lexicon[number_of_words]=input.nextLine();
				number_of_words += 1;
			}
			return lexicon;
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			String[] FileNotFoundException = new String[1];
			FileNotFoundException[0]="FileNotFoundException";
			return FileNotFoundException;
		}
	}
}
