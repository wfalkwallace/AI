import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class KB {

	private ArrayList<Clause> clauses;
	private ArrayList<String> facts;
	private Hashtable<String, Boolean> inferred;
	Queue<String> agenda;

	private ArrayList<String> CNFclauses;
	private ArrayList<String> CNFnew;

	public KB (File kbfile) {
		clauses = new ArrayList<Clause>();
		facts = new ArrayList<String>();
		inferred = new Hashtable<String, Boolean>();
		agenda = new LinkedList<String>();

		loadKB(kbfile);
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

		//for each sentence, add to KB
		for ( String s : statements ) {
			addStatement(s);
		}

		//add facts to agenda and print first steps
		for(String f : facts) {
			agenda.add(f.trim());
			System.out.println(f.trim());
		}


	}

	private void addStatement(String s) {
		//TODO check if valid horn clause/conjunction/negated/??
		if ( s.contains("=>") || s.contains("v") ){
			clauses.add(new Clause(s));
		}
		else
			facts.add(s);
	}

	public boolean fc (String q) {
		while ( !agenda.isEmpty() ) {
			String p = agenda.poll();
			if ( p.trim().equals(q) )
				return true;
			if ( !inferred.containsKey(p) ) {
				inferred.put(p, true);
				//for each clause
				for ( Clause c : clauses ) {
					//for each premise symbol of each clause
					for ( String cp : c.getPremise() ) {
						//for each premise symbol of each clause, if that premise symbol 
						//is p ('contains' to deal with whitespace, etc)
						if( cp.contains(p) ) {
							c.decrCount();
							if ( c.getCount() == 0 ) {
								agenda.add( c.getConclusion() );
								System.out.println(c.print());
							}
						}
					}
				}
			}	
		}
		return false;
	}

	public boolean bc (String q) {

		return false;
	}

	//================================================================================
	//            v CNF STUFF v            ||             ^ HORN STUFF ^          
	//================================================================================

	public KB(File kbfile, String mode) {
		CNFclauses = new ArrayList<String>();
		CNFnew = new ArrayList<String>();

		loadCNFKB(kbfile);
	}

	private void loadCNFKB(File kbfile) {
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

		//for each sentence, add to KB
		for ( String s : statements ) {
			addCNFClause(s);
		}
	}

	private void addCNFClause(String s) {
		String[] splitsentence = s.replaceAll("(", " ").replaceAll(")", " ").split("^");
		for (String subclause : splitsentence)
			CNFclauses.add(subclause);
	}


	public boolean resolution (String q) {
		CNFclauses.add("~"+q);
		ArrayList<String> resolvents;
		while(true) {
			for( String c1 : CNFclauses ) {
				for( String c2 : CNFclauses ) {
					resolvents = resolve(c1, c2);
					for( String c : resolvents ) 
						if( c.length() == 0 )
							return true;
					CNFnew.addAll(resolvents);
				}
			}
			if( CNFclauses.containsAll(CNFnew) )
				return false;
			CNFclauses.addAll(CNFnew);
		}
		return false;
	}

	public ArrayList<String> resolve(String c1, String c2) {
		ArrayList<String> resolvents;
		
		

		return resolvents;
	}

}
