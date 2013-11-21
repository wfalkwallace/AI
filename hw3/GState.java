import java.util.ArrayList;

/**
 * 
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
		for(int i = 0; i < boardsize; i++)
			for(int j = 0; j < boardsize; j++)
				if(board[i][j] == '.')
					if( (j + 1  < boardsize && board[i][j+1] == player) || 
							(j - 1  > 0 && board[i][j-1] == player) || 
							(i + 1  < boardsize && board[i+1][j] == player) || 
							(i - 1  > 0 && board[i-1][j] == player) || 
							(i + 1  < boardsize && j + 1  < boardsize && board[i+1][j+1] == player) || 
							(i - 1  > 0 && j - 1  > 0 && board[i-1][j-1] == player) || 
							(i - 1  > 0 && j + 1  < boardsize && board[i-1][j+1] == player) || 
							(i + 1  < boardsize && j - 1  > 0 && board[i+1][j-1] == player))
						actions.add(new int[] {i, j});
		//					else
		//						actions.add(new int[] {i, j});
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
		if(isWin())
			return 1000000;
		if(isDraw())
			return 0;
		int u = 0, v = 0, w = 0, x = 0;
		int max = 0, opp = 0;
		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				//check the rows
				if(board[i][j] == p)
					u++;
				else {
					max = (max < u && u <= chainlength) ? u : max;
					u = 0;
				}
				//check the columns
				if(board[j][i] == p)
					v++;
				else {
					max = (max < v && v <= chainlength) ? v : max;
					v = 0;
				}

				//account for opponent's near win
				//check the rows
				if(board[i][j] == (p == 'x' ? 'o' : 'x'))
					w++;
				else {
					opp = (opp < w && w <= chainlength) ? w : opp;
					w = 0;
				}
				//check the columns
				if(board[j][i] == (p == 'x' ? 'o' : 'x'))
					x++;
				else {
					opp = (opp < x && x <= chainlength) ? x : opp;
					x = 0;
				}

			}
		}
		return max - opp;
	}

}
