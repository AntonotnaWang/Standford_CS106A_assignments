/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

	/** Animation delay or pause time between ball moves */
	private static final int DELAY = 9;

	private GRect[][] brick = new GRect[NBRICK_ROWS][NBRICKS_PER_ROW] ;
	private GRect paddle;
	private GOval ball;
	private int brick_num = NBRICKS_PER_ROW * NBRICK_ROWS;

	private GLabel round_info;
	private int current_round = NTURNS;
	private GLabel game_info;

	private boolean cheat = false;

	private double vx, vy;
	private double gravity = 3.0;

	private GPoint last; /* The last mouse position */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void init() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		set_up_the_bricks();
		set_up_the_paddle();
		set_up_the_ball();
		set_up_the_label();
		println("The y range of bricks: "+BRICK_Y_OFFSET+" ~ "+(BRICK_Y_OFFSET+NBRICK_ROWS*(BRICK_HEIGHT+BRICK_SEP)));
		println("The upper face of the paddle: "+(APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT));
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		playTheGame();
	}

	public void set_up_the_bricks() {
		Color[] brick_color = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
				Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN};
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				brick[i][j] = new GRect(j * (BRICK_WIDTH + BRICK_SEP), BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP),
						BRICK_WIDTH, BRICK_HEIGHT);
				brick[i][j].setFilled(true);
				brick[i][j].setColor(brick_color[i]);
				add(brick[i][j]);
			}
		}
	}

	public void set_up_the_paddle() {
		paddle = new GRect((APPLICATION_WIDTH-PADDLE_WIDTH)/2, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT,
				PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
		addKeyListeners();
	}

	public void set_up_the_ball() {
		ball = new GOval(rgen.nextDouble(PADDLE_WIDTH,APPLICATION_WIDTH-PADDLE_WIDTH),
				rgen.nextDouble(BRICK_Y_OFFSET + NBRICK_ROWS * (BRICK_HEIGHT + BRICK_SEP)+BRICK_SEP, APPLICATION_HEIGHT-(PADDLE_Y_OFFSET+PADDLE_HEIGHT)*5),
				BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
		vx=rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx=-vx;
		}
		vy=5.0;
	}

	public void set_up_the_label() {
		round_info = new GLabel("Please press mouse to start!");
		round_info.setLocation(50,50);
		round_info.setFont("Times New Roman-18");
		round_info.setLocation((APPLICATION_WIDTH-round_info.getWidth())/2, (APPLICATION_HEIGHT)/2);
		add(round_info);
		game_info = new GLabel("Good luck bro! Lives left: "+current_round);
		game_info.setFont("Times New Roman-14");
		game_info.setLocation(10, BRICK_Y_OFFSET/2);
		add(game_info);
	}

	public void playTheGame() {
		while (current_round>=0 && brick_num>0) {
			waitForClick();
			remove(round_info);
			while (check_if_die() == false && current_round>=0 && brick_num>0) {
				bounceBall();
			}
			current_round--;
			game_info.setLabel("Good luck, bro! Lives left: " + current_round);
			remove(game_info);
			add(game_info);
			add(round_info);
			remove(ball);
			set_up_the_ball();
		}

		if (brick_num<=0) {
			round_info.setLabel("Winner winner, chicken dinner!");
			remove(game_info);
			add(game_info);
			add(round_info);
		}
		else if (current_round<=0) {
			round_info.setLabel("Loser!");
			remove(game_info);
			add(game_info);
			add(round_info);
		}
	}

	public void bounceBall() {
		velocity_change_after_collision_with_object_walls();
		GObject colliding_object=get_colliding_object();
		if (colliding_object==paddle) {
			game_info.setLabel("Hey, bro! Ball hits the paddle!");
			remove(game_info);
			add(game_info);
			println("Hit the paddle!");
			vy = -vy;
		}
		else if (colliding_object!=null && colliding_object!=game_info) {
			brick_num--;
			game_info.setLabel("Good job, bro! Number of left brick(s): "+brick_num);
			remove(game_info);
			add(game_info);
			println("Hit brick: "+colliding_object);
			remove(colliding_object);
			vy = -vy;
		}
		//println("Vx: "+vx);
		//println("Vy: "+vy);
		moveBall();
		pause(DELAY);
	}

	public void moveBall() {
		//vy += gravity;
		ball.move(vx, vy);
	}

	public GObject get_colliding_object() {
		GObject up_left_obj = getElementAt(ball.getX(), ball.getY());
		GObject up_right_obj = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY());
		GObject down_left_obj = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS*2);
		GObject down_right_obj = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY()+BALL_RADIUS*2);
		if (up_left_obj!=null) {
			//println("Hit object: "+up_left_obj);
			return up_left_obj;
		}
		else if (up_right_obj!=null) {
			//println("Hit object: "+up_right_obj);
			return up_right_obj;
		}
		else if (down_left_obj!=null) {
			//println("Hit object: "+down_left_obj);
			return down_left_obj;
		}
		else if (down_right_obj!=null) {
			//println("Hit object: "+down_right_obj);
			return down_right_obj;
		}
		else {
			return null;
		}
	}

	public void velocity_change_after_collision_with_object_walls() {
		double colliding_surface;
		if (cheat==false) {
			if ((ball.getX() - vx <= 0 && vx < 0) || (ball.getX() + BALL_RADIUS * 2 + vx >= APPLICATION_WIDTH && vx > 0)) {
				vx = -vx;
			}
			if (ball.getY() - vy <= 0 && vy < 0) {
				vy = -vy;
			}
		}
		else {
			if ((ball.getX() - vx <= 0 && vx < 0) || (ball.getX() + BALL_RADIUS * 2 + vx >= APPLICATION_WIDTH && vx > 0)) {
				vx = -vx;
			}
			if ((ball.getY() - vy <= 0 && vy < 0) || (ball.getY() + BALL_RADIUS * 2 + vy >= APPLICATION_HEIGHT && vy > 0)){
				vy = -vy;
			}
		}
	}

	public boolean check_if_die() {
		if (cheat==false) {
			if (ball.getY() + BALL_RADIUS * 2 >= APPLICATION_HEIGHT) {
				return true;
			}
		}
		return false;
	}

	public void mousePressed(MouseEvent e) {
		if (paddle!=null) {
			last = new GPoint(e.getPoint());
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (paddle!=null) {
			paddle.move(e.getX() - last.getX(), 0);
			last = new GPoint(e.getPoint());

			if (paddle.getX()<=0) {
				paddle.setLocation(0, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
				last = new GPoint(0, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
			}

			if (paddle.getX()>=APPLICATION_WIDTH-PADDLE_WIDTH) {
				paddle.setLocation(APPLICATION_WIDTH-PADDLE_WIDTH, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
				last = new GPoint(APPLICATION_WIDTH-PADDLE_WIDTH, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		if (paddle != null) {
			paddle.setColor(rgen.nextColor());
		}
		if (ball != null) {
			ball.setColor(rgen.nextColor());
		}
		if (e.getKeyCode()==0) {
			cheat=true;
			game_info.setLabel("God mod!");
			remove(game_info);
			add(game_info);
		}
	}
}
