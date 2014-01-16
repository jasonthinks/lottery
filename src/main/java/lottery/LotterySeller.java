package lottery;

import java.util.HashMap;
import java.util.Map;

/**
 * the lottery seller
 * @author Jason Wang
 *
 */
public class LotterySeller {
	
	/**
	 * name of the lottery seller
	 */
	private String name;

	/**
	 * the ongoing lottery ball pot
	 */
	private Pot pot = new Pot(200);
	
	/**
	 * sold tickets, ball numbers
	 */
	private Map<Integer, Buyer> soldTickets = new HashMap<Integer, Buyer>();
	
	/**
	 * winners
	 */
	private Map<Integer, Winner> winners = new HashMap<Integer, Winner>();
	
	public LotterySeller(String name) {
		this.name = name;
	}
	
	/**
	 * sell a ball to a buyer
	 * @param buyer
	 * @param number
	 * @return
	 */
	public boolean sell(Buyer buyer, Integer number) {
		while(pot.sellTicket(number)) {
			soldTickets.put(number, buyer);
			return true;
		}
		return false;
	}
	
	/**
	 * draw a ball and confirm the winner
	 * @return
	 */
	public Winner drawABallAndGetWinner() {
		boolean valid = false;
		while(!valid) {
			Ball ball = pot.drawABall();
			valid = soldTickets.containsKey(ball.getNumber());
			if(valid) {
				Winner winner = new Winner(soldTickets.get(ball.getNumber()));
				winners.put(winner.getSequence(), winner);
				pot.confirmPrize(winner);
				return winner;
			}
		}
		return null;
	}

	/**
	 * list winners
	 */
	public void listWinners() {
		System.out.println("1st ball\t\t\t2nd ball\t\t\t3rd ball");
		for(int i = 1; i <= winners.size(); i++) {
			Winner winner = winners.get(i);
			System.out.printf("%s: $%.2f", winner.getBuyer().getName(), winner.getPrize() );
			System.out.print("\t\t\t");
		}
		System.out.println();
	}

	public String getName() {
		return name;
	}
	
}
