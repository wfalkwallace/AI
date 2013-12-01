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



		String levelpath;
		int searchtype;
		char stats;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a sokoban puzzle file [<filepath>]:");
			levelpath = input.nextLine();
			System.out.println("Specify which search to use [1-7]: ");
			System.out.println("1) BFS");
			System.out.println("2) DFS");
			System.out.println("3) UCS");
			System.out.println("4) Greedy Best First Search (with open goals heuristic)");
			System.out.println("5) Greedy Best First Search (with Manhattan distance heuristic)");
			System.out.println("6) A* Search (with open goals heuristic)");
			System.out.println("7) A* Search (with Manhattan distance heuristic)");
			searchtype = input.nextInt();
			System.out.println("Statistics [y/n]:");
			stats = input.next().charAt(0);
		}
		else {
			levelpath = args[0];
			searchtype = Integer.parseInt(args[1]);
			stats = args[2].charAt(0);
		}

		//TREEPREP
		File lvl_src = new File(levelpath);
		LevelLoader ll = new LevelLoader(lvl_src);		
		STree tree = new STree(ll.init());
		//ENDTREEPREP

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
