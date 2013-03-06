import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class ShortestPath {
	/*
	 * The first element in the list is the original source node parameter, 
	 * and the last element in the list is the original target node parameter.
	 */
	
	private GraphMaker graph;	
	private HashMap<IntersectionI, IntersectionI> backTrace = new HashMap<IntersectionI, IntersectionI>();
	private HashMap<Point, IntersectionX> pointToX = new HashMap<Point, IntersectionX>();
	
	public ShortestPath(GraphMaker graph){
		this.graph = graph;
	}
	
	public List<IntersectionI> dijkstraPath(IntersectionI start, IntersectionI end){
		LinkedList<IntersectionI> dPath = new LinkedList <IntersectionI>();
		PriorityQueue<IntersectionX> pq = new PriorityQueue<IntersectionX>(); 

		Set<Point> vertices = graph.getNodeMap().keySet();
						
		//Set cost of all other nodes..i.e. points (including "end") to infinity
		for(Point pt : vertices){
			IntersectionX intersect = new IntersectionX(pt);
			
			if(intersect.equals(start)){
				pt.setCost(0.0);
			} else {
				pt.setCost(Double.POSITIVE_INFINITY);
			}
			
			//Puts all of the vertices into a priority queue			
			pq.add(intersect);
			
			//Adds the point, intersectionX mapping to a hashmap for later access
			pointToX.put(pt, intersect);
		}
		
		while(!pq.isEmpty()){
			IntersectionX inter = pq.poll();	
			
			if(inter.equals(end)){
				break;
			} else if(inter.getLocation().getCost() == Double.POSITIVE_INFINITY) {
				break;
			} 
			relax(inter, pq, start);
		}	
		
		return findPath(dPath, end, start);
	}
	
	private void relax(IntersectionI inter, PriorityQueue<IntersectionX> pq, IntersectionI start){
		double dist = 0;

		//"inter" represents the currently focused node, "pt" represents "inter"'s neighboring nodes
		for(Point pt : graph.getNodeMap().get(inter.getLocation())){
			dist = inter.getLocation().getCost() + edgeLen(pt, inter.getLocation());		
			IntersectionX x = new IntersectionX(pt);
			if(dist < pointToX.get(pt).getLocation().getCost() && pq.contains(x)){		
				pq.remove(pointToX.get(pt));
				pt.setCost(dist);
				pq.add(x);
				pointToX.put(pt, x);					
				backTrace.put((IntersectionI) x, inter);
			}
		}
	}
	
	//Takes the two points of a street and returns the distance between them (the street weight/length)
	private double edgeLen(Point one, Point two){
		int ID = 0;
		if(graph.getPointsToEdge().containsKey(one.toString() + "," + two.toString())){
			ID = graph.getPointsToEdge().get(one.toString() + "," + two.toString());
		}

		return graph.getEdgeWeights().get(ID);
	}
	
	//Runs through backtrace to find the actual shortest path
	private List<IntersectionI> findPath(LinkedList<IntersectionI> dPath, IntersectionI end, IntersectionI start){
		dPath.addFirst(end);
		IntersectionI next = backTrace.get(end);
		while(next != null){
			dPath.addFirst(next);
			if(next.equals(start)){
				break;
			}
			next = backTrace.get(next);
		}
		return dPath;
	}
}

class IntersectionX extends Intersection implements IntersectionI,Comparable<Intersection>{
	
	public IntersectionX(Point point) {
		super(point);
	}

	@Override
	public int compareTo(Intersection o) {
		if(this.getLocation().getCost() == o.getLocation().getCost()){
			return 0;
		} else if(this.getLocation().getCost() > o.getLocation().getCost()){
			return 1;
		} else {
			return -1;
		}
	}		
}
