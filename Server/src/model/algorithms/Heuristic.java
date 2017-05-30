package model.algorithms;

/**
 * 
 * This interface defines the function getEvaluation
 *
 */
public interface Heuristic {
	/**
	 * get the estimated distance from the current state to the destination
	 * @param current - the current state (State)
	 * @param destination - the goal state(State)
	 * @return number representing the evaluate distance to the destination (double)
	 */
	public double getEvaluation(State current, State destination); 
}
