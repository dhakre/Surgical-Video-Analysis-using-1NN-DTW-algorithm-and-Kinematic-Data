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

public class CreateXYKinematicGraph {
	// create graph function
public void createXYgraph(String dataFileLoc,String SaveGraphFileLoc,String Filename) throws IOException
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
ChartUtilities.saveChartAsJPEG(new File(SaveGraphFileLoc+"/"+Filename+".jpg"), chart, 700, 500);
System.out.println("xy data graph is printed");
System.out.println(i);
	}
public void createAllXYGraphs(String datafolderLoc,String saveLoc) throws IOException
{
String filename=null,savefilename=null,datafileloc=null;
File folder=new File(datafolderLoc);
File[] listOfFiles = folder.listFiles();

for (int i = 0; i < listOfFiles.length; i++) 
{
  if (listOfFiles[i].isFile()) {
    //System.out.println("File " + listOfFiles[i].getName());
	  filename=listOfFiles[i].getName();
	  savefilename=filename.substring(0,13);//get the save file name
	  datafileloc=datafolderLoc+"/"+filename;
	  createXYgraph(datafileloc,saveLoc,savefilename);
	  //System.out.println(savefilename);
	  
  }
}

}
//main body
public static void main(String args[]) throws IOException
{
	String folderloc,saveGraphLoc,filename;
	folderloc="C:/Users/user/Documents/Intership/Suturing/kinematics/AllGestures";
	saveGraphLoc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Suturing";
	filename="B001";
	//Scanner in = new Scanner(System.in);
	//System.out.println("Enter the xy data file location ");
    //xydataLoc=in.next();
    //System.out.println("Enter the location to save the graph");
    //saveGraphLoc=in.next();
    CreateXYKinematicGraph obj=new CreateXYKinematicGraph();
   // obj.createXYgraph(xydataLoc, saveGraphLoc,filename);
    obj.createAllXYGraphs(folderloc,saveGraphLoc);
    
}
}