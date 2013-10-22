import java.util.ArrayList;

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

	public State (char[][] map) {
		this.map = map;
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void printState() {
		for(char[] row : map){
			for(char c : row){
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public int[] getLocation() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; i++) {
				if(map[i][j] == '@' || map[i][j] == '+')
					return {i, j};
			}
		}
	}
	
	public char[] getValidMoves() {
		int[] loc = getLocation();
		char[] moves = new char[4];
		if(map[loc[0] + 1][loc[1]] == '.' || 
		   map[loc[0] + 1][loc[1]] == '$' ||
		   map[loc[0] + 1][loc[1]] == '*') 
			moves[0] = 'u';
		else if(map[loc[0] - 1][loc[1]] == '.' || 
		        map[loc[0] - 1][loc[1]] == '$' ||
		        map[loc[0] - 1][loc[1]] == '*') 
			moves[1] = 'd';
		else if(map[loc[0]][loc[1] + 1] == '.' || 
		        map[loc[0]][loc[1] + 1] == '$' ||
		        map[loc[0]][loc[1] + 1] == '*') 
			moves[2] = 'r';
		else if(map[loc[0]][loc[1] - 1] == '.' || 
		        map[loc[0]][loc[1] - 1] == '$' ||
		        map[loc[0]][loc[1] - 1] == '*') 
			moves[3] = 'l';
		
		return moves;
	}

}
