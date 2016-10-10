package group01;

import java.awt.Point;
import java.util.ArrayList;

public class ConvexHullArray {
	private ArrayList<Point> points;
	private ArrayList<Point> chPoints;
	
	ConvexHullArray(ArrayList<Point> points) {
		this.points = points;
		this.chPoints = new ArrayList<Point>();
		
		calculateConvexHull();
	}
	
	private void calculateConvexHull() {
		// TODO: Calculate convex hull.
	}
	
	public void addPoint(Point p) {
		// TODO: Add point and recalculate convex hull.
	}
	
	public void removePoint(Point p) {
		// TODO: Remove point and recalculate convex hull.
	}
	
	public void drawConvexHull() {
		// TODO: Draw convex hull.
	}
}
