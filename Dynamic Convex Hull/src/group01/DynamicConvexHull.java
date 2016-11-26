package group01;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class DynamicConvexHull {
	public static void main(String[] args) throws IOException {
		PointReader pr = new PointReader();
		ArrayList<Point2D> points = pr.readPoints("ConvexHullTestData.csv");
		Point2D pchange = new Point2D.Double();
		pchange.setLocation(3,15);
		
		
		// Array implementation
		ConvexHullArray chArray = new ConvexHullArray(points);
		chArray.drawConvexHull();
		
		//example of adding a point
		//chArray.removePoint(pchange);
		
	
		

	}
}



