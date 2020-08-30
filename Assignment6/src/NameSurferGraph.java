/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	private static final int INTERVAL_YEAR = 10;

	private ArrayList<NameSurferEntry> name_surfer_entry_list = new ArrayList<>();

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}

	/**
	 *  draw the background
	 */
	public void drawBackground() {
		double interval_len = getWidth()/NDECADES;
		double current_x_pos;
		int current_year;

		// draw the horizontal line
		// on the top
		add(new GLine(0, GRAPH_MARGIN_SIZE,
				getWidth(), GRAPH_MARGIN_SIZE));
		// on the bottom
		add(new GLine(0, getHeight()-GRAPH_MARGIN_SIZE,
				getWidth(), getHeight()-GRAPH_MARGIN_SIZE));

		for (int i = 0; i<NDECADES; i++) {
			current_year = START_DECADE + i*INTERVAL_YEAR;
			current_x_pos = i*interval_len;

			//draw year label
			add(new GLabel(Integer.toString(current_year),
					current_x_pos,
					getHeight()));

			// draw the vertical line
			add(new GLine(current_x_pos, 0,
					current_x_pos, getHeight()));
		}
	}

	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		name_surfer_entry_list.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		name_surfer_entry_list.add(entry);
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		// deleting all the graphical objects from the canvas
		removeAll();

		drawBackground();

		Color current_color;
		Iterator<NameSurferEntry> iter = name_surfer_entry_list.iterator();
		while (iter.hasNext()) {
			current_color = rgen.nextColor();
			drawOneEntry(iter.next(), current_color);
		}
	}

	public void drawOneEntry(NameSurferEntry entry, Color current_color) {
		String name = entry.getName();
		int current_rank, next_rank;
		double current_x_pos, next_x_pos, current_y_pos, next_y_pos;
		GLine current_line;
		GLabel current_label;

		double factor = Double.valueOf(getHeight()-2*GRAPH_MARGIN_SIZE)/Double.valueOf(MAX_RANK);
		double interval_len = getWidth()/NDECADES;

		// draw all lines and (entry.getRankLength()-1) labels
		for (int i = 0; i < entry.getRankLength()-1; i++) {
			current_rank = entry.getRank(i);
			next_rank = entry.getRank(i+1);

			current_x_pos = i*interval_len;
			next_x_pos = (i+1)*interval_len;

			if (current_rank == 0) {
				current_y_pos = MAX_RANK * factor + GRAPH_MARGIN_SIZE;
			}
			else {
				current_y_pos = current_rank * factor + GRAPH_MARGIN_SIZE;
			}

			if (next_rank == 0) {
				next_y_pos = MAX_RANK * factor + GRAPH_MARGIN_SIZE;
			}
			else {
				next_y_pos = next_rank * factor + GRAPH_MARGIN_SIZE;
			}

			// draw line
			current_line = new GLine(current_x_pos, current_y_pos,
					next_x_pos, next_y_pos);
			current_line.setColor(current_color);
			add(current_line);

			// draw label
			if (current_rank == 0) {
				current_label = new GLabel(name+" *");
			}
			else {
				current_label = new GLabel(name+" "+Integer.toString(current_rank));
			}
			current_label.setLocation(current_x_pos, current_y_pos);
			current_label.setColor(current_color);
			add(current_label);
		}

		// draw the last label
		current_rank = entry.getRank(entry.getRankLength()-1);
		current_x_pos = (entry.getRankLength()-1)*interval_len;
		if (current_rank == 0) {
			current_y_pos = MAX_RANK * factor + GRAPH_MARGIN_SIZE;
		}
		else {
			current_y_pos = current_rank * factor + GRAPH_MARGIN_SIZE;
		}
		if (current_rank == 0) {
			current_label = new GLabel(name+" *");
		}
		else {
			current_label = new GLabel(name+" "+Integer.toString(current_rank));
		}
		current_label.setLocation(current_x_pos, current_y_pos);
		current_label.setColor(current_color);
		add(current_label);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
