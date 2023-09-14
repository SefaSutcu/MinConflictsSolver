package project_src;

import src.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 *  first two columns stable on 4, 2.
 *  considering figure on assignment, positions on odd value columns increases one by one,
 *  so constraint and this class configured for finding that pattern.
 *  
 */

public class NQueensCSP extends CSP<Variable, Integer> {

	public NQueensCSP(int size) {
		for (int i = 0; i < size; i++)
			addVariable(new Variable("Q" + (i+1)));
		
		List<Integer> values = new ArrayList<>();
		for (int val = 1; val <= size; val++)
			values.add(val);
		Domain<Integer> positions = new Domain<>(values);

		for (Variable var : getVariables())
			setDomain(var, positions);
		
		Variable var1;
		Variable var2;
		var1 = getVariables().get(0); // first two columns stable on 4, 2.
		addConstraint(new ProjectConstraint(var1, var1, 4));
		var1 = getVariables().get(1);
		addConstraint(new ProjectConstraint(var1, var1, 2));
		
		for (int i = 0; i < size; i++) { 
			var1 = getVariables().get(i);
			for (int j = i+1; j < size; j++) {
				var2 = getVariables().get(j); 
				addConstraint(new DiffNotEqualConstraint(var1, var2, 0));
				addConstraint(new DiffNotEqualConstraint(var1, var2, j-i));
			}
		}
		
		for (int i=2; i<size; i+=2) { 	  // considering figure on assignment, positions on odd value columns increases one by one,
			var1 = getVariables().get(i); // so constraint and this class configured for finding that pattern.
			int j = i+2;
			if (j>7) {
				j-=8;
			}
			var2 = getVariables().get(j);
			addConstraint(new ProjectConstraint(var1, var2, 1));
		}
		
	}
}
