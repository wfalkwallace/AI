
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

	private char[][] map;
	private int x;
	private int y;

	public State (char[][] map, int x, int y) {
		this.map = map;
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public int[] getPlayer() {
		return new int[] {x, y};
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void printState() {
		for(char[] row : map){
			for(char c : row){
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public char[] getValidMoves() {
		char[] moves = new char[4];
		if(map[x + 1][y] == '.' || 
		   map[x + 1][y] == '$' ||
		   map[x + 1][y] == '*') 
			moves[0] = 'u';
		else if(map[x - 1][y] == '.' || 
		        map[x - 1][y] == '$' ||
		        map[x - 1][y] == '*') 
			moves[1] = 'd';
		else if(map[x][y + 1] == '.' || 
		        map[x][y + 1] == '$' ||
		        map[x][y + 1] == '*') 
			moves[2] = 'r';
		else if(map[x][y - 1] == '.' || 
		        map[x][y - 1] == '$' ||
		        map[x][y - 1] == '*') 
			moves[3] = 'l';
		
		return moves;
	}

}
