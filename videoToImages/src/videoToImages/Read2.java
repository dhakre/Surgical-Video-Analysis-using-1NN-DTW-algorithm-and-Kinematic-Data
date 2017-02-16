package videoToImages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;

public class Read2{

    public static void main(String []args) throws IOException, Exception, InterruptedException, ExecutionException
    {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber("/Users/user/Documents/Java gesture recognistion/videoToImages/Learn.mp4");
        frameGrabber.start();
        IplImage i;
        try {
            for(int ii=0;ii<frameGrabber.getLengthInFrames();ii++){

            i = frameGrabber.grab();
            BufferedImage  bi = i.getBufferedImage();
            String path = "/Users/user/Documents/Java gesture recognistion/videoToImages/Frames/frame"+ii+".png";
            ImageIO.write(bi,"png", new File(path));

            }
            frameGrabber.stop();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
