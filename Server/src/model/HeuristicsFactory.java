package model;

import java.util.HashMap;

import model.algorithms.Heuristic;
import model.domains.MazeHeuristic;
import model.domains.SlidePuzzleHeuristic;

/**
 * HeuristicsFactory class contains hashMap of available heuristics <br>
 * this class maps between the String heuristic name to the relevant heuristic
 */
public class HeuristicsFactory {
	
	private HashMap<String, HeuristicCreator> heuristics;
	
	/**
	 * default c'tor <br>
	 * initialize the hashMap and adds to it all it's properties using factory pattern
	 *
	 */
	public HeuristicsFactory() {
		heuristics = new HashMap<String,HeuristicCreator>();
		heuristics.put("MazeHeuristic".toLowerCase(), new MazeHeuristicCreator());
		heuristics.put("SlidePuzzleHeuristic".toLowerCase(), new SlidePuzzleHeuristicCreator());
	}
	
	/**
	 * gets String heuristic name and creates an instance of the suitable heuristic <br>
	 * this method return the instance of the heuristic which was created.<br>
	 * if there isn't suitable heuristic the method will return null. 
	 * @param heuristicName (String)
	 * @return {@link Heuristic}
	 */
	public Heuristic createHeuristic(String heuristicName)
	{
		HeuristicCreator creator = heuristics.get(heuristicName);
		if (creator != null)
			return creator.create();
		return null;
	}
	
	/**
	 * this interface define the function - "create()" which create an instance of an heuristic and returns it
	 */
	private interface HeuristicCreator
	{
		/**
		 * the function create an instance of the relevant heuristic and returns it
		 * @return Heuristic 
		 */
		Heuristic create();
	}
	
	/**
	 * The Class implements {@link HeuristicCreator} <br>
	 * create slide puzzle heuristic
	 */
	private class SlidePuzzleHeuristicCreator implements HeuristicCreator
	{
		@Override
		public Heuristic create() {
			return new SlidePuzzleHeuristic();
		}
	}
	
	/**
	 * The Class implements {@link HeuristicCreator} <br>
	 * create maze puzzle heuristic
	 */
	private class MazeHeuristicCreator implements HeuristicCreator
	{
		@Override
		public Heuristic create() {
			return new MazeHeuristic();
		}
	}

}
