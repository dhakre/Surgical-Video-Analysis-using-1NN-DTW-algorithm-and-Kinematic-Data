import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

//the class compare the kinematic data curve with the curves of the other kinematic data and tries to find the DTW distance between those curves 
public class CompareDistances extends DTW{
	//function to read data from kinematic file and create and array
	@SuppressWarnings("null")
	public double[] getGestureArray(String gfileLoc) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(gfileLoc)); 
	    String line,a;
	    int size=calculateFileLength(gfileLoc);
	    double x;
	    double [] arrX =new double[size];
	    int i=1,j=0;
	    while (((line = br.readLine()) != null)&&(j<size)) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   x=Double.parseDouble(a);
	    	   arrX[j]=x;
	       }
	       i++;
	       j++;
	    }
	    br.close();
	    return arrX;
	}
	//function to read the transcript file and ave the start and end valuesof each gesture into startValues,endValues array
	public void readGetureValues(String transcriptfileloc,int []startValues,int []endValues,String []getureNames) throws IOException
	{    
		
		 BufferedReader br=new BufferedReader(new FileReader(transcriptfileloc));
		 String gesture,a,b;
		 String[] gstr = null;
		 int i=1,j=0;
		 int glength=calculateFileLength(transcriptfileloc);//to calculate total line in the gesture file a the length of array
		 System.out.println("length"+glength);
		 while(((gesture= br.readLine())!=null)&&(j<=glength))
		    {  
		    	gesture = gesture.trim();
		        gstr = gesture.split(" ");
		        if (gstr.length <= 3)
		        {
		           
		            a=gstr[0];
		            b=gstr[1];
		            getureNames[j]=gstr[2];
		            //System.out.println("gName="+getureNames[j]);
		            startValues[j]=Integer.parseInt(a);
		            //System.out.println("gstart="+startValues[j]);
		            endValues[j]= Integer.parseInt(b);
		            //System.out.println("gend="+endValues[j]);
		        }
		      
		        i++;
		        j++;
		    }
		 br.close();
		
	}
	//to calculate comparison array from other Kinematic file
	public double[] comparisonArr(String otherKfileloc,int start,int end) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(otherKfileloc)); 
	    String line,a;
	    int size=end-start;
	    double x2;
	    double [] arr2X =new double[size];
	    int i=1,j=0;
	    int k=start;
	    while (((line = br.readLine()) != null)&&(j<size)&&(k>=start)&&(k<=end)) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   x2=Double.parseDouble(a);
	    	   arr2X[j]=x2;
	       }
	       i++;
	       j++;
	       k++;
	    }
	    br.close();
	    return arr2X;
		
	}
	
	
	
	//function to calculate the dtw ditance between kinematic data file and transcript data files only her for X-Values
	public double[] calculateDTWdistance(String gfileloc,String otherkfile,String tfileloc,int []startValues,int []endValues) throws IOException
	{
		int len=calculateFileLength(gfileloc);
		double gestureArr[]=new double[len];
		gestureArr=getGestureArray(gfileloc);//first array
		
		int len2=calculateFileLength(tfileloc);
		double DTWdistance[]=new double[len2];// it contains all DTW distance for the comparison
		int count=startValues.length;
		//System.out.println("len2"+len2);
		int i=0,j=0;
		while((i<count)||(j<DTWdistance.length))
		{    
			double[] arrX2=comparisonArr(otherkfile,startValues[i],endValues[i]);
			System.out.println("begin "+startValues[i]+" end "+endValues[i]+"i="+i);
			DTWdistance[j]=DTWDistance(gestureArr, arrX2);
			//System.out.println("dtw"+j+" no. value ="+DTWdistance[j]);
			i++;
			j++;
		}
		
		return DTWdistance;
		
	}
	//gesture recognition function
	public void recognizeGesture(double []DTWdistance,String []gestureName)
	{
		double minimum;
		int poition = 0;
		String gesture = null;
		minimum=DTWdistance[0];
		for(int i=1;i<DTWdistance.length;i++)
		{
             if(minimum>DTWdistance[i])
             {
             minimum=DTWdistance[i]; 
             poition=i;
             }
		}
		for(int i=0;i<gestureName.length;i++)
		{
			if(poition==i)
			{
				gesture=gestureName[i];
				System.out.println("Gesture Recognsized ="+gesture+"\n"+"minimum DTW distance found ="+minimum);
				break;
			}
			else
			{
				gesture=null;
			}
			
		}
		if(gesture==null)
		{
			System.out.println("No match for this gesture  found ");
		}
		
	}
	// print function
	public void printvalue(double []arr)
	{   
		for(int i=0;i<arr.length;i++)
		System.out.println(arr[i]);
	}
	
	
}
