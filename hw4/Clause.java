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
	private String sentence;

	Clause(String s) {
		sentence = s;
		premise = new ArrayList<String>();

		if( s.contains("v") )
			convert(s);
		else {
			String splitclause[] = sentence.split("=>");
			conclusion = splitclause[1].trim();

			String splitpremise[] = splitclause[0].split("\\^");

			for ( String sym : splitpremise ){
				premise.add(sym.trim());
			}
		}

		count = premise.size();
	}

	public String print() {
		return sentence;
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

	private void convert(String s) {
		String splitclause[] = s.split("v");
		for(int i = 0; i < splitclause.length; i++ ) {
			if( i == splitclause.length - 1 && conclusion == null){
				conclusion = splitclause[i].trim();
			}
			else {
				if ( splitclause[i].contains("~") ){
					premise.add( splitclause[i].replace('~', ' ').trim() );
				}
				else {
					//TODO: if second positive lit
					conclusion = splitclause[i].trim();
				}
			}
		}

		sentence = "";
		for(int i = 0; i < premise.size() - 1; i++ )
			sentence += premise.get(i) + " ^ ";
		sentence += premise.get(premise.size() - 1) + " ";

		sentence += "=> " + conclusion;

		System.out.println(s + " ---> " + sentence);
	}

	//	public void removeSym(String sym) {
	//		for(String s : premise) {
	//			if (s.contains(sym)) {
	//				premise.remove(s);
	//				return;
	//			}
	//		}
	//	}

}
