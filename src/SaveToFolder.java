
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.IplImage;

//This program helps to save all or few selected images from one folder to other using javacv 
public class SaveToFolder {
	public void saveImagestoFolder(int startloc, int endloc, File folder, String savefolder) {
		int i = startloc;
		while ((folder != null) && (i <= endloc)) {
			IplImage image = cvLoadImage(folder + "/frame" + i + ".png");
			cvSaveImage(savefolder + i + ".png", image);
			System.out.println("copying Frame" + i);
			i++;
		}
	}

	public static void main(String agrs[])
	{
		int i=40, j=50;
		File folder=new File("C:/Users/user/Documents/Java gesture recognistion/Images");
		String savefolder="C:/Users/user/Documents/Java gesture recognistion/Gestures/G121/frame";
	   SaveToFolder sf=new SaveToFolder();
	   sf.saveImagestoFolder(i, j, folder, savefolder);
	}
}
