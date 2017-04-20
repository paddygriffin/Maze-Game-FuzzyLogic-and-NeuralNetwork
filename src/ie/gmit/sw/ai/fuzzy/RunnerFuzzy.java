package ie.gmit.sw.ai.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class RunnerFuzzy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FIS fis = FIS.load("fcl/health.fcl", true);
		FunctionBlock fb = fis.getFunctionBlock("Health");
		JFuzzyChart.get().chart(fb);
		fis.setVariable("playerHealth", 50);
		fis.setVariable("enemyHealth", 50);
		fis.evaluate();
		Variable Aggressiveness = fb.getVariable("Aggressiveness");
		//System.out.println();
		JFuzzyChart.get().chart(Aggressiveness, true);
		//System.out.println(tip.defuzzify());
		JFuzzyChart.get().chart(Aggressiveness.getDefuzzifier(), "Result Aggressiveness (%)", true);
		
		//print ruleset
		System.out.println(fb.getVariable("Aggressiveness").getValue());

	}

}
