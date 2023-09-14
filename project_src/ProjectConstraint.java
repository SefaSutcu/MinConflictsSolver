package project_src;

import src.*;

import java.util.ArrayList;
import java.util.List;

/**
 * works two way, if variables are same, checks the position on queen (for first two columns)
 * 				  if different, checks position values on queens diff equal with position
 */

public class ProjectConstraint implements Constraint<Variable, Integer> {

	private Variable var1;
	private Variable var2;
	private int pos;
	private List<Variable> scope;

	public ProjectConstraint(Variable var1, Variable var2, int pos) {
		this.var1 = var1;
		this.var2 = var2;
		this.pos = pos;
		scope = new ArrayList<Variable>(2);
		scope.add(var1);
		scope.add(var2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, Integer> assignment) {
		Integer value1 = assignment.getValue(var1);
		Integer value2 = assignment.getValue(var2);
		if (var1 == var2) { // for first two columns
			return (value1 == null || value2 == null || value1 == pos);
		} else {
			return (value1 == null || value2 == null || Math.abs(value1 - value2) == pos);
		}
//		return (value1 == null || value2 == null || Math.abs(value1 - value2) == pos);
	}
}

