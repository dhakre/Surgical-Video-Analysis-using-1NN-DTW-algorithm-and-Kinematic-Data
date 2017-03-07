package convertFormatVideo;

import java.io.File;
import java.util.Scanner;

public class ReadTextFile {
private Scanner f;

public void openfile(){
	try{
		f= new Scanner(new File("C:/Users/user/Documents/INTERNSHIP_DATA/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt"));
		
	}
	catch(Exception e)
	{
		System.out.println("Error while reading the file");
	}
}

public void readFileData()
{    int i=0;
	while(f.hasNext())
	{   
		
		//read a data from the text file
		Double a=f.nextDouble();
		/*Double b=f.nextDouble();
		//Double c=f.nextDouble();
		//if(a== null)
		 a= 0.0;
		if(b==null)
			b=0.0;
		if(c==null)
			c=0.0; */
          if(i==3)
          {    
        	  System.out.println("\n");
			   System.out.println(a+"  ");	
			   
			   }
          else{
        	  System.out.println(a);
          }
       
	}
	//System.out.println("\n");
}

public void closeFile()
{
     f.close();	
}

//main function to run the code
public static void main(String args[])
{
ReadTextFile r= new ReadTextFile();
r.openfile();
r.readFileData();
r.closeFile();

}
}
