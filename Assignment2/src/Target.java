/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	private static final double OVAL_SIZE_1 = 233;

	private static final double OVAL_SIZE_2 = 100;

	private static final double OVAL_SIZE_3 = 33;

	public void run() {
		/* You fill this in. */
		double mid_height=getHeight()/2;
		double mid_width=getWidth()/2;

		GOval oval1=new GOval(mid_width-OVAL_SIZE_1/2, mid_height-OVAL_SIZE_1/2, OVAL_SIZE_1, OVAL_SIZE_1);
		oval1.setFilled(true);
		oval1.setColor(Color.RED);
		add(oval1);

		GOval oval2=new GOval(mid_width-OVAL_SIZE_2/2, mid_height-OVAL_SIZE_2/2, OVAL_SIZE_2, OVAL_SIZE_2);
		oval2.setFilled(true);
		oval2.setColor(Color.WHITE);
		add(oval2);

		GOval oval3=new GOval(mid_width-OVAL_SIZE_3/2, mid_height-OVAL_SIZE_3/2, OVAL_SIZE_3, OVAL_SIZE_3);
		oval3.setFilled(true);
		oval3.setColor(Color.RED);
		add(oval3);
	}
}
