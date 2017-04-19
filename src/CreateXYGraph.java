import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CreateXYGraph {
	// create graph function
public void createXYgraph(String dataFileLoc,String SaveGraphFileLoc) throws IOException
{
	BufferedReader br = new BufferedReader(new FileReader(dataFileLoc));
	XYSeries series = new XYSeries("XYGraph");
int i=0;
double x = 0,y = 0;
String a,b;
String line=null;
while((line = br.readLine()) != null)
{
	line = line.trim();
    String[] values = line.split(" +");
    System.out.println("line  length"+values.length);
    System.out.println(Arrays.toString(values));
    //System.out.println(line);
    //System.out.println("in the while loop");
   if (values.length>=2)
   {
    try{  
    	for(int j=0;j<values.length;j++)
    	{
         a=values[0];
         b=values[1];
         System.out.println("a="+a+"b="+b);
         x=Double.parseDouble(a);
         y=Double.parseDouble(b);
        System.out.println("x="+x+"y="+y);
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
JFreeChart chart = ChartFactory.createXYAreaChart(                   //this line creates the graph using the parameters
		 "XY Chart", // Title
		 "x-axis", // x-axis Label
		 "y-axis", // y-axis Label
		 dataset1, // Dataset
		 PlotOrientation.VERTICAL, // Plot Orientation
		 true, // Show Legend
		 true, // Use tooltips
		 false // Configure chart to generate URLs?
		 );
ChartUtilities.saveChartAsJPEG(new File(SaveGraphFileLoc+"/XYB003Graph.jpg"), chart, 600, 400);
System.out.println("xy data graph is printed");
System.out.println(i);
	}
//main body
public static void main(String args[]) throws IOException
{
	String xydataLoc,saveGraphLoc;
	xydataLoc="C:/Users/user/Documents/Intership/Suturing/kinematics/AllGestures/Suturing_B003.txt";
	saveGraphLoc="C:/Users/user/Documents/Java gesture recognistion/KinematicData/Suturing";
	//Scanner in = new Scanner(System.in);
	//System.out.println("Enter the xy data file location ");
    //xydataLoc=in.next();
    //System.out.println("Enter the location to save the graph");
    //saveGraphLoc=in.next();
    CreateXYGraph obj=new CreateXYGraph();
    obj.createXYgraph(xydataLoc, saveGraphLoc);
    
}
}