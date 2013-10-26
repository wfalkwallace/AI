import java.util.ArrayList;
import java.util.Hashtable;


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
	
	private State parent;
	private Hashtable<State, Character> children;

	public State (char[][] map, int x, int y) {
		parent = null;
		children = new Hashtable<State, Character>();
		
		this.level = map;
		this.x = x;
		this.y = y;
		
		setStateString();
	}

	public State (State s, char d) {

		parent = s;
		children = new Hashtable<State, Character>();
		//make the move
		level = computeState(s.getState(), d, s.getX(), s.getY());
		//get new x and y
		switch (d) {
		case 'u': 
			x = s.getX() - 1;
			y = s.getY();
			break;
		case 'd': 
			x = s.getX() + 1;
			y = s.getY();
			break;
		case 'l': 
			x = s.getX();
			y = s.getY() - 1;
			break;
		case 'r': 
			x = s.getX();
			y = s.getY() + 1;
			break;	
		}
			
		setStateString();
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

	public String setStateString() {
		String tmp = "";
		for(char[] row : level){
			for(char c : row){
				tmp += c;
			}
		}
		statestring = tmp;
		return statestring;
	}
	
	public String getStateString() {
		return statestring;
	}
	
	public State getParent() {
		return parent;
	}
	
	public void addChild(State ch, char dir) {
		children.put(ch, dir);
	}
	
	public Hashtable<State, Character> getChildren() {
		return children;
	}
	
	public String getPath() {
		if(parent == null)
			return "";
		return parent.getPath() + parent.getChildren().get(this);
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
	
	private char[][] computeState(char[][] oldlevel, char mv, int x, int y) {
		switch (mv) {
		//if move is up
		case 'u': 
			//and the spot youre moving to is open floorspace
			if(oldlevel[x - 1][y] == ' ')
				//move the player into the space
				oldlevel[x - 1][y] = '@';
			//and the spot youre moving to is an empty goal
			if(oldlevel[x - 1][y] == '.')
				//move the player onto the goal
				oldlevel[x - 1][y] = '+';
			//and the spot youre moving to is a box-on-floor
			if(oldlevel[x - 1][y] == '$') {
				//and there's a space in front of it,
				if(oldlevel[x - 2][y] == ' '){
					//make that space a box on floor
					oldlevel[x - 2][y] = '$';
					//and move the player into the old box-space
					oldlevel[x - 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					oldlevel[x - 2][y] = '*';
					//and move the player into the old box-space
					oldlevel[x - 1][y] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(oldlevel[x][y] == '@')
				//leave the space
				oldlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				oldlevel[x][y] = '.';
			break;	
		case 'd': 
			//and the spot youre moving to is open floorspace
			if(oldlevel[x + 1][y] == ' ')
				//move the player into the space
				oldlevel[x + 1][y] = '@';
			//and the spot youre moving to is an empty goal
			if(oldlevel[x + 1][y] == '.')
				//move the player onto the goal
				oldlevel[x + 1][y] = '+';
			//and the spot youre moving to is a box-on-floor
			if(oldlevel[x + 1][y] == '$') {
				//and there's a space in front of it,
				if(oldlevel[x + 2][y] == ' '){
					//make that space a box on floor
					oldlevel[x + 2][y] = '$';
					//and move the player into the old box-space
					oldlevel[x + 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					oldlevel[x + 2][y] = '*';
					//and move the player into the old box-space
					oldlevel[x + 1][y] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(oldlevel[x][y] == '@')
				//leave the space
				oldlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				oldlevel[x][y] = '.';
			break;	
		case 'l': 
			//and the spot youre moving to is open floorspace
			if(oldlevel[x][y - 1] == ' ')
				//move the player into the space
				oldlevel[x][y - 1] = '@';
			//and the spot youre moving to is an empty goal
			if(oldlevel[x][y - 1] == '.')
				//move the player onto the goal
				oldlevel[x][y - 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(oldlevel[x][y - 1] == '$') {
				//and there's a space in front of it,
				if(oldlevel[x][y - 2] == ' '){
					//make that space a box on floor
					oldlevel[x][y - 2] = '$';
					//and move the player into the old box-space
					oldlevel[x][y - 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					oldlevel[x][y - 2] = '*';
					//and move the player into the old box-space
					oldlevel[x][y - 1] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(oldlevel[x][y] == '@')
				//leave the space
				oldlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				oldlevel[x][y] = '.';
			break;	
		case 'r': 
			//and the spot youre moving to is open floorspace
			if(oldlevel[x][y + 1] == ' ')
				//move the player into the space
				oldlevel[x][y + 1] = '@';
			//and the spot youre moving to is an empty goal
			if(oldlevel[x][y + 1] == '.')
				//move the player onto the goal
				oldlevel[x][y + 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(oldlevel[x][y + 1] == '$') {
				//and there's a space in front of it,
				if(oldlevel[x][y + 2] == ' '){
					//make that space a box on floor
					oldlevel[x][y + 2] = '$';
					//and move the player into the old box-space
					oldlevel[x][y + 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					oldlevel[x][y + 2] = '*';
					//and move the player into the old box-space
					oldlevel[x][y + 1] = '@';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(oldlevel[x][y] == '@')
				//leave the space
				oldlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				oldlevel[x][y] = '.';
			break;
		}
		return oldlevel;
	}

}
