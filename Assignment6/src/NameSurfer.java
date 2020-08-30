/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/**
	 * some java fields
	 */
	// widgets
	private JLabel label;
	private JTextField textField;
	private JButton button_draw_graph;
	private JButton button_clear;

	// the line graph
	private NameSurferGraph graph;

	// the database
	private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

	// a constant
	private static final int LEN_OF_JTEXTFIELD = 10;

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //

		// set size
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

		// init widgets and add them
		label = new JLabel("Name");
		textField = new JTextField(LEN_OF_JTEXTFIELD);
		button_draw_graph = new JButton("Graph");
		button_clear = new JButton("Clear");
		add(label, SOUTH);
		add(textField, SOUTH);
		add(button_draw_graph, SOUTH);
		add(button_clear, SOUTH);

		// init NameSurferGraph instance, graph, and add it
		graph = new NameSurferGraph();
		add(graph);

		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getActionCommand().equals("Graph")) {
			String name = textField.getText();
			//println("Graph: \""+name+"\"");
			NameSurferEntry current_entry = dataBase.findEntry(name);
			if (current_entry!=null) {
				graph.addEntry(current_entry);
			}
			else {
				println("\""+name+"\" does not exist in the name list");
			}
			graph.update();
		}
		else if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
			//println("Clear");
		}
	}
}
