import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
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
	private static boolean JUSTKEEPSWIMMING = true;

	public STree(State r){
		root = r;
		cleanLog();
	}

	private void cleanLog() {
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
		//LOG
		//System.out.println("Solution Found");
		//ENDLOG
		return solution.getPath();
	}
	
	private String solution(State solution, int generated, int repeated, int fringe, int seen, int start) {
		JUSTKEEPSWIMMING = false;
		//LOG
		//System.out.println("Solution Found");
		//ENDLOG
		return solution.getPath();
	}

	private String failure() {
		return "Search Completed and No Solution Found";
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

			//LOG
			//node.log("===========================================================\nSTATE: " + node.getPath() + "\n");
			//node.logState();
			//node.log("HAS UNEXPLORED CHILDREN: \n");
			//ENDLOG

			for(char c : node.getValidMoves()) {
				State child = new State(node, c);
				//LOG
				//assert child.equals(new State(new State(new State(node, 'u'), 'd'), c)) : "CHILD EQUALITY BROKEN";
				//ENDLOG
				if( !explored.contains(child.getStateString()) && !frontier.contains(child) ) {
					
					//LOG
					//child.log(c + "\n");
					//child.logState();
					//child.log(child.getStateString() + '\n');
					//for(char mv : child.getValidMoves())
					//	child.log(mv + ", ");
					//child.log("\n\n");
					//ENDLOG

					if(child.isGoal())
						return solution(child);
					frontier.add(child);
				}
			}
		}
		return "huh?";
	}
}
