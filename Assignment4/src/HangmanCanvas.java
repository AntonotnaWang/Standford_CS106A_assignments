/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();

		Scafford.add(line_SCAFFOLD_HEIGHT);
		Scafford.add(line_BEAM_LENGTH);
		Scafford.add(line_ROPE_LENGTH);

		left_arm.add(line_left_UPPER_ARM);
		left_arm.add(line_left_LOWER_ARM);
		right_arm.add(line_right_UPPER_ARM);
		right_arm.add(line_right_LOWER_ARM);

		left_leg.add(line_left_HIP);
		left_leg.add(line_left_LEG);
		right_leg.add(line_right_HIP);
		right_leg.add(line_right_LEG);

		add(Scafford);

		add(word_shown);

		add(IncorrectGuess_shown);
		/*
		add(head);
		add(body);
		add(left_arm);
		add(right_arm);
		add(left_leg);
		add(right_leg);
		add(left_foot);
		add(right_foot);
		*/
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		word_shown.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String letter) {
		/* You fill this in */
		IncorrectGuess=IncorrectGuess+letter;
		IncorrectGuess=IncorrectGuess.toUpperCase();
		IncorrectGuess_shown.setLabel(IncorrectGuess);

		wrong_letter_count += 1;

		switch (wrong_letter_count) {
			case 1: add(head);break;
			case 2: add(body);break;
			case 3: add(left_arm);break;
			case 4: add(right_arm);break;
			case 5: add(left_leg);break;
			case 6: add(right_leg);break;
			case 7: add(left_foot);break;
			case 8: add(right_foot);break;
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private static final int LEFT_TOP_X=100;
	private static final int LEFT_TOP_Y=10;

	private static final int WORD_SHOWN_X=LEFT_TOP_X;
	private static final int WORD_SHOWN_Y=LEFT_TOP_Y+SCAFFOLD_HEIGHT;

	private int wrong_letter_count = 0;

	private static GLine line_SCAFFOLD_HEIGHT = new GLine(LEFT_TOP_X, LEFT_TOP_Y, LEFT_TOP_X, LEFT_TOP_Y+SCAFFOLD_HEIGHT);
	private static GLine line_BEAM_LENGTH = new GLine(LEFT_TOP_X, LEFT_TOP_Y, LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y);
	private static GLine line_ROPE_LENGTH = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y, LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH);
	private static GCompound Scafford = new GCompound();

	private static GOval head = new GOval(LEFT_TOP_X+BEAM_LENGTH-HEAD_RADIUS, LEFT_TOP_Y+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
	private static GLine body = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2, LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);

	private static GLine line_left_UPPER_ARM = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, LEFT_TOP_X+BEAM_LENGTH-UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
	private static GLine line_left_LOWER_ARM = new GLine(LEFT_TOP_X+BEAM_LENGTH-UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, LEFT_TOP_X+BEAM_LENGTH-UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private static GCompound left_arm = new GCompound();

	private static GLine line_right_UPPER_ARM = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, LEFT_TOP_X+BEAM_LENGTH+UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
	private static GLine line_right_LOWER_ARM = new GLine(LEFT_TOP_X+BEAM_LENGTH+UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD, LEFT_TOP_X+BEAM_LENGTH+UPPER_ARM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private static GCompound right_arm = new GCompound();

	private static GLine line_left_HIP = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, LEFT_TOP_X+BEAM_LENGTH-HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
	private static GLine line_left_LEG = new GLine(LEFT_TOP_X+BEAM_LENGTH-HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, LEFT_TOP_X+BEAM_LENGTH-HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	private static GCompound left_leg = new GCompound();

	private static GLine line_right_HIP = new GLine(LEFT_TOP_X+BEAM_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, LEFT_TOP_X+BEAM_LENGTH+HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH);
	private static GLine line_right_LEG = new GLine(LEFT_TOP_X+BEAM_LENGTH+HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH, LEFT_TOP_X+BEAM_LENGTH+HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	private static GCompound right_leg = new GCompound();

	private static GLine left_foot = new GLine(LEFT_TOP_X+BEAM_LENGTH-HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, LEFT_TOP_X+BEAM_LENGTH-HIP_WIDTH-FOOT_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
	private static GLine right_foot = new GLine(LEFT_TOP_X+BEAM_LENGTH+HIP_WIDTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH, LEFT_TOP_X+BEAM_LENGTH+HIP_WIDTH+FOOT_LENGTH, LEFT_TOP_Y+ROPE_LENGTH+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);

	private String IncorrectGuess = "";
	private GLabel IncorrectGuess_shown = new GLabel("", WORD_SHOWN_X, WORD_SHOWN_Y+100);
	private GLabel word_shown = new GLabel("", WORD_SHOWN_X, WORD_SHOWN_Y+50);
}
