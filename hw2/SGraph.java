import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author wgf2104
 *
 */
public class SGraph {

	private Hashtable<State, ArrayList<Move>> graph;

	public SGraph(){
		graph = new Hashtable<State, ArrayList<Move>>();
	}

	private void addV(State v){
		graph.put(v, new ArrayList<Move>());
	}

	// be weary of cycles! check for them elsewhere and addE in sets of validmoves!
	// ie. check if the v is contained, if it is, its validmoves are there,
	// if not, then add all valid moves.
	public void addE(State v, State e, char d){
		if(graph.containsKey(v)){
			ArrayList<Move> tmpedgelist = graph.get(v);
			tmpedgelist.add(new Move(v, e, d));
			graph.put(v, tmpedgelist);
		}
		else{
			addV(v);
			addE(v, e, d);
		}
	}

	public Hashtable<State, ArrayList<Move>> getGraph() {
		return graph;
	}

	public ArrayList<Move> getSuccessors(State v){
		return graph.get(v);
	}

}
