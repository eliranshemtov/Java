package model;

import model.algorithms.CommonSearcher;

/**
 * Model interface<br>
 * Defines the following functions:<br>
 *	void selectDomain(String domainName, String arguments) - select the domain using factory<br>
	void selectAlgorithm(String algorithmName) -  select the algorithm using factory<br>
	void selectHeuristic(String heuristicName) -  select the heuristic using factory<br>
	void solveDomain() - solve the domain's problem and notify<br>
	CommonSearcher getAlgorithm() - return the instance of the algorithm that was chosen for this Model <br>
 */

public interface Model{
	/**
	 * select the domain using factory<br>
	 * @param domainName (String)
	 * @param arguments (String)
	 */
	void selectDomain(String domainName, String arguments);
	
	/**
	 * select the algorithm using factory<br>
	 * @param algorithmName (String)
	 */
	void selectAlgorithm(String algorithmName);
	
	/**
	 * select the heuristic using factory<br>
	 * @param heuristicName (String)
	 */
	void selectHeuristic(String heuristicName); 
	
	/**
	 * solve the domain's problem and notify
	 */
	void solveDomain(); 
	
	/**
	 * get the instance of the algorithm that was chosen for this Model <br>
	 * @return {@link CommonSearcher} 
	 */
	CommonSearcher getAlgorithm();
}
