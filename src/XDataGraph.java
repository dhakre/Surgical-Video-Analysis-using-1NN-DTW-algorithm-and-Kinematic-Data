import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XDataGraph {
	public static void main(String args[])
	{
		XYSeries series = new XYSeries("XYGraph");
		try{
		//File f=new File("C:/Users/user/Documents/Java gesture recognistion/HistogramsAndCharts/Xdata.txt");
		InputStream in = new FileInputStream( new File( "C:/Users/user/Documents/Java gesture recognistion/HistogramsAndCharts/Xdata.txt" ) );          
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in ) );          
	    StringBuilder out = new StringBuilder();          
	    String line;          
	    DefaultXYDataset dataset = new DefaultXYDataset();
	    while (( line = reader.readLine() ) != null ) 
	    {
	       out.append( line );
	    }
	    StringTokenizer s = new StringTokenizer( out.toString(), "." );
	    int i=0;    
	    while( s.hasMoreTokens( ) )
	    {

	    	//dataset.getXValue((int) Double.parseDouble( s.nextToken( ) ),5);
	    	series.add(Double.parseDouble( s.nextToken( ) ), 100.0);
	       i++;
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
	    ChartUtilities.saveChartAsJPEG(new File("/C:/Users/user/Documents/Java gesture recognistion/HistogramsAndCharts/XY1datachart.jpg"), chart, 600, 400);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
