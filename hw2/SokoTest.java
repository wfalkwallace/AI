import java.io.File;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class SokoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File lvl_src = new File("test.txt");
		LevelLoader ll = new LevelLoader(lvl_src);		
		State st = ll.init();
		
		st.printState();
//		System.out.println(st.getX() + ", " + st.getY());
		
		
	}

}
