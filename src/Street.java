import java.util.Arrays;




public class Street implements StreetI {
	private double[] dataLine = new double[5];
	private String streetName = ""; 
	
	public void setIdNumber(int id){
		dataLine[0] = id;
	}
	
	public void setName(String name){
		streetName = name;
	}

	public String getName(){
		return streetName;
	}

	public void setPoints(Point firstPoint, Point secondPoint){
		dataLine[1] = firstPoint.getx();
		dataLine[2] = firstPoint.gety();
		dataLine[3] = secondPoint.getx();
		dataLine[4] = secondPoint.gety();
	}

	public Point getFirstPoint(){
		return new Point(dataLine[1], dataLine[2]);
	}
	
	public Point getSecondPoint(){
		return new Point(dataLine[3], dataLine[4]);
	}

	public int getIdNumber(){
		return (int) dataLine[0];
	}
	
	//Distance Formula
	public Double getDistance(){
		return Math.sqrt(Math.pow((dataLine[3] - dataLine[1]), 2) + Math.pow((dataLine[4] - dataLine[2]), 2));
	}

	public String toString(){
		return getName() + "," + getDistance() + "," +  getIdNumber(); 
	}

	//Auto-generated (eclipse)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dataLine);
		result = prime * result
				+ ((streetName == null) ? 0 : streetName.hashCode());
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
		Street other = (Street) obj;
		if (!Arrays.equals(dataLine, other.dataLine))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		return true;
	}


	


}
