package ie.gmit.sw.ai.fuzzy;

//Fuzzy logic import
import net.sourceforge.jFuzzyLogic.FIS;

public class Anger {
	
	private String fileName;
	private FIS fis;
	
	public Anger()
	{
		 fileName = "fcl/health.fcl";
		 
	     fis = FIS.load(fileName,true);
	     
	     if( fis == null ) 
	     { 
	         System.err.println("Can't load file: '" + fileName + "'");
	         return;
	     }
	}//
	
	public AngerLevel howAngryAmI(int h,int f)
	{
	    fis.setVariable("playerHealth", h);
	    fis.setVariable("angerrlevel", f);

	    fis.evaluate();
	    
	    double x = fis.getVariable("Aggressiveness").getValue();
	    
	    if(x < 2)
	    {
	    	return AngerLevel.RUN;
	    }
	    else if(x < 4)
	    {
	    	return AngerLevel.FIGHT;
	    }
	    else 
	    {
	    	return AngerLevel.ATTACK;
	    }
	   
	}

}
