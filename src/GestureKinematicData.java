import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GestureKinematicData {
 public void readKinemticForGesture(String KinematicLoc,String saveLoc,int start,int end,String gestureName) throws IOException
 {
	 BufferedReader br = new BufferedReader(new FileReader(KinematicLoc));
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
  
}
