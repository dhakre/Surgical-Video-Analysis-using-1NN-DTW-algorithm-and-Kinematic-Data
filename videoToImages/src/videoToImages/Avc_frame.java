package videoToImages;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;

public class Avc_frame {

 
    public static void main(String[] args) throws IOException, JCodecException {

        long time = System.currentTimeMillis();
        for (int i = 50; i < 57; i++) {
            Picture frame = FrameGrab.getNativeFrame(new File("/Users/user/Documents/Java gesture recognistion/videoToImages/Learn.mp4"), i);
            RenderedImage b = (RenderedImage) frame;
            ImageIO.write(b, "png", new File("/Users/user/Documents/Java gesture recognistion/videoToImages/Frames/frame_"+i+".png"));
        }
        System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
    }
}

