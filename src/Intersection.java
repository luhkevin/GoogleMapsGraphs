
import java.util.List;

public class Intersection implements IntersectionI{
	private Point pnt;
	private List<StreetI> adjStreets;
	
	public Intersection(Point point){
		this.pnt = point;
	}
	/**
	 * Returns the point at which this intersection is located at.
	 * @return A point.
	 */
	public Point getLocation() {
		return pnt;
	}

	/**
	 * Returns the list of streets that pass through this intersection.
	 * @return A street list.
	 */
	public List<StreetI> getStreetList(){
		return adjStreets;
	}

	/**
	 * Sets the point at which this intersection is located at.
	 * @param p
	 */
	public void setPointOfIntersection(final Point p){
		pnt = p;
	}

	/**
	 * Sets the list of streets that pass through this intersection.
	 * @param list
	 */
	public void setStreetList(final List<StreetI> list){
		adjStreets = list;
	}

	/**
	 * Determines whether this intersection is connected another intersection.
	 * @param intersection to check for connectivity
	 * @return the street that links the two intersections, null if none.
	 */
	public StreetI isConnected(final IntersectionI intersection){
		List<StreetI> interList = intersection.getStreetList();
		for(StreetI l : adjStreets){
			if(interList.contains(l)){
				return l;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		return pnt.toString();
	}
	
	@Override
	public int hashCode(){		
		return pnt.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		return ((IntersectionI) o).getLocation().getx() == this.pnt.getx() &&
				((IntersectionI) o).getLocation().gety() == this.pnt.gety();
	}
	
}

