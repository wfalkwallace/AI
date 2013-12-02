import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class KB {

	private ArrayList<String> facts;
	private Hashtable<String, String> clauses;
	
	Hashtable<Entry<String, String>, Integer> count;
	Hashtable<String, Boolean> inferred;
	Queue<String> agenda;

	public KB (File kbfile) {
		facts = new ArrayList<String>();
		clauses = new Hashtable<String, String>();
		
		count = new Hashtable<Entry<String, String>, Integer>();
		inferred = new Hashtable<String, Boolean>();
		agenda = new LinkedList<String>();
		
		loadKB(kbfile);
	}

	private void addFact(String f) {
		facts.add(f);
	}

	public ArrayList<String> getFacts() {
		return facts;
	}

	public void addStatement(String s) {
		if ( s.contains("=>") ){
			String sc[] = s.split("=>");
			addClause(sc[0], sc[1]);
		}
		else
			addFact(s);
	}

	private void addClause(String p, String c) {
		clauses.put(p, c);
	}

	public Hashtable<String, String> getClauses() {
		return clauses;
	}


	private void loadKB(File kbfile) {
		Scanner input;
		ArrayList<String> statements = new ArrayList<String>();
		try {
			input = new Scanner(kbfile);
			while(input.hasNextLine())
				statements.add(input.nextLine());
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("The specified file could not be located");
			e.printStackTrace();
		}

		for ( String s : statements ) {
			//TODO check if valid horn clause
			addStatement(s);
		}
	}


	public boolean fc (KB kb, String q) {
		while ( !agenda.isEmpty() ) {
			String p = agenda.poll();
			if ( p.equals(q) )
				return true;
			if ( inferred.get(p) ) {
				inferred.put(p, true);
				for ( Entry<String, String> c : kb.getClauses().entrySet() ) {
					if( c.getKey().contains(p) ) {
						count.put(c, count.get(c) - 1);
						if ( count.get(c) == 0 )
							agenda.add( c.getValue() );
					}
				}
			}	
		}
		return false;
	}






}
