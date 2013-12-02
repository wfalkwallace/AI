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

		//=====
		
		//HEADER
		String searchstring;
		switch(searchtype) {
		case 1:
			searchstring = "BFS";
			break;
		case 2:
			searchstring = "DFS";
			break;
		case 3:
			searchstring = "UCS";
			break;
		case 4:
			searchstring = "Greedy (using OG)";
			break;
		case 5:
			searchstring = "Greedy (using MD)";
			break;
		case 6:
			searchstring = "A* (using OG)";
			break;
		case 7:
			searchstring = "A* (using MD)";
			break;
		default:
			searchstring = "nothing";
		}
		System.out.println("Running " + searchstring + " on,");
		tree.getRoot().printState();
		//ENDHEADER


		switch(searchtype) {
		case 1:
			if(stats == 'y')
				for(String s : tree.BFS())
					System.out.println(s);
			else
				System.out.println(tree.BFS()[0]);
			break;
		case 2:
			if(stats == 'y')
				for(String s : tree.DFS())
					System.out.println(s);
			else
				System.out.println(tree.DFS()[0]);
			break;
		}



	}

}
