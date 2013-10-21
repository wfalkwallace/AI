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
//
//	 <0 - Wall
//	  0 - empty space
//	  1 - empty goal
//	  2 - player
//	  3 - player on goal
//	  4 - box
//	  5 - box on goal

	private int[][] map;
	private int width;
	private int height;

	public State (int[][] map) {
		this.map = map;
	}
	
}
