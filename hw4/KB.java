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

	}

	private void addStatement(String s) {
		//TODO check if valid horn clause/conjunction/negated/??
		if ( s.contains("=>") || s.contains("v") ){
			clauses.add(new Clause(s));
		}
		else
			facts.add(s.trim());
	}

	public boolean fc (String q) {

		//add facts to agenda and print first steps
		for(String f : facts) {
			agenda.add(f);
			System.out.println(f.trim());
		}

		while ( !agenda.isEmpty() ) {
			String p = agenda.poll().trim();
			if ( p.equals(q) )
				return true;
			if ( !inferred.containsKey(p) ) {
				inferred.put(p, true);
				//for each clause
				for ( Clause c : clauses ) {
					//for each premise symbol of each clause
					for ( String cp : c.getPremise() ) {
						//for each premise symbol of each clause, if that premise symbol 
						//is p ('contains' to deal with whitespace, etc)
						if( cp.trim().contains(p) ) {
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

	//	public boolean bc0 (String q) {
	//		if ( facts.contains(q) )
	//			return true;
	//
	//		agenda.add(q);
	//
	//		while ( !agenda.isEmpty() ) {			
	//			String p = agenda.poll();
	//			if ( facts.contains(p) ) { 
	//				System.out.println(p);
	//				facts.remove(p);
	//			}
	//			if ( !inferred.containsKey(p) ) {
	//				inferred.put(p, true);
	//				//for each clause
	//				for ( Clause c : clauses ) {
	//					//if that symbol's conclusion is p ('contains' to deal with whitespace, etc)
	//					if( c.getConclusion().contains(p) ) {
	//						agenda.addAll( c.getPremise() );
	//						System.out.println(c.print());
	//					}
	//				}
	//			}
	//		}
	//		if( agenda.size() == 0 )
	//			return true;
	//		return false;
	//
	//	}


	public boolean bc (String q) {

		//		boolean bail = false;

		//if H matches an assertion in working memory then true
		if( facts.contains(q) ){
			System.out.println(q);
			return true;
		}

		//for every rule R with a consequent that matches H
		for( Clause c : clauses ){
			if( c.getConclusion().equals(q) ){
				//for each of its premises
				for( String p : c.getPremise() ){
					//dont check twice
					if( !inferred.containsKey(p) ) {
						inferred.put(p, true);
						if ( bc(p) )
							System.out.println(c.print());
					}
				}
				if( inferred.keySet().containsAll(c.getPremise()) )
					return true;
			}
		}
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
	}

	public ArrayList<String> resolve(String c1, String c2) {
		ArrayList<String> resolvents = new ArrayList<String>();
		ArrayList<String> similars = new ArrayList<String>();
		String c1syms[] = c1.split("v");
		String c2syms[] = c2.split("v");

		for ( String c1sym : c1syms ) {
			for ( String c2sym : c2syms ) {
				if ( c1sym.replace('~', ' ').trim().equals( c2sym.replace('~', ' ').trim() ) ) {
					similars.add(c1sym.trim());
					similars.add(c2sym.trim());
				}
			}
		}
		for ( String c1sym : c1syms ) {
			if(similars.contains(c1sym.trim()))
				resolvents.add(c1sym.trim());
		}
		for ( String c2sym : c2syms ) {
			if(similars.contains(c2sym.trim()))
				resolvents.add(c2sym.trim());
		}

		return resolvents;
	}

}
