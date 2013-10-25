
/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class State {



	//	# (hash) Wall
	//	
	//	. (period) Empty goal
	//	@ (at) Player on floor
	//	+ (plus) Player on goal 
	//	$ (dollar) Box on floor
	//	* (asterisk) Box on goal

	private String statestring;
	private char[][] level;
	private int x;
	private int y;

	public State (char[][] map, int x, int y) {
		this.level = map;
		this.x = x;
		this.y = y;
	}

	public char[][] getState() {
		return level;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void printState() {
		for(char[] row : level){
			for(char c : row){
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}

	public String getStateString() {
		if(statestring != null)
			return statestring;
		statestring = "";
		for(char[] row : level){
			for(char c : row){
				statestring += c;
			}
		}
		return statestring;
	}


    public int hashCode() {
        return statestring.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof State))
            return false;
        return ( ((State) obj).getStateString() == this.getStateString() ) ? true : false;
    }









	public char[] getValidMoves() {
		char[] moves = new char[4];
		if(level[x + 1][y] == '.' || 
				level[x + 1][y] == '$' ||
				level[x + 1][y] == '*') 
			moves[0] = 'u';
		else if(level[x - 1][y] == '.' || 
				level[x - 1][y] == '$' ||
				level[x - 1][y] == '*') 
			moves[1] = 'd';
		else if(level[x][y + 1] == '.' || 
				level[x][y + 1] == '$' ||
				level[x][y + 1] == '*') 
			moves[2] = 'r';
		else if(level[x][y - 1] == '.' || 
				level[x][y - 1] == '$' ||
				level[x][y - 1] == '*') 
			moves[3] = 'l';

		return moves;
	}

}
