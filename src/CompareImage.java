package newcv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CompareImage {
	public void compareImage(BufferedImage image1,BufferedImage image2)
	{
		//start calculating time of processing
	long start = System.currentTimeMillis();
	int pixSimilar=0,pixDiff=0,pixtotal=0;
	int width1,width2,height1,height2;
	int smallestW=0,smallestH=0;
	float percentSimilarity;
	float w,h=0;
	width1 = image1.getWidth(null);
    height1 = image1.getHeight(null);
    // w and h for secounf image
     width2 = image2.getWidth(null);
     height2 = image2.getHeight(null);
	 int[][] pixMatrix1=  new int[width1][height1];
	 int[][] pixMatrix2=  new int[width2][height2];
	//CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
	 if(width1>width2)
     { 
         smallestW =width2;
     }
     else 
     {
    	 smallestW=width1;
     }
     if(height1>height2)
     {
    	 smallestH=height2;
     }
     else 
     {
         smallestH=height1;
     }
   //CHECKING NUMBER OF PIXELS SIMILARITY
     for(int a=0;a<smallestW;a++)
     {
         for(int b=0;b<smallestH;b++)
         {
             pixMatrix1[a][b]=image1.getRGB(a,b); //get the single pixel RGB value
             pixMatrix2[a][b]=image2.getRGB(a,b);
             if(pixMatrix1[a][b]==pixMatrix2[a][b]) 
             {
            	 pixSimilar++;
             }
             else
                 pixDiff++;
         }
     }
     //calculate total pixels
     if(width1>width2) 
     {
         w=width1;
     }
     else 
     {
         w=width2;
     }
     if(height1>height2)
     { 
         h = height1;
     }
     else
     {
         h = height2;
     }
      pixtotal = (smallestW*smallestH);
     //CALUCLATING PERCENTAGE
     percentSimilarity =(100*pixSimilar)/pixtotal;
     //show results
     System.out.println("THE PERCENTAGE SIMILARITY IS APPROXIMATELY ="+percentSimilarity+"%");
     long stop = System.currentTimeMillis();
     System.out.println("TIME TAKEN IS ="+(stop-start));
     System.out.println("NO OF PIXEL GETS VARIED:="+pixDiff);
     System.out.println("NO OF PIXEL GETS MATCHED:="+pixSimilar);
	}
	
	//main function
	public static void main(String args[]) throws IOException
	{
		CompareImage c= new CompareImage();
		File frame1 =new File("C:/Users/user/Documents/Java gesture recognistion/newcv/frame2.png");
		File frame2 =new File("C:/Users/user/Documents/Java gesture recognistion/newcv/frame2.png");
		 BufferedImage img1 = ImageIO.read(frame1);
		 BufferedImage img2 = ImageIO.read(frame2);
		 c.compareImage(img1, img2);
		
	}
	
}
