package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.algorithms.Action;

/**
 * 
 * Solution Class (Serializable) contains the solution actions (in ArrayList) and a String description for the suitable problem 
 *
 */
@SuppressWarnings("serial")
public class Solution implements Serializable{
	
	private ArrayList<String> displayStrings;
	private ArrayList<Action> actions;
	private String concatenatedString;
	private int numOfNodes;

	/**
	 * getter for the solution actions arrayList
	 * @return actions arrayList
	 */
	public ArrayList<Action> getActions() {
		return actions;
	}

	/**
	 * setter for the actions arrayList
	 * @param actions (ArrayList<Action>)
	 */
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}

	/**
	 * getter for the String arrayList that represents the solution
	 * @return this.displayStrings (ArrayList<String>)
	 */
	public ArrayList<String> getDisplayStrings() {
		return displayStrings;
	}

	/**
	 * setter for the String arrayList that represents the solution
	 * @param displayStrings (ArrayList<String>)
	 */
	public void setDisplayStrings(ArrayList<String> displayStrings) {
		this.displayStrings = displayStrings;
	}

	/**
	 * getter for the catenated String describing the suitable problem
	 * @return this.concatenatedString (String)
	 */
	public String getConcatenatedString() {
		return concatenatedString;
	}

	/**
	 * setter for the catenated String describing the suitable problem
	 * @param concatenatedString (String)
	 */
	public void setConcatenatedString(String concatenatedString) {
		this.concatenatedString = concatenatedString;
	}

	public int getNumOfNodes() {
		return numOfNodes;
	}

	public void setNumOfNodes(int numOfNodes) {
		this.numOfNodes = numOfNodes;
	}
	
	
}
