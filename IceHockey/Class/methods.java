package Class;
import java.awt.Point;
import Interface.IPlayersFinder;



public class methods implements IPlayersFinder{
	
	public boolean cover(Point[] allIndexArray, int size, int x, int y) 
	{
	    for (int i = 0; i < size; i++) 
	    {
	      if (allIndexArray[i].x == x && allIndexArray[i].y == y) 
	      {
	    	  return true; 
	      }
	    }
	    return false;
	 }
	
	public void returnSurroundings (String[] photo, Point[] backArray,int[] counter,Point [] allIndexArray ,  int team, int i, int j) 
	{
			
			// check number equal team 
			allIndexArray[counter[1]++] = new Point(i, j);
			char cmpr = Character.forDigit(team, 10);
			if (j < photo.length-1 && photo[j+1].charAt(i) == cmpr && cover(allIndexArray, counter[1], i, j+1) == false) 
			{
				Point temp = new Point(i, j+1);
				backArray [counter[0]] = temp ;
				counter[0]++;
				returnSurroundings (photo, backArray, counter, allIndexArray, team, i, j+1);
			}
			if (j > 0 && photo[j-1].charAt(i) == cmpr && cover(allIndexArray, counter[1], i, j-1) == false) 
			{
				Point temp = new Point(i, j-1);
				backArray [counter[0]] = temp;
				counter[0]++;
				returnSurroundings (photo, backArray, counter, allIndexArray, team, i, j-1);
			}
			if (i < photo[0].length()-1 && photo[j].charAt(i+1) == cmpr && cover(allIndexArray, counter[1], i+1, j) == false) 
			{
				Point temp = new Point(i+1, j);
				
				backArray [counter[0]] = temp ;
				counter[0]++;
				returnSurroundings (photo, backArray, counter, allIndexArray, team, i+1, j);
			}
			if (i > 0 && photo[j].charAt(i-1) == cmpr && cover(allIndexArray, counter[1], i-1, j) == false) 
			{
				Point temp = new Point(i-1, j);
				backArray [counter[0]] = temp;
				counter[0]++;
				returnSurroundings (photo, backArray, counter, allIndexArray, team, i-1, j);
			}
	}

	
	public Point findCenter (String[] photo, Point[] backArray, int size) 
	{
		int Xmax=backArray[0].x, Xmin=backArray[0].x, Ymax=backArray[0].y, Ymin=backArray[0].y;
		Point center = new Point() ;
		for (int i = 0 ; i < size ; i++) 
		{
			if (Xmax <= backArray[i].x ) 
			{
				Xmax = backArray[i].x;
			}
			if (Xmin >= backArray[i].x ) 
			{
				Xmin = backArray[i].x;
			}
			 if (Ymax <= backArray[i].y ) 
			{
				Ymax = backArray[i].y;
			}
			if (Ymin >= backArray[i].y ) 
			{
				Ymin = backArray[i].y;
			}
		}
		Xmax = Xmax * 2 ;
		Xmin = Xmin * 2 ;
		Ymax = Ymax * 2 ;
		Ymin = Ymin * 2 ;
		center.x = ((Xmax+Xmin)/2)+1 ;
		center.y = ((Ymax+Ymin)/2)+1 ;
		return center;
	}

	@Override
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		if (photo == null || photo.length == 0) {
			return null;
		}
	
		Point[] backArray = new Point[photo.length * photo[0].length()] ;
		Point[] allIndexArray = new Point[photo.length * photo[0].length()];
		Point[] centers = new Point[photo.length * photo[0].length() / threshold];
		int [] counter =  new int[3] ;
		counter [0] = 0;
		counter [1] = 0;
		counter [2]= 0;
		for (int i = 0; i < photo.length; i++) 
		{
			for (int j = 0; j < photo[0].length(); j++) 
			{
				if (cover(allIndexArray, counter[1], j, i)== false && photo[i].charAt(j) == Character.forDigit(team, 10)) 
				{
					counter[0] = 0;
					backArray[counter[0]] = new Point (j, i);
					counter[0]++;
					returnSurroundings(photo, backArray, counter, allIndexArray, team, j, i);
					if(counter[0] * 4 >= threshold) 
					{
						centers[counter[2]++] = findCenter(photo, backArray, counter[0]);
					}
				}
			}
		}
		// sorting centers array
		int c = counter[2]; 
		for (int i = 1; i < c; ++i) { 
            Point pass = centers[i]; 
            int j = i - 1; 
            while (j >= 0 && (centers[j].x > pass.x || (centers[j].x == pass.x && centers[j].y > pass.y ))) { 
            	centers[j + 1] = centers[j]; 
                j = j - 1; 
            } 
            centers[j + 1] = pass; 
		}
		Point[] finalCenters = new Point[c];
		for (int i = 0; i < c; ++i) { 
            finalCenters[i] = centers[i];
		}
		return finalCenters;
	}
}
