import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestureSpecificDataArrayList {
	
	public ArrayList<Double> GXValue= new ArrayList<Double>();
	public ArrayList<Double> GYValue= new ArrayList<Double>();
	
	public void addGXValue(double gxValue)
	{
		GXValue.add(gxValue);
	}
	public void addGYValue(double gyValue)
	{
		GXValue.add(gyValue);
	}
	
	//function to extract the element of any required poition from the geture file .For Eg. getting all the  X value in an arraylist
	//here the argument position defines the value of the position that you want to store in the array list for eg. 1 represent the value of the X values in the kinmeatic data file.
	public ArrayList<Double> getGestureArrayList(String gfileLoc,int position) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(gfileLoc)); 
	    String line,a;
	    double x;
	    ArrayList<Double> arrX =new ArrayList<>() ;
	    int i=1,j=0;
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[position];
	    	   x=Double.parseDouble(a);
	    	   arrX.add(x);
	       }
	       i++;
	    }
	    br.close();
	    return arrX;
	}
	
	public static GestureSpecificDataArrayList getGestureArrayList(String gfileLoc) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(gfileLoc)); 
	    String line,a,b;
	    double x,y;
	    GestureSpecificDataArrayList Garr =new GestureSpecificDataArrayList() ;
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
	    	   Garr.addGXValue(x);
	    	   Garr.GYValue.add(y);
	    	   //System.out.println("Gy="+Garr.GYValue.get(i));
	       }
	       i++;
	    }
	    br.close();
	    //System.out.println("Gy  size="+Garr.GYValue.size());
	    return Garr;
	}
	
	public void print(GestureSpecificDataArrayList g)
	{   
		ArrayList <Double> a=new ArrayList<Double>();
		//ArrayList <Double> b=new ArrayList<Double>();
		a=g.GXValue;
		for(double d :a)
		{
			System.out.println("data X="+d);
		}
		a=g.GYValue;
		for(double d :a)
		{
			System.out.println("data Y="+d);
		}
		
	}
	
	//main function
	public static void main(String args[]) throws IOException
	{
		String gfileloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Gesture_Data/Knot_Tying/Knot_Tying_B001/gestureB001G14_8.txt";
		ArrayList<Double> Gxvalue= new ArrayList<Double>();
		GestureSpecificDataArrayList Gobj= new GestureSpecificDataArrayList();
		GestureSpecificDataArrayList Gobj1= new GestureSpecificDataArrayList();
		Gobj1=GestureSpecificDataArrayList.getGestureArrayList(gfileloc);	   //	Gobj.GXValue=Gobj.getGestureArrayList(gfileloc,0);
		//Gobj.GYValue=Gobj.getGestureArrayList(gfileloc,1);
		//Gobj.print(Gobj);
		Gobj.print(Gobj1);
		int size = Gxvalue.size()-1;
		//System.out.println("size="+size);
	}

}
