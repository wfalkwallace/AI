import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class GTree {

	private static Random random = new Random();
	private static final int DEPTH_LIMIT = 4;
	private Hashtable<Integer, GState> explored = new Hashtable<Integer, GState>();
	private GState current;
	private double timeout;
	private long start;
	private Scanner input;

	public GTree(int boardsize, int chainlength, double timeout){
		current = new GState(boardsize, chainlength);
		this.timeout = timeout;
		//user input
		input = new Scanner(System.in);
	}

	public char game(int p1, int p2) {
		while(true) {
			start = System.nanoTime();
			move(p1, start);
			current.printState();
			System.out.println((System.nanoTime() - start) / 1000000000.0);
			if(current.isWin())
				//TODO WIN
				return current.getParent().getPlayer();
			else if(current.isDraw())
				//TODO DRAW
				return '0';
			start = System.nanoTime();
			move(p2, start);
			current.printState();
			System.out.println((System.nanoTime() - start) / 1000000000.0);
			if(current.isWin())
				//TODO WIN
				return current.getParent().getPlayer();
			else if(current.isDraw())
				//TODO DRAW
				return '0';
		}
	}


	private void move (int mode, long start) {
		switch(mode) {
		case 0:
			randomMove();
			break;
		case 1:
			promptMove(start);
			break;
		case 2:
			abSearch(start);
			break;
		}		
	}

	private void randomMove() {
		ArrayList<int[]> moves = current.getActions();
		int[] move = moves.get(random.nextInt(moves.size()));
		current = current.getResult(move[0], move[1]);
	}

	private void promptMove(long start) {
		//		if((System.nanoTime() - start) / 1000000000.0 < timeout) {
		System.out.println("Row:");
		int row = input.nextInt();
		System.out.println("Column:");
		int col = input.nextInt();
		current = current.getResult(row, col);
		//		}
	}

	public void closeScanner() {
		input.close();
	}

	private void abSearch(long start) {
		int v = maxValue(current, -1000000000, 1000000000, 0);
		//TODO: which one to get?
		GState end = explored.get(v);
//		if(next == null)
//			next = end;
		while(end.getParent() != current)
			end = end.getParent();
		current = end;
		explored.clear();
		//		current = current.getResult(next.getMove()[0], next.getMove()[1]);
		//		return next.getMove();
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

	//TODO timer and terminal (draw and win) cutoffs
	private boolean cutoff(GState state, int depth) {
		double time = (System.nanoTime() - start) / 1000000000.0;
		if(time > .75 * timeout)
			return true;
		if(depth == DEPTH_LIMIT)
			return true;
		if(state.isWin() || state.isDraw())
			return true;
		return false;
	}

	//TODO Utility
	private int utility(GState state) {
//		if(explored.contains(state))
//			for(int c : explored.keySet())
//				if(explored.get(c) == state)
//					return c;
		int u = state.getUtility(current.getPlayer());
		if(explored.containsKey(u)) {
			if(random.nextBoolean()) {
				explored.put(u, state);
			}
		}
		else
			explored.put(u, state);
		return u;
	}









}
