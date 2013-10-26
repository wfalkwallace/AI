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
	private Hashtable<State, Integer> seen;

	public STree(State r){
		seen = new Hashtable<State, Integer>();
		root = r;

		//		seen.put(root, 1);

	}

	public State getRoot() {
		return root;
	}
	
	private String solution(State sol) {
		return sol.getPath();
	}

	public void DFS() {
		DFS(root);
	}

	private void DFS(State st) {
		//if st has been expanded
		if(seen.contains(st)){

		}
		//otherwise if st hasnt been expanded
		else {
			//check if it is a solution
			if(st.isGoal()) {
				//if it is, call solution on it (to compute and return the path)
				solution(st);
				//and quit the search
				return;
			}
			//for all valid moves (that havent been expanded yet: avoid loops and save mem)
			for (char c : st.getValidMoves()) {
				//make the successor state
				State tmp = new State(st, c);
				//add it to its parent's childset
				st.addChild(tmp, c);
				//mark it as seen
				seen.put(tmp, 1);
				//check if it's a solution
				if (tmp.isGoal()){
					solution(tmp);
					return;
				}
				//then recurse on its valid moves
				DFS(tmp);
			}
		}
	}
}
