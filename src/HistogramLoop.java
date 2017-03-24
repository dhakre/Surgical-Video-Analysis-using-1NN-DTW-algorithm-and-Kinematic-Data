import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class HistogramLoop {
	private static final int BINS = 256;
   // private final BufferedImage image;
    private static HistogramDataset dataset;
    private static XYBarRenderer renderer;
    static int i=370;
	static int j=782;
   static File folder=new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G11");
   public static void main(String args[]) throws IOException
   {
    while((folder!=null) &&(i<j))
    {
    	File f= new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G11/frame"+i+"png");
    	BufferedImage image= ImageIO.read(new File( "C:/Users/user/Documents/Java gesture recognistion/Gestures/G15_1/frame"+i+".png")); // read each image
    	//create the images Histogram
    	dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
        // chart
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value","Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue for the values on histogram
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
    	try{
    		ChartUtilities.saveChartAsJPEG(new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G15_1/Histograms/HistogramFrame"+i+".jpg"), chart, 600, 400);
    		System.out.println("Histogram no"+i+" is saved");
    	}
    	catch(Exception e){
    		System.out.println("error while saving the image");
    	}
    	i++;
    }
   }
}
