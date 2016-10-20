package group01;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ConvexHullHeap {
	private ArrayList<Point2D> points;
	private PriorityQueue<Point2D> chPoints;
	
	ConvexHullHeap(ArrayList<Point2D> points) {
		this.points = points;
		this.chPoints = new PriorityQueue<Point2D>();
		
		calculateConvexHull();
	}
	
	private void calculateConvexHull() {
		// TODO: Calculate convex hull.
	}
	
	public void addPoint(Point2D p) {
		// TODO: Add point and recalculate convex hull.
	}
	
	public void removePoint(Point2D p) {
		// TODO: Remove point and recalculate convex hull.
	}
	
	public void drawConvexHull() {
		// TODO: Draw convex hull.
	}
}
