package main;

import src.*;
import project_src.*;

import java.util.Optional;

/**
 *  MinConflictsSolver_project
 *  
 *  Muzaffer Tasiran 181805017
 *  Sefa Samet Sutcu 181805038
 *  
 *  Because of randomness MinConflictsSolver cannot find solutions mostly.
 *  for loop added for show to there is solution exists.
 *
 */

public class project_main {
	public static void main(String[] args) {
		int size = 8;
		CSP<Variable, Integer> csp = new NQueensCSP(size);
		CspListener.StepCounter<Variable, Integer> stepCounter = new CspListener.StepCounter<>();
		CspSolver<Variable, Integer> solver;
		Optional<Assignment<Variable, Integer>> solution;
		
		System.out.println(size + "-Queens (MinConflictsSolver_project)");
		for (int i=1;i<100;i++) { // for loop added for show to there is solution exists 
			solver = new MinConflictsSolver<>(1000);
			solver.addCspListener(stepCounter);
			stepCounter.reset();
			solution = solver.solve(csp);
			if (solution.isPresent()) {
				System.out.println(solution.get());
				System.out.println(stepCounter.getResults());
				System.out.println("Solution found on " + i + "th try \n");
				break;
			}	
			System.out.println(stepCounter.getResults() + "\n");
		}
	}
}

