package group01;

import java.awt.Point;
import java.util.ArrayList;

public class DynamicConvexHull {
	public static void main(String[] args) {
		PointReader pr = new PointReader();
		ArrayList<Point> points = pr.readPoints();
		
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
