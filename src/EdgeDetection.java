import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;
import static org.bytedeco.javacpp.opencv_imgproc.cvCanny;
//import static org.bytedeco.javacpp.opencv_imgproc.cvThreshold;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
//import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
//import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
//import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BLUR;
import static org.bytedeco.javacpp.opencv_imgproc.CV_THRESH_BINARY;
import static org.bytedeco.javacpp.opencv_imgproc.cvThreshold;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class EdgeDetection {
	 static IplImage image = cvLoadImage("C:/Users/user/Documents/Java gesture recognistion/newcv/frame3.png");
	 static IplImage smooth=IplImage.create(image.width(), image.height(), image.depth(), 1);
	 static IplImage edge=IplImage.create(image.width(), image.height(), image.depth(), 1);
	 static IplImage gry=IplImage.create(image.width(), image.height(), image.depth(), 1);
	 static CanvasFrame canvas = new CanvasFrame("Image");
	 static CanvasFrame canvas1 = new CanvasFrame("gray");
	 static CanvasFrame canvas2 = new CanvasFrame("Edge Detection");
	 static CanvasFrame canvas3 = new CanvasFrame("Smooth");
	 //main function
	 public static void main(String args[]){
		 
	 OpenCVFrameConverter.ToIplImage converter=new OpenCVFrameConverter.ToIplImage();
	 //canvas.showImage(converter.convert(image));
     cvCvtColor(image,gry,COLOR_BGR2GRAY);
     //canvas1.showImage(converter.convert(gry));
 	
	 cvSmooth(gry,smooth,CV_BLUR,9,9,2,2);
	 //canvas3.showImage(converter.convert(smooth));
		
	 cvThreshold(gry,gry, 155, 255, CV_THRESH_BINARY);
	//cvSaveImage("C:/Users/user/Documents/Java gesture recognistion/grayFrame.jpg",gry);
	cvCanny( gry, gry, 150, 105, 3 );
	 canvas2.showImage(converter.convert(gry));
	

	 }
	
}
