import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DTW {
	 private static int inf = 9000;
     
	    public static double DTWDistance(double[] s, double[] t){
	        double[][] dtw = new double[s.length + 1][t.length + 1];

	        dtw[0][0] = 0;
	        for (int i = 1; i <s.length + 1; i++) {
	            dtw[i][0] = inf;
	        }
	        for (int i = 1; i <t.length + 1; i++) {
	            dtw[0][i] = inf;
	        }

	        for (int i = 1; i < s.length + 1; i++) {
	            for (int j = 1; j < t.length + 1; j++) {
	                double cost = distanceD(s[i - 1], t[j - 1]);
	                dtw[i][j] = cost + minimum(dtw[i - 1][j],      //match
	                                           dtw[i][j - 1],      //insertion
	                                           dtw[i - 1][j - 1]); //deletion
	            }
	        }
	        return dtw[s.length][t.length];
	    }

	    private static double distanceD(double a, double b) {
	        return Math.abs(a-b);
	    }

	    ///Finds the lowest value and returns it
	    private static double minimum(double i, double i1, double i2) {
	        if (i < i1) {
	            if (i < i2) {
	                return i;
	            }
	        } else if (i1 < i2) {
	            return i1;
	        }
	        return i2;
	    }
	    //function to calculate the total number of lines in the file
	    public int calculateFileLength(String fileloc) throws IOException
	    { 
	    	BufferedReader br = new BufferedReader(new FileReader(fileloc));
	    	String line=null;
	    	int i=0;
	    	while((line = br.readLine()) != null)
	    	{
	    		line = line.trim();
	    	    String[] values = line.split(" +");
	    	    i++;
	    	}
	    	//System.out.println("total values read="+i);
	    	return(i);
	    }
	    
	    //funcition to read the first character of each line from the file(here i am taking x value)
	    public double[] getdata(String fileLoc) throws IllegalArgumentException, IOException
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(fileLoc));
	    	String line=null;
	        String a;
	        double b;
	        int i=1,size=0,j=0;
	        size=calculateFileLength(fileLoc);
	        double arr[] = new double[size];// diclare array for the calculated size
	    	while(((line = br.readLine()) != null)&&(j<size))
	    	{
	    		line = line.trim();
	    	    String[] values = line.split(" +");
	    	   // System.out.println("line  length"+values.length);
	    	    //System.out.println(Arrays.toString(values));
	    	    i++;
	    	    //System.out.println(line);
	    	    //System.out.println("in the while loop");
	    	   if (values.length>=2)
	    	   { 
	    	         a=values[0];
	    	         b=Double.parseDouble(a);
	    	         arr[j]=b;
	    	        // System.out.println("b="+b);
	    	   }
	    	   //System.out.println("arr="+arr[j]);
	    	   j++;
	    	}
	    	//System.out.println("total values read="+i);
	    	System.out.println("arr length"+arr.length);
			return (arr);
	    }
	    //function to get distance between any values(here y values i am taking)
	    public double[] getpositiondata(String fileLoc,int position) throws IllegalArgumentException, IOException
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(fileLoc));
	    	String line=null;
	        String a;
	        double b;
	        int i=1,size=0,j=0,Tvalue=0;
	        size=calculateFileLength(fileLoc);
	        double arr[] = new double[size];// diclare array for the calculated size
	    	while(((line = br.readLine()) != null)&&(j<size))
	    	{
	    		line = line.trim();
	    	    String[] values = line.split(" +");
	    	    i++;
	    	   if(position==77)
	    		   Tvalue=77;
	    	   else
	    		   Tvalue=position+1;
	    	   
	    	   if (values.length>=Tvalue)
	    	   { 
	    	         a=values[position];
	    	         b=Double.parseDouble(a);
	    	         arr[j]=b;
	    	        // System.out.println("b="+b);
	    	   }
	    	   //System.out.println("arr="+arr[j]);
	    	   j++;
	    	}
	    	//System.out.println("total values read="+i);
	    	System.out.println("arr length"+arr.length);
			return (arr);
	    }
	    //create graph
	    public void createGraph(String fileloc,String saveloc,double DTWdistanceX,double DTWdistanceY) throws IOException
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(fileloc));
	    	XYSeries series = new XYSeries("XYGraph");
	    int i=0;
	    double x = 0,y = 0;
	    String a = null,b = null;
	    String line=null;
	    while((line = br.readLine()) != null)
	    {
	    	line = line.trim();
	        String[] values = line.split(" +");
	        //System.out.println("line  length"+values.length);
	        //System.out.println(Arrays.toString(values));
	        //System.out.println(line);
	        //System.out.println("in the while loop");
	       if (values.length>=2)
	       {
	        try{  
	        	for(int j=0;j<values.length;j++)
	        	{ 
	        	if((j==DTWdistanceX)||(j>DTWdistanceX))
	        	{
	             a=values[0];
	             x=Double.parseDouble(a);
	        	}
	        	else{
	        		if(j==109)
	        			break;
	        	}
	        	if((j==DTWdistanceY)||(j>DTWdistanceY))
	        	{
	             b=values[1];
	             y=Double.parseDouble(b);
	        	}
	        	else{
	        		if(j==109)
	        			break;
	        	}
	             //System.out.println("a="+a+"b="+b);
	            //System.out.println("x="+x+"y="+y);
	        	}
	        }catch(Exception e)
	        {
	        	
	    e.printStackTrace();   
	       }
	        series.add(x, y);
	       }
	        i++;
	    }
	    br.close();

XYSeriesCollection dataset1 = new XYSeriesCollection();
dataset1.addSeries(series);
JFreeChart chart = ChartFactory.createXYLineChart(                   //this line creates the graph using the parameters
		 "XY Chart", // Title
		 "x-axis", // x-axis Label
		 "y-axis", // y-axis Label
		 dataset1, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );
ChartUtilities.saveChartAsJPEG(new File(saveloc+"DTWgraph"+".jpg"), chart, 600, 500);
System.out.println("xy data graph is printed");
System.out.println(i);
	    }
	    
	    
   // main body
	    public static void main(String args[]) throws IOException{
	    
	    	String kloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B003.txt";
	    	String gloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Gesture_Data/Knot_Tying/Knot_Tying_B003/gestureB001G11_9.txt";
	    	String saveloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/";
	    	DTW dt= new DTW();
	    	double DTWdistanceX=0,DTWdistanceY=0;
	    	double [] arrX1=dt.getdata(kloc);  //x data
	    	double [] arrX2=dt.getdata(gloc);
	    	double [] arrY1=dt.getpositiondata(kloc, 1);
	    	double [] arrY2=dt.getpositiondata(gloc, 1);
	    	int gfileLength=dt.calculateFileLength(gloc);
	    		
	    	DTWdistanceX=DTW.DTWDistance(arrX1, arrX2);
	      System.out.println("DTW X distance="+DTWdistanceX);
	    	//taking y data	
	    	DTWdistanceY=DTW.DTWDistance(arrY1, arrY2);
	    	System.out.println("DTW Y distance="+DTWdistanceY);
	    	//dt.createGraph(kloc, saveloc, DTWdistanceX, DTWdistanceY);
	    	//new function used from Multiplegraph class
	    	MultipleGraph Mg= new MultipleGraph();
	    	//Mg.createmultipleXYgraph(kloc, gloc, saveloc, DTWdistanceX, DTWdistanceY);
	    	//Mg.multigraph(saveloc);
	    	//Mg.graphmulti(saveloc, arrX1, arrX2, arrY1, arrY2);
	    	Mg.createdistanceGraph(kloc, gloc, saveloc, DTWdistanceX, DTWdistanceY, gfileLength);
	    }
}
