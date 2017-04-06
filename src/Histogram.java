import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histogram {

    private static final int BINS = 256;
    private final BufferedImage image = getImage();
    private HistogramDataset dataset;
    private XYBarRenderer renderer;
   
    private BufferedImage getImage() {
        try {
            return ImageIO.read(new File( "C:/Users/user/Documents/Java gesture recognistion/Images/frame1627.png"));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private ChartPanel createChartPanel() {
        // dataset
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
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value",
         "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JCheckBox(new VisibleAction(0)));
        panel.add(new JCheckBox(new VisibleAction(1)));
        panel.add(new JCheckBox(new VisibleAction(2)));
        return panel;
    }
    // to control the visibility of histogram on mouse click on the check box
    private class VisibleAction extends AbstractAction {

        private final int i;

        public VisibleAction(int i) {
            this.i = i;
            this.putValue(NAME, (String) dataset.getSeriesKey(i));
            this.putValue(SELECTED_KEY, true);
            renderer.setSeriesVisible(i, true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
        }
    }

    private void display() {
        JFrame f = new JFrame("Histogram");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(createChartPanel());
        f.add(createControlPanel(), BorderLayout.SOUTH);
       // f.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST); //to show image along with the pannel of the histogram
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        //save the histogram as a image
        try{
       	 BufferedImage image = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            f.paint(graphics2D);
            ImageIO.write(image,"jpeg", new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G11/Histograms/HistogramFrame1627.jpg"));
            System.out.println("histogram is saved");
        }
        catch(Exception e)
        {
        	System.out.println("error while saving historam image");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Histogram().display();
        });
      
        
    }
}

