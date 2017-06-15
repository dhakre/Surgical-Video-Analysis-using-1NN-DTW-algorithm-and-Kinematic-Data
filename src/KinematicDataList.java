import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KinematicDataList {
	public ArrayList<Double> KXValue= new ArrayList<Double>();
	public ArrayList<Double> KYValue= new ArrayList<Double>();
	
	/*  
	 //other 75 values
	  //Rotation Matrix 4 to 12 values
	  public ArrayList<Double> KXValue= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix4= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix5= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix6= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix7= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix8= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix9= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix10= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix11= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix12= new ArrayList<Double>();
	  //Linear velocity values 13 to 15
	  public ArrayList<Double> KLinearveocity13= new ArrayList<Double>();
	  public ArrayList<Double> KLinearveocity14= new ArrayList<Double>();
	  public ArrayList<Double> KLinearveocity15= new ArrayList<Double>();
	  //Rotation velocities 16 to 18 
	  public ArrayList<Double> KRotationVelocity16= new ArrayList<Double>();
	  public ArrayList<Double> KRotationVelocity17= new ArrayList<Double>();
	  public ArrayList<Double> KRotationVelocity18= new ArrayList<Double>();
	  //Angle velocity 19
	  public ArrayList<Double> KAngleVelocity= new ArrayList<Double>();
	  //Right Kinemaics Values 20 to 38 values
	  public ArrayList<Double> KKinematics20= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics21= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics22= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics23= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics24= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics25= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics26= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics27= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics28= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics29= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics30= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics31= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics32= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics33= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics34= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics35= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics36= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics37= new ArrayList<Double>();
	  public ArrayList<Double> KKinematics38= new ArrayList<Double>();
      //PSM1 Tool tip position XYZ 39 to 41
      public ArrayList<Double> KToolTipX= new ArrayList<Double>();
	  public ArrayList<Double> KToolTipY= new ArrayList<Double>();
	  public ArrayList<Double> KToolTipZ= new ArrayList<Double>();
	  //PSM1 Rotation Matrix 42 to 50
	  public ArrayList<Double> KRotationMatrix42= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix43= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix44= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix45= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix46= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix47= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix48= new ArrayList<Double>();
	  public ArrayList<Double> KRotationMatrix49= new ArrayList<Double>();
      public ArrayList<Double> KRotationMatrix50= new ArrayList<Double>();
      //PSM1 Tool tip linear velocity 51 to 53
      public ArrayList<Double> KLinearVelocity51= new ArrayList<Double>();
      public ArrayList<Double> KLinearVelocity52= new ArrayList<Double>();
      public ArrayList<Double> KLinearVelocity53= new ArrayList<Double>();
      //PSM1 tool tip Rotational Velocity 54 to 56
      public ArrayList<Double> KRotationalVelocity54= new ArrayList<Double>();
      public ArrayList<Double> KRotationalVelocity55= new ArrayList<Double>();
      public ArrayList<Double> KRotationalVelocity56= new ArrayList<Double>();
      //PSM 1 gripper angle velocity 57
      public ArrayList<Double> KGripperAngleVelocity= new ArrayList<Double>();
      //PSM2 Kinematics 58 to 76
      public ArrayList<Double> KKinematics58= new ArrayList<Double>();
      public ArrayList<Double> KKinematics59= new ArrayList<Double>();
      public ArrayList<Double> KKinematics60= new ArrayList<Double>();
      public ArrayList<Double> KKinematics61= new ArrayList<Double>();
      public ArrayList<Double> KKinematics62= new ArrayList<Double>();
      public ArrayList<Double> KKinematics63= new ArrayList<Double>();
      public ArrayList<Double> KKinematics64= new ArrayList<Double>();
      public ArrayList<Double> KKinematics65= new ArrayList<Double>();
      public ArrayList<Double> KKinematics66= new ArrayList<Double>();
      public ArrayList<Double> KKinematics67= new ArrayList<Double>();
      public ArrayList<Double> KKinematics68= new ArrayList<Double>();
      public ArrayList<Double> KKinematics69= new ArrayList<Double>();
      public ArrayList<Double> KKinematics70= new ArrayList<Double>();
      public ArrayList<Double> KKinematics71= new ArrayList<Double>();
      public ArrayList<Double> KKinematics72= new ArrayList<Double>();
      public ArrayList<Double> KKinematics73= new ArrayList<Double>();
      public ArrayList<Double> KKinematics74= new ArrayList<Double>();
      public ArrayList<Double> KKinematics75= new ArrayList<Double>();
      public ArrayList<Double> KKinematics76= new ArrayList<Double>();
 
	  */
	
	public void addKXValue(double kxValue)
	{
		KXValue.add(kxValue);
	}
	public void addKYValue(double kyValue)
	{
		KYValue.add(kyValue);
	}
	
	public double getXvalue(int index)
	{
		double x=KXValue.get(index);
		return x;
	}
	public double getYvalue(int index)
	{
		double y=KYValue.get(index);
		return y;
	}
	
	//function to calculate kinematic data values
	public  KinematicDataList readkinematicValue(String kfileloc) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(kfileloc)); 
	    String line,a,b;
	    double element1,element2;
	    KinematicDataList kdata =new KinematicDataList();
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   b=values[1];
	    	   element1=Double.parseDouble(a);
	    	   element2=Double.parseDouble(b);
	    	   kdata.addKXValue(element1);
	    	   kdata.addKYValue(element2);
	       }
	    }
	    br.close();
	    return kdata;
	}
	
	public void print(KinematicDataList k)
	{   
		ArrayList <Double> a=new ArrayList<Double>();
		//ArrayList <Double> b=new ArrayList<Double>();
		a=k.KXValue;
		for(double d :a)
		{
			System.out.println("data X="+d);
		}
		a=k.KYValue;
		for(double d :a)
		{
			System.out.println("data Y="+d);
		}
		
	}
	
	//main method
	/*public static void main(String args[]) throws IOException
	{
		String kfileloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt";;
		ArrayList<Double> kxvalue= new ArrayList<Double>();
		KinematicDataList kobj= new KinematicDataList();
		kxvalue=kobj.readkinematicValue(kfileloc);
		for(double d :kxvalue)
		{
			System.out.println("data="+d);
		}
		int size = kxvalue.size()-1;
		System.out.println("size="+size);
	}*/

}
