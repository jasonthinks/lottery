package lottery;

import java.math.BigDecimal;

public class Winner {
	// seq generator used to ensure the sequence unique and increment
	private static SequenceGenerator seqGenerator = new SequenceGenerator();
	
	// the sequence of the winner
	private int sequence = -1;
	
	// the buyyer
	private Buyer buyer;
	
	// the amount of the prize
	private BigDecimal prize = new BigDecimal(0);
	
	// ensure the prize can only be set once
	private boolean prizeSet = false;
	
	public Winner(Buyer buyer) {
		this.buyer = buyer;
		this.sequence = seqGenerator.nextSequence();
	}
	
	public int getSequence() {
		return sequence;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public BigDecimal getPrize() {
		return prize;
	}
	/**
	 * set the price
	 * @param prize2
	 */
	public void setPrize(BigDecimal prize2) {
		if(!this.prizeSet) {
			this.prize = prize2;
			this.prizeSet = true;
		}
	}
}

class SequenceGenerator {
	int i = 0;
	
	int nextSequence() {
		return ++i;
	}
}