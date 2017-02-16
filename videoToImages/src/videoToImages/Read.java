package videoToImages;

import java.awt.Frame;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;


public class Read {

	FFmpegFrameGrabber g = new FFmpegFrameGrabber("textures/video/anim.mp4");
	g.start();

	for (int i = 0 ; i < 50 ; i++) {
	    ImageIO.write(g.grab().getBufferedImage(), "png", new File("frame-dump/video-frame-" + System.currentTimeMillis() + ".png"));
	}

	g.stop();


}