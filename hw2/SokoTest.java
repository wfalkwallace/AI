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
		State root = ll.init();
		root.printState();

		SGraph graph = new SGraph();
		graph.addV(root);





		for(char c : root.getValidMoves()){
			System.out.print(c + ", ");
		}

		//		System.out.println(st.getX() + ", " + st.getY());


	}

	private void expand(SGraph g, State st) {
		if(!g.contains(st)){
			for (char c : st.getValidMoves()) {
				State tmp = new State(st, c);
				if(!g.contains(tmp))
					g.addV(tmp);
				g.addE(st, tmp, c);
			}
		}
	}

}
