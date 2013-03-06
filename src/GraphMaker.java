import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GraphMaker {
	
	//Map from point to adjacent points
	private HashMap<Point, List <Point>> adjList = new HashMap<Point, List<Point>>();
		
	//Maps from street ID (i.e. streets) to street length (i.e. weight)
	private HashMap<Integer, Double> edgeWeight = new HashMap<Integer, Double>();
	
	//Maps from street ID to street 
	private HashMap<Integer, Street> idStreet = new HashMap<Integer, Street>();
	
	//Maps from pairs of points to an edge (street ID number)
	private HashMap<String, Integer> pointsToEdge = new HashMap<String, Integer>();
		
	//Maps from a point to its streetList
	private HashMap<Point, HashSet<StreetI>> streetList = new HashMap<Point, HashSet<StreetI>>();
	
	//Maps streets to connected streets
	private HashMap<StreetI, HashSet<StreetI>> connected = new HashMap<StreetI, HashSet<StreetI>>();
	
	private Street street = new Street();
	private HashSet<StreetI> sList = new HashSet<StreetI>();
	
	public GraphMaker(String fileName) {
		BufferedReader r = null;
		String[] lineSplit;

		try{
			FileReader f = new FileReader(fileName);
			r = new BufferedReader(f);

		//Skip the first data-info line		
		r.readLine();	
		String line = r.readLine();
		while(line != null){
			lineSplit = line.split(",");
			parse(lineSplit);
			makeStructures();
			
			line = r.readLine();
			street = new Street();
		}
		
		makeConnected();
		} catch(FileNotFoundException e){
			e.printStackTrace();		
		} catch(IOException e){
			e.printStackTrace();
		}
		
		outTextFile();
	}
	//Parses the file
	private void parse(String[] lineSplit){
		street.setIdNumber(Integer.parseInt(lineSplit[0])); 
		street.setPoints(new Point(Double.parseDouble(lineSplit[1]), Double.parseDouble(lineSplit[2])), 
				new Point(Double.parseDouble(lineSplit[3]), Double.parseDouble(lineSplit[4])));
		street.setName(lineSplit[5]);
		sList.add(street);
	}
	
	//build the data structures
	private void makeStructures(){
		pointsToEdge.put(street.getFirstPoint().toString() + "," + street.getSecondPoint().toString(), street.getIdNumber());
		pointsToEdge.put(street.getSecondPoint().toString() + "," + street.getFirstPoint().toString(), street.getIdNumber());
		idStreet.put(street.getIdNumber(), street);
		edgeWeight.put(street.getIdNumber(), street.getDistance());
		makeAdjList();
		makeStreetList();		
	}
	
	//Makes streetList 
	private void makeStreetList(){
		if(streetList.containsKey(street.getFirstPoint())){
			streetList.get(street.getFirstPoint()).add(street);
		} else {
			HashSet<StreetI> stList1 = new HashSet<StreetI>();
			stList1.add(street);
			streetList.put(street.getFirstPoint(), stList1);
		}
		
		if(streetList.containsKey(street.getSecondPoint())){
			streetList.get(street.getSecondPoint()).add(street);
		} else {
			HashSet<StreetI> stList2 = new HashSet<StreetI>();
			stList2.add(street);
			streetList.put(street.getSecondPoint(), stList2);
		}
	}
		
	//Determines if two streets are connected (i.e., edges adjacent to each other). If they are, then returns true
	public boolean isConnected(StreetI st, StreetI str){
		Point st1 = st.getFirstPoint();
		Point st2 = st.getSecondPoint();
		Point str1 = str.getFirstPoint();
		Point str2 = str.getSecondPoint();
		
		if(st1.equals(str1) || st1.equals(str2) || st2.equals(str1) || st2.equals(str2)){
			return true;
		}
		return false;
	}
	
	//Connects streets to streets in a map
	private void makeConnected(){
		for(StreetI a : sList){
			for(StreetI b : sList){
				if(isConnected(a, b) && !a.equals(b)){
					if(connected.containsKey(a)){
						connected.get(a).add(b);
					} else {
						HashSet<StreetI> hsh = new HashSet<StreetI>();
						hsh.add(b);
						connected.put(a, hsh);
					}					
				}
			}
		}
	}
	
	public HashMap<StreetI, HashSet<StreetI>> getConnected(){
		return connected;
	}
		
	//Makes the adjacency list (Points [Intersections] to adjacent points [intersections])
	//Makes an entry for both points on each line
	private void makeAdjList(){		
		if(adjList.containsKey(street.getFirstPoint())){
			adjList.get(street.getFirstPoint()).add(street.getSecondPoint());
		} else {
			LinkedList<Point> adjStreetsP1 = new LinkedList<Point>();
			adjStreetsP1.add(street.getSecondPoint());
			adjList.put(street.getFirstPoint(), adjStreetsP1);				
		}
		
		if(adjList.containsKey(street.getSecondPoint())){
			adjList.get(street.getSecondPoint()).add(street.getFirstPoint());
		} else {
			LinkedList<Point> adjStreetsP2 = new LinkedList<Point>();
			adjStreetsP2.add(street.getFirstPoint());
			adjList.put(street.getSecondPoint(), adjStreetsP2);
		}
	}
	
	public HashMap<Point, List<Point>> getNodeMap(){
		return adjList;
	}
	
	public HashMap<Integer, Double> getEdgeWeights(){
		return edgeWeight;
	}
	
	public HashMap<String, Integer> getPointsToEdge(){
		return pointsToEdge;
	}
	
	public HashMap<Integer, Street> getIdStreet(){
		return idStreet;
	}
	
	public HashMap<Point, HashSet<StreetI>> getStreetList(){
		return streetList;
	}
		
	private void outTextFile(){
		try {
			File file = new File("adjacencyList.txt");
			file.createNewFile();
			FileWriter fwriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter out = new BufferedWriter(fwriter);
			
			String pointLine = "";
			StringBuffer adjPts = new StringBuffer();
					
			for(Point pt : adjList.keySet()){
				pointLine = "(" + pt.toString() + ")" + " Adjacent to:";
				for(Point pp : adjList.get(pt)){					
					Street str = idStreet.get(pointsToEdge.get(pp.toString() + "," + pt.toString()));
					adjPts.append("(" + pp.toString() +  "," + str.toString() + ")");
				}
				out.write(pointLine + adjPts + "\n");
				adjPts.delete(0, adjPts.length());				
			}
			out.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
