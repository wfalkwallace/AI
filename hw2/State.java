import java.io.FileWriter;
import java.io.IOException;
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
		char[][] tmplevel = computeState(s.getState(), d, s.getX(), s.getY());
		level = new char[tmplevel.length][];
		for(int i = 0; i < tmplevel.length; i++){
			level[i] = new char[tmplevel[i].length];
			for(int j = 0; j < tmplevel[i].length; j++)
				level[i][j]=tmplevel[i][j];
		}
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

	public void printState(int outputStream) {
		if(outputStream == 0) {
			for(char[] row : level){
				for(char c : row){
					System.out.print(c);
				}
				System.out.println();
			}
			System.out.println();
		}
		else{

			try {
				FileWriter fw = new FileWriter("log.txt", true);


				for(char[] row : level){
					for(char c : row){
						fw.write(c);
					}
					fw.write('\n');
				}
				fw.write('\n');
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void print(String line, int outputStream) {
		if(outputStream == 0)
			System.out.println(line);
		else {
			try {
				FileWriter fw = new FileWriter("log.txt", true);
				fw.write(line);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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


	//	# (hash) Wall
	//	  (space) open floor
	//	. (period) Empty goal
	//	@ (at) Player on floor
	//	+ (plus) Player on goal 
	//	$ (dollar) Box on floor
	//	* (asterisk) Box on goal

	private boolean isUpValid() {
		//there's always an up, because I'll check later 
		//to make sure you dont move into a wall					
		char up = level[x - 1][y];;
		//if youre below a wall
		if(up == '#') 
			//you cant move up
			return false;
		//if you cna move up, set the up variable and check open space
		if(up == ' ' || up == '.') 
			return true;
		//lets check if upup exists: if the row 2 above is
		//long enough to support this column, then carry on checking
		if(level[x - 2].length <= y)
			//if it's not, it's not an open space/goal, and there isn't 
			//a space two rows above to push the box into
			return false;
		//now that we know there is a two rows up in this column,
		char up2 = level[x - 2][y];
		//and check if the space two rows up can accept a box (ie. its an empty space or goal)
		if( (up == '$' || up == '*') && (up2 == ' ' || up2 == '.') )
			return true;
		//backup here shouldnt ever be reached or is maybe a really odd deadlock?
		// might as well default to false
		return false;
	}

	private boolean isDownValid() {
		//there's always a down, because I'll check later 
		//to make sure you dont move into a wall					
		char down = level[x + 1][y];;
		//if youre above a wall
		if(down == '#') 
			//you cant move down
			return false;
		//if you can move down, check for open space
		if(down == ' ' || down == '.') 
			return true;
		//lets check if downdown exists: if the row 2 below is
		//long enough to support this column, then carry on checking
		if(level[x + 2].length <= y)
			//if it's not, it's not an open space/goal, and there isn't 
			//a space two rows below to push the box into
			return false;
		//now that we know there is a two rows down in this column,
		char down2 = level[x + 2][y];
		//and check if the space two rows down can accept a box (ie. its an empty space or goal)
		if( (down == '$' || down == '*') && (down2 == ' ' || down2 == '.') )
			return true;
		//backup here shouldnt ever be reached or is maybe a really odd deadlock?
		// might as well default to false
		return false;
	}

	private boolean isLeftValid() {
		//there's always a left, because I'll check later 
		//to make sure you dont move into a wall					
		char left = level[x][y - 1];;
		//if youre right of a wall
		if(left == '#') 
			//you cant move left
			return false;
		//if you can move left, check for open space
		if(left == ' ' || left == '.') 
			return true;
		//lets check if leftleft exists: if the column is >1, then carry on checking
		if(y > 1)
			//if it's not, it's not an open space/goal, and there isn't 
			//a space two rows right to push the box into
			return false;
		//now that we know there is a two rows down in this column,
		char left2 = level[x][y - 2];
		//and check if the space two rows down can accept a box (ie. its an empty space or goal)
		if( (left == '$' || left == '*') && (left2 == ' ' || left2 == '.') )
			return true;
		//backup here shouldnt ever be reached or is maybe a really odd deadlock?
		// might as well default to false
		return false;
	}

	private boolean isRightValid() {
		//there's always a right, because I'll check later 
		//to make sure you dont move into a wall					
		char right = level[x][y + 1];;
		//if youre left of a wall
		if(right == '#') 
			//you cant move right
			return false;
		//if you can move right, check for open space
		if(right == ' ' || right == '.') 
			return true;
		//lets check if rightright exists: if the row is at least 2 longer, then carry on checking
		if(level[x].length <= y + 2)
			//if it's not, it's not an open space/goal, and there isn't 
			//a space two rows right to push the box into
			return false;
		//now that we know there is a two rows down in this column,
		char right2 = level[x][y + 2];
		//and check if the space two rows down can accept a box (ie. its an empty space or goal)
		if( (right == '$' || right == '*') && (right2 == ' ' || right2 == '.') )
			return true;
		//backup here shouldnt ever be reached or is maybe a really odd deadlock?
		// might as well default to false
		return false;
	}


	public ArrayList<Character> getValidMoves() {

		ArrayList<Character> moves = new ArrayList<Character>();

		if(isUpValid()) 
			moves.add('u');

		if(isDownValid()) 
			moves.add('d');

		if(isLeftValid()) 
			moves.add('l');

		if(isRightValid()) 
			moves.add('r');

		return moves;
	}

	private char[][] computeState(char[][] oldlevel, char mv, int x, int y) {
		char[][] newlevel = new char[oldlevel.length][];

		for(int i = 0; i < oldlevel.length; i++){
			newlevel[i] = new char[oldlevel[i].length];
			for(int j = 0; j < oldlevel[i].length; j++)
				newlevel[i][j]=oldlevel[i][j];
		}

		switch (mv) {
		//if move is up
		case 'u': 
			//the spot youre moving to is open floorspace
			if(newlevel[x - 1][y] == ' ')
				//move the player into the space
				newlevel[x - 1][y] = '@';
			//the spot youre moving to is an empty goal
			if(newlevel[x - 1][y] == '.')
				//move the player onto the goal
				newlevel[x - 1][y] = '+';
			//the spot youre moving to is a box-on-floor
			if(newlevel[x - 1][y] == '$') {
				//and there's a space in front of it,
				if(newlevel[x - 2][y] == ' '){
					//make that space a box on floor
					newlevel[x - 2][y] = '$';
					//and move the player into the old box-space
					newlevel[x - 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x - 2][y] = '*';
					//and move the player into the old box-space
					newlevel[x - 1][y] = '@';
				}	
			}
			//the spot youre moving to is a box-on-goal
			if(newlevel[x - 1][y] == '*') {
				//and there's a space in front of it,
				if(newlevel[x - 2][y] == ' '){
					//make that space a box on floor
					newlevel[x - 2][y] = '$';
					//and move the player into the old box-space
					newlevel[x - 1][y] = '+';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x - 2][y] = '*';
					//and move the player into the old box-space
					newlevel[x - 1][y] = '+';
				}	
			}
			//now that we've moved the player and the box (if there was one),
			//lets remove his tail
			//if he was on empty space,
			if(newlevel[x][y] == '@')
				//leave the space
				newlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				newlevel[x][y] = '.';
			break;	
		case 'd': 
			//and the spot youre moving to is open floorspace
			if(newlevel[x + 1][y] == ' ')
				//move the player into the space
				newlevel[x + 1][y] = '@';
			//and the spot youre moving to is an empty goal
			if(newlevel[x + 1][y] == '.')
				//move the player onto the goal
				newlevel[x + 1][y] = '+';
			//and the spot youre moving to is a box-on-floor
			if(newlevel[x + 1][y] == '$') {
				//and there's a space in front of it,
				if(newlevel[x + 2][y] == ' '){
					//make that space a box on floor
					newlevel[x + 2][y] = '$';
					//and move the player into the old box-space
					newlevel[x + 1][y] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x + 2][y] = '*';
					//and move the player into the old box-space
					newlevel[x + 1][y] = '@';
				}	
			}
			//and the spot youre moving to is a box-on-goal
			if(newlevel[x + 1][y] == '*') {
				//and there's a space in front of it,
				if(newlevel[x + 2][y] == ' '){
					//make that space a box on floor
					newlevel[x + 2][y] = '$';
					//and move the player into the old box-space
					newlevel[x + 1][y] = '+';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x + 2][y] = '*';
					//and move the player into the old box-space
					newlevel[x + 1][y] = '+';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(newlevel[x][y] == '@')
				//leave the space
				newlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				newlevel[x][y] = '.';
			break;	
		case 'l': 
			//and the spot youre moving to is open floorspace
			if(newlevel[x][y - 1] == ' ')
				//move the player into the space
				newlevel[x][y - 1] = '@';
			//and the spot youre moving to is an empty goal
			if(newlevel[x][y - 1] == '.')
				//move the player onto the goal
				newlevel[x][y - 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(newlevel[x][y - 1] == '$') {
				//and there's a space in front of it,
				if(newlevel[x][y - 2] == ' '){
					//make that space a box on floor
					newlevel[x][y - 2] = '$';
					//and move the player into the old box-space
					newlevel[x][y - 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x][y - 2] = '*';
					//and move the player into the old box-space
					newlevel[x][y - 1] = '@';
				}	
			}
			//and the spot youre moving to is a box-on-goal
			if(newlevel[x][y - 1] == '*') {
				//and there's a space in front of it,
				if(newlevel[x][y - 2] == ' '){
					//make that space a box on floor
					newlevel[x][y - 2] = '$';
					//and move the player into the old box-space
					newlevel[x][y - 1] = '+';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x][y - 2] = '*';
					//and move the player into the old box-space
					newlevel[x][y - 1] = '+';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(newlevel[x][y] == '@')
				//leave the space
				newlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				newlevel[x][y] = '.';
			break;	
		case 'r': 
			//and the spot youre moving to is open floorspace
			if(newlevel[x][y + 1] == ' ')
				//move the player into the space
				newlevel[x][y + 1] = '@';
			//and the spot youre moving to is an empty goal
			if(newlevel[x][y + 1] == '.')
				//move the player onto the goal
				newlevel[x][y + 1] = '+';
			//and the spot youre moving to is a box-on-floor
			if(newlevel[x][y + 1] == '$') {
				//and there's a space in front of it,
				if(newlevel[x][y + 2] == ' '){
					//make that space a box on floor
					newlevel[x][y + 2] = '$';
					//and move the player into the old box-space
					newlevel[x][y + 1] = '@';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x][y + 2] = '*';
					//and move the player into the old box-space
					newlevel[x][y + 1] = '@';
				}	
			}
			//and the spot youre moving to is a box-on-goal
			if(newlevel[x][y + 1] == '*') {
				//and there's a space in front of it,
				if(newlevel[x][y + 2] == ' '){
					//make that space a box on floor
					newlevel[x][y + 2] = '$';
					//and move the player into the old box-space
					newlevel[x][y + 1] = '+';
				}
				//if it's a goal ahead of the box
				else{
					//make the goal a box-on-goal
					newlevel[x][y + 2] = '*';
					//and move the player into the old box-space
					newlevel[x][y + 1] = '+';
				}	
			}
			//now that we've moved the player and the box, if there was one
			//lets remove his tail
			//if he was on empty space,
			if(newlevel[x][y] == '@')
				//leave the space
				newlevel[x][y] = ' ';
			//otherwise he was on a goal (+),
			else
				//keep the goal
				newlevel[x][y] = '.';
			break;
		}
		return newlevel;
	}

}
