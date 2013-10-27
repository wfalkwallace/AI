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
		
//		BFS
//		String[] solution = tree.BFS();
//		for(String s : solution)
//			System.out.println(s);
		
//		DFS
//		String[] solution = tree.DFS();
//		for(String s : solution)
//			System.out.println(s);
		
//		UCS
//		String[] solution = tree.UCS();
//		for(String s : solution)
//			System.out.println(s);

	}



}
