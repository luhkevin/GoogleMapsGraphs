import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class MinTurns {
	private GraphMaker graph;

	private HashMap<StreetI, HashSet<StreetI>> connected = new HashMap<StreetI, HashSet<StreetI>>();
	private HashMap<Point, HashSet<StreetI>> ptsStreets = new HashMap<Point, HashSet<StreetI>>();
	private HashMap<StreetI, StreetI> backTrace = new HashMap<StreetI, StreetI>();
	public MinTurns(String filename){
		this.graph = new GraphMaker(filename);
		connected = this.graph.getConnected();
		ptsStreets = this.graph.getStreetList();
	}
	
	public int minTurnsBetween(IntersectionI source, IntersectionI destination){
		Queue<StreetI> stQ = new LinkedList<StreetI>();		
		Set<StreetI> marked = new HashSet<StreetI>();
		HashSet<StreetI> endStreets = ptsStreets.get(destination.getLocation());

		Point pt = source.getLocation();
		HashSet<StreetI> sts = graph.getStreetList().get(pt);
		
		
		for(StreetI str : sts){
			stQ.offer(str);
			marked.add(str);
		}
		
		while(!stQ.isEmpty() && !endStreets.isEmpty()){	
			//Removes from the head
			StreetI st = stQ.remove();
			
		
			for(StreetI s : connected.get(st)){
				if(st.getFirstPoint().equals(destination.getLocation()) || st.getSecondPoint().equals(destination.getLocation())){
					endStreets.remove(st);
					break;
				}
				if(!marked.contains(s)){
					marked.add(s);
					stQ.offer(s);
					
					backTrace.put(s, st);
				}
			}
		}		
		return count(destination, source);
			
	}
	
	private int count(IntersectionI dest, IntersectionI start){
		HashSet<StreetI> startStreets = ptsStreets.get(start.getLocation());

		Point destPnt = dest.getLocation();
		
		int outerCount = Integer.MAX_VALUE;
		for(StreetI str : backTrace.keySet()){
			Point str1 = str.getFirstPoint();
			Point str2 = str.getSecondPoint();
			int count = 0;
			
			StreetI temp = str;
			if(str1.equals(destPnt) || str2.equals(destPnt)){
				while(!startStreets.contains(temp)){
					StreetI backVal = backTrace.get(temp);
					
					if(!backVal.getName().equals(temp.getName())){
						count++;
					} 
					temp = backVal;
					
				}
			} else {
				count = Integer.MAX_VALUE;
			}
			
			if(count < outerCount){
				outerCount = count;
			}
		}
		
		return outerCount;
	}

		
		
		

}
