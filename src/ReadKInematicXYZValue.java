import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadKInematicXYZValue {
	public static void main(String args[])
	{
	try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/user/Documents/Internship/Knot_Tying_B001.txt"));
		     BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/user/Documents/Java gesture recognistion/HistogramsAndCharts/XYZdata.txt"))){  
		//File f=new File("C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt");
		String line;
		int i=0;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(" ");
		        System.out.println("line vale"+values.length);
		        if (values.length >= 3)
		        {
		        	bw.write(values[0] + ' ' + values[1] + ' ' + values[2] + "\n");
		        	bw.write("\n");
		        	System.out.println("writing data to file");
		        	
		        }
		        
		        i++;
		    }
		    br.close();
		    bw.close();
		    System.out.println(i);
		   // String line2 = Files.readAllLines(Paths.get("C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt")).get(2);
		    //System.out.println("secound line of code"+line2);
		}
	catch(Exception e)
	{
		System.out.println("cannout read file");
	}


}

}
