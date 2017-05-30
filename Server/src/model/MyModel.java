package model;

import java.util.ArrayList;

import model.algorithms.Action;
import model.algorithms.CommonHeuristicSearcher;
import model.algorithms.CommonSearcher;
import model.algorithms.Heuristic;
import model.algorithms.Searcher;
import model.domains.CommonSearchDomain;


/**
 * This class defines a search domain({@link CommonSearchDomain}) and it's properties: <br> 
 * Searcher algorithm - the search algorithm to solve the domain <br>
 * Solution solution - contains the Solution for the domain search problem. <br>
 * Heuristic heuristic - the heuristic which the algorithm can use to solve the domain.<br>
 * SolutionManager solutionManager - contains the only instance of the solutionManager <br>
 * String concatenatedStringWithAlgorithm - describe the algorithm and the domain description
 *
 */
public class MyModel implements Model {

	private CommonSearchDomain domain;
	private Searcher algorithm;
	private Solution solution;
	private Heuristic heuristic;
	private SolutionManager solutionManager;
	private String concatenatedStringWithAlgorithm;

	/**
	 * default c'tor. get the only instance of the solutionManager
	 */
	public MyModel() {
		solutionManager = SolutionManager.getInstance();
	}
	
	/**
	 * get Model and initialize all the data member in the class
	 * @param model
	 */
	public MyModel(MyModel model){
		this.domain = model.domain;
		this.algorithm = model.algorithm;
		this.solutionManager = model.solutionManager;
		this.solution = model.solution;
	}
	
	@Override 
	public void selectHeuristic(String heuristicName) {
		HeuristicsFactory heuristicsFactory = new HeuristicsFactory();
		this.heuristic = heuristicsFactory.createHeuristic(heuristicName);
		if (this.algorithm instanceof CommonHeuristicSearcher){
			((CommonHeuristicSearcher)this.algorithm).setHeuristic(this.heuristic);
		}
	}
	
	@Override
	public void selectDomain(String domainName, String arguments) {
		SearchDomainsFactory domainFactory = new SearchDomainsFactory();
		this.domain = domainFactory.createDomain(domainName,arguments);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		SearchAlgorithmsFactory SearchAlgorithm = new SearchAlgorithmsFactory();
		this.algorithm = SearchAlgorithm.createAlgorithm(algorithmName);
	}

	@Override
	public void solveDomain() {
		ArrayList<String> stringToDisplay = this.domain.getStringsToDisplay();
		
		this.solution = solutionManager.getSolution(concatenatedStringWithAlgorithm);
		if (this.solution == null){
			ArrayList<Action> actions = algorithm.search(this.domain);
			if (!actions.isEmpty())
			{	
				this.solution = new Solution();
				this.solution.setActions(actions);
				this.solution.setDisplayStrings(stringToDisplay);
				this.solution.setConcatenatedString(concatenatedStringWithAlgorithm);
				this.solution.setNumOfNodes(algorithm.getNumOfNodes());
				this.solutionManager.addSolution(solution);
			}
		}
	}

	@Override
	public CommonSearcher getAlgorithm() {
		return (CommonSearcher)this.algorithm;
	}
	
	/**
	 * getter for the concatenatedStringWithAlgorithm
	 * @return String
	 */
	public String getConcatenatedStringWithAlgorithm() {
		return concatenatedStringWithAlgorithm;
	}

	/**
	 * create the concatenatedStringWithAlgorithm using the this.algorithm and this.domain 
	 */
	public void concatenateStringWithAlgorithm() {
		concatenatedStringWithAlgorithm = this.algorithm.toString()+this.domain.getConcatenatedString();
	}
}
