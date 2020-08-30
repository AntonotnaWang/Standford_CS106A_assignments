/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
					implements FacePamphletConstants {

	/**
	 * java field
	 */
	// widgets
	private JButton b_change_stat, b_change_pic, b_add_friend,
			b_add_profile, b_delete_profile, b_lookup_profile;
	private JTextField tf_change_stat, tf_change_pic, tf_add_friend, tf_name;

	// database of the profiles, each profile is a FacePamphletProfile
	private FacePamphletDatabase database = new FacePamphletDatabase();

	// canvas
	private FacePamphletCanvas canvas = new FacePamphletCanvas();

	// current profile
	private FacePamphletProfile current_profile = null;

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in

		// set the size of the window
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

		// init all widgets
		b_change_stat = new JButton("Change Status");
		b_change_stat.setActionCommand("change_status");
		b_change_pic = new JButton("Change Picture");
		b_add_friend = new JButton("Add Friend");
		b_add_profile = new JButton("Add");
		b_delete_profile = new JButton("Delete");
		b_lookup_profile = new JButton("Lookup");
		tf_change_stat = new JTextField(TEXT_FIELD_SIZE);
		tf_change_pic = new JTextField(TEXT_FIELD_SIZE);
		tf_add_friend = new JTextField(TEXT_FIELD_SIZE);
		tf_name = new JTextField(TEXT_FIELD_SIZE);

		// add widgets to the WEST
		add(tf_change_stat, WEST);
		add(b_change_stat, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(tf_change_pic, WEST);
		add(b_change_pic, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(tf_add_friend, WEST);
		add(b_add_friend, WEST);

		// add widgets to the NORTH
		add(new JLabel("Name"), NORTH);
		add(tf_name, NORTH);
		add(b_add_profile, NORTH);
		add(b_delete_profile, NORTH);
		add(b_lookup_profile, NORTH);

		// add canvas
		add(canvas);

		/*
		add action listeners
		attention "Adds the program as an ActionListener to every button in the structure that does not have a listener already"
		addActionListeners() only works for the buttons
		 */
		addActionListeners();
		tf_change_stat.addActionListener(this);
		tf_change_pic.addActionListener(this);
		tf_add_friend.addActionListener(this);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */

    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
		/**
		 * update the info of a profile
		 */
		if (current_profile!=null) {
			String current_stat = tf_change_stat.getText();
			if (e.getSource() == tf_change_stat || e.getActionCommand().equals("change_status")) {
				if (!current_stat.equals("")) {
					current_profile.setStatus(current_stat);
					canvas.displayProfile(current_profile);
					canvas.showMessage("Status updated to "+current_stat);
					//println("Update status: " + current_profile.toString());
				}
				else {
					canvas.showMessage("Please input the status of "+current_profile.getName());
				}
			}

			String pic_name = tf_change_pic.getText();
			if (e.getSource() == tf_change_pic || e.getActionCommand().equals("Change Picture")) {
				if (!pic_name.equals("")) {
					GImage image = null;
					try {
						image = new GImage(pic_name);
						current_profile.setImage(image);
						canvas.displayProfile(current_profile);
						canvas.showMessage("Picture updated");
						//println("Update picture: " + current_profile.toString());
					} catch (ErrorException ex) {
						canvas.showMessage("No such image");
					}
				}
				else {
					canvas.showMessage("Please input an image name");
				}
			}

			String friend = tf_add_friend.getText();
			if (e.getSource() == tf_add_friend || e.getActionCommand().equals("Add Friend")) {
				if (friend.equals("")) {
					canvas.showMessage("Please input the name of the friend");
				}
				else if (current_profile.getName().equals(friend)) {
					canvas.showMessage("Cannot add friend to yourself");
				}
				else if (!database.containsProfile(friend)) {
					canvas.showMessage("Such person does not exist");
				}
				else {
					if (current_profile.addFriend(friend)) {
						canvas.displayProfile(current_profile);
						canvas.showMessage(friend+" added a a friend");
						//println("Update friend: " + current_profile.toString());
					}
					else {
						canvas.showMessage("Such a friend already exists");
					}
				}
			}
		}

		/**
		 * add, delete, or lookup profile
		 */
		String name = tf_name.getText();
		if (e.getActionCommand().equals("Add")) {
			current_profile = new FacePamphletProfile(name);
			database.addProfile(current_profile);
			canvas.displayProfile(current_profile);
			canvas.showMessage("New profile created");
			//println("Add new profile: "+current_profile.toString());
		}

		if (e.getActionCommand().equals("Delete")) {
			if (database.containsProfile(name)) {
				println("Delete profile: " + database.getProfile(name).toString());
				database.deleteProfile(name);
				if (current_profile.getName().equals(name)) {
					current_profile = null;
				}
				canvas.displayProfile(current_profile);
				canvas.showMessage("Profile of "+name+" deleted");
			}
			else {
				canvas.showMessage("Profile "+name+" does not exist");
			}
		}

		if (e.getActionCommand().equals("Lookup")) {
			if (database.containsProfile(name)) {
				current_profile = database.getProfile(name);
				canvas.displayProfile(current_profile);
				canvas.showMessage("Displaying "+current_profile.getName());
				//println("Get profile: " + current_profile.toString());
			}
			else {
				canvas.showMessage("Profile "+name+" does not exist");
				//println("Profile "+name+" does not exist");
			}
		}
	}

}
