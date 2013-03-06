import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {
	
	
	@Test
	public void testGraphMaker() {
		GraphMaker g = new GraphMaker("test.txt");
		ShortestPath s = new ShortestPath(g);
		IntersectionI i = new Intersection(new Point(10.0, 10.0));
		IntersectionI j = new Intersection(new Point(10.0, 20.0));
		IntersectionI k = new Intersection(new Point(20.0, 20.0));
		


		
		s.dijkstraPath(i, j);
		
		GraphMaker gr = new GraphMaker("testMST.txt");
		MinSpanningTree t = new MinSpanningTree(gr);
		BreadthFirstSearch br = new BreadthFirstSearch(gr);
		
		MinTurns min = new MinTurns("testMIN.txt");
//		System.out.print(min.minTurnsBetween(i, k));
	
		IntersectionI l = new Intersection(new Point(20.0, 25.0));
		IntersectionI m = new Intersection(new Point(15.0, 20.0));
//		IntersectionI n = new Intersection(new Point(10.0, 25.0));
		
		GraphMaker gr2 = new GraphMaker("test2.txt");
		ShortestPath sh = new ShortestPath(gr2);
		
//		System.out.println(sh.dijkstraPath(l, m).toString());
		

	}

}
