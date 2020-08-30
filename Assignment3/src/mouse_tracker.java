/*
 * File: MouseTracker.java
 * -----------------------
 * This program displays the (x, y) location of the mouse.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

public class mouse_tracker extends GraphicsProgram {

    public void run() {
        label = new GLabel("");
        label.setFont("Times New Roman-36");
        add(label, 50, 50);

        // Must call this method to be able to get mouse events
        addMouseListeners();
    }

    // This method is called everytime user moves mouse
    public void mouseMoved(MouseEvent e) {
        label.setLabel("Mouse: (" + e.getX() + ", " + e.getY() + ")");
    }

    /* Private instance variable */
    private GLabel label;
}