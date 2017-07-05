import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestureComparison {
	public static ArrayList<ArrayList<Double>> kinematic = new ArrayList<ArrayList<Double>>();
    public static ArrayList<Double> all77values = new ArrayList<Double>(); //it holds all the 77 value of each frame of the kinematic data values
    
    //function to print the data of the Arraylist
    public void printDouble( ArrayList<ArrayList<Double>> arr)
    {
    	for(int i=0;i<arr.size();i++)
        {
    		System.out.println(" All 77 values for a Frame = "+arr.get(i));
        }	
    }
    //function to get gesture multi-arraylist data
   public ArrayList<ArrayList<Double>> getGestureValues(String gfileLoc) throws IllegalArgumentException, IOException
   {
	   BufferedReader br = new BufferedReader(new FileReader(gfileLoc)); 
	    String line,a,b;
	    double gvalue;
	    ArrayList<ArrayList<Double>> GestureValues =new ArrayList<ArrayList<Double>>();
	    ArrayList<Double> G77values=new ArrayList<Double>();
	    int i=0;
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	      // if(values.length<=77)
	       { 
	    	   for(int j=0;j<values.length;j++)
	    	   {
	    	   a=values[j];
	    	   gvalue=Double.parseDouble(a);
	    	   //System.out.println("y="+y);
	    	   G77values.add(gvalue);
	    	   
	    	   }
	    	  // System.out.println("G77values length"+G77values.size());
	    	   GestureValues.add(G77values);
	    	   G77values=new ArrayList<Double>();
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
	    ArrayList<ArrayList<Double>>  kother =new ArrayList<ArrayList<Double>>();
	    ArrayList<Double> allvalue=new ArrayList<Double>();
	    int i=1,j=0;
	    int k=start;
	    while (((line = br.readLine()) != null)&&(j<size)&&(k>=start)&&(k<=end)) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       //if(values.length<=77)
	       { 
	    	   for(int m=0;m<values.length;m++)
	    	   {
	    	   a=values[m];
	    	   x=Double.parseDouble(a);
	    	   //System.out.println("x="+x);
	    	   allvalue.add(x);
	    	   
	    	   }
	    	  // System.out.println(allvalue);
	    	   //System.out.println("kother length"+allvalue.size());
	    	   kother.add(allvalue);
	    	   allvalue=new ArrayList<Double>();
	       }
	       i++;
	       j++;
	       k++;
	    }
	    br.close();
	    return kother;  
   }
   //function to calculate DTW distance  by comparing each individual arrays individually
   //but here we have two arraylists of 77 values and we have to find 77 DTW distances between each value
   public ArrayList<ArrayList<Double>> distanceDTW(ArrayList<ArrayList<Double>> Gesture ,ArrayList<ArrayList<Double>> Kinematic)
   {   
	   //holds 77 DTW distance form the all 77 values
	   ArrayList<ArrayList<Double>> dtwd=new ArrayList<ArrayList<Double>>();
	   ArrayList<Double> allDTWdistance=new ArrayList<Double>();   //77 value
	   double dtwData;
		//calculating distance for X coordinate
		
		ArrayList<Double> kvalues=new ArrayList<Double>();
		ArrayList<Double> Gvalues=new ArrayList<Double>();
		
		//double[] karr;
		//double[] Garr;
		//kinematic data value for one dimention
		//int sizekarr,sizeGarr;
		int m = 0,n = 0;
		//calculating dtw distance
	   while((m<Kinematic.size())||(n<Gesture.size()))   //loop over the number of row in two array lists
	   {   int i=0,j=0;
	       double karr[],Garr[];
		   while((i<Kinematic.get(m).size())||(j<Gesture.get(n).size()))
		   {
			   kvalues=get1Ddata(Kinematic,i);
			   System.out.println(kvalues);
			   Gvalues=get1Ddata(Gesture,j);
			   System.out.println(Gvalues);
			   karr=new double[kvalues.size()];          //create array of Kvalue size
			   Garr=new double[Gvalues.size()];
			   karr=assinArray(kvalues);
			   Garr=assinArray(Gvalues);
			   dtwData=DTW.DTWDistance(karr, Garr);           //get the dtw distance for a dimension
			   allDTWdistance.add(dtwData); 
			   			   
		   }
		   System.out.println(allDTWdistance);
		   dtwd.add(allDTWdistance);                             //add calculated dtw to the main DTW arrayList
		   allDTWdistance=new ArrayList<Double>();               //reinitionalize the DTW arrayList which holds the values for a single frame i.e all 77 values
	   }
		   
		
		return dtwd;	
   }
   
   //function to assin value from arrayList to array
   public double[] assinArray(ArrayList<Double> arrl)
   {
	   double[] arr=new double[arrl.size()];
	   for(int i=0;i<arr.length;i++)
	   {
		   arr[i]=arrl.get(i);
	   }
	   return arr;
   }
   
   
   
   
   //function that will return a 1D data (ArrayList)like all X values or all Y values from the multi dimensional Kinematic ArrayList
   
   public ArrayList<Double> get1Ddata(ArrayList<ArrayList <Double>>kinematic,int col)
   {
	   ArrayList<Double> kdata=new ArrayList<Double>();
	   double data;
	   for(int row=0;row<kinematic.size();row++)
	   {
		   data=kinematic.get(row).get(col);
		   kdata.add(data);
	   }
	 //  System.out.println(kdata);
	   return kdata;
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
   public String recognizeGesture(ArrayList<ArrayList<Double>> DTWdistance,String tfileloc) throws IOException
   {
	   double minx,miny;
	   String gname;
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
		gname=getGestureName(tfileloc,posx);
		System.out.println("Recognized gesture name="+gname);
		return gname;
	   
   }
    
    //main body
    public static void main(String[] args) throws NumberFormatException, IOException {
    	String otherKfileloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B003.txt";
		String tfileloc="C:/Users/user/Documents/Intership/Knot_Tying/transcriptions/Knot_Tying_B003.txt";
		String gfileloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Gesture_Data/Knot_Tying/Knot_Tying_B003/gestureB003G15_8.txt";
		
		GestureComparison gobj=new GestureComparison();
		
		ArrayList <Integer> startValue=new ArrayList <Integer>();
		ArrayList <Integer> endValue=new ArrayList <Integer>();
		ArrayList <String> gestureName=new ArrayList <String>();
		DTWDistanceArrayList dtwDitances[];
		ArrayList<ArrayList<Double>> gestureValues =new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> kotherValues =new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> dtwDistances=new ArrayList<ArrayList<Double>>();
		String gname;
		//String actualgesture=gfileloc.substring(123, 126);
		int ind=gfileloc.indexOf("G1");
		String actualgesture=gfileloc.substring(ind, ind+3);
		
		//get geture  arrayLit value
		gestureValues=gobj.getGestureValues(gfileloc);
		//gobj.printDouble(gestureValues);
		//comparison array values
		kotherValues=gobj.comparisonArrayValues(otherKfileloc, 0, 5);
		//gobj.get1Ddata(kotherValues, 75);
		gobj.printDouble(kotherValues);

		/*for(int i=0;i<kotherValues.size();i++)
		{   System.out.println(kotherValues.get(i).size());
			for(int j=0;j<kotherValues.get(i).size();j++)
			{
			gobj.get1Ddata(kotherValues, j);
			}
		}
		System.out.println("count ="+count);*/
		//calculate DTW ditance
		dtwDistances=gobj.distanceDTW(gestureValues, kotherValues);
		gobj.printDouble(dtwDistances);
    }
}
