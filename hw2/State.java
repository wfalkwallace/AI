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
	//	  (space) open floor
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
		getStateString();
	}

	public State (State s, char d) {

		level = s.getState();
		x = s.getX();
		y = s.getY();

		switch (d) {
		//if move is up
		case 'u': 
			//and the spot youre moving to is open floorspace
			if(level[x - 1][y] == ' ')
				//move the player into the space
				level[x - 1][y] = '@';
			//and the spot youre moving to is an empty goal
			if(level[x - 1][y] == '.')
				//move the player onto the goal
				level[x - 1][y] = '+';
			//and the spot youre moving to is a box-on-floor
			if(level[x - 1][y] == '$') {
				//and there's a space in front of it,
				if(level[x - 2][y] == ' '){
					//make that space a box on floor
					level[x - 2][y] = '$';
					//and move the player into the old box-space
					level[x - 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					level[x - 2][y] = '*';
					//and move the player into the old box-space
					level[x - 1][y] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(level[x][y] == '@')
				//leave the space
				level[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				level[x][y] = '.';
			break;	
		case 'd': 
			//and the spot youre moving to is open floorspace
			if(level[x + 1][y] == ' ')
				//move the player into the space
				level[x + 1][y] = '@';
			//and the spot youre moving to is an empty goal
			if(level[x + 1][y] == '.')
				//move the player onto the goal
				level[x + 1][y] = '+';
			//and the spot youre moving to is a box-on-floor
			if(level[x + 1][y] == '$') {
				//and there's a space in front of it,
				if(level[x + 2][y] == ' '){
					//make that space a box on floor
					level[x + 2][y] = '$';
					//and move the player into the old box-space
					level[x + 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					level[x + 2][y] = '*';
					//and move the player into the old box-space
					level[x + 1][y] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(level[x][y] == '@')
				//leave the space
				level[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				level[x][y] = '.';
			break;	
		case 'l': 
			//and the spot youre moving to is open floorspace
			if(level[x][y - 1] == ' ')
				//move the player into the space
				level[x][y - 1] = '@';
			//and the spot youre moving to is an empty goal
			if(level[x][y - 1] == '.')
				//move the player onto the goal
				level[x][y - 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(level[x][y - 1] == '$') {
				//and there's a space in front of it,
				if(level[x][y - 2] == ' '){
					//make that space a box on floor
					level[x][y - 2] = '$';
					//and move the player into the old box-space
					level[x][y - 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					level[x][y - 2] = '*';
					//and move the player into the old box-space
					level[x][y - 1] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(level[x][y] == '@')
				//leave the space
				level[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				level[x][y] = '.';
			break;	
		case 'r': 
			//and the spot youre moving to is open floorspace
			if(level[x][y + 1] == ' ')
				//move the player into the space
				level[x][y + 1] = '@';
			//and the spot youre moving to is an empty goal
			if(level[x][y + 1] == '.')
				//move the player onto the goal
				level[x][y + 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(level[x][y + 1] == '$') {
				//and there's a space in front of it,
				if(level[x][y + 2] == ' '){
					//make that space a box on floor
					level[x][y + 2] = '$';
					//and move the player into the old box-space
					level[x][y + 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					level[x][y + 2] = '*';
					//and move the player into the old box-space
					level[x][y + 1] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(level[x][y] == '@')
				//leave the space
				level[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				level[x][y] = '.';
			break;
		}

		getStateString();
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
		// this would prevent re-compute, maybe better for caching, 
		// but not helpful for state(state) constructor
		//		if(statestring != null)
		//			return statestring;
		statestring = "";
		for(char[] row : level){
			for(char c : row){
				statestring += c;
			}
		}
		return statestring;
	}
	
	public boolean isGoal() {
		for(char[] row : level){
			for(char c : row){
				if(c == '.' || c == '+')
					return false;
			}
		}
		//if there are no empty or player-covered goals, success
		// assume validity of other checks and balances 
		return true;
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


	//	# (hash) Wall
	//	  (space) open floor
	//	. (period) Empty goal
	//	@ (at) Player on floor
	//	+ (plus) Player on goal 
	//	$ (dollar) Box on floor
	//	* (asterisk) Box on goal
	public ArrayList<Character> getValidMoves() {
		char up = level[x - 1][y];
		char upup = level[x - 2][y];
		char down = level[x + 1][y];
		char downdown = level[x + 2][y];
		char left = level[x][y - 1];
		char leftleft = level[x][y - 2];
		char right = level[x][y + 1];
		char rightright = level[x][y + 2];

		ArrayList<Character> moves = new ArrayList<Character>();

		if(up == ' ' || up == '.') 
			moves.add('u');
		else if( (up == '$' || up == '*') && (upup == ' ' || upup == '.') )
			moves.add('u');

		if(down == ' ' || down == '.') 
			moves.add('d');
		else if( (down == '$' || down == '*') && (downdown == ' ' || downdown == '.') )
			moves.add('d');

		if(left == ' ' || left == '.') 
			moves.add('l');
		else if( (left == '$' || left == '*') && (leftleft == ' ' || leftleft == '.') )
			moves.add('l');

		if(right == ' ' || right == '.') 
			moves.add('r');
		else if( (right == '$' || right == '*') && (rightright == ' ' || rightright == '.') )
			moves.add('r');

		return moves;
	}

}
