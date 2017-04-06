
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//now to compare G11 form two folders

public class RecognizesGesture {
	public void compareG11(File folder1,File folder2,int frameF1Loc,int frameF2Loc,int totalFrameF1,int totalFrameF2) throws IOException  //pass the two folders
	{   int imgCountf1=0,imgCountf2=0,i,j;
	    int frameSimilar=0;
	i=frameF1Loc;
	j=frameF2Loc;
	imgCountf1=totalFrameF1;
	imgCountf2=totalFrameF2;
	
		while((folder1!=null)&&(i<185))
		{   
			File imgF1=new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G13_1/frame"+i+".png");
			 BufferedImage img1 = ImageIO.read(imgF1);
			 j=frameF2Loc;
			while((folder2!=null)&&(j<=291))
			{
				 File imgF2=new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G13_2/frame"+j+".png");
				 BufferedImage img2 = ImageIO.read(imgF2);
			 int pixDif=compareImage(img1,img2);
			 if(pixDif==0)
			 {frameSimilar++;}
			 j++;
			 System.out.println("reading file number "+j);
			}
			i++;
			System.out.println("the i loop value"+i);
		}
		if(frameSimilar==0)
			System.out.println("no frmaes are similar");
		else if((frameSimilar==imgCountf1) ||(frameSimilar==imgCountf2))
			System.out.println("two gesture folders are same");
		else
			System.out.println("total number of similar frames="+frameSimilar);
	 	
	}
	//function to compare two images
	public int compareImage(BufferedImage image1,BufferedImage image2)
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
    // w and h for secound image
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
    // System.out.println("TIME TAKEN IS ="+(stop-start));
     System.out.println("NO OF PIXEL GETS VARIED:="+pixDiff);
     System.out.println("NO OF PIXEL GETS MATCHED:="+pixSimilar);
     return(pixDiff);
	}
	
	public static void main(String[] args) throws IOException {
		File folder1=new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G13_1");
		File folder2=new File("C:/Users/user/Documents/Java gesture recognistion/Gestures/G13_2");
		int frameStart1=86;
		int frameStart2=186;
		int totalframeF1=101;
		int totalframeF2=106;
		RecognizesGesture r=new RecognizesGesture();
		r.compareG11(folder1, folder2, frameStart1, frameStart2, totalframeF1, totalframeF2);

	}

}
