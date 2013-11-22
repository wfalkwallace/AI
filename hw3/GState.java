import java.util.ArrayList;

/**
 * William Falk-Wallace
 * AI HW3
 */

/**
 * @author wgf2104
 *
 */
public class GState {

	private GState parent;
	private static int boardsize;
	private static int chainlength;
	private char[][] board;
	private char player;
	private int[] move;
	private String statestring;
	private ArrayList<int[]> actions;

	public GState(int size, int length) {
		parent = null;
		player = 'x';
		boardsize = size;
		chainlength = length;
		board = new char[boardsize][boardsize];
		init();
		setStateString();
		actions = new ArrayList<int[]>();
		setActions();
	}

	private void init() {
		for(int i = 0; i < boardsize; i++)
			for(int j = 0; j < boardsize; j++)
				board[i][j] = '.';
	}

	public GState getResult(int x, int y) {
		return new GState(this, x, y);
	}

	private GState(GState par, int x, int y) {
		parent = par;
		move = new int[] {x, y};
		player = (par.getPlayer() == 'x') ? 'o' : 'x';
		board = new char[boardsize][boardsize];

		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				board[i][j] = par.getBoard()[i][j];
			}
		}

		board[x][y] = par.getPlayer();

		setStateString();
		actions = new ArrayList<int[]>();
		setActions();
	}

	/**
	 * @return the boardsize
	 */
	public int getBoardsize() {
		return boardsize;
	}

	public char getPlayer() {
		return player;
	}

	//TODO priority queue
	private void setActions(){
		//			for(int i = 0; i < boardsize; i++) {
		//				for(int j = 0; j < boardsize; j++) {
		//					if(board[i][j] == '.') {
		//						if( (j + 1  < boardsize && board[i][j+1] != '.') || 
		//								(j - 1  > 0 && board[i][j-1] != '.') || 
		//								(i + 1  < boardsize && board[i+1][j] != '.') || 
		//								(i - 1  > 0 && board[i-1][j] != '.') || 
		//								(i + 1  < boardsize && j + 1  < boardsize && board[i+1][j+1] != '.') || 
		//								(i - 1  > 0 && j - 1  > 0 && board[i-1][j-1] != '.') || 
		//								(i - 1  > 0 && j + 1  < boardsize && board[i-1][j+1] != '.') || 
		//								(i + 1  < boardsize && j - 1  > 0 && board[i+1][j-1] != '.')) {
		//							actions.add(new int[] {i, j});
		//						}
		//					}
		//				}
		//			}
		//		if(actions.size() == 0) {
		//			if(board[boardsize/2][boardsize/2] == '.') {
		//				actions.add(new int[] {boardsize/2, boardsize/2});
		//			}
		//			else {
		//				actions.add(new int[] {boardsize/2, boardsize/2 - 1});
		//			}
		//		}

		for(int i = 0; i < boardsize; i++)
			for(int j = 0; j < boardsize; j++)
				if(board[i][j] == '.')
					actions.add(new int[] {i, j});		
	}

	public ArrayList<int[]> getActions() {
		return actions;
	}

	/**
	 * @param boardsize the boardsize to set
	 */
	public void setBoardsize(int size) {
		boardsize = size;
	}

	/**
	 * @return the chainlength
	 */
	public int getChainlength() {
		return chainlength;
	}

	/**
	 * @param chainlength the chainlength to set
	 */
	public void setChainlength(int length) {
		chainlength = length;
	}

	public int[] getMove() {
		return move;
	}

	public void printState() {
		// Simple print:
		//		for(char[] row : board) {
		//			for(char c : row) {
		//				System.out.print(c);
		//			}
		//			System.out.println();
		//		}

		// Line number print:
		//col number header
		System.out.print("   ");
		for(int i = 0; i < boardsize; i++){
			if(i < 10)
				System.out.print(" ");
			System.out.print(" " + i);
		}
		System.out.println();

		for(int i = 0; i < boardsize; i++) {
			if(i < 10)
				System.out.print(" ");
			System.out.print(i + " ");
			for(int j = 0; j < boardsize; j++) {
				System.out.print("  " + board[i][j]);
			}
			System.out.println();
		}
	}

	public char[][] getBoard() {
		return board;
	}

	public GState getParent() {
		return parent;
	}

	private void setStateString() {
		statestring = "";
		for(char[] row : board)
			for(char c : row)
				statestring += c;
	}

	public String getStateString() {
		return statestring;
	}

	public boolean isDraw() {
		return !statestring.contains(".");
	}

	//what about X's where one chainlength is too long, one is right?
	public boolean isWin() {
		if(move == null)
			return false;
		char mvp = (player == 'x') ? 'o' : 'x';
		if(checkNorth(mvp, move) + checkSouth(mvp, move) + 1 == chainlength) {
			return true;
		}
		else if(checkEast(mvp, move) + checkWest(mvp, move) + 1 == chainlength)
			return true;
		else if(checkNE(mvp, move) + checkSW(mvp, move) + 1 == chainlength)
			return true;
		else if(checkNW(mvp, move) + checkSE(mvp, move) + 1 == chainlength)
			return true;
		return false;
	}

	private int checkNorth(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x - 1; i >= 0; i--){
			if(board[i][y] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSouth(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x + 1; i < boardsize; i++){
			if(board[i][y] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkEast(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = y + 1; i < boardsize; i++){
			if(board[x][i] == mvp)
				count++;
			else{
				return count;
			}
		}
		return count;
	}

	private int checkWest(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = y - 1; i >= 0; i--){
			if(board[x][i] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkNW(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x-1, j = y-1; i >= 0 && j >= 0; i--, j--){
			if(board[i][j] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkNE(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x-1, j = y+1; i >= 0 && j < boardsize; i--, j++){
			if(board[i][j] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSW(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x+1, j = y-1; i < boardsize && j >= 0; i++, j--){
			if(board[i][j] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSE(char mvp, int[] move){
		int count = 0;
		int x = move[0];
		int y = move[1];
		for(int i = x+1, j = y+1; i < boardsize && j < boardsize; i++, j++){
			if(board[i][j] == mvp)
				count++;
			else
				return count;
		}
		return count;
	}

	//TODO better. check diagonals and stuff.
	public int getUtility(char p) {
		//just handle edge cases
		if(isWin())
			return 1000000;
		if(isDraw())
			return 0;

		//directional chainlength
		int n = 0, s = 0, e = 0, w = 0, nw = 0, sw = 0, ne = 0, se = 0;
		int o_n = 0, o_s = 0, o_e = 0, o_w = 0, o_nw = 0, o_sw = 0, o_ne = 0, o_se = 0;

		char o = (p == 'x') ? 'o' : 'x';
		int threat = 0;
		int max = 0, opp = 0;

		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				//my threat
				n = checkNorth(p, new int[] {i, j});
				s = checkSouth(p, new int[] {i, j});
				e = checkEast(p, new int[] {i, j});
				w = checkWest(p, new int[] {i, j});
				nw = checkNW(p, new int[] {i, j});
				sw = checkSW(p, new int[] {i, j});
				ne = checkNE(p, new int[] {i, j});
				se = checkSE(p, new int[] {i, j});

				threat += (n + s < chainlength) ? (n + s) : 0;
				threat += (e + w < chainlength) ? (e + w) : 0;
				threat += (nw + se < chainlength) ? (nw + se) : 0;
				threat += (ne + sw < chainlength) ? (ne + sw) : 0;
				max = (threat > max) ? threat : max;

				//opponent threat
				threat = 0;
				o_n = checkNorth(o, new int[] {i, j});
				o_s = checkSouth(o, new int[] {i, j});
				o_e = checkEast(o, new int[] {i, j});
				o_w = checkWest(o, new int[] {i, j});
				o_nw = checkNW(o, new int[] {i, j});
				o_sw = checkSW(o, new int[] {i, j});
				o_ne = checkNE(o, new int[] {i, j});
				o_se = checkSE(o, new int[] {i, j});

				threat += (o_n + o_s < chainlength) ? (o_n + o_s) : 0;
				threat += (o_e + o_w < chainlength) ? (o_e + o_w) : 0;
				threat += (o_nw + o_se < chainlength) ? (o_nw + o_se) : 0;
				threat += (o_ne + o_sw < chainlength) ? (o_ne + o_sw) : 0;
				opp = (threat > opp) ? threat : opp;

			}
		}
		return max;
	}

}
