import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYKinematicGraph {
	static Double Xarray[]=null;
	static Double Yarray[]=null;
	static int j=0,k=0;
	static int m=0,n=0;
	public static void main(String args[]) throws IOException
	{
	
		//getting X data
		Scanner in=new Scanner(new File("C:/Users/user/Documents/Java gesture recognistion/KinematicData/XKinematicdata2.txt"));
		//getting Y data
		Scanner sc=new Scanner(new File("C:/Users/user/Documents/Java gesture recognistion/KinematicData/YKinematicdata2.txt"));
	
	//Creating xy dataset for making graph
	XYSeries series = new XYSeries("XYGraph");
	int p=0,q=0;
	 while((in.hasNext())&&(sc.hasNext()))
	 {
		 series.add(in.nextDouble(), sc.nextDouble());
		 p++;
		 q++;
	 }
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
	    ChartUtilities.saveChartAsJPEG(new File("/C:/Users/user/Documents/Java gesture recognistion/KinematicData/XYKinematicGraph.jpg"), chart, 600, 400);
	    System.out.println("xy data graph is printed");
	}
}

