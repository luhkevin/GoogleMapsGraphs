import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class BreadthFirstSearch {
	private HashMap<IntersectionI, List<IntersectionI>> interMap = new HashMap<IntersectionI, List<IntersectionI>>();
	private GraphMaker graph;
	public BreadthFirstSearch(GraphMaker graph){
		this.graph = graph;
	}
	
	public HashMap<IntersectionI, List<IntersectionI>> bfsTraverse(IntersectionI source){
		Set<IntersectionI> marked = new HashSet<IntersectionI>();
		Queue<IntersectionI> qGraph = new LinkedList<IntersectionI>();
		
		qGraph.offer(source);
		marked.add(source);
		while(!qGraph.isEmpty()){
			List<IntersectionI> buf = new LinkedList<IntersectionI>();
			IntersectionI i = qGraph.remove();
			List<Point> nbr = graph.getNodeMap().get(i.getLocation());
			for(Point e : nbr){
				IntersectionI inter = new Intersection(e);
				if(!marked.contains(inter)){
					marked.add(inter);
					qGraph.offer(inter);
					buf.add(inter);
				} 
				interMap.put(i, buf);
			}			
		}
		return interMap;
	}
}
