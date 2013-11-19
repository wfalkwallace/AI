import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class GTree {

	private static Random random = new Random();
	private static final int DEPTH_LIMIT = 5;
	private Hashtable<String, Integer> explored = new Hashtable<String, Integer>();

	private GState current;
	private int timeout;
	public GTree(int boardsize, int chainlength, int timeout){
		current = new GState(boardsize, chainlength);
		this.timeout = timeout;
	}
	
	public void game(int p1, int p2) {
		
		
	}
	
	
	private void move (int mode) {
		switch(mode) {
		case 0:
			randomMove();
			break;
		case 1:
			promptMove();
			break;
		case 2:
			abSearch();
			break;
		}		
	}
	
	private void randomMove() {
		ArrayList<int[]> moves = current.getActions();
		int[] move = moves.get(random.nextInt(moves.size()));
		current = current.getResult(move[0], move[1]);
	}
	
	
	
	
	
	private int[] abSearch() {
		int v = maxValue(current, -1000000000, 1000000000, 0);
		//TODO: which one to get?
		return current.getActions().get(0);
	}
	
	private int maxValue(GState state, int alpha, int beta, int depth) {
		if(cutoff(state, depth))
			return utility(state);
		int v = -1000000000;
		for(int[] action : state.getActions()){
			v = max(v, minValue(state.getResult(action[0], action[1]), alpha, beta, depth + 1));
			if(v >= beta)
				return v;
			alpha = max(v, alpha);
		}
		return v;
	}
	
	private int minValue(GState state, int alpha, int beta, int depth) {
		if(cutoff(state, depth))
			return utility(state);
		int v = 1000000000;
		for(int[] action : state.getActions()){
			v = min(v, maxValue(state.getResult(action[0], action[1]), alpha, beta, depth + 1));
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
	
	private boolean cutoff(GState state, int depth) {
		return depth == DEPTH_LIMIT;
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
