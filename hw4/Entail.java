import java.io.File;
import java.util.Scanner;


/**
 * @author wgf2104
 *
 */
public class Entail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String mode;
		String kbpath;
		String query;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a mode [forward/backward]:");
			mode = input.nextLine();
			System.out.println("Enter a knowledge base file [<filepath>]:");
			kbpath = input.nextLine();
			System.out.println("Enter a query symbol to be entailed:");
			query = input.nextLine();
			input.close();
		}
		else {
			mode = args[0];
			kbpath = args[1];
			query = args[2];
		}

		File kbfile = new File(kbpath);
		KB kb = new KB(kbfile);
		
		if ( mode.equals("forward") ) {
			if(kb.fc(query))
				System.out.println("true");
			else
				System.out.println("false");
		}
		else if ( mode.equals("backward") ) {
			kb.bc(query);
		}
		else
			System.out.println("That's not a valid mode.");


	}

}
