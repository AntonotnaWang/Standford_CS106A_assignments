/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {

	private String profile_name = "No current name";
	private String profile_status = "No current status";
	private GImage profile_img = null;
	private ArrayList<String> profile_friend_list = new ArrayList<>();

	// this is used for testing this class
	/*
	public static void main(String[] args) {
		FacePamphletProfile profile = new FacePamphletProfile("Anton");
		profile.setStatus("Busy");
		profile.addFriend("a");
		profile.addFriend("b");
		profile.addFriend("c");
		profile.removeFriend("hahaha");
		System.out.println(profile.getName());
		System.out.println(profile.toString());
	}
	 */

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public FacePamphletProfile(String name) {
		// You fill this in
		profile_name = name;
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		// You fill this in.  Currently always returns the empty string.
		return profile_name;
	}

	/** 
	 * This method returns the image associated with the profile.  
	 * If there is no image associated with the profile, the method
	 * returns null. */ 
	public GImage getImage() {
		// You fill this in.  Currently always returns null.
		return profile_img;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		// You fill this in
		profile_img = image;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		// You fill this in.  Currently always returns the empty string.
		return profile_status;
	}
	
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		// You fill this in
		profile_status = status;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		// You fill this in.  Currently always returns true.
		if (!profile_friend_list.contains(friend)) {
			profile_friend_list.add(friend);
			return true;
		}
		else {
			return false;
		}
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		// You fill this in.  Currently always returns false.
		if (profile_friend_list.contains(friend)) {
			profile_friend_list.remove(friend);
			return true;
		}
		else {
			return false;
		}
	}

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	public Iterator<String> getFriends() {
		// You fill this in.  Currently always returns null.
		return profile_friend_list.iterator();
	}
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	public String toString() {
		// You fill this in.  Currently always returns the empty string.
		String output_str = profile_name+" ("+profile_status+"): ";
		Iterator<String> friendList = getFriends();
		if (friendList.hasNext()) {
			output_str += friendList.next();
		}
		while (friendList.hasNext()) {
			output_str += ", "+friendList.next();
		}
		return output_str;
	}

	public boolean containsFriend(String name) {
		return profile_friend_list.contains(name);
	}
}
