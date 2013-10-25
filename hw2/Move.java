/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class Move {

	private State start;
	private State end;
	private char dir;

	public Move(State i, State f, char d) {
		start = i;
		end = f;
		dir = d;
	}

	public State getStart() {
		return start;
	}

	public void setStart(State i) {
		start = i;
	}

	public State getEnd() {
		return end;
	}

	public void setEnd(State f) {
		end = f;
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char d) {
		dir = d;
	}
	
	
	
	
	
	
	
	
	
}
