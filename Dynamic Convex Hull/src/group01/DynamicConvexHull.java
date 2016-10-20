package group01;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class DynamicConvexHull {
	public static void main(String[] args) throws IOException {
		PointReader pr = new PointReader();
		ArrayList<Point2D> points = pr.readPoints("ConvexHullTestData.csv");
		
		// Array implementation
		ConvexHullArray chArray = new ConvexHullArray(points);
		chArray.drawConvexHull();
		// TODO: Add point and draw.
		// TODO: Remove point and draw.
		
		// Binary search tree implementation
		ConvexHullTree chTree = new ConvexHullTree(points);
		chTree.drawConvexHull();
		// TODO: Add point and draw.
		// TODO: Remove point and draw.
		
		// Heap implementation
		ConvexHullHeap chHeap = new ConvexHullHeap(points);
		chHeap.drawConvexHull();
		// TODO: Add point and draw.
		// TODO: Remove point and draw.
	}
}
