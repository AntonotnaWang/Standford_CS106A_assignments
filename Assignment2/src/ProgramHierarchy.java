/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	private static final int BOX_WIDTH = 100;
	private static final int BOX_HEIGHT = 30;
	private static final int Y_DISTANCE_BETWEEN_BOXES = 100;
	public void run() {
		/* You fill this in. */
		double mid_height=getHeight()/2;
		double mid_width=getWidth()/2;

		//GLine line1 = new GLine(0, mid_height, getWidth(), mid_height);
		//add(line1);
		//GLine line2 = new GLine(mid_width, 0, mid_width, getHeight());
		//add(line2);

		GRect rect_program = new GRect(mid_width-BOX_WIDTH/2,
				mid_height-Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2,
				BOX_WIDTH, BOX_HEIGHT);
		add(rect_program);
		GLabel label_program = new GLabel("Program");
		label_program.setLocation(mid_width-label_program.getWidth()/2,
				mid_height-Y_DISTANCE_BETWEEN_BOXES/2+label_program.getHeight()/2);
		add(label_program);

		GRect rect_consoleprogram = new GRect(mid_width-BOX_WIDTH/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2,
				BOX_WIDTH, BOX_HEIGHT);
		add(rect_consoleprogram);
		GLabel label_consoleprogram = new GLabel("ConsoleProgram");
		label_consoleprogram.setLocation(mid_width-label_consoleprogram.getWidth()/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2+label_consoleprogram.getHeight()/2);
		add(label_consoleprogram);

		GRect rect_graphicsprogram = new GRect(mid_width-BOX_WIDTH/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2,
				BOX_WIDTH, BOX_HEIGHT);
		rect_graphicsprogram.move(-BOX_WIDTH*2,0);
		add(rect_graphicsprogram);
		GLabel label_graphicsprogram = new GLabel("GraphicsProgram");
		label_graphicsprogram.setLocation(mid_width-label_graphicsprogram.getWidth()/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2+label_graphicsprogram.getHeight()/2);
		label_graphicsprogram.move(-BOX_WIDTH*2,0);
		add(label_graphicsprogram);

		GRect rect_dialogprogram = new GRect(mid_width-BOX_WIDTH/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2,
				BOX_WIDTH, BOX_HEIGHT);
		rect_dialogprogram.move(BOX_WIDTH*2,0);
		add(rect_dialogprogram);
		GLabel label_dialogprogram = new GLabel("DialogProgram");
		label_dialogprogram.setLocation(mid_width-label_dialogprogram.getWidth()/2,
				mid_height+Y_DISTANCE_BETWEEN_BOXES/2+label_dialogprogram.getHeight()/2);
		label_dialogprogram.move(BOX_WIDTH*2,0);
		add(label_dialogprogram);

		GLine line3 = new GLine(mid_width, mid_height-Y_DISTANCE_BETWEEN_BOXES/2+BOX_HEIGHT/2, mid_width, mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2);
		add(line3);
		GLine line4 = new GLine(mid_width, mid_height-Y_DISTANCE_BETWEEN_BOXES/2+BOX_HEIGHT/2, mid_width-2*BOX_WIDTH, mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2);
		add(line4);
		GLine line5 = new GLine(mid_width, mid_height-Y_DISTANCE_BETWEEN_BOXES/2+BOX_HEIGHT/2, mid_width+2*BOX_WIDTH, mid_height+Y_DISTANCE_BETWEEN_BOXES/2-BOX_HEIGHT/2);
		add(line5);
	}
}

