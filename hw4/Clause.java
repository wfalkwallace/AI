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
		premise = new ArrayList<String>();
		String splitclause[] = s.split("=>");
		conclusion = splitclause[1].trim();

		String splitpremise[] = splitclause[0].split("\\^");

		for ( String sym : splitpremise ){
			premise.add(sym.trim());
		}
		
		count = premise.size();
	}

	public int getCount() {
		return count;
	}
	
	public void decrCount() {
		count--;
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
