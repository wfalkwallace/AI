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
		STree tree = new STree(ll.init());
		tree.getRoot().printState();
		tree.BFS();

	}



}
