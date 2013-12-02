import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
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



		int mode;
		String kbpath;
		String query;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a mode:");
			System.out.println("1) Forward");
			System.out.println("2) Backward");
			mode = input.nextInt();
			System.out.println("Enter a knowledge base file [<filepath>]:");
			kbpath = input.nextLine();
			System.out.println("Enter a query symbol to be entailed:");
			query = input.nextLine();
		}
		else {
			mode = Integer.parseInt(args[0]);
			kbpath = args[1];
			query = args[2];
		}

		File kbfile = new File(kbpath);
		KBLoader kbl = new KBLoader(kbfile);		

		String searchstring;
		switch(mode) {
		case 1:
			//FORWARD
			System.out.println("Running Forward Chaining on,");
			break;
		case 2:
			//BACKWARD
			System.out.println("Running Backward Chaining on,");
			break;

		}

		
		
		
		
		
		
		
		
		
		
		


	}

}
