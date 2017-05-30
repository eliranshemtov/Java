package model.algorithms;

import java.util.ArrayList;

import model.domains.CommonSearchDomain;
/**
 * 
 * This interface defines the function search that w
 *
 */
public interface Searcher {
	/**
	 * 
	 * @param domain - representing the problem to solve (CommonSearchDomain)
	 * @return ArrayList<Action> - all the actions to do in order to get to the destination
	 */
	ArrayList<Action> search (CommonSearchDomain domain);
	public void setNumOfNodes(int numOfNodes);
	public int getNumOfNodes();
}
