import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

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
		
		try {
			FileWriter fw = new FileWriter("log.txt");
			fw.write(new Date().toString() + '\n');
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public State getRoot() {
		return root;
	}

	private String solution(State solution) {
		JUSTKEEPSWIMMING = false;
		System.out.println("SOLVED");
		return solution.getPath();
	}

	private String failure() {
		return "failed";
	}

	public String BFS() {
		State node = root;

		if(node.isGoal())
			return solution(node);

		HashSet<String> explored = new HashSet<String>();
		Queue<State> frontier = new LinkedList<State>();
		frontier.add(root);

		while(JUSTKEEPSWIMMING) {
			if(frontier.peek() == null)
				return failure();
			node = frontier.remove();
			explored.add(node.getStateString());

			for(char c : node.getValidMoves()) {
				State child = new State(node, c);
				if( !explored.contains(child.getStateString()) && !frontier.contains(child) ) {
					child.logState();
					if(child.isGoal())
						return solution(child);
					frontier.add(child);
				}
			}
		}
		return "huh?";
	}












	public void oldDFS() {
		JUSTKEEPSWIMMING = true;
		try {
			FileWriter fw = new FileWriter("log.txt");
			fw.write(new Date().toString() + '\n');
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.logState();
		oldDFS(root);
	}

	private void oldDFS(State st) {
		if(JUSTKEEPSWIMMING){

			//if st has been expanded, dont expand again
			//why isnt this working???
			if(!seen.containsKey(st.getStateString())) {
				//check if it is a solution
				if(st.isGoal()) {
					//if it is, call solution on it (to compute and return the path)
					System.out.println(solution(st));
					//and quit the search
					return;
				}
				//for all valid moves (that havent been expanded yet: 
				//avoid loops and save some memory)
				for (char c : st.getValidMoves()) {
					//make the successor state
					State tmp = new State(st, c);
					if(!seen.containsKey(tmp.getStateString())) {
						//add it to its parent's childset
						st.addChild(tmp, c);

						tmp.log("path to below: " + tmp.getPath() + '\n');
						tmp.log(tmp.getStateString() + '\n');
						tmp.logState();
					}
				}
				//mark it as seen
				seen.put(st.getStateString(), 1);

				for(State ch : st.getChildren().keySet())
					oldDFS(ch);
			} //end if not seen
		} //end justkeepswimming
	}
}
