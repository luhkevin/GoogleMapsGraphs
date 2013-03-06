import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;



public class MapMaker {
	private GraphMaker graph;
	private static ShortestPath shr;
	private static MinSpanningTree min;
	private static BreadthFirstSearch bfs;
	private JavaScriptPointsWriter jScript;
	
	public MapMaker(String filename) throws FileNotFoundException, IOException{
		this.graph = new GraphMaker(filename);
		MapMaker.shr = new ShortestPath(graph);
		MapMaker.min = new MinSpanningTree(graph);
		MapMaker.bfs = new BreadthFirstSearch(graph);
		this.jScript = new JavaScriptPointsWriter(filename);
	}
	
	public static List<IntersectionI> getShortestPath(IntersectionI i, IntersectionI j){
		return shr.dijkstraPath(i, j);
	}
	
	public static Set<StreetI> getMST(){
		return min.getMST();
	}
	
	public static List<IntersectionI> getBFS(IntersectionI i){
		List<IntersectionI> bfsList = new LinkedList<IntersectionI>();
		Queue<IntersectionI> hashQ = new LinkedList<IntersectionI>();
		HashMap<IntersectionI, List<IntersectionI>> hashBFS = bfs.bfsTraverse(i);
		
		hashQ.add(i);
		while(!hashBFS.isEmpty() && !hashQ.isEmpty()){
			IntersectionI rt = hashQ.poll();
			for(IntersectionI inter : hashBFS.get(rt)){
				bfsList.add(rt);
				bfsList.add(inter);
				hashQ.add(inter);
			}
			hashBFS.remove(rt);
		}
		return bfsList;
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException{
		IntersectionI i = new Intersection(new Point(46.39604122600002,-63.79113776899999));
		IntersectionI j = new Intersection(new Point(46.39480726599999,-63.79387750199999));

		MapMaker roadSet = new MapMaker("roadSet1Formatted.txt");
		roadSet.jScript.outMinTree(getMST());
		roadSet.jScript.outShortestPath(getShortestPath(i, j));
		roadSet.jScript.outBFS(getBFS(i));
		
		
		Intersection ny1 = new Intersection(new Point(40.741668,-74.001129));
		Intersection ny2 = new Intersection(new Point(40.743425,-73.999848));

		MapMaker ny = new MapMaker("ny.txt");
		ny.jScript.outMinTree(getMST());
		ny.jScript.outShortestPath(getShortestPath(ny1, ny2));
		ny.jScript.outBFS(getBFS(ny1));
		
		
		Intersection penn1 = new Intersection(new Point(39.953902,-75.194623));
		Intersection penn2 = new Intersection(new Point(39.954074,-75.189719));
		
		MapMaker penn = new MapMaker("penn.txt");
		penn.jScript.outMinTree(getMST());
		penn.jScript.outShortestPath(getShortestPath(penn1, penn2));
		penn.jScript.outBFS(getBFS(penn1));
		
	}
}
