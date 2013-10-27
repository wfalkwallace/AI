import java.io.File;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class SokoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String levelpath;
		int searchtype;
		char stats;

		if(args.length < 3){
			//user input
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a sokoban puzzle file [<filepath>]:");
			levelpath = input.nextLine();
			System.out.println("Specify which search to use [1-5]: ");
			System.out.println("1) BFS");
			System.out.println("2) DFS");
			System.out.println("3) UCS");
			System.out.println("4) Greedy Best First Search");
			System.out.println("5) A* Search");
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
			searchstring = "Greedy";
			break;
		case 5:
			searchstring = "A*";
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
		case 3:
			if(stats == 'y')
				for(String s : tree.UCS())
					System.out.println(s);
			else
				System.out.println(tree.UCS()[0]);
			break;
//		case 4:
//			if(stats == 'y')
//				for(String s : tree.Greedy())
//					System.out.println(s);
//			else
//				System.out.println(tree.Greedy()[0]);
//			break;
//		case 5:
//			if(stats == 'y')
//				for(String s : tree.AStar())
//					System.out.println(s);
//			else
//				System.out.println(tree.AStar()[0]);
//			break;
		}


		//BFS
		//String[] bfs = tree.BFS();
		//System.out.println("BFS:");
		//for(String s : bfs)
		//	System.out.println(s);
		//System.out.println();

		//DFS
		//String[] dfs = tree.DFS();
		//System.out.println("DFS:");
		//for(String s : dfs)
		//	System.out.println(s);
		//System.out.println();

		//UCS
		//String[] ucs = tree.UCS();
		//System.out.println("UCS:");
		//for(String s : ucs)
		//	System.out.println(s);
		//System.out.println();

	}



}
