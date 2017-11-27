import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class NewExtractGestureData {
	void readkinematicData(String kinematicLoc,String saveLoc,int start,int end,String gestureName) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(kinematicLoc));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(saveLoc+"gestureB001"+gestureName+".txt"));  
	    String line;
	    int i=0;
	    while ((line = br.readLine()) != null) {
	    	line = line.trim();
	        String[] values = line.split("  +");
	        System.out.println("line vale"+values.length);
	        System.out.println(Arrays.toString(values));
	        System.out.println(line);
	        if ((i>=start)&&(i<=end))
	        {
	        	bw.write(line);
	        	bw.newLine();
	        	System.out.println("writing data to file");
	        	bw.flush();
	        }
	        
	        i++;
	    }
	    br.close();
	    bw.close();
	    System.out.println(i);
	}
	//gesture data function
	public String[] readGesturevalue(String gestureFileLoc,int linenum) throws IOException
	{
		 BufferedReader br=new BufferedReader(new FileReader(gestureFileLoc));
		 String gesture;
		 String[] gstr = null;
		 int i=1;
		 while((gesture= br.readLine())!=null)
		    {   
	            if(linenum==i)
	            {
		    	gesture = gesture.trim();
		        gstr = gesture.split(" ");
		      // System.out.println("gstr Length="+gstr.length);
		        //System.out.println(Arrays.toString(gstr));
		        System.out.println("reading line number:"+linenum);
		       break;
	            }
	            else
	            	{i++;}
	            
		    }
		 br.close();
		 return gstr;
	}
	void getGestureKinematicdata(String kinematicLoc,String gestureLoc,String saveLoc,int totalgestureLines) throws IOException
	{   
		String [] gvalue=null;
		String gestureName=null;
		String a=null,b=null;
		int start=0,end=0;
		for(int i=1;i<totalgestureLines;i++)
		{
			gvalue=readGesturevalue(gestureLoc,i);
			System.out.println(Arrays.toString(gvalue));
	    	if (gvalue.length <= 3)
	        {
	           
	            a=gvalue[0];
	            b=gvalue[1];
	            gestureName=gvalue[2];
	            System.out.println("a="+a+"b="+b+"name="+gestureName);
	            start=Integer.parseInt(a);
	            end= Integer.parseInt(b);
	        }
	    	//call the function to make the file and save the kinematic data values according to the gestures
	    	readkinematicData(kinematicLoc,saveLoc,start,end,gestureName);
		}
	}

}
