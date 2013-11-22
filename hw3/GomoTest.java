import java.util.Scanner;

/**
 * William Falk-Wallace
 * AI HW3
 */

/**
 * @author wgf2104
 *
 */
public class GomoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int boardsize, chainlength, p1, p2;
		double timeout;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a Gomoku board size:");
			boardsize = input.nextInt();
			System.out.println("Enter a winning chain length: ");
			chainlength = input.nextInt();
			System.out.println("Enter a turn time limit (in seconds):");
			timeout = input.nextDouble();
			System.out.println("Enter a player 1 type:");
			System.out.println("0: Random");
			System.out.println("1: Human");
			System.out.println("2: Computer");
			p1 = input.nextInt();
			System.out.println("Enter a player 1 type:");
			System.out.println("0: Random");
			System.out.println("1: Human");
			System.out.println("2: Computer");
			p2 = input.nextInt();
			input.close();
		}
		else {
			boardsize = Integer.parseInt(args[0]);
			chainlength = Integer.parseInt(args[1]);
			timeout = Double.parseDouble(args[2]);
			p1 = Integer.parseInt(args[3]);
			p2 = Integer.parseInt(args[4]);
		}
		
		GTree tree = new GTree(boardsize, chainlength, timeout);
		System.out.println("Player " + tree.game(p1, p2) + " WINS!");
		tree.closeScanner();
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
