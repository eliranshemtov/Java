package model;

import java.util.Observable;
import network.Client;
/**
 * MyModel Class extends observable and implements Model interface<br>
 * This class defines a problem(type of problem class) and a solution(type of solution class) data members 
 * 
 *
 */
public class MyModel extends Observable implements Model {
	
	private Problem problem;
	private Solution solution;
	
	/**
	 * MyModel() Default C'tor. defines this.problem to a new Problem object.
	 */
	public MyModel() {
		problem = new Problem();
	}
	
	/**
	 * selectHeuristic function -<br>
	 * gets a String that will describe a heuristic method and will define this. {@link Problem} .heuristic 
	 */
	@Override 
	public void selectHeuristic(String heuristicName) {
		this.problem.setHeuristic(heuristicName);
	}
	
	/**
	 * selectDomain - gets domainName(String) and arguments(String) that will describe its properties<br>
	 * Sets this.problem.domainName and this.problem.arguments to the given params
	 */
	@Override
	public void selectDomain(String domainName, String arguments) {
		this.problem.setDomainName(domainName);
		this.problem.setDomainArgs(arguments);
	}

	
	/**
	 * selectAlgorithm will get algorithmName(String) and will set this.problem.algorithm to the given String
	 */
	@Override
	public void selectAlgorithm(String algorithmName) {
		this.problem.setAlgorithmName(algorithmName);
	}

	/**
	 * SolveDomain method is not relevant to GUI view. suitable for earlier modes (CMD)<br>
	 * A request to solveDomain will initially try to start a connection with the server (according to xml properties)<br>
	 * gets the instance of ProblemManager (singleTone) <br>
	 * it will send the problem to the server and will get the catenated String which will describe the entire problem
	 * <br> finally it will save the problem in the problem manager.
	 */
	@Override
	public void solveDomain() {
		Client client = new Client();
		ProblemManager problemManager = ProblemManager.getInstance();
		this.problem.setGui(false);
		this.problem.setEntireDomainDescription(client.runSolution(this.problem));
		problemManager.addProblem(this.problem);
	}
	
	/**
	 * getSolution function is relevant only after running solveDomain function (Not relevant to GUI view!)<br>
	 * Gets the problem's index in the problem manager hashMap and will. fetch the right problem from it. <br>
	 * initialize connection with the server and sends the problem to the server in order to get solution.<br>
	 * If the server can solve the problem - it will send it back to the client and this MyModel instance will save the solution inside this.solution.<br>
	 * else, it will notify the observer.
	 */
	@Override
	public void getSolution(int index) {
		ProblemManager problemManager = ProblemManager.getInstance();
		Problem problem = problemManager.getProblem(index);
		
		this.setChanged();
		if (problem != null){
		
			Client client = new Client();
			this.solution = client.getSolution(problem);
			if (solution == null)
				this.notifyObservers("No solution");
			else this.notifyObservers(solution);
		}
		else
			this.notifyObservers("no problem");
		
	}

	/**
	 * The solveDomainAndDisply method is relevant to GUI view!<br>
	 * initialize connection with the server, send the problem to it and receive the solution using solveAndDisplay client's method.
	 * it will set this.solution to the received solution and notify the observer.
	 */
	@Override
	public void solveDomainAndDisplay() {
		Client client = new Client();
		this.problem.setGui(true); // self indicator to view mode (gui/cmd)
		MyModel.this.solution = client.solveAndDisplay(MyModel.this.problem);
		MyModel.this.setChanged();
		MyModel.this.notifyObservers(solution);
	}
}
