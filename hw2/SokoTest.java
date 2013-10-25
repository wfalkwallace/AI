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

		File lv = new File("test.txt");
		LevelLoader ll = new LevelLoader(lv);		
		State st = new State(ll.getLevel(), ll.getPlayer(0), ll.getPlayer(1));
		System.out.println('\n');
		st.printState();
		System.out.println(ll.getPlayer(0) + ", " + ll.getPlayer(1));
		
		
	}

}
