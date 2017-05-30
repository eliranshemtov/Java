package model;

import java.util.HashMap;

import model.algorithms.AstarSearch;
import model.algorithms.Bfs;
import model.algorithms.Searcher;

/**
 * SearchAlgorithmsFactory class contains hashMap of available Algorithms <br>
 * this class maps between the String heuristic name to the relevant algorithm
 */
public class SearchAlgorithmsFactory {
	
	private HashMap<String, AlgorithmCreator> algorithms;
	
	/**
	 * default c'tor <br>
	 * initialize the hashMap and adds to it all it's properties using factory pattern
	 *
	 */
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("BFS".toLowerCase(),new BfsCreator());
		algorithms.put("Astar".toLowerCase(),new AstarCreator());
	}
	
	/**
	 * gets String algorithm name and creates an instance of the suitable algorithm <br>
	 * this method return the instance of the algorithm which was created.<br>
	 * if there isn't suitable algorithm the method will return null. 
	 * @param algorithmName (String)
	 * @return {@link Searcher}
	 */
	public Searcher createAlgorithm(String algorithmName)
	{
		AlgorithmCreator creator = algorithms.get(algorithmName);
		if (creator != null)
			return creator.create();
		return null;
	}
	
	/**
	 * this interface define the function - "create()" which create an instance of an algorithm and returns it
	 */
	private interface AlgorithmCreator
	{
		/**
		 * the function create an instance of the relevant algorithm and returns it
		 * @return Searcher 
		 */
		Searcher create();
	}
	
	/**
	 * The Class implements {@link AlgorithmCreator} <br>
	 * create BFS search algorithm
	 */
	private class BfsCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Bfs();
		}
		
	}
	
	/**
	 * The Class implements {@link AlgorithmCreator} <br>
	 * create Astar search algorithm
	 */
	private class AstarCreator implements AlgorithmCreator
	{	
		
		@Override
		public Searcher create() {
			return new AstarSearch();
		}
	}
	
}
