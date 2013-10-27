import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class STree {

	private State root;
	private static Hashtable<String, Integer> seen;
	private static boolean JUSTKEEPSWIMMING = true;

	public STree(State r){
		seen = new Hashtable<String, Integer>();
		root = r;

		//		seen.put(root, 1);

	}

	public State getRoot() {
		return root;
	}

	private String solution(State sol) {
		JUSTKEEPSWIMMING = false;
		return sol.getPath();
	}

	public void DFS() {
		JUSTKEEPSWIMMING = true;
		try {
			FileWriter fw = new FileWriter("log.txt");
			fw.write(new Date().toString() + '\n');
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.printState(1);
		DFS(root);
	}

	private void DFS(State st) {
		if(st.getStateString().equals("##### *## @####*   ##    ##    #######"))
			System.out.println("SOLUTION");

		if(JUSTKEEPSWIMMING){
//			st.print("\n ============================ \n");
//			st.print("============================ \n", 1);
//			st.print("SEEN IS:\n", 1);
//			for(String s : seen.keySet())
//				st.print(s + '\n', 1);
//			st.print("VALID MOVES ARE:\n", 1);
//			for (char c : st.getValidMoves())
//				st.print(c + ", ", 1);
//			st.print("\n", 1);
//			st.printState(1);
//			st.print("============================ \n", 1);
//			st.print(" ============================ \n");
			
			
			
			//if st has been expanded, dont expand again
			if(seen.containsKey(st.getStateString())) {
//				st.print("seen contains " + st.getStateString() + "\n", 1);
				return;
			}
			else{
//				st.print("\n ============================ \n");
//				st.print("\n ============================ \n");
//				st.print("\n ============================ \n");
//				st.print("DFS on State:\n");
//				st.printState(1);
//				st.print(" ============================ \n");
				//check if it is a solution
				if(st.isGoal()) {
					//if it is, call solution on it (to compute and return the path)
					st.print("DFS SOLUTION", 0);
					st.print(solution(st), 0);
					//and quit the search
					return;
				}
				//for all valid moves (that havent been expanded yet: 
				//avoid loops and save some memory)
				for (char c : st.getValidMoves()) {
					//make the successor state
					State tmp = new State(st, c);
//					st.print("Produced Successor State (by " + c + "):\n");
//					tmp.printState(1);
//					st.print(" ============================ \n");
//					if(!seen.contains(tmp)){
					
					for(String s : seen.keySet())
						st.print("-      " + s + '\n', 1);
					//mark it as seen
					seen.put(st.getStateString(), 1);
					st.print ("+      " + st.getStateString() + "\n\n", 1);

					tmp.print("State: " + tmp.getStateString() + '\n', 1);
//					tmp.print("Final: ##### *## @####*   ##    ##    #######\n", 1);

					tmp.print(st.getPath() + "+" + c + '\n', 1);
//					st.print("rdldluuu\n", 1);
					tmp.printState(1);
					st.print("========================================== \n", 1);

//					st.print(Integer.toString(tmp.hashCode()) + '\n');
//					st.print("added to seen.\n");
//					st.print(c + "\n");
					//add it to its parent's childset
//					st.print("adding child \n", 1);
					st.addChild(tmp, c);

					//check if it's a solution
					if (tmp.isGoal()){
						tmp.print(solution(tmp), 0);
						return;
					}
					//then recurse on its valid moves
//					st.print("about to recurse on " + tmp.getStateString() + "\n", 1);
					DFS(tmp);
//					st.print("recursion on " + tmp.getStateString() + "DONE\n", 1);
				}
			} //end if not seen
			
			
			
			
			
			
		} //end justkeepswimming
	}
}
