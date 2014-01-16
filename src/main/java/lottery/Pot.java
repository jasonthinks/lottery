package lottery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The lottery pot class
 * @author Jason Wang
 *
 */
public class Pot {
	/**
	 * Balls number
	 */
	final int TOTAL_BALLS = 50;
	
	/**
	 * the original total prize
	 */
	BigDecimal totalPrize;
	
	/**
	 * prize left in pot after paying buyers
	 */
	BigDecimal prizeInPot;
	
	/**
	 * balls in the pot
	 */
	List<Ball> balls = new ArrayList<Ball>();

	/**
	 * sold tickets, numbers of balls
	 */
	List<Integer> soldTickets = new ArrayList<Integer>();
	
	
	public Pot(double totalPrize) {
		this.totalPrize = new BigDecimal(totalPrize);
		this.prizeInPot = new BigDecimal(totalPrize);
		for(int i = 0; i < TOTAL_BALLS; i++) {
			Ball ball = new Ball(i + 1);
			balls.add(ball);
		}
	}
	
	/**
	 * sell a ball with specified number to a user, needs to check if the number has been sold out.
	 * @param the number of the ball 
	 * @return
	 */
	public boolean sellTicket(Integer number) {
		if(!this.soldTickets.contains(number)) {
			this.soldTickets.add(number);
			return true;
		}
		return false;
	}
	
	/**
	 * draw a ball randomly
	 * @return
	 */
	public Ball drawABall() {
		Random rnd = new Random();
		int index = rnd.nextInt(balls.size());
		Ball ball = balls.get(index);
		balls.remove(index);
		return ball;
	}

	/**
	 * to confirm the prize of the winner after drawing a ball
	 * @param winner
	 */
	public void confirmPrize(Winner winner) {
		BigDecimal prize = this.totalPrize.multiply(new BigDecimal(0.50).multiply(getPrizeRatio(winner.getSequence())));
		winner.setPrize(prize);
		prizeInPot = prizeInPot.subtract(prize);
	}
	/**
	 * determine the amount ratio of the prize by the sequence of the number
	 * @param seq
	 * @return
	 */
	private BigDecimal getPrizeRatio(int seq) {
		return new BigDecimal((seq == 1?0.75:(seq == 2?0.15:(seq == 3?0.10:0))));
	}
}
