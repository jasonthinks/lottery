package lottery;

import java.util.Scanner;

/**
 * The main entry class, with the command parser
 * @author Jason Wang
 *
 */
public class LotteryApp 
{
    public static void main( String[] args )
    {
    	LotterySeller lotteryHolder = new LotterySeller("Tommy");
    	System.out.println("What are you going to do? [purchase, draw, or winners]");
    	Scanner scanner = new Scanner(System.in);
    	String command = null;
    	while((command = scanner.nextLine()) != null) {
    		if("purchase".equalsIgnoreCase(command)) {
    			String buy = null;
    			System.out.println("Please give us the buyer's name and choosen a number, like: Tom 33");
    			while((buy = scanner.nextLine()) != null) {
    				String[] ss = buy.replace("-", " ").replaceAll(",", " ").replaceAll("  ", " ").split(" ");
    				boolean succ = lotteryHolder.sell(new Buyer(ss[0]), Integer.parseInt(ss[1]));
    				while(!succ) {
    					System.out.println(ss[1] + " has been sold out, please try another ball number (Just the number):");
    					ss[1] = scanner.nextLine();
    					succ = lotteryHolder.sell(new Buyer(ss[0]), Integer.parseInt(ss[1]));
    				}
    				if(succ) {
    					System.out.printf("Bingo! %s bought ball [%s] successfully. Another purchase?[y, n]", ss[0], ss[1]);
    					String anotherPurchase = scanner.nextLine();
    					boolean anotherPur = false;
    					while(anotherPurchase != null && (anotherPurchase.equalsIgnoreCase("y") || anotherPurchase.equalsIgnoreCase("n"))) {
	    					if("n".equalsIgnoreCase(anotherPurchase)) {
	    						anotherPur = false;
	    						break;
	    					}
	    					else if("y".equals(anotherPurchase)) {
	    						anotherPur = true;
	    						System.out.println("\nPlease give us the buyer's name and choosen number, like: Tom 33");
	    						break;
	    					}
	    					else {
	    						anotherPurchase = scanner.nextLine();
	    					}
    					}
    					if(!anotherPur) {
    						break;
    					}
    				}
    				
    			}
    		}
    		
    		if("draw".equalsIgnoreCase(command)) {
    			Winner winner = lotteryHolder.drawABallAndGetWinner();
    			System.out.printf("%5d : %s won %.2f dollars", winner.getSequence(), winner.getBuyer().getName(), winner.getPrize());
    		}
    		
    		if("winners".equalsIgnoreCase(command)) {
    			lotteryHolder.listWinners();
    			System.exit(0);
    		}
    		System.out.println("\nWhat are you going to do? [purchase, draw, or winners]");
    	}
    	scanner.close();
    }
}
