/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		println("Enter a and b.");
		double a = readInt("a: ");
		double b = readInt("b: ");
		print("c: " + Math.sqrt(Math.pow(a,2)+Math.pow(b,2)));
	}
}
