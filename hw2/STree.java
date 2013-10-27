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
		System.out.println("SOLVED");
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
		if(JUSTKEEPSWIMMING){

			//if st has been expanded, dont expand again
			//why isnt this working???
			if(!seen.containsKey(st.getStateString())) {
				//check if it is a solution
				if(st.isGoal()) {
					//if it is, call solution on it (to compute and return the path)
					st.print(solution(st), 0);
					//and quit the search
					return;
				}
				//for all valid moves (that havent been expanded yet: 
				//avoid loops and save some memory)
				for (char c : st.getValidMoves()) {
					//make the successor state
					State tmp = new State(st, c);
					if(!seen.containsKey(tmp)) {
						//add it to its parent's childset
						st.addChild(tmp, c);
						
						tmp.print("path to below: " + tmp.getPath() + '\n', 1);
						tmp.print(tmp.getStateString() + '\n', 1);
						tmp.printState(1);
					}
				}
				//mark it as seen
				seen.put(st.getStateString(), 1);
				
				for(State ch : st.getChildren().keySet())
					DFS(ch);
			} //end if not seen
		} //end justkeepswimming
	}
}
