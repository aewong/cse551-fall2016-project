package group01;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;


import org.jfree.ui.RefineryUtilities;

public class ConvexHullArray {
	private ArrayList<Point2D> points;
	private ArrayList<Point2D> chPoints;
	private ArrayList<Point2D>sortedPoints;
	private ArrayList<Point2D>UpperEdgeMap;
	private ArrayList<Point2D>LowerEdgeMap;
	
	private ArrayList<Point2D>UpperHull;
	private ArrayList<Point2D>LowerHull;
	
	private ArrayList<Point2D>rightUpperHull;
	private ArrayList<Point2D>rightLowerHull;
	
	private Point2D MinXMinY; 
	private Point2D MinXMaxY;
	private Point2D MaxXMaxY;
	private Point2D MaxXMinY;
	double upperSlope;
	double lowerSlope;
	
	
	ConvexHullArray(ArrayList<Point2D> points) {
		this.points = points;
		
		
		this.chPoints = new ArrayList<Point2D>();
		sortedPoints = new ArrayList<Point2D>(); 
		UpperEdgeMap = new ArrayList<Point2D>();
		LowerEdgeMap= new ArrayList<Point2D>();
		UpperHull = new ArrayList<Point2D>();
		LowerHull = new ArrayList<Point2D>();
		rightUpperHull = new ArrayList<Point2D>();
		rightLowerHull = new ArrayList<Point2D>();
		
		calculateConvexHull();
	}
	
	private void calculateConvexHull() {
		// TODO: Calculate convex hull.
		Point2D t;
		Point2D leftanchor1;
		Point2D leftanchor2;
		Point2D leftanchor3;
		Point2D rightanchor1;
		Point2D rightanchor2;
		Point2D rightanchor3;
		
		 if (points.size() >3 )//Only process the hull if there's at least 4 points
		 {
		 
		SortList();//sort list in terms of by x then by y
		System.out.println("sorted list");
		for (Point2D temp : sortedPoints)
		{
			System.out.println( temp.getX() + " ," + temp.getY());
		}
		 
		 //finding the 4 anchor points
		 
		//finding the left side anchor points
		 
	  /*  MinXMinY= sortedPoints.get(1);// temporarily set as  secondSmallest X with the smallest Y
		MinXMaxY = sortedPoints.get(1);//temporarily set to find SecondSmallest X with the Highest Y to compare to the Min X 
		
		for ( Point2D temp : sortedPoints)//loop find the second smallest x with the highest y to compare to the smallest X to find first two anchor 
			{ 	if(MinXMaxY.getX() == temp.getX() )
					{
						if(temp.getY() > MinXMaxY.getY())
						{
							MinXMaxY= temp;
						}
				
					}
				else if ( MinXMaxY.getX()>temp.getX())//break once we iterate through all of the second  smallest X
				{
					break;
				}
			}//end of for
		if( sortedPoints.get(0).getX()== MinXMaxY.getX())//case where the First and second number has the same X value
		{
			MinXMinY= sortedPoints.get(0);
			
		}
		else {
	     
	        if( sortedPoints.get(0).getY() < MinXMaxY.getY())
	        {
	        	MinXMinY= sortedPoints.get(0);
	        	
	        }
	        else if ( sortedPoints.get(0).getY() > MinXMaxY.getY())
	        {
	        	MinXMaxY= sortedPoints.get(0);
	        }
	        else // case where the first point is equal to the second min X with the highest Y
	        {
	        	if(MinXMinY != MinXMaxY)//check if theres another point  lower in Y as the MinXMaxY if so make that point the MinXMinY, 
	        		//but already assigned in the beggining, so just change MinXMaxY to first point
	        	{
	        		MinXMaxY= sortedPoints.get(0);
	        	}
	        	else//case where the two minx X points are on the same line, then find the first points that is different y from the very first point
	        	{
	        		for ( Point2D temp : sortedPoints)
	        		{
	        			if (temp.getY() >sortedPoints.get(0).getY()&& temp.getX()!= sortedPoints.get(sortedPoints.indexOf(temp)-1).getX())
	        			{
	        				MinXMaxY = temp;
	        				MinXMinY= sortedPoints.get(0);
	        				break;
	        				
	        			}
	        			else if(temp.getY()< sortedPoints.get(0).getY())
	        			{
	        				MinXMaxY= sortedPoints.get(0);
	        				MinXMinY=temp;
	        				break;
	        			}
	        		}
	        	}
	      
	        }
		   }
		//finding the right side anchor points, should be same logic but from the back ->front 
		
		
		MaxXMinY =sortedPoints.get(sortedPoints.size()-2);//set initially the second to last point as these values
		MaxXMaxY =sortedPoints.get(sortedPoints.size()-2);
		for (  Point2D temp : sortedPoints)// loop to find the Highest Y that has the same X value as the second last 
		{
			if(temp.getX()==MaxXMinY.getX())
			{
				if(temp.getY() < MaxXMinY.getY())
				{
					MaxXMinY = temp;
					break;
				}
			}
			else if (temp.getX()>MaxXMinY.getX())
			{
				break;
			}
		}//end of for
		if(sortedPoints.get(sortedPoints.size()-1).getX() == MaxXMinY.getX() )//if last point and MaxXMinY has the same X
		{
			MaxXMaxY=sortedPoints.get(sortedPoints.size()-1);
			
		}
		else
		{
			if(sortedPoints.get(sortedPoints.size()-1).getY()<MaxXMaxY.getY() )//if Y of last point is less than Y of second to last point
			{
				MaxXMinY= sortedPoints.get(sortedPoints.size()-1);//then Last point is MaxXMinY
				
			}
			else if(sortedPoints.get(sortedPoints.size()-1).getY()>MaxXMaxY.getY())//if last point Y is higher than Second last point Y
			{
				MaxXMaxY= sortedPoints.get(sortedPoints.size()-1);//last point is MaxXMaxY and the MaxXMinY found before is what it is
				
			}
			else//case where last and second to last has same Y 
			{
				if(MaxXMinY!=MaxXMaxY)// if the second to last point is not the only point in the X value
				{
					MaxXMaxY= sortedPoints.get(sortedPoints.size()-1);
				}
				else//if second to last point is the only point in that x value, then find the next point that is higher/lower in y
				{
					for (int i = sortedPoints.size()-2;i>=0;i--)
					{
						t = sortedPoints.get(i);
				
						if(t.getY()>sortedPoints.get(sortedPoints.size()-1).getY())
						{
							MaxXMaxY=t;
							MaxXMinY=sortedPoints.get(sortedPoints.size()-1);
							break;
						}
						else if (t.getY()<sortedPoints.get(sortedPoints.size()-1).getY() && t.getX()!=sortedPoints.get(sortedPoints.indexOf(t)-1).getX())
						{	
							MaxXMinY=t;
							MaxXMaxY=sortedPoints.get(sortedPoints.size()-1);
							break;
						}
						
					}
				}
				
			}
		}//end of finding 3rd 4rd anchor point*/
		
		
			leftanchor1= sortedPoints.get(0);
			leftanchor2= sortedPoints.get(0);
			leftanchor3=sortedPoints.get(0);
			for(int i = 1;i<sortedPoints.size();i++)
			{
				t=sortedPoints.get(i);
				
				if(t.getX()!=sortedPoints.get(i+1).getX())
				{	
					
					if(t.getY() != leftanchor1.getY() )
					{
					leftanchor2= t;
					break;
					}
				}
				leftanchor3=t;
			}
			
			if(leftanchor1.getY()< leftanchor2.getY())
				{
					MinXMinY=leftanchor1;
					MinXMaxY=leftanchor2;
				}
			else
				{	MinXMinY=leftanchor2;
					MinXMaxY=leftanchor1;
					if(leftanchor3.getY()<leftanchor2.getY())
					{
						MinXMinY=leftanchor3;
					}
				
				}
			
		
			
		
			rightanchor1= sortedPoints.get(sortedPoints.size()-1);//initialize as last element
			rightanchor2= sortedPoints.get(sortedPoints.size()-2);//initialize as last element
			rightanchor3=sortedPoints.get(sortedPoints.size()-1);
			for(int i= sortedPoints.size()-2;i>0;i--)
			{
				t=sortedPoints.get(i);
				
				if(t.getX()!=sortedPoints.get(i-1).getX())
				{
					if(t.getY() != rightanchor1.getY() )
					{
					rightanchor2= t;
					break;
					}
					
				}
				rightanchor3=t;
			}
		 if(rightanchor1.getY() > rightanchor2.getY())
		 {	 MaxXMinY=rightanchor2;
			 MaxXMaxY=rightanchor1;
			 
		 }
		 else
		 {
			 MaxXMinY=rightanchor1;
			 MaxXMaxY=rightanchor2;
			 if(rightanchor3.getY()>rightanchor2.getY())
			 {
				 MaxXMaxY=rightanchor3;
			 }
		 }
		System.out.println("MinXMaxY" + MinXMaxY.getX()+" , " + MinXMaxY.getY());
		System.out.println("MaxXMaxY" + MaxXMaxY.getX()+" , " + MaxXMaxY.getY());
		System.out.println("MinXMinY" + MinXMinY.getX()+" , " + MinXMinY.getY());
		System.out.println("MaxXMinY" + MaxXMinY.getX()+" , " + MaxXMinY.getY());
		//Slope calculation
		upperSlope= slope(MinXMaxY,MaxXMaxY);
		lowerSlope= slope(MinXMinY,MaxXMinY);//find slope of the anchors
		
		
		UpperEdgeMap.add(MinXMaxY);
		LowerEdgeMap.add(MinXMinY);
		
		MakeUpperHull();
	
		MakeLowerHull();
		
	System.out.println("points in upper area: " + UpperEdgeMap.size());
	System.out.println("points in lower area: " + LowerEdgeMap.size());
		
		 }//end of size check
	}//end ofCalConv
	
	
	
		
	
	
	public void addPoint(Point2D p) {
		// TODO: Add point and recalculate convex hull.
		
		if(points.contains(p))
		{
			System.out.println("points already on Set");//points already on p
		}
		else {
		//first step is to identify where the point will be , outside hull( new anchor point or just an edge ) or inside hull.  
		if ( MinXMaxY.getX() < MinXMinY.getX())// CASE :MinXMaxY is left of MinXMinY
		{
			if( p.getX() < MinXMaxY.getX())//new point is left of MinXMaxY
			{
				if(p.getY()>MinXMaxY.getY())
				{	
					MinXMinY=MinXMaxY;//new point is the new MinXMaxY
					LowerEdgeMap.add(0,MinXMinY);
				
					MinXMaxY=p;
					UpperEdgeMap.add(0,MinXMaxY);
				}
				else if ( p.getY() < MinXMaxY.getY())
				{	
					MinXMinY=p;//new point is new MinXMinY
					LowerEdgeMap.add(0, MinXMinY);
				
				}
				
				else
				{
					MinXMaxY=p;//new point is new MinXMaxY
					UpperEdgeMap.add(0, MinXMaxY);
				}
			}
			else if ( p.getX()>MinXMaxY.getX() && p.getX()<MinXMinY.getX())//point is in between the two Anchor
				{
						if(p.getY()<MinXMaxY.getY())
						{
							MinXMinY= p;
							LowerEdgeMap.add(0,MinXMinY);
						}
						else if(p.getY()>MinXMaxY.getY())
						{	
							MinXMinY=MinXMaxY;
							UpperEdgeMap.remove(MinXMaxY);
							LowerEdgeMap.add(0,MinXMinY);
							MinXMaxY=p;
							UpperEdgeMap.add(0,MinXMaxY);
						}

				}

			}//end case for  MinXMaxY is left of MinXMinY
		else//MinXMinY is left MinXMaxY
			{
			if( p.getX() < MinXMinY.getX())//new point is left of MinXMaxY
			{
				
				if(p.getY()> MinXMinY.getY())//new point is higher than MinXMinY
				{
					MinXMaxY=p;
					UpperEdgeMap.add(0,MinXMaxY);
					
					
				}
				else if(p.getY()<MinXMinY.getY())// new point is lower than MinXMinY, so its the new MinXMinY and MinXMaxY is now the old MinXMinY
				{
					MinXMaxY = MinXMinY;
					UpperEdgeMap.add(0,MinXMaxY);
					
					MinXMinY=p;
					LowerEdgeMap.add(0,MinXMinY);
					
				}
				else// new point is on same Y as MinXMinY, this makes it the new MinXMinY
				{
					MinXMinY=p;
					LowerEdgeMap.add(0,MinXMinY);
				}
				
				
				
			}
			else if (p.getX()>MinXMinY.getX() && p.getX()<MinXMaxY.getX())// point P is in between the  left anchor
				{ 			if(p.getY()>MinXMinY.getY())//P is higher than MinXMinY , so its the new MinXMaxY
							{
									MinXMaxY=p;
									UpperEdgeMap.add(0,MinXMaxY);
							}
						else if ( p.getY() <MinXMinY.getY())//P is lower than MinXMinY, then p is new MinXMinY and MinXMinY now is MinXMaxY
						{
							MinXMaxY=MinXMinY;
							LowerEdgeMap.remove(MinXMinY);
							UpperEdgeMap.add(0,MinXMaxY);
							
							MinXMinY=p;
							LowerEdgeMap.add(0,MinXMinY);
						}
			
				}
			}//end of left anchor point checking
		
		
		
		//start checking right anchor points
		int lastup = UpperEdgeMap.size()-1;
		int lastdown= LowerEdgeMap.size()-1;
		if(MaxXMaxY.getX() > MaxXMinY.getX())//case MaxXMaxY is right of MaxXMinY
		{
			if(p.getX()>MaxXMaxY.getX())//casewherep is right of MaxXMaxY
			{
				if(p.getY() >MaxXMaxY.getY())// p is now new MaxXMaxY
				{
				MaxXMaxY=p;
				UpperEdgeMap.add(lastup,MaxXMaxY);
				}
				else if (p.getY()<MaxXMaxY.getY())// is now new MaxXMinY
				{
				MaxXMinY=p;
				LowerEdgeMap.add(lastdown, MaxXMinY);
				}
				else// same height p is now new MaxXMaxY
				{
				MaxXMaxY=p;
				UpperEdgeMap.add(lastup,MaxXMaxY);
				}
			}
			else if (p.getX() <MaxXMaxY.getX() && p.getX() >MaxXMinY.getX())//case where  p is in between MaxXMaxY and MaxXMinY
				{
				
				if(p.getY() > MaxXMaxY.getY())//p is higher than MaxXMaxY becoming new MaxXMaxY
					{	
						MaxXMinY=MaxXMaxY;
						UpperEdgeMap.remove(MaxXMaxY);
						LowerEdgeMap.add(lastdown,MaxXMinY);
						
					
						MaxXMaxY=p;
						UpperEdgeMap.add(lastup,MaxXMaxY);
					
					}
				else if( p.getY() < MaxXMaxY.getY())//making it  new MaxXMinY
					{
						MaxXMinY=p;
						LowerEdgeMap.add(lastdown,MaxXMinY);
					
					}
				
				}
		}
		else if( MaxXMaxY.getX() < MaxXMinY.getX())//case where MaxXMinY is right of MaxXMaxY
		{
			if(p.getX()>MaxXMinY.getX())
			{
				if(p.getY()>MaxXMinY.getY())
					{
						MaxXMaxY=p;
					}
				else if(p.getY()<MaxXMinY.getY())
					{
						MaxXMaxY=MaxXMinY;
						MaxXMinY=p;
					}
				else
				{
					MaxXMinY=p;
				}
			}
			else if ( p.getX()<MaxXMinY.getX() && p.getX()>MaxXMaxY.getX())
			{
				if(p.getY()>MaxXMinY.getY())
				{
					MaxXMaxY=p;
				}
				else if(p.getY()<MaxXMinY.getY())
				{
					MaxXMaxY=MaxXMinY;
					MaxXMinY=p;
				}
				
			}
		}

		//start recomputing
		points.add(p);
		SortList();
		updateSlopes();
		recomputeUpper();
		recomputeLower();
		drawConvexHull();
		
		
		
		
		System.out.println(" new MinXMaxY " + MinXMaxY.getX()+" , " + MinXMaxY.getY());
		System.out.println("new MaxXMaxY " + MaxXMaxY.getX()+" , " + MaxXMaxY.getY());
		System.out.println("new MinXMinY " + MinXMinY.getX()+" , " + MinXMinY.getY());
		System.out.println("new MaxXMinY " + MaxXMinY.getX()+" , " + MaxXMinY.getY());
		
		
		
		
		}//end else
	}//end of add
		
		
	
	
	public void removePoint(Point2D p) {
		// TODO: Remove point and recalculate convex hull.
		Point2D t;
		Point2D leftanchor1;
		Point2D leftanchor2;
		Point2D leftanchor3;
		Point2D rightanchor1;
		Point2D rightanchor2;
		Point2D rightanchor3;
		/*if (p==MinXMaxY)
		{ if(MinXMaxY.getX()<MinXMinY.getX())
			{
				for(int i =sortedPoints.indexOf(MinXMaxY)+1;i<sortedPoints.size();i++)
				{
					t=sortedPoints.get(i);
					if(t.getY()>MinXMinY.getY())
					{
						MinXMaxY= t;
						break;
					}
				}
			}
			else {
				for(int i =sortedPoints.indexOf(MinXMinY)+1;i<sortedPoints.size();i++)
				{
					t=sortedPoints.get(i);
					if(t.getY()<MinXMaxY.getY())
					{
						MinXMinY= t;
						break;
					}
				}
				}
			
		}
		
		else if (p==MinXMinY)
		{
			
			
		}
		else if (p==MaxXMaxY)
		{
			
			
		}
		else if (p==MaxXMinY)
		{
			
		}
		else
		{
			
		}*/
		if(points.contains(p)==false)
		{
			System.out.println(" point not on plot");
		}
		points.remove(p);
		SortList();
		

		if( (p.getX()== MinXMaxY.getX() && p.getY()==MinXMaxY.getY()) || (p.getX()==MinXMinY.getX() && p.getY()==MinXMinY.getY()))
		{	
			leftanchor1= sortedPoints.get(0);
			leftanchor2= sortedPoints.get(0);
			leftanchor3=sortedPoints.get(0);
			for(int i = 1;i<sortedPoints.size();i++)
			{
				t=sortedPoints.get(i);
				
				if(t.getX()!=sortedPoints.get(i+1).getX())
				{
					if(t.getY() != leftanchor1.getY() )
					{
					leftanchor2= t;
					break;
					}
				}
				leftanchor3=t;
			}
			
			if(leftanchor1.getY()< leftanchor2.getY())
				{
					MinXMinY=leftanchor1;
					MinXMaxY=leftanchor2;
				}
			else
				{	MinXMinY=leftanchor2;
					MinXMaxY=leftanchor1;
					if(leftanchor3.getY()<leftanchor2.getY())
					{
						MinXMinY=leftanchor3;
					}
				}
			
		}//left anchor case
			
		else if((p.getX()== MaxXMaxY.getX() && p.getY()==MaxXMaxY.getY()) || (p.getX()==MaxXMinY.getX() && p.getY()==MaxXMinY.getY()))//right anchor case
		{
			rightanchor1= sortedPoints.get(sortedPoints.size()-1);//initialize as last element
			rightanchor2= sortedPoints.get(sortedPoints.size()-2);//initialize as last element
			rightanchor3= sortedPoints.get(sortedPoints.size()-1);//initialize as last element
			for(int i= sortedPoints.size()-2;i>0;i--)
			{
				t=sortedPoints.get(i);
				
				if(t.getX()!=sortedPoints.get(i-1).getX())
				{
					if(t.getY() != rightanchor1.getY() )
					{
					rightanchor2= t;
					break;
					}
				}
				rightanchor3=t;
			}
			System.out.println("rightanchor1"+ rightanchor1.getX() + " , " + rightanchor1.getY());
			System.out.println("rightanchor2"+ rightanchor2.getX() + " , " + rightanchor2.getY());
			if(rightanchor1.getY() > rightanchor2.getY())
			 {	 MaxXMinY=rightanchor2;
				 MaxXMaxY=rightanchor1;
				 
			 }
			 else
			 {
				 MaxXMinY=rightanchor1;
				 MaxXMaxY=rightanchor2;
				 if(rightanchor3.getY()>rightanchor2.getY())
				 {
					 MaxXMaxY=rightanchor3;
				 }
			 }
			
		}
		
		
		updateSlopes();
		recomputeUpper();
		recomputeLower();
		drawConvexHull();
		
		System.out.println("MinXMaxY" + MinXMaxY.getX()+" , " + MinXMaxY.getY());
		System.out.println("MaxXMaxY" + MaxXMaxY.getX()+" , " + MaxXMaxY.getY());
		System.out.println("MinXMinY" + MinXMinY.getX()+" , " + MinXMinY.getY());
		System.out.println("MaxXMinY" + MaxXMinY.getX()+" , " + MaxXMinY.getY());
		
	}
	
	public void drawConvexHull() {
		// copies in upperHull and reverse copy in lower hull + pointMinXMaxY to complete the Hull again
		int s;
		chPoints.clear();
		if(points.size()<3)
		{	
			chPoints.addAll(points);
		}
		else
		{
			
			chPoints.addAll(UpperHull);
			s = LowerHull.size()-1;
			while(s>=0)
			{
				chPoints.add(LowerHull.get(s));
				s--;
			}
			
			
			chPoints.add(MinXMaxY);
			//final concavity removal around the anchor points
		/*if(MinXMaxY.getX() < MinXMinY.getX())
		{
			 if(slope(MinXMaxY,MinXMinY)>slope(MinXMaxY,chPoints.get(chPoints.indexOf(MinXMinY)-1)))
			 {
				 chPoints.remove(MinXMinY);
			 }
			 chPoints.add(MinXMaxY);
		}
		else
		{
			 if(slope(MinXMinY,MinXMaxY)<slope(MinXMinY,UpperHull.get(UpperHull.indexOf(MinXMaxY)+1)))
			 {
				 chPoints.remove(MinXMaxY);
			 }
			chPoints.add(chPoints.get(0));
		}*/
			
		
		}
		
		/*if(MaxXMaxY.getX()>MaxXMinY.getX())
		{
			if(slope(LowerHull.get(LowerHull.indexOf(MaxXMinY)-1),MaxXMaxY)<slope(LowerHull.get(LowerHull.indexOf(MaxXMinY)-1),MaxXMinY))
			 {
				 chPoints.remove(MaxXMinY);
			 }
		}
		else
		{
			if(slope(UpperHull.get(UpperHull.indexOf(MaxXMaxY)-1),MaxXMinY)>slope(UpperHull.get(UpperHull.indexOf(MaxXMaxY)-1),MaxXMaxY))
			{
				chPoints.remove(MaxXMaxY);
			}
		}*/
			
		//append upper and then lower hull to chPoints
		
		
		XYPlotter chart = new XYPlotter("Array List", points, chPoints);
	    chart.pack();          
	    RefineryUtilities.centerFrameOnScreen(chart);
	    chart.setVisible(true);
	}
	
	
	
	
	public void SortList()//this function sort by x and then y , by picking first point named current, compare it to rest of cloned list 
	// if its lowest in term of both x and y it gets deleted from the cloned list and added to sorted list if not it gets replaced by the lowest.

	{	
		ArrayList<Point2D> ClonePoints = new ArrayList<Point2D>(points);
		
		
		Point2D current = ClonePoints.get(0);
		int i=0;
		sortedPoints.clear();
	while(i<points.size())// while sorted list size not equal to original list size
	{	current= ClonePoints.get(0);
		
		for(Point2D temp : ClonePoints)
		{
			if(temp.getX() < current.getX())
			{
				current = temp;
			}
			else if (temp.getX()==current.getX() && temp.getY() < current.getY())
			{
				current=temp;
			}
		}
	
		sortedPoints.add(current);
		ClonePoints.remove(current);
		i++;
	}//end of while
	System.out.println(" done sorting:" + sortedPoints.size() );//debugging Statement to show sorted list
	}//end of sort
	
	
	
	private double slope(Point2D p1, Point2D p2)//find slope between two points
	{	double result;
		result = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
		return  result;
	}//end of slope
	
	private void MakeUpperHull()//make the edgemap for the upper hull
	{	Point2D t;
	
		Point2D leftNeighbor;
		Point2D rightNeighbor;
		Point2D tallest=MinXMinY;
		Point2D rtallest = MaxXMinY;
		int j;
		
		for(int i = sortedPoints.indexOf(MinXMaxY)+1;i<sortedPoints.indexOf(MaxXMaxY);i++)
		{
			t=sortedPoints.get(i);

			if(slope(MinXMaxY, t) > upperSlope)//if slope from of the  point being iterated > slope from the two top anchor point, 
												  
			{
			
				if(t.getY()>tallest.getY())//find tallest point
				{
					tallest=t;
					
				}

				UpperEdgeMap.add(t);//add the potential point to edge map 
				
			}
			
		}

	
		UpperEdgeMap.add(MaxXMaxY);//add right anchor point to edgemap
		j=UpperEdgeMap.indexOf(tallest);
		
		UpperHull.add(MinXMaxY);//add first point of Hull
		
		
		//now edge map is avail, we can take away concavity and points on the same line to make it convex
		leftNeighbor = MinXMaxY;
		for(int i = 1; i<j;i++)
		{
			t=UpperEdgeMap.get(i);
			if(slope(leftNeighbor,t)>slope(leftNeighbor,tallest)&& slope(leftNeighbor,t)>slope(leftNeighbor,UpperEdgeMap.get(i+1)))
			{	
				UpperHull.add(t);
				leftNeighbor=t;
			}

		}//end of for
		
		UpperHull.add(tallest);
		
		for(int i = UpperEdgeMap.size()-1;i>=j;i--)
		{	t=UpperEdgeMap.get(i);
			if(t.getY()>rtallest.getY())
			{
				rtallest = t;
			}
		}
		System.out.println("rtallest" + rtallest.getX() + " ," + tallest.getY());
		j=UpperEdgeMap.indexOf(rtallest);
		
		if(tallest!=rtallest)
		{
			UpperHull.add(rtallest);
		}
		rightNeighbor= MaxXMaxY;
		for(int i = UpperEdgeMap.size()-2;i>j;i--)
		{	t = UpperEdgeMap.get(i);
			
			{
				if(slope(rightNeighbor,t)<slope(rtallest,rightNeighbor) && slope(rightNeighbor,t)<slope(rightNeighbor,UpperEdgeMap.get(i-1)))
				{	
				rightUpperHull.add(t);//add that point to the rightUpperhull
				rightNeighbor=t;
				}
			}
		}
		
		
		for (int i = rightUpperHull.size()-1; i>=0;i--)
		{
			UpperHull.add(rightUpperHull.get(i));
		}
		
		
		UpperHull.add(MaxXMaxY);//add last point of upper hull
		
	}//end of upper hull
	
	
	
	private void MakeLowerHull()//same as upper but inverse logic since we're looking at negative side
	{

	Point2D t;
	Point2D leftNeighbor;
	Point2D rightNeighbor;
	Point2D rtallest = MaxXMaxY;
	int j;
	Point2D tallest=MinXMaxY;
	
	
	for(int i = sortedPoints.indexOf(MinXMinY)+1;i<sortedPoints.indexOf(MaxXMinY);i++)
	{
		t=sortedPoints.get(i);

		if(slope(MinXMinY, t) < lowerSlope)//if slope from of the  point being iterated < slope from the two bottom anchor point, 
											  
		{
		
			if(t.getY()<tallest.getY())//find tallest point
			{
				tallest=t;
				
			}

			LowerEdgeMap.add(t);//add the potential point to edge map 
			
		}
		
	}
	j=LowerEdgeMap.indexOf(tallest);

	LowerEdgeMap.add(MaxXMinY);//add right anchor point to edgemap
	
	
	LowerHull.add(MinXMinY);//add first point of Hull
	
	//now edge map is avail, we can take away concavity and points on the same line to make it convex
			leftNeighbor = MinXMinY;
			for(int i = 1; i<j;i++)
			{
				t=LowerEdgeMap.get(i);
				if(slope(leftNeighbor,t)<slope(leftNeighbor,tallest)&& slope(leftNeighbor,t)<slope(leftNeighbor,LowerEdgeMap.get(i+1)))
				{	
					LowerHull.add(t);
					leftNeighbor=t;
				}

			
				
				
			}//end of for
			
			LowerHull.add(tallest);
			
			for(int i = LowerEdgeMap.size()-1;i>=j;i--)
			{	t=LowerEdgeMap.get(i);
				if(t.getY()<rtallest.getY())
				{
					rtallest = t;
				}
			}
			if(tallest!=rtallest)
			{
				LowerHull.add(rtallest);
			}	
			j=LowerEdgeMap.indexOf(rtallest);
			
			rightNeighbor= MaxXMinY;
			for(int i = LowerEdgeMap.size()-2;i>j;i--)
			{	t = LowerEdgeMap.get(i);
				
				{
					if(slope(rightNeighbor,t)>slope(rtallest,rightNeighbor) && slope(rightNeighbor,t)>slope(rightNeighbor,LowerEdgeMap.get(i-1)))
					{	
					rightLowerHull.add(t);//add that point to the rightUpperhull
					rightNeighbor=t;
					}
				}
			}
			
			
			for (int i = rightLowerHull.size()-1; i>=0;i--)
			{
				LowerHull.add(rightLowerHull.get(i));
			}
			

			
			LowerHull.add(MaxXMinY);//add last point of lower  hull
	}//end Make Lower
	private void updateSlopes()
	{
		upperSlope= slope(MinXMaxY,MaxXMaxY);
		lowerSlope= slope(MinXMinY,MaxXMinY);//find slope of the anchors
	}
	
	
	
	private void recomputeUpper()
	{	Point2D t;
		Point2D tallest=MinXMinY;
		Point2D rtallest=MaxXMinY;
		Point2D leftNeighbor;
		Point2D rightNeighbor;
		int j;
		
		
		
		UpperEdgeMap.clear();
		UpperEdgeMap.add(MinXMaxY);
		for(int i = sortedPoints.indexOf(MinXMaxY)+1;i<sortedPoints.indexOf(MaxXMaxY);i++)
		{
			t=sortedPoints.get(i);

			if(slope(MinXMaxY, t) > upperSlope)//if slope from of the  point being iterated > slope from the two top anchor point, 
												  
			{
			
				if(t.getY()>tallest.getY())//find tallest point
				{
					tallest=t;
					
				}

				UpperEdgeMap.add(t);//add the potential point to edge map 
				
			}
			
		}
		UpperEdgeMap.add(MaxXMaxY);
		/*for(Point2D temp:UpperEdgeMap)
		{
			System.out.println(temp.getX() + " , " + temp.getY());
		}*/
		
		
		
		/*for(int i=1;i<UpperEdgeMap.size()-1;i++)
			
		{
			t=UpperEdgeMap.get(i);
			if(t.getY()>tallest.getY())
			{
				tallest=t;
			}
			
		}*/
	/*System.out.println("UpperEdgeMap");
	for(Point2D temp:UpperEdgeMap)
		
	{
		System.out.println( temp.getX() +  " , " +  temp.getY());
	}*/
		
		UpperHull.clear();
		UpperHull.add(MinXMaxY);
		leftNeighbor = MinXMaxY;
		j=UpperEdgeMap.indexOf(tallest);
		
		for(int i = 1; i<j;i++)
		{
			t=UpperEdgeMap.get(i);
			if(slope(leftNeighbor,t)>slope(leftNeighbor,tallest)&& slope(leftNeighbor,t)>slope(leftNeighbor,UpperEdgeMap.get(i+1)))
			{	
				UpperHull.add(t);
				leftNeighbor=t;
			}

		}//end of for
		
		UpperHull.add(tallest);
		rightUpperHull.clear();
		
		for(int i = UpperEdgeMap.size()-1;i>j;i--)
		{	t=UpperEdgeMap.get(i);
			if(t.getY()>rtallest.getY())
			{
				rtallest = t;
			}
		}


j=UpperEdgeMap.indexOf(rtallest);
System.out.println("rtallest" + rtallest.getX() + ","+  rtallest.getY() +" index of " + j);
		if(tallest!=rtallest)
		{
			UpperHull.add(rtallest);
		}
		/*rightNeighbor=rtallest;
		
		for(int i =j+1;i<UpperEdgeMap.size()-1;i++)
		{	t = UpperEdgeMap.get(i);
			
			{
				if(slope(rightNeighbor,t)>slope(rightNeighbor,MaxXMaxY ) && slope(rightNeighbor,t)>slope(rightNeighbor,UpperEdgeMap.get(i+1)))
				{	
				rightUpperHull.add(t);//add that point to the rightUpperhull
				rightNeighbor=t;
				}
			}
		}*/

		rightNeighbor= MaxXMaxY;
		for(int i = UpperEdgeMap.size()-2;i>j;i--)
		{	t = UpperEdgeMap.get(i);
			System.out.println("checking " + t.getX() +  " , " +  t.getY());
			{
				if(slope(rightNeighbor,t)<slope(rtallest,rightNeighbor) && slope(rightNeighbor,t)<slope(rightNeighbor,UpperEdgeMap.get(i-1)))
				{	
				rightUpperHull.add(t);//add that point to the rightUpperhull
				rightNeighbor=t;
				}
			}
		}
		
		
		
		for (int i = rightUpperHull.size()-1; i>=0;i--)
		{
			UpperHull.add(rightUpperHull.get(i));
		}
		
		
		UpperHull.add(MaxXMaxY);//add last point of upper hull
		

		
	}//end of recompute upper hull
	
	private void recomputeLower()
	{	Point2D t;
		Point2D tallest= MinXMinY;
		Point2D rtallest; ;
		Point2D leftNeighbor;
		Point2D rightNeighbor;
		int j;
	
		LowerEdgeMap.clear();
		LowerEdgeMap.add(MinXMinY);
		
		for(int i = sortedPoints.indexOf(MinXMinY)+1;i<sortedPoints.indexOf(MaxXMinY);i++)
		{
			t=sortedPoints.get(i);

			if(slope(MinXMinY, t) < lowerSlope)//if slope from of the  point being iterated < slope from the two bottom anchor point, 
												  
			{
			
				if(t.getY()<tallest.getY())//find tallest point
				{
					tallest=t;
					
				}

				LowerEdgeMap.add(t);//add the potential point to edge map 
				
			}
			
		}
		j=LowerEdgeMap.indexOf(tallest);

		LowerEdgeMap.add(MaxXMinY);//add right anchor point to edgemap
		
		
		for(int i=1;i<LowerEdgeMap.size()-1;i++)
			
		{
			t=LowerEdgeMap.get(i);
			if(t.getY()<tallest.getY())
			{
				tallest=t;
			}
			
		}
		LowerHull.clear();
		LowerHull.add(MinXMinY);
		leftNeighbor = MinXMinY;
		j=LowerEdgeMap.indexOf(tallest);
		for(int i = 1; i<j;i++)
		{
			t=LowerEdgeMap.get(i);
			if(slope(leftNeighbor,t)<slope(leftNeighbor,tallest)&& slope(leftNeighbor,t)<slope(leftNeighbor,LowerEdgeMap.get(i+1)))
			{	
				LowerHull.add(t);
				leftNeighbor=t;
			}

		
			
			
		}//end of for
		
		LowerHull.add(tallest);
		rtallest = LowerEdgeMap.get(LowerEdgeMap.size()-1);
		for(int i = LowerEdgeMap.size()-1;i>=j;i--)
		{	t=LowerEdgeMap.get(i);
			if(t.getY()<rtallest.getY())
			{
				rtallest = t;
			}
		}
	
		if(tallest!=rtallest)
		{
			LowerHull.add(rtallest);
		}	
		j=LowerEdgeMap.indexOf(rtallest);
		rightLowerHull.clear();
		rightNeighbor= MaxXMinY;
		for(int i = LowerEdgeMap.size()-2;i>j;i--)
		{	t = LowerEdgeMap.get(i);
			
			{
				if(slope(rightNeighbor,t)>slope(rtallest,rightNeighbor) && slope(rightNeighbor,t)>slope(rightNeighbor,LowerEdgeMap.get(i-1)))
				{	
				
				rightLowerHull.add(t);//add that point to the rightUpperhull
				rightNeighbor=t;
				}
			}
		}
		
		
		for (int i = rightLowerHull.size()-1; i>=0;i--)
		{
			LowerHull.add(rightLowerHull.get(i));
		}
		

		
		LowerHull.add(MaxXMinY);//add last point of lower  hull
		
	}//end of recompute lower
}//end of class
