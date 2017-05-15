import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MultipleGraph {
	public void createdistanceGraph(String kfile,String gfile,String saveloc,double DTWdistanceX,double DTWdistanceY,int GfileLength) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(kfile));
		BufferedReader br1 = new BufferedReader(new FileReader(gfile));
    	XYSeries series = new XYSeries("kinematic");
    	XYSeries series1 = new XYSeries("gesture");
    int i=0;
    double x = 0,y = 0;
    String a = null,b = null;
    String line=null,str=null;
    while((line = br.readLine()) != null)
    {
    	line = line.trim();
        String[] values = line.split(" +");
        //System.out.println("line  length"+values.length);
        //System.out.println(Arrays.toString(values));
        //System.out.println(line);
       if (values.length>=2)
       {
         
        	for(int j=0;j<values.length;j++)
        	{ 
        	if((j==DTWdistanceX)||(j>DTWdistanceX))
        	{
             a=values[0];
             x=Double.parseDouble(a);
        	}
        	if((j==DTWdistanceY)||(j>DTWdistanceY))
        	{
             b=values[1];
             y=Double.parseDouble(b);
        	}
             //System.out.println("a="+a+"b="+b);
            //System.out.println("x="+x+"y="+y);
        	}
        
        series.add(x, y);
       }
       if(i==GfileLength)
       {
    	   break;
       }
        i++;
    }
    br.close();
    while((str = br1.readLine()) != null)
    {
    	str= str.trim();
    	String[] val=str.split(" +");
    	if (val.length>=2)
        {
          
         	for(int j=0;j<val.length;j++)
         	{ 
         	
              a=val[0];
              x=Double.parseDouble(a);
         
              b=val[1];
              y=Double.parseDouble(b);
         	         
              //System.out.println("a="+a+"b="+b);
             //System.out.println("x="+x+"y="+y);
         	}
         
         series1.add(x, y);
        }
         i++;
    	 	
    }
    br1.close();
XYSeriesCollection dataset1 = new XYSeriesCollection();
dataset1.addSeries(series);
dataset1.addSeries(series1);
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
chart.setBackgroundPaint(Color.white);
final XYPlot plot = chart.getXYPlot();
plot.setBackgroundPaint(Color.lightGray);
plot.setDomainGridlinePaint(Color.white);
plot.setRangeGridlinePaint(Color.white);
ChartUtilities.saveChartAsJPEG(new File(saveloc+"DTWmultigraph2"+".jpg"), chart, 600, 500);
System.out.println("xy data graph is printed");
System.out.println(i);
	}

public void graphmulti(String saveloc,double []arrX1,double[] arrX2,double []arrY1,double []arrY2) throws IOException
{
	XYSeries s1 = new XYSeries("kinematic");
	XYSeries s2 = new XYSeries("gesture");
int i=0,j=0;
System.out.println(arrX1.length+" array length "+arrY1.length);
	while((i<arrX1.length)||(j<arrY1.length))
	{
		s2.add(arrX1[i], arrY1[j]);
		System.out.println(arrX1[i]+"arr1 data"+arrY1[j]);
		i++;
		j++;
	}
	
	int m=0,n=0;
	System.out.println("secound array");
	while((m<arrX2.length)||(n<arrY2.length))
	{
		s1.add(arrX2[m], arrY2[n]);
		System.out.println(arrX2[m]+" "+arrY2[n]);
		m++;
		n++;
	}
	XYSeriesCollection dataset = new XYSeriesCollection();
	dataset.addSeries(s1);
	dataset.addSeries(s2);
	JFreeChart chart = ChartFactory.createXYLineChart( "XY Chart", "x-axis",  "y-axis",  dataset, PlotOrientation.VERTICAL, true,true,false );
		chart.setBackgroundPaint(Color.white);
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		ChartUtilities.saveChartAsJPEG(new File(saveloc+"DTWdemograph"+".jpg"), chart, 600, 500);
		System.out.println("xy multi data graph is printed");
}

//function to create the Kinematic curve only upto the length of gesture file to see the clearly the difference between the curves
public void createmultipleXYgraph(String kfile,String gfile,String saveloc,double DTWdistanceX,double DTWdistanceY) throws IOException
{
	BufferedReader br = new BufferedReader(new FileReader(kfile));
	BufferedReader br1 = new BufferedReader(new FileReader(gfile));
	XYSeries series = new XYSeries("kinematic");
	XYSeries series1 = new XYSeries("gesture");
int i=0;
double x = 0,y = 0;
String a = null,b = null;
String line=null,str=null;
while((line = br.readLine()) != null)
{
	line = line.trim();
    String[] values = line.split(" +");
    //System.out.println("line  length"+values.length);
    //System.out.println(Arrays.toString(values));
    //System.out.println(line);
   if (values.length>=2)
   {
     
    	for(int j=0;j<values.length;j++)
    	{ 
    	if((j==DTWdistanceX)||(j>DTWdistanceX))
    	{
         a=values[0];
         x=Double.parseDouble(a);
    	}
    	if((j==DTWdistanceY)||(j>DTWdistanceY))
    	{
         b=values[1];
         y=Double.parseDouble(b);
    	}
         //System.out.println("a="+a+"b="+b);
        //System.out.println("x="+x+"y="+y);
    	}
    
    series.add(x, y);
   }
   if(i==69)
   {
	   break;
   }
    i++;
}
br.close();
while((str = br1.readLine()) != null)
{
	str= str.trim();
	String[] val=str.split(" +");
	if (val.length>=2)
    {
      
     	for(int j=0;j<val.length;j++)
     	{ 
     	
          a=val[0];
          x=Double.parseDouble(a);
     
          b=val[1];
          y=Double.parseDouble(b);
     	         
          //System.out.println("a="+a+"b="+b);
         //System.out.println("x="+x+"y="+y);
     	}
     
     series1.add(x, y);
    }
     i++;
	 	
}
br1.close();
XYSeriesCollection dataset1 = new XYSeriesCollection();
dataset1.addSeries(series);
dataset1.addSeries(series1);
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
chart.setBackgroundPaint(Color.white);
final XYPlot plot = chart.getXYPlot();
plot.setBackgroundPaint(Color.lightGray);
plot.setDomainGridlinePaint(Color.white);
plot.setRangeGridlinePaint(Color.white);
ChartUtilities.saveChartAsJPEG(new File(saveloc+"DTWmultigraph2"+".jpg"), chart, 600, 500);
System.out.println("xy data graph is printed");
System.out.println(i);
}


}
