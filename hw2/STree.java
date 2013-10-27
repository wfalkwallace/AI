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
		DFS(root);
	}

	private void DFS(State st) {
		if(JUSTKEEPSWIMMING){
			
//			st.print("\n ============================ \n");
			st.print(" ============================ \n");
			st.print("SEEN IS:\n");
			for(String s : seen.keySet())
				st.print(s + '\n');
			st.print("VALID MOVES ARE:\n");
			for (char c : st.getValidMoves())
				st.print(c + ", ");
			st.print("\n");
			st.printState(1);
			st.print(" ============================ \n");
//			st.print(" ============================ \n");
			
			
			
			//if st has been expanded, dont expand again
			if(seen.containsKey(st.getStateString())) {
//				st.print("FOUNDCONTAINEDINSEEN!");
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
					st.print("DFS SOLUTION");
					st.print(solution(st));
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
					//mark it as seen
					seen.put(tmp.getStateString(), 1);
//					st.print('\n' + tmp.getStateString() + '\n');
//					st.print(Integer.toString(tmp.hashCode()) + '\n');
//					st.print("added to seen.\n");
//					st.print(c + "\n");
					//add it to its parent's childset
					st.addChild(tmp, c);

					//check if it's a solution
					if (tmp.isGoal()){
						tmp.print(solution(tmp));
						return;
					}
					//then recurse on its valid moves
					DFS(tmp);
				}
			} //end if not seen
			
			
			
			
			
			
		} //end justkeepswimming
	}
}
