package group01;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.TreeMap;

import org.jfree.ui.RefineryUtilities;

public class ConvexHullTree {
	private ArrayList<Point2D> points;
	private TreeMap chPoints;
	
	ConvexHullTree(ArrayList<Point2D> points) {
		this.points = points;
		this.chPoints = new TreeMap();
		
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
		XYPlotter chart = new XYPlotter("Binary Search Tree", points, chPoints);
	    chart.pack();          
	    RefineryUtilities.centerFrameOnScreen(chart);          
	    chart.setVisible(true);
	}
}
