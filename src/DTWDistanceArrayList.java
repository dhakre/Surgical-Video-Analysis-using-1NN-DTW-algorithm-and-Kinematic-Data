import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DTWDistanceArrayList extends DataArrayListXY{
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
	
	//main method
	public static void main(String args[]) throws IllegalArgumentException, IOException
	{
		DTWDistanceArrayList dt=new DTWDistanceArrayList();
		String otherKfileloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B003.txt";
		KinematicDataList kdata=new KinematicDataList();
		kdata=dt.comparisonArrayList(otherKfileloc, 45, 85);
		kdata.print(kdata);
		
		
	}
	

}
