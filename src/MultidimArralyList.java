import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MultidimArralyList extends DTWDistanceArrayList {
	public static ArrayList<ArrayList<Double>> kinematic = new ArrayList<ArrayList<Double>>();
    public static ArrayList<Double> XY = new ArrayList<Double>(); 
    
    //function to print the data of the Arraylist
    public void printDouble( ArrayList<ArrayList<Double>> arr)
    {
    	for(int i=0;i<arr.size();i++)
        {
    		System.out.println(" XY values = "+arr.get(i));
        }	
    }
    //function to get gesture multi-arraylist data
   public ArrayList<ArrayList<Double>> getGestureValues(String gfileLoc) throws IllegalArgumentException, IOException
   {
	   BufferedReader br = new BufferedReader(new FileReader(gfileLoc)); 
	    String line,a,b;
	    double x,y;
	    ArrayList<ArrayList<Double>> GestureValues =new ArrayList<ArrayList<Double>>();
	    ArrayList<Double> GXY=new ArrayList<Double>();
	    int i=0;
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   b=values[1];
	    	   x=Double.parseDouble(a);
	    	   y=Double.parseDouble(b);
	    	   //System.out.println("y="+y);
	    	   GXY.add(x);
	    	   GXY.add(y);
	    	   GestureValues.add(GXY);
	    	   GXY=new ArrayList<Double>();
	    	   //System.out.println("Gy="+Garr.GYValue.get(i));
	       }
	       i++;
	    }
	    br.close();
	    //System.out.println("Gy  size="+Garr.GYValue.size());
	    return GestureValues;	   
   }    
   //functio to calculate the comparison array from the other kinematic file
   public ArrayList<ArrayList<Double>> comparisonArrayValues(String otherKfileloc,int start,int end) throws NumberFormatException, IOException
   { 
	    BufferedReader br = new BufferedReader(new FileReader(otherKfileloc)); 
	    String line,a,b;
	    int size=end-start;
	    double x,y;
	    ArrayList<ArrayList<Double>>  kotherXY =new ArrayList<ArrayList<Double>>();
	    ArrayList<Double> xy=new ArrayList<Double>();
	    int i=1,j=0;
	    int k=start;
	    while (((line = br.readLine()) != null)&&(j<size)&&(k>=start)&&(k<=end)) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   b=values[1];
	    	   x=Double.parseDouble(a);
	    	   //System.out.println("x="+x);
	    	   y=Double.parseDouble(b);
	    	   //System.out.println("y="+y);
	    	   xy.add(x);
	    	   xy.add(y);
	    	   kotherXY.add(xy);
	    	   xy=new ArrayList<Double>();
	       }
	       i++;
	       j++;
	       k++;
	    }
	    br.close();
	    return kotherXY;  
   }
   //function to calculate DTW distance  by comparing each single XY arrays individually
   public ArrayList<ArrayList<Double>> distanceDTW(ArrayList<ArrayList<Double>> Gesture ,ArrayList<ArrayList<Double>> Kinematic)
   {
	   ArrayList<ArrayList<Double>> dtwd=new ArrayList<ArrayList<Double>>();
	   ArrayList<Double> dtwxy=new ArrayList<Double>();
	   double dtwx,dtwy;
		//calculating distance for X coordinate
		
		ArrayList<Double> kX1=new ArrayList<Double>();
		ArrayList<Double> GX1=new ArrayList<Double>();
		ArrayList<Double> kY1=new ArrayList<Double>();
		ArrayList<Double> GY1=new ArrayList<Double>();
		
		int sizekx;
		//System.out.println(sizekx);
		int sizeGx;
		//System.out.println(sizeGx);
		int sizeky;
		//System.out.println(sizeky);
		int sizeGy;
		//System.out.println(sizeGy);
		
		double[] kX;
		double[] GX;
		double[] kY;
		double[] GY;
		
		for(int i=0;i<Kinematic.size();i++)
		{  
			kX1.add(Kinematic.get(i).get(0)); //get all X values
			//System.out.println(kX1.get(i));
		}	
		//put value into the array
		sizekx=kX1.size();
		kX=new double[sizekx];
		for(int i=0;i<sizekx;i++)
		{
			kX[i]=kX1.get(i);
		}
			
		for(int i=0;i<Gesture.size();i++)
		{  
			GX1.add(Gesture.get(i).get(0)); //get all X values
		}
		sizeGx=GX1.size();
		GX=new double[sizeGx];
		for(int i=0;i<sizeGx;i++)
		{
			GX[i]=GX1.get(i);
		}
		//function from DTW class to calculate x distance
		dtwx=DTW.DTWDistance(kX, GX);
		
		//calculating Y distance
		for(int i=0;i<Kinematic.size();i++)
		{  
			kY1.add(Kinematic.get(i).get(1)); //get all X values
		}
		sizeky=kY1.size();
		kY=new double[sizeky];
		for(int i=0;i<sizeky;i++)
		{
			kY[i]=kY1.get(i);
		}
		
		for(int i=0;i<Gesture.size();i++)
		{  
			GY1.add(Gesture.get(i).get(1)); //get all X values
		}
		sizeGy=GY1.size();
		GY=new double[sizeGy];
		for(int i=0;i<sizeGy;i++)
		{
			GY[i]=GY1.get(i);
		}
		dtwy=DTW.DTWDistance(kY, GY);
		//add  distance to array list
		dtwxy.add(dtwx);
		//System.out.println("x distance="+dtwx);
		dtwxy.add(dtwy);
		dtwd.add(dtwxy);
		
		//printDouble(dtwd);
		
		return dtwd;	
   }
   //function to calculate DTW distance values
   public ArrayList<ArrayList<Double>> calculateDTWdistance(String gfileloc,String tfileloc,String otherkfile,ArrayList<Integer> start,ArrayList <Integer> end) throws IllegalArgumentException, IOException
   {
	   ArrayList<ArrayList<Double>> gestureArr=new ArrayList<ArrayList<Double>>();
		gestureArr=getGestureValues(gfileloc);//gesture  array for comparision
		//gestureArr.print(gestureArr);
		int len2=DTW.calculateFileLength(tfileloc);
		ArrayList<ArrayList<Double>> DTWdistance=new ArrayList<ArrayList<Double>>();// it contains all DTW distance for the comparison
		int i=0,j=0;
		while((i<len2))
		{    
			ArrayList<ArrayList<Double>> kdata=new ArrayList<ArrayList<Double>>();
            kdata=comparisonArrayValues(otherkfile,start.get(i),end.get(i));
            //printDouble(kdata);
			//System.out.println("begin "+start.get(i)+" end "+end.get(i)+"i="+i);
			DTWdistance.addAll(distanceDTW(kdata,gestureArr));
			//System.out.println("dtw"+j+" no. value ="+DTWdistance[j]);
			i++;
			j++;
		}
		
		return DTWdistance;	
   }
   //function to recognize gesture
   public void recognizeGesture(ArrayList<ArrayList<Double>> DTWdistance,String tfileloc) throws IOException
   {
	   double minx,miny;
	   int posx = 0,posy = 0;
	   minx=DTWdistance.get(0).get(0);
	   miny=DTWdistance.get(0).get(1);
	   for(int i=1;i<DTWdistance.size();i++)
	   {
		   if(minx>DTWdistance.get(i).get(0))
		   {
			   minx=DTWdistance.get(i).get(0);
			   posx=i;
		   }
		   else
		   {
			   //minx=minx;
			   posx=0;
		   }
		   
		   
	   }
	   for(int i=1;i<DTWdistance.size();i++)
	   {
		   if(miny>DTWdistance.get(i).get(1))
		   {
			   miny=DTWdistance.get(i).get(1);
			   posy=i;
		   }
		   else
		   {
			   posy=0;
		   }
	   }
	   System.out.println("dtw min X value="+minx+"position="+posx);
		System.out.println("dtw min Y value="+miny+"position="+posy);
		posx=posx;
		System.out.println("Recognized gesture name="+getGestureName(tfileloc,posx));
	   
   }
    
    //main body
    public static void main(String[] args) throws NumberFormatException, IOException {
    	String otherKfileloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B003.txt";
		String tfileloc="C:/Users/user/Documents/Intership/Knot_Tying/transcriptions/Knot_Tying_B003.txt";
		String gfileloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Gesture_Data/Knot_Tying/Knot_Tying_B003/gestureB003G14_7.txt";
		
		MultidimArralyList mobj=new MultidimArralyList();
		
		ArrayList <Integer> startValue=new ArrayList <Integer>();
		ArrayList <Integer> endValue=new ArrayList <Integer>();
		ArrayList <String> gestureName=new ArrayList <String>();
		DTWDistanceArrayList dtwDitances[];
		ArrayList<ArrayList<Double>> gestureValues =new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> kotherValues =new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> dtwDistances=new ArrayList<ArrayList<Double>>();
		//calculate gesture values start/end and names
		mobj.getGestureStartEndValues(tfileloc, gestureName, startValue, endValue);
		//mobj.print(startValue);
		//mobj.print(endValue);
		//calculate gesture values
		gestureValues=mobj.getGestureValues(gfileloc);
		for(int i=0;i<gestureValues.size();i++)
        {
    		//System.out.println("gesture X values = "+gestureValues.get(i).get(0));
    		//System.out.println("gesture Y values = "+gestureValues.get(i).get(1));
        }
	    //calculate comparion array
		//kotherValues=mobj.comparisonArrayValues(otherKfileloc, 8, 100);
		//mobj.printDouble(kotherValues);
		//System.out.println("size="+kotherValues.size());
		
	   //calculate dtw distance 
		dtwDistances=mobj.calculateDTWdistance(gfileloc, tfileloc, otherKfileloc, startValue, endValue);
		mobj.printDouble(dtwDistances);
		mobj.recognizeGesture(dtwDistances,tfileloc);
    	
    }

}
