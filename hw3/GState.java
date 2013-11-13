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
	}

	public GState(GState par, char dir) {
		parent = par;
		children = new ArrayList<GState>();
		boardsize = par.getBoardsize();
		chainlength = par.getChainlength();
		for(int i = 0; i < par.getBoardsize(); i++)
			for(int j = 0; j < par.getBoardsize(); j++)
				board[i][j] = par.getBoard()[i][j];

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
		for(char[] row : board) {
			for(char c : row) {
				System.out.println(c);
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
	
	

}
