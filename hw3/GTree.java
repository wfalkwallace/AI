import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class GTree {


	private GState root;

	public GTree(GState root){
		this.root = root;
	}

	public GState getRoot() {
		return root;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String solution(GState solution, long start) {
		JUSTKEEPSWIMMING = false;
		//give in millis
		double time = (System.nanoTime() - start) / 1000000000.0;
		return "time: " + time;
	}

	private String[] failure() {
		String[] fail = {"Search Completed and No Solution Found"};
		return fail;
	}

	//BFS
	public String[] BFS() {
		JUSTKEEPSWIMMING = true;
		//data and results
		long start = System.nanoTime();
		int rep = 0;
		int gen = 1;
		//here we go
		GState node = root;
		HashSet<String> explored = new HashSet<String>();
		Queue<State> frontier = new LinkedList<State>();
		frontier.add(node);
		if(node.isGoal())
			return solution(node, gen, rep, frontier.size(), explored.size(), start);
		while(JUSTKEEPSWIMMING) {
			if(frontier.peek() == null)
				return failure();
			node = frontier.poll();
			explored.add(node.getStateString());
			for(char c : node.getValidMoves()) {
				State child = new State(node, c);
				gen++;
				if( !explored.contains(child.getStateString()) && !frontier.contains(child) ) {
					if(child.isGoal())
						return solution(child, gen, rep, frontier.size(), explored.size(), start);
					frontier.add(child);
				}
				else if(explored.contains(child.getStateString()))
					rep++;
			}
		}
		return new String[0];
	}
	//ENDBFS







}
