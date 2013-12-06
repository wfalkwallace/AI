import java.util.ArrayList;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class Clause {

	private ArrayList<String> premise;
	private String conclusion;
	private int count;

	Clause(String s) {
		String splitclause[] = s.split("=>");
		conclusion = splitclause[1];
		
		String splitpremise[] = s.split(" ");
		for ( String sym : splitpremise ){
			if ( sym.contains("~") )
				sym.replace("~", "");
			premise.add(sym);
		}
		
		count = premise.size();
		
	}


}
