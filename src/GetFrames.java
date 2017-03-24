package videoToFrames;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;
import org.jcodec.scale.AWTUtil;

public class GetFrames {

public static void testSingleFrame() throws Exception {
int frameNumber = 0;
//grab the frame from video 
Picture frame = FrameGrab.getNativeFrame(new File("C:/Users/user/Documents/Java gesture recognistion/videoToFrames/knot.mp4"), frameNumber);

Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
transform.transform(frame, rgb);
BufferedImage bi = AWTUtil.toBufferedImage(rgb);
ImageIO.write(bi, "png", new File("C:/Users/user/Documents/Java gesture recognistion/videoToFrames/frame_"+ frameNumber  +".png"));
}

//main function
public static void main(String args[]) throws Exception
{
	testSingleFrame();
	
}
}
