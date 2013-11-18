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
	private ArrayList<GState> children;
	private int boardsize;
	private int chainlength;
	private char[][] board;

	private String statestring;
	private String o_winstring;	
	private String x_winstring;	
	private int cost;


	public GState(int size, int length) {
		parent = null;
		children = new ArrayList<GState>();
		boardsize = size;
		chainlength = length;
		board = new char[boardsize][boardsize];
		init();
	}

	private void init() {
		for(int i = 0; i < getBoardsize(); i++)
			for(int j = 0; j < getBoardsize(); j++)
				board[i][j] = '.';
		setStateString();
		setWinStrings();
	}

	public GState(GState par, char player, int x, int y) {
		parent = par;
		children = new ArrayList<GState>();
		boardsize = par.getBoardsize();
		chainlength = par.getChainlength();

		for(int i = 0; i < par.getBoardsize(); i++)
			for(int j = 0; j < par.getBoardsize(); j++)
				board[i][j] = par.getBoard()[i][j];

		board[x][y] = player;

		setStateString();
	}

	/**
	 * @return the boardsize
	 */
	public int getBoardsize() {
		return boardsize;
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

	public ArrayList<GState> getChildren() {
		return children;
	}

	public void addChild(GState child) {
		children.add(child);
	}

	private void setStateString() {
		statestring = "";
		for(char[] row : board)
			for(char c : row)
				statestring += c;
	}

	private void setWinStrings() {
		o_winstring = "";
		x_winstring = "";
		for(int i = 0; i < chainlength; i++){
			o_winstring += 'o';
			x_winstring += 'x';
		}
	}

	public String getStateString() {
		return statestring;
	}

	public boolean isDraw() {
		return statestring.contains(".");
	}

	public boolean isWin(int x, int y, char player) {
		String winstring = (player == 'x') ? x_winstring : o_winstring;
		int row = 0, col = 0;
		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				//check the rows
				if(board[i][j] == player)
					//if you see a player, add to the chain count
					row++;
				else
					//if you see an opponent, check to see if you are at the chainlength
					if(row == chainlength)
						return true;
					else
						//if you see an opponent and dont have a win, reset the chain counter
						row = 0;
				//check the columns
				if(board[j][i] == player)
					//if you see a player, add to the chain count
					col++;
				else
					//if you see an opponent, check to see if you are at the chainlength
					if(col == chainlength)
						return true;
					else
						//if you see an opponent and dont have a win, reset the chain counter
						col = 0;
			}
			//when you get to the end of a row/col, check if you've got the chainlength
			if(row == chainlength || col == chainlength)
				return true;
			//if not reset the chain count for the next row/col
			row = col = 0;
		}
		//if you haven't found a win after all that...
		return false;
	}
}
