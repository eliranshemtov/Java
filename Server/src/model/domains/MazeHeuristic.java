package model.domains;

import model.algorithms.Heuristic;
import model.algorithms.State;

/**
 * an Heuristic that can be used by different algorithm to find the maze path
 */
public class MazeHeuristic implements Heuristic {

	@Override // Manhattan heuristic
	public double getEvaluation(State current, State destination) {
		
		String[] coordinates = current.getState().split(",");
		int xCurrent = Integer.parseInt(coordinates[0]);
		int yCurrent = Integer.parseInt(coordinates[1]);
		
		coordinates = destination.getState().split(",");
		int xDestination = Integer.parseInt(coordinates[0]);
		int yDestination = Integer.parseInt(coordinates[1]);
		
		return ((yDestination - yCurrent) + (xDestination - xCurrent));
	}

}
