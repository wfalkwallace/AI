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

	Clause(String s) {		
		String splitclause[] = s.split("=>");
		conclusion = splitclause[1];

		String splitpremise[] = s.split("^");

		for ( String sym : splitpremise ){
			premise.add(sym);
		}
	}

	public int getCount() {
		return premise.size();
	}

	public ArrayList<String> getPremise() {
		return premise;
	}

	public String getConclusion() {
		return conclusion;
	}
	
	public void removeSym(String sym) {
		for(String s : premise) {
			if (s.contains(sym)) {
				premise.remove(s);
				return;
			}
		}
	}
	
}
