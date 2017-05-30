package model.algorithms;

import java.io.Serializable;

/**
 * 
 * Action Class (Serializable) describe an action<br>
 * String that describe the action and a double for action's cost
 *
 */
@SuppressWarnings("serial")
public class Action implements Serializable{

	private String moves;
	private double priceAction;
	
	/**
	 * getter for the move
	 * @return this.moves (String)
	 */
	public String getMoves() {
		return moves;
	}

	/**
	 * sets this.moves to the given String
	 * @param moves (String)
	 */
	public void setMoves(String moves) {
		this.moves = moves;
	}
	
	/**
	 * Action C'tor - gets a String of a move and sets this.moves by the given one
	 * @param moves
	 */
	public Action (String moves){
		this.moves = moves;
	}
	
	@Override
	public String toString() {
		return this.moves;
	}
	
	@Override
	public int hashCode() {
		return this.moves.hashCode();
	}

	/**
	 * gets this.priceAction (describes the cost of the action)
	 * @return priceAction (double)
	 */
	public double getPriceAction() {
		return priceAction;
	}

	/**
	 * sets this.priceAction (describes the cost of the action)
	 * @param priceAction (double
	 */
	public void setPriceAction(double priceAction) {
		this.priceAction = priceAction;
	}
	
	
}
