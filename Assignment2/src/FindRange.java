/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		println("This program finds the max and min num.");
		int num = readInt("? ");
		int max;
		max = num;
		int min;
		min = num;

		while (num!=0) {
			num = readInt("? ");

			if (num==0) {
				break;
			}
			if (max<=num) {
				max = num;
			}
			if (min>=num) {
				min = num;
			}
		}

		println("max: "+max);
		println("min: "+min);
	}
}

