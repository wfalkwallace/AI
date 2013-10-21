import java.util.ArrayList;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class LevelLoader {

	
	
//	# (hash) Wall
//	. (period) Empty goal
//	@ (at) Player on floor
//	+ (plus) Player on goal $ (dollar) Box on floor
//	* (asterisk) Box on goal
//
//	 < 0 - Wall/out
//	   0 - empty space
//	  10 - goal

	ArrayList<String> stringmap;
	private char[][] levelmap;

	public LevelLoader (File levelsource) {
		
		loadStringMap(levelsource);
		
		
	}
	
	private void loadStringMap(File levelsource) {
		Scanner input = new Scanner(levelsource);
		stringmap = new ArrayList<String>();
		//file should start with a puzzle height
		if(input.hasNextInt()){
			//iterate over that full height/depth
			for(int i = 0; i < input.nextInt(); i++) {
				//check for validity (is height as specified)
				if(input.hasNextLine()){
					//add the row to the stringmap
					stringmap.add(input.nextLine());
				}
				//invalid puzzle; bail
				else {
					System.out.println("That is not a valid puzzle file");
					exit;
				}
			}
		}
		//close the scanner
		input.close();
	}
	
	private void parseStringMap() {
		map = new char[stringmap.size()];
		for(i = 
				
			
				
			}
		}
		
		
		
	}
	
	
	
	
	
}
