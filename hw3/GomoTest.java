import java.util.Scanner;

/**
 * 
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
		int boardsize;
		int chainlength;
		int turntime;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a Gomoku board size:");
			boardsize = input.nextInt();
			System.out.println("Enter a winning chain length: ");
			chainlength = input.nextInt();
			System.out.println("Enter a turn time limit (in seconds):");
			turntime = input.nextInt();
			input.close();
		}
		else {
			boardsize = Integer.parseInt(args[0]);
			chainlength = Integer.parseInt(args[1]);
			turntime = Integer.parseInt(args[2]);
		}
		
		GState root = new GState(boardsize, chainlength);
		GTree tree = new GTree();
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
