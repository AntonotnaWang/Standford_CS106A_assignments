/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {

	private GLabel l_message, l_status, l_name, l_friendlist, l_friendlabel;
	private GObject img_obj;
	private GCompound no_img_sign;

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in

		l_message = new GLabel("");
		l_message.setFont(MESSAGE_FONT);

		l_status = new GLabel("");
		l_status.setFont(PROFILE_STATUS_FONT);

		l_name = new GLabel("");
		l_name.setFont(PROFILE_NAME_FONT);
		l_name.setColor(Color.BLUE);

		l_friendlist = new GLabel("");
		l_friendlist.setFont(PROFILE_FRIEND_FONT);

		l_friendlabel = new GLabel("Friends:");
		l_friendlabel.setFont(PROFILE_FRIEND_LABEL_FONT);

		no_img_sign = new GCompound();
		no_img_sign.add(new GRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT));
		GLabel no_img_sign_label = new GLabel("No Image");
		no_img_sign_label.setFont(PROFILE_IMAGE_FONT);
		no_img_sign_label.setLocation((IMAGE_WIDTH-no_img_sign_label.getWidth())/2,IMAGE_HEIGHT/2);
		no_img_sign.add(no_img_sign_label);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		this.remove(l_message);
		l_message.setLabel(msg);
		l_message.setLocation((getWidth()-l_message.getWidth())/2, getHeight()-BOTTOM_MESSAGE_MARGIN);
		this.add(l_message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();

		if (profile!=null) {
			String name = profile.getName();
			String status = profile.getStatus();
			Iterator<String> friendlist = profile.getFriends();
			GImage profile_img = profile.getImage();

			String friendlist_str = "";
			while (friendlist.hasNext()) {
				friendlist_str = friendlist_str + friendlist.next() + "\n";
			}

			l_name.setLabel(name);
			l_name.setLocation(LEFT_MARGIN, TOP_MARGIN + l_name.getHeight());
			this.add(l_name);

			if (profile_img != null) {
				profile_img.scale(IMAGE_WIDTH / profile_img.getWidth(),
						IMAGE_HEIGHT / profile_img.getHeight());
				img_obj = profile_img;
			} else {
				img_obj = no_img_sign;
			}
			img_obj.setLocation(LEFT_MARGIN, l_name.getY() + IMAGE_MARGIN);
			this.add(img_obj);

			if (!status.equals("No current status")) {
				status = name + " is " + status;
			}
			l_status.setLabel(status);
			l_status.setLocation(LEFT_MARGIN, img_obj.getY() + img_obj.getHeight() + STATUS_MARGIN);
			this.add(l_status);

			l_friendlabel.setLocation(getWidth() / 2, img_obj.getY());
			this.add(l_friendlabel);

			l_friendlist.setLabel(friendlist_str);
			l_friendlist.setLocation(getWidth() / 2, l_friendlabel.getY() + l_friendlabel.getHeight());
			this.add(l_friendlist);
		}
	}

	
}
