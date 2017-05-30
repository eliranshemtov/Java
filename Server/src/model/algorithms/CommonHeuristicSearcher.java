package model.algorithms;

/**
 * This abstract class defines common denominator to all searchers with heuristic
 * contains Heuristic data member
 */
public abstract class CommonHeuristicSearcher extends CommonSearcher {

	protected Heuristic h;
	
	/**
	 * setter for this.h data member
	 * @param h (Heuristic)
	 */
	public void setHeuristic(Heuristic h){
		this.h = h;
	}
}
