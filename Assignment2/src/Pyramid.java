/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/* You fill this in. */
		double screen_width=getWidth();
		double screen_height=getHeight();

		for (int i=BRICKS_IN_BASE;i>=1;i--) {
			for (int j=1;j<=i;j++) {
				double x=(screen_width-i*BRICK_WIDTH)/2+(j-1)*BRICK_WIDTH;
				double y=screen_height-BRICK_HEIGHT*(BRICKS_IN_BASE-i+1);
				GRect rect=new GRect(x ,y, BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
			}
		}
	}
}

