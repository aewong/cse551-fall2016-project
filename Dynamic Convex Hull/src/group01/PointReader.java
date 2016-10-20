package group01;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PointReader {
	public ArrayList<Point2D> readPoints() throws IOException {
		ArrayList<Point2D> points = new ArrayList<Point2D>();

		BufferedReader br = new BufferedReader(new FileReader("ConvexHullTestData.csv"));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(",");
			
			if (!tokens[0].equals("x") && !tokens[1].equals("y")) {
				double x = Double.parseDouble(tokens[0]);
				double y = Double.parseDouble(tokens[1]);
				
				Point2D point = new Point2D.Double(x, y);
				
				points.add(point);
			}
		}
		
		br.close();
		
//		printPoints(points);
		
		return points;
	}
	
	private void printPoints(ArrayList<Point2D> points) {
		for (Point2D point : points) {
			System.out.println(point.getX() + "," + point.getY());
		}
	}
}
