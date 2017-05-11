import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DTW {
	 private static int inf = 9000;

	    public static double DTWDistance(double[] s, double[] t){
	        double[][] dtw = new double[s.length + 1][t.length + 1];

	        dtw[0][0] = 0;
	        for (int i = 1; i <s.length + 1; i++) {
	            dtw[i][0] = inf;
	        }
	        for (int i = 1; i <t.length + 1; i++) {
	            dtw[0][i] = inf;
	        }

	        for (int i = 1; i < s.length + 1; i++) {
	            for (int j = 1; j < t.length + 1; j++) {
	                double cost = distanceD(s[i - 1], t[j - 1]);
	                dtw[i][j] = cost + minimum(dtw[i - 1][j],      //match
	                                           dtw[i][j - 1],      //insertion
	                                           dtw[i - 1][j - 1]); //deletion
	            }
	        }
	        return dtw[s.length][t.length];
	    }

	    private static double distanceD(double a, double b) {
	        return Math.abs(a-b);
	    }

	    ///Finds the lowest value and returns it
	    private static double minimum(double i, double i1, double i2) {
	        if (i < i1) {
	            if (i < i2) {
	                return i;
	            }
	        } else if (i1 < i2) {
	            return i1;
	        }
	        return i2;
	    }
	    //function to calculate the total number of lines in the file
	    public int calculateFileLength(String fileloc) throws IOException
	    { 
	    	BufferedReader br = new BufferedReader(new FileReader(fileloc));
	    	String line=null;
	    	int i=0;
	    	while((line = br.readLine()) != null)
	    	{
	    		line = line.trim();
	    	    String[] values = line.split(" +");
	    	    i++;
	    	}
	    	System.out.println("total values read="+i);
	    	return(i);
	    }
	    //funcition to read the first character of each line from the file
	    public double[] getdata(String fileLoc) throws IllegalArgumentException, IOException
	    {
	    	BufferedReader br = new BufferedReader(new FileReader(fileLoc));
	    	String line=null;
	        String a;
	        double b;
	        int i=1,size=0,j=0;
	        size=calculateFileLength(fileLoc);
	        double arr[] = new double[size];// diclare array for the calculated size
	    	while(((line = br.readLine()) != null)&&(j<size))
	    	{
	    		line = line.trim();
	    	    String[] values = line.split(" +");
	    	   // System.out.println("line  length"+values.length);
	    	    //System.out.println(Arrays.toString(values));
	    	    i++;
	    	    //System.out.println(line);
	    	    //System.out.println("in the while loop");
	    	   if (values.length>=2)
	    	   { 
	    	         a=values[0];
	    	         b=Double.parseDouble(a);
	    	         arr[j]=b;
	    	        // System.out.println("b="+b);
	    	   }
	    	   //System.out.println("arr="+arr[j]);
	    	   j++;
	    	}
	    	//System.out.println("total values read="+i);
	    	System.out.println("arr length"+arr.length);
			return (arr);
	    }
	    
   // main body
	    public static void main(String args[]) throws IOException{
	    
	    	String kloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt";
	    	String gloc="C:/Users/user/Documents/Java gesture recognistion/XYKinematicDataGraphs/Gesture_Data/Knot_Tying/Knot_Tying_B001/gestureB001G11_10.txt";
	    	DTW dt= new DTW();
	    	double [] arr1=dt.getdata(kloc);
	    	double [] arr2=dt.getdata(gloc);
	    	int len=arr1.length;
	    	//System.out.println(arr1.length);
	    	//for(int i=0;i<len;i++)
	        //System.out.println(arr1[i]);
	    	//for(int i=0;i<arr2.length;i++)
	    		//System.out.println(arr2[i]);	
	    	System.out.println("DTW distance="+DTW.DTWDistance(arr1, arr2));
	    }
}
