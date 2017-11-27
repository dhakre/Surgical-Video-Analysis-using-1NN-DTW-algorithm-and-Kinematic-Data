import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DTWDistanceArrayList extends DataArrayListXY{
	
	double DTWdistanceX;
	double DTWdistanceY;
	
	
	
	//function to calculate the DTW distance using Arraylist 
	public DTWDistanceArrayList[] calculateDTWdistanceList(String gfileloc,String tfileloc,String otherkfile,ArrayList<Integer> start,ArrayList <Integer> end) throws IllegalArgumentException, IOException
	{
		GestureSpecificDataArrayList gestureArr=new GestureSpecificDataArrayList();
		gestureArr=GestureSpecificDataArrayList.getGestureArrayList(gfileloc);//gesture  array for comparision
		//gestureArr.print(gestureArr);
		int len2=DTW.calculateFileLength(tfileloc);
		DTWDistanceArrayList DTWdistance[]=new DTWDistanceArrayList[len2];// it contains all DTW distance for the comparison
		int i=0,j=0;
		while((i<len2))
		{    
			KinematicDataList kdata=new KinematicDataList();
            kdata=comparisonArrayList(otherkfile,start.get(i),end.get(i));
			//System.out.println("begin "+start.get(i)+" end "+end.get(i)+"i="+i);
			DTWdistance[i]=DTWdistance(kdata,gestureArr);
			//System.out.println("dtw"+j+" no. value ="+DTWdistance[j]);
			i++;
			j++;
		}
		
		return DTWdistance;		
	}
	
	//function to calculate DTW distance by comparing each x and y values 
	public DTWDistanceArrayList DTWdistance(KinematicDataList karr,GestureSpecificDataArrayList Grr)
	{    
		DTWDistanceArrayList dtwd=new DTWDistanceArrayList();
		//calculating distance for X coordinate
		int sizekx=karr.KXValue.size();
		System.out.println(sizekx);
		int sizeGx=Grr.GXValue.size();
		System.out.println(sizeGx);
		int sizeky=karr.KYValue.size();
		System.out.println(sizeky);
		int sizeGy=Grr.GYValue.size();
		System.out.println(sizeGy);
		double[] kX=new double[sizekx];
		double[] GX=new double[sizeGx];
		double[] kY=new double[sizeky];
		double[] GY=new double[sizeGy];
		int i=0,j=0;
		while(i<karr.KXValue.size())
		{
			kX[i]=karr.KXValue.get(i);
			i++;
		}	
		while(j<Grr.GXValue.size())
		{
			GX[j]=Grr.GXValue.get(j);
			j++;
		}
		//function from DTW class to calculate distance
		dtwd.DTWdistanceX=DTW.DTWDistance(kX, GX);
		//calculating Y distance
		i=0;j=0;
		while(i<karr.KYValue.size())
		{
			kY[i]=karr.KYValue.get(i);			
			i++;

		}
		while(j<Grr.GYValue.size())
		{
			GY[j]=Grr.GXValue.get(j);
			j++;
		}
		dtwd.DTWdistanceY=DTW.DTWDistance(kY, GY);
		
		return dtwd;	
	}
	
	//calculate start and end values from the Transcript file
	public void getGestureStartEndValues(String tfileloc,ArrayList <String> gestureName,ArrayList <Integer> start,ArrayList <Integer> end) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(tfileloc));
		 String gesture,a,b;
		 int x,y;
		 String[] gstr = null;
		 int i=1,j=0;
		 while((gesture= br.readLine())!=null)
		    {  
		    	gesture = gesture.trim();
		        gstr = gesture.split(" ");
		        if (gstr.length <= 3)
		        {
		           
		            a=gstr[0];
		            b=gstr[1];
		            gesture=gstr[2];
		            gestureName.add(gesture);
		            //System.out.println("gName="+gestureName.get(j));
		            x=Integer.parseInt(a);
		            start.add(x);
		            //System.out.println("gstart="+start.get(j));
		            y=Integer.parseInt(b);
		            end.add(y);
		            //System.out.println("gend="+end.get(j));
		        }
		      
		        i++;
		        j++;
		    }
		 br.close();
		 System.out.println("total read value="+i);
	}
	//function to change ArrayList to normal Array
	public void changeToArray(KinematicDataList k,double []arrX,double arrY[])
	{
       int len=k.KXValue.size();
       arrX = new double[k.KXValue.size()];
       arrY = new double[k.KYValue.size()];
       //arrX=(double[]) k.KXValue.toArray(new double[k.KXValue.size()]);
       for(int i=0;i<arrX.length;i++)
       { 
    	   arrX[i]=k.getXvalue(i);
    	   arrY[i]=k.getYvalue(i);      
       }
       
            System.out.println("ArrayList to array changed");
	}
	
	//function to find DTW distance between the dataset of Kinematic Data and Gesture Data .
	public KinematicDataList comparisonArrayList(String otherKfileloc,int start,int end) throws IllegalArgumentException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(otherKfileloc)); 
	    String line,a,b;
	    int size=end-start;
	    double x,y;
	    KinematicDataList kinmeaticXY =new KinematicDataList();
	    int i=1,j=0;
	    int k=start;
	    while (((line = br.readLine()) != null)&&(j<size)&&(k>=start)&&(k<=end)) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   b=values[1];
	    	   x=Double.parseDouble(a);
	    	   y=Double.parseDouble(b);
	    	   kinmeaticXY.addKXValue(x);
	    	   kinmeaticXY.addKYValue(y);
	       }
	       i++;
	       j++;
	       k++;
	    }
	    br.close();
	    return kinmeaticXY;
	}
	
	//calculate average values from the DTW and find the gesture
	public void recognizeGestureFromDTW(DTWDistanceArrayList dtwdistanceArray[],String tfileloc) throws IOException
	{
		double dtwx[]=new double[dtwdistanceArray.length];
		double dtwy[]=new double[dtwdistanceArray.length];
		double avgX=0,avgY=0; //average value of DTW distances of X and Y value
		double minX,minY;
		int Xindex = 0,Yindex = 0;
		minX=dtwdistanceArray[0].DTWdistanceX;
		minY=dtwdistanceArray[0].DTWdistanceY;
		for(int i=1;i<dtwdistanceArray.length;i++)
		{
			if(minX > dtwdistanceArray[i].DTWdistanceX)
			{
				minX=dtwdistanceArray[i].DTWdistanceX;
				Xindex=i;
			}
			
		}
		for(int i=1;i<dtwdistanceArray.length;i++)
		{
			if(minY > dtwdistanceArray[i].DTWdistanceY)
			{
				minY=dtwdistanceArray[i].DTWdistanceY;
				Yindex=i;
			}
			
		}
		System.out.println("dtw min X value="+minX+"position="+Xindex);
		System.out.println("dtw min Y value="+minY+"position="+Yindex);
		System.out.println("dtw X Y value for poition="+Xindex+" X="+dtwdistanceArray[Xindex].DTWdistanceX+"Y="+dtwdistanceArray[Xindex].DTWdistanceY);
		System.out.println("dtw X Y value for poition="+Yindex+" X="+dtwdistanceArray[Yindex].DTWdistanceX+"Y="+dtwdistanceArray[Yindex].DTWdistanceY);
		System.out.println("first posibile gesture name="+getGestureName(tfileloc,Xindex));
		System.out.println("Secound posibile gesture name="+getGestureName(tfileloc,Yindex));
		
	}
	
	//read gesture name on a specific location form the Transcript file
	public String getGestureName(String tfileloc,int position) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(tfileloc)); 
	    String line;
	    String gestureName = null;
	    int i=1;
	    position=position+1;
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split(" ");
	        //System.out.println(values.length);
	       if(values.length<=3)
	       {   
	    	  // System.out.println(values[2]);
	    	   if(i==position)
	    	   {
	    	   gestureName =values[2];
	    	   }
	    
	       }
	       i++;
	    }
	    br.close();
				
		return gestureName;
	}
	
	//print arraylist
	public void print(ArrayList <Integer> arr)
	{
		for(double d:arr)
		{
			System.out.println(d);
		}
	}
	//print dtw distances
	public void printDTW(DTWDistanceArrayList []dt)
	{  // ArrayList <Double> arr=new ArrayList <Double>();
        for(int i=0;i<dt.length;i++)
        {
         System.out.println("dtw X value="+dt[i].DTWdistanceX);
         System.out.println("dtw Y value="+dt[i].DTWdistanceY);
        }
	}
	
	
	

}
