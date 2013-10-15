import java.util.ArrayList;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class Puzzle {

	
	
//	# (hash) Wall
//	. (period) Empty goal
//	@ (at) Player on floor
//	+ (plus) Player on goal $ (dollar) Box on floor
//	* (asterisk) Box on goal
//
//	 < 0 - Wall/out
//	   0 - empty space
//	  10 - goal

private int[][] map;
private int width;
private int height;

public Puzzle () {
	map = new int[width][height];

}
	
ArrayList<String> inputRows;
}
