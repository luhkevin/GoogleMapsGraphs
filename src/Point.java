/**
 * A self explanatory class.
 * For this assignment you can assume that every point is essentially
 * an intersection. More precisely, the location of an intersection
 * is a point, and all points will be represented by an intersection, as
 * we have chosen a closed set of points (a closed graph).
 *
 * @author StudentName
 *
 */
public class Point
{
	private double lat = 0.0;
	private double lon = 0.0;
	private double cost = 0.0;
		
	public Point(double x, double y){
		lat = x;
		lon = y;
	}
	
	public double getx()
	{
		return lat;
	}

	public void setx(double x)
	{
		lat = x;
	}

	public double gety()
	{
		return lon;
	}

	public void sety(double y)
	{
		lon = y;
	}
	
	public void setCost(double x){
		cost = x;
	}
	
	public double getCost(){
		return cost;
	}
	
	@Override	
	public String toString(){
		return lat + "," + lon;
	}
	
	//Auto-generated (eclipse)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		return true;
	}
}

