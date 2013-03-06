import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;


public class JavaScriptPointsWriter {
	private String filename = "";
	public JavaScriptPointsWriter(String filename){
		this.filename = filename;
		
	}
	
	public void outShortestPath(List<IntersectionI> pathList){
		try {
		    PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("points1.js", true)));

			out1.write("var points1_" + filename.substring(0, filename.length() - 4) + "= new Array();\n\n");
			
			int index = 0;
			for(IntersectionI inter : pathList){
				out1.write("points1_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + inter.getLocation().getx() + "\n");
				index++;
				out1.write("points1_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + inter.getLocation().gety() + "\n");
				index++;
			}	
			out1.write("\n");
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outMinTree(Set<StreetI> minTree){
		try {
		    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("points2.js", true)));

			out2.write("var points2_" + filename.substring(0, filename.length() - 4) + "= new Array();\n\n");
			
			int index = 0;
			for(StreetI str : minTree){
				out2.write("points2_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + str.getFirstPoint().getx() + "\n");
				index++;
				out2.write("points2_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + str.getFirstPoint().gety() + "\n");
				index++;
				out2.write("points2_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + str.getSecondPoint().getx() + "\n");
				index++;
				out2.write("points2_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + str.getSecondPoint().gety() + "\n");
				index++;
			}	
			out2.write("\n");
			out2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outBFS(List<IntersectionI> hashInter){
		try {
		    PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter("points3.js", true)));

			out3.write("var points3_" + filename.substring(0, filename.length() - 4) + "= new Array();\n\n");
			
			int index = 0;
			for(IntersectionI inter : hashInter){
				out3.write("points3_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + inter.getLocation().getx() + "\n");
				index++;
				out3.write("points3_" + filename.substring(0, filename.length() - 4) + "[" + index + "] =" + inter.getLocation().gety() + "\n");
				index++;
			}
			out3.write("\n");
			out3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
