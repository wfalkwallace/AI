import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class KB {

	
	boolean fc (KB kb, String q) {
		Hashtable<String, Integer> count = new Hashtable<String, Integer>();
		Hashtable<String, Boolean> inferred = new Hashtable<String, Boolean>();
		Queue<String> agenda = new LinkedList<String>();
		
		while ( !agenda.isEmpty() ) {
			String p = agenda.poll();
			if ( p.equals(q) )
				return true;
			if ( inferred.get(p) ) {
				inferred.put(p, true);
				// TODO foreach
				// for each clause c in KB where p is in c.PREMISE
				for (  ) {
					count.put(c, count.get(c) - 1);
					if ( count.get(c) == 0 )
						agenda.add(c.conclusion())
				}
			}	
		}
	}
	
	
	
	
	
	
}
