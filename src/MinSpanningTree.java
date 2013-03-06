import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinSpanningTree {
	
	private GraphMaker graph;
	private Set<StreetI> streetSet = new HashSet<StreetI>();
	private HashMap<Point, Integer> UFmap = new HashMap<Point, Integer>();

	public MinSpanningTree(GraphMaker graph){
		this.graph = graph;
	}
	
	public Set<StreetI>getMST(){
		
		/* USING CODE FROM SEDGEWICK */
		//Implementation based on algorithm 4.8 from book
		streetSet = new HashSet<StreetI>();
		PriorityQueue<StreetX> pq = new PriorityQueue<StreetX>();
		Collection<Street> streetEdge = graph.getIdStreet().values();
		
		for(Street c : streetEdge){
			StreetX str = new StreetX();
			str.setIdNumber(c.getIdNumber());
			str.setName(c.getName());
			str.setPoints(c.getFirstPoint(), c.getSecondPoint());
			pq.add(str);
		}
		
		UnionFind uf = new UnionFind(graph.getNodeMap().size());
		
		//Puts each point in the graph to a map with an integer for use in UnionFind
		int n = 0;
		for(Point p : graph.getNodeMap().keySet()){
			UFmap.put(p, new Integer(n));
			n++;
		}
		
		while(!pq.isEmpty() && streetSet.size() < graph.getNodeMap().size() - 1){
			StreetX st = pq.poll();
			Point v = st.getFirstPoint(), w = st.getSecondPoint();
			if(uf.connected(UFmap.get(v), UFmap.get(w))) continue;
			uf.union(UFmap.get(v), UFmap.get(w));
			streetSet.add(st);
		}
		/*END USING CODE FROM SEDGEWICK*/
		

		
		return streetSet;
	}
	
}

class StreetX extends Street implements StreetI,Comparable<Street>{
	public StreetX(){
		super();
	}
	@Override
	public int compareTo(Street o) {
		if(this.getDistance() == o.getDistance()){
			return 0;
		} else if (this.getDistance() > o.getDistance()){
			return 1;
		} else {
			return -1;
		}
	}
}

/*USING CODE FROM SEDGEWICK*/
//Union-find implementation from book, algorithm 1.5
class UnionFind{
	private int[] id;
	private int count;
	
	public UnionFind(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++)
			id[i] = i;	
	}
	
	public int count(){
		return count;
	}
	
	public int find(int p){
		while(p != id[p]){
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if(i == j) return;
		
		id[i] = j;
		count --;
	
	}
	
	public boolean connected(int p, int q){
		return find(p) == (find(q));
	}
}
/*END CODE FROM SEDGEWICK*/