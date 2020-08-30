/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

import java.util.stream.IntStream;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void init() {
		for (int i=0; i<MAX_PLAYERS; i++) {
			for (int j=0; j<N_CATEGORIES; j++) {
					score_record[i][j] = 0;
			}
		}

		for (int i=0; i<MAX_PLAYERS; i++) {
			for (int j = 0; j < N_CATEGORIES; j++) {
				select_record[i][j] = false;
			}
		}

		for (int i=0; i<N_DICE; i++) {
			dice_show[i] = 0;
		}
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");

		while (nPlayers>MAX_PLAYERS || nPlayers<=0) {
			nPlayers = dialog.readInt("Enter number of players (1~4)");
		}

		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);

		playGame();
	}

	private void playGame() {
		/* You fill this in */
		for (int match=0; match<N_SCORING_CATEGORIES; match++) {
			for (int player=1; player<=nPlayers; player++) {
				onePlayerTurn(player);
			}
		}
	}

	private void onePlayerTurn(int player) {
		for (int turn_count=1; turn_count<=3; turn_count++) {
			if (turn_count==1) {
				display.waitForPlayerToClickRoll(player);
				for (int dice_idx=0; dice_idx<N_DICE; dice_idx++) {
					dice_show[dice_idx]=rollDice();
				}
				/*
				for (int i=0; i<N_DICE; i++) {
					System.out.print(dice_show[i]);
				}
				System.out.println();
				*/
			}
			else {
				display.waitForPlayerToSelectDice();
				for (int dice_idx=0; dice_idx<N_DICE; dice_idx++) {
					if (display.isDieSelected(dice_idx)==true) {
						dice_show[dice_idx]=rollDice();
					}
				}
				/*
				for (int i=0; i<N_DICE; i++) {
					System.out.print(dice_show[i]);
				}
				System.out.println();
				 */
			}
			display.displayDice(dice_show);
		}

		int category = display.waitForPlayerToSelectCategory();

		while (select_record[player-1][category-1]) {
			display.printMessage("Please choose another category.");
			category = display.waitForPlayerToSelectCategory();
		}

		select_record[player-1][category-1]=true;

		int score = computeScore(dice_show, category);

		updateScoreRecordAndCard(category, player, score);
	}

	private int rollDice() {
		return rgen.nextInt(DICE_LOW, DICE_HIGH);
	}

	private int computeScore(int[] dice, int category) {
		if (dice.length != 5) {
			throw new ErrorException("computeScore: Illegal number of dice");
		}
		else {
			if (YahtzeeMagicStub.checkCategory(dice_show, category)==false) {
				return 0;
			}
			else {
				/*
				int[] eachNumCount = new int[]{0,0,0,0,0,0};
				int maxCount = 0;

				for (int idx=0; idx<N_DICE; idx++) {
					if (dice[idx]<1 || dice[idx]>6) {
						throw new ErrorException("computeScore: Illegal dice value");
					}
					eachNumCount[dice[idx]-1]++;
					maxCount=Math.max(maxCount, eachNumCount[dice[idx]-1]);
				}
				*/
				switch(category) {
					case 1:
						return computeScoreCase1to6(dice, category);
					case 2:
						return computeScoreCase1to6(dice, category);
					case 3:
						return computeScoreCase1to6(dice, category);
					case 4:
						return computeScoreCase1to6(dice, category);
					case 5:
						return computeScoreCase1to6(dice, category);
					case 6:
						return computeScoreCase1to6(dice, category);
					case 9:
						return IntStream.of(dice).sum();
					case 10:
						return IntStream.of(dice).sum();
					case 11:
						return 25;
					case 12:
						return 30;
					case 13:
						return 40;
					case 14:
						return 50;
					case 15:
						return IntStream.of(dice).sum();
					default:
						throw new ErrorException("computeScore: Illegal category");
				}
			}
		}
	}

	private int computeScoreCase1to6(int [] dice, int category) {
		int score = 0;
		for (int i=0; i<N_DICE; i++) {
			if (dice[i]==category) {
				score += dice[i];
			}
		}
		return score;
	}

	public void updateScoreRecordAndCard(int category, int player, int score) {
		// update "category"
		score_record[player-1][category-1]=score;
		display.updateScorecard(category, player, score);

		// update upper score
		score_record[player-1][UPPER_SCORE-1]=0;
		for (int idx=0; idx<SIXES; idx++) {
			score_record[player-1][UPPER_SCORE-1] += score_record[player-1][idx];
		}
		display.updateScorecard(UPPER_SCORE, player, score_record[player-1][UPPER_SCORE-1]);

		// update lower score
		score_record[player-1][LOWER_SCORE-1]=0;
		for (int idx=THREE_OF_A_KIND-1; idx<CHANCE; idx++) {
			score_record[player-1][LOWER_SCORE-1] += score_record[player-1][idx];
		}
		display.updateScorecard(LOWER_SCORE, player, score_record[player-1][LOWER_SCORE-1]);

		// update bonus
		score_record[player-1][UPPER_BONUS-1]=0;
		if (score_record[player-1][UPPER_SCORE-1]>=63) {
			score_record[player-1][UPPER_BONUS-1]=35;
		}
		display.updateScorecard(UPPER_BONUS, player, score_record[player-1][UPPER_BONUS-1]);

		// update total
		score_record[player-1][TOTAL-1]=score_record[player-1][UPPER_SCORE-1]+score_record[player-1][LOWER_SCORE-1]+score_record[player-1][UPPER_BONUS-1];
		display.updateScorecard(TOTAL, player, score_record[player-1][TOTAL-1]);
	}

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

	public static final int DICE_LOW = 1;
	public static final int DICE_HIGH = 6;

	private int[] dice_show = new int[N_DICE];
	private int[][] score_record = new int[MAX_PLAYERS][N_CATEGORIES];
	private boolean[][] select_record = new boolean[MAX_PLAYERS][N_CATEGORIES];
}
