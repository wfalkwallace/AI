import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class LevelLoader {



	//	# (hash) Wall
	//	_ (space) empty space
	//	. (period) Empty goal
	//	@ (at) Player on floor
	//	+ (plus) Player on goal 
	//  $ (dollar) Box on floor
	//	* (asterisk) Box on goal


	ArrayList<String> stringmap;
	private char[][] levelmap;
	int [] loc;

	public LevelLoader (File levelsource) {

		loadStringMap(levelsource);
		loc = getPlayerLocation();
		printLevel();
		parseStringMap();

	}

	public char[][] getLevel() {

		return levelmap;

	}

	private void loadStringMap(File levelsource) {
		Scanner input;
		try {
			input = new Scanner(levelsource);
			stringmap = new ArrayList<String>();
			//file should start with a puzzle height
			if(input.hasNextInt()){
				int height = Integer.parseInt(input.nextLine());
				//System.out.println(height);
				//iterate over that full height/depth
				for(int i = 0; i < height; i++) {
					//check for validity (is height as specified)
					if(input.hasNextLine()){
						//add the row to the stringmap
						stringmap.add(input.nextLine());
					}
					//invalid puzzle; bail
					else {
						System.out.println("That is not a valid puzzle file");
					}
				}
			}
			//close the scanner
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The specified file could not be located");
			e.printStackTrace();
		}
	}

	private void parseStringMap() {
		int height = stringmap.size();
		levelmap = new char[height][];
		for(int i = 0; i < height; i++) {
			levelmap[i] = stringmap.get(i).toCharArray();
			for(char c : levelmap[i]) {
				if(c == '@') {
					c = ' ';
					System.out.println("HERE");
				}
				else if(c == '+')
					c = '.';
			}
		}
	}

	private void printLevel() {
		for(char[] row : levelmap){
			for(char c : row){
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	private int[] getPlayerLocation() {
		for(int i = 0; i < levelmap.length; i++) {
			for(int j = 0; j < levelmap[i].length; j++) {
				if(levelmap[i][j] == '@' || levelmap[i][j] == '+')
					return new int[] {i, j};
			}
		}
		//never reached, ignoring playerless game entry for now.
		return new int[2];
	}
	
	public int[] getPlayer() {
		return loc;
	}

	public int getPlayer(int i) {
		return loc[i];
	}

}
