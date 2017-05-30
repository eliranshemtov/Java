package model.algorithms;

/**
 * This abstract class defines common denominator to all searchers
 * contains boolean data member - stop
 */
public abstract class CommonSearcher implements Searcher {
	
	protected int numOfNodes;
	boolean stop;
	
	/**
	 * changes the stop data member value to false <br>
	 * when this happens the searcher knows it needs to stop it's activity
	 */
	public void stop(){
		this.stop = true;
	}

	public int getNumOfNodes() {
		return numOfNodes;
	}

	public void setNumOfNodes(int numOfNodes) {
		this.numOfNodes = numOfNodes;
	}
	

}
