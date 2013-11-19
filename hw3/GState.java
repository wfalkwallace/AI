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
	private int boardsize;
	private int chainlength;
	private char[][] board;
	private char player;
	private String path;
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
		setActions();
	}

	private void init() {
		for(int i = 0; i < getBoardsize(); i++)
			for(int j = 0; j < getBoardsize(); j++)
				board[i][j] = '.';
	}

	public GState getResult(int x, int y) {
		return new GState(this, x, y);
	}
	
	private GState(GState par, int x, int y) {
		parent = par;
		player = (par.getPlayer() == 'x') ? 'o' : 'x';
		boardsize = par.getBoardsize();
		chainlength = par.getChainlength();

		for(int i = 0; i < par.getBoardsize(); i++)
			for(int j = 0; j < par.getBoardsize(); j++)
				board[i][j] = par.getBoard()[i][j];

		board[x][y] = par.getPlayer();

		setStateString();
		setActions();
	}

	/**
	 * @return the boardsize
	 */
	public int getBoardsize() {
		return boardsize;
	}
	
	private char getPlayer() {
		return player;
	}

	private void setActions(){
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
		for(int i = 0; i < boardsize; i++){
			System.out.print("  i");
		}
		System.out.println();

		for(int i = 0; i < boardsize; i++) {
			System.out.print(i + " ");
			for(int j = 0; j < boardsize; j++) {
				System.out.print(board[i][j]);
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
	public boolean isWin(int x, int y, char player) {
		if(checkNorth(x, y, player) + checkSouth(x, y, player) + 1 == chainlength)
			return true;
		else if(checkEast(x, y, player) + checkWest(x, y, player) + 1 == chainlength)
			return true;
		else if(checkNE(x, y, player) + checkSW(x, y, player) + 1 == chainlength)
			return true;
		else if(checkNW(x, y, player) + checkSE(x, y, player) + 1 == chainlength)
			return true;
		else 
			return false;
	}

	private int checkNorth(int x, int y, char player){
		int count = 0;
		for(int i = y; i >= 0; i--){
			if(board[x][i] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSouth(int x, int y, char player){
		int count = 0;
		for(int i = y; i < boardsize; i++){
			if(board[x][i] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkEast(int x, int y, char player){
		int count = 0;
		for(int i = x; i < boardsize; i++){
			if(board[i][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkWest(int x, int y, char player){
		int count = 0;
		for(int i = x; i >= 0; i--){
			if(board[i][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkNW(int x, int y, char player){
		int count = 0;
		for(int i = x, j = y; i >= 0 && j >= 0; i--, j--){
			if(board[x][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkNE(int x, int y, char player){
		int count = 0;
		for(int i = x, j = y; i < boardsize && j >= 0; i++, j--){
			if(board[x][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSW(int x, int y, char player){
		int count = 0;
		for(int i = x, j = y; i >= 0 && j < boardsize; i--, j++){
			if(board[x][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

	private int checkSE(int x, int y, char player){
		int count = 0;
		for(int i = x, j = y; i < boardsize && j < boardsize; i++, j++){
			if(board[x][y] == player)
				count++;
			else
				return count;
		}
		return count;
	}

}
