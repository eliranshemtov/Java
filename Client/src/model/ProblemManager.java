package model;

import java.util.HashMap;

/**
 * 
 * ProblemManager class is a singleTone Class which will map the problems descriptions (Strings) to the problem{@link Problem}<br><br>
 *
 */
public class ProblemManager {
	
	private static ProblemManager instance = null;
	private int count;
	private HashMap<Integer, Problem> problemMap;
	
	/**
	 * initialize the problemMap hashMap
	 */
	protected ProblemManager() {
		this.problemMap = new HashMap<Integer, Problem>();
		count = 0;
	}
	
	/**
	 * @return the only (SingleTone) instance of the class
	 */
	public static ProblemManager getInstance() {
		if (instance == null)
			instance = new ProblemManager();
		return instance;
	}
	
	/**
	 * gets a problem and add it to problemMap hashMap
	 * @param problem {@link Problem}
	 */
	public void addProblem (Problem problem){
		count = count+1;
		problemMap.put(count, problem);
	}

	/**
	 * getter for the hashMap
	 * @return problemMap hashMap
	 */
	public HashMap<Integer, Problem> getProblemMap() {
		return problemMap;
	}
	
	/**
	 * 
	 * @param index (int)
	 * @return problem at the given index(int) in the hashMap
	 */
	public Problem getProblem(int index){
		return this.problemMap.get(index);
	}
}
