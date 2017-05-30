package model;

import java.util.Observer;

/**
 * 
 * Model interface<br>
 * Defines the following functions:<br>
 *	void selectDomain(String domainName, String arguments) - sets the domain name<br>
	void selectAlgorithm(String algorithmName) -  sets the algorithm algorithm name<br>
	void selectHeuristic(String heuristicName) -  sets the heuristic name<br>
	void solveDomain() - solve the domain's problem and notify<br>
	void solveDomainAndDisplay() - solve domain and display the solution<br>
	void getSolution(int index) - get the solution by it's index<br>
	void addObserver(Observer o) - set new observer to this object (which will be observable) <br>
 */
public interface Model {
	/**
	 * void selectDomain(String domainName, String arguments) - sets the domain name<br>
	 */
	void selectDomain(String domainName, String arguments); // select the domain using factory
	
	/**
	 *void selectAlgorithm(String algorithmName) -  sets the algorithm algorithm name<br>
	 */
	void selectAlgorithm(String algorithmName); // select the algorithm using factory
	
	/**
	 * void selectHeuristic(String heuristicName) -  sets the heuristic name<br>
	 */
	void selectHeuristic(String heuristicName); // select the heuristic using factory
	
	/**
	 * void solveDomain() - solve the domain's problem and notify<br>
	 */
	void solveDomain(); // solve the domain's problem and notify
	
	/**
	 * void solveDomainAndDisplay() - solve domain and display the solution<br>
	 */
	void solveDomainAndDisplay();
	
	/**
	 * 
	 * void getSolution(int index) - get the solution by it's index<br>
	 */
	void getSolution(int index); // get the solution by it's index
	
	/**
	 * void addObserver(Observer o) - set new observer to this object (which will be observable) <br>
	 */
	void addObserver(Observer o);
}
