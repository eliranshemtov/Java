package model.domains;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithms.Action;
import model.algorithms.State;
/**
 * This interface defines common denominator to all domains that can be solved using search algorithm. <br>
 */
public interface CommonSearchDomain {
	/**
	 * getter for the start state
	 * @return State
	 */
	State getStartState();
	/**
	 * getter for the destination state
	 * @return State
	 */
	State getDestinationState();
	/**
	 * get all possible moves from current state
	 * @param current (State)
	 * @return HashMap<Action, State>
	 */
	HashMap<Action, State> getAllPossibleMoves (State current); 
	/**
	 * get strings array that can be displayed
	 * @return ArrayList<String> 
	 */
	ArrayList<String> getStringsToDisplay(); 
	/**
	 * get all parameters of the domain concatenated in one string
	 * @return String
	 */
	String getConcatenatedString(); 
}