import java.util.Date;
import java.util.Hashtable;
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

	private static final int DEPTH_LIMIT = 5;
	private Hashtable<String, Integer> explored = new Hashtable<String, Integer>();

	public GTree(){
	}
	
	public int[] abSearch(GState current) {
		int v = maxValue(current, -1000000000, 1000000000);
		//TODO: which one to get?
		return current.getActions().get(0);
	}
	
	private int maxValue(GState state, int alpha, int beta) {
		if(state.getDepth() == DEPTH_LIMIT)
			return utility(state);
		int v = -1000000000;
		for(int[] action : state.getActions()){
			v = max(v, minValue(state.getResult(action[0], action[1]), alpha, beta));
			if(v >= beta)
				return v;
			alpha = max(v, alpha);
		}
		return v;
	}
	
	private int minValue(GState state, int alpha, int beta) {
		if(state.getDepth() == DEPTH_LIMIT)
			return utility(state);
		int v = 1000000000;
		for(int[] action : state.getActions()){
			v = min(v, maxValue(state.getResult(action[0], action[1]), alpha, beta));
			if(v <= alpha)
				return v;
			beta = min(v, beta);
		}
		return v;
	}
	
	private int min(int a, int b) {
		return (a < b) ? a : b;
	}
	
	private int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	//TODO Utility
	private int utility(GState state) {
		int u = 0;
		
		
		
		return u;
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

	//DFS
	public String[] DFS() {
		JUSTKEEPSWIMMING = true;
		//data and results
		long start = System.nanoTime();
		int rep = 0;
		int gen = 1;

		//here we go
		State node = root;
		HashSet<String> explored = new HashSet<String>();
		Stack<State> frontier = new Stack<State>();
		
		frontier.push(node);
		while(JUSTKEEPSWIMMING) {
			if(frontier.peek() == null)
				return failure();
			node = frontier.pop();
			if( !explored.contains(node.getStateString()) ) {
				explored.add(node.getStateString());
				if(node.isGoal())
					return solution(node, gen, rep, frontier.size(), explored.size(), start);
				for(char c : node.getValidMoves()) {
					State child = new State(node, c);
					gen++;
					if( !explored.contains(child.getStateString()) ) {
						frontier.add(child);
					}
					else
						rep++;
				}
			}
			else
				rep++;
		}
		return new String[0];
	}
	//ENDDFS







}
