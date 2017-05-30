package model.algorithms;

/**
 * this abstract class representing a state by it's description. <br>
 * the class also contains the previous state, the previous action which lead to this state and the state price
 */
public abstract class State implements Comparable<State>{

	private String stateDescription;
	private State previousState;
	private Action previousAction;
	private double statePrice; 
	
	/**
	 * c'tor gets String representing the state and initialize the this.stateDescription by the given one
	 * @param description
	 */
	public State(String description) {
		this.stateDescription = description;
	}
	
	/**
	 * getter for this.stateDescription
	 * @return String 
	 */
	public String getState() {
		return stateDescription;
	}
	
	@Override
	public String toString() {
		return this.stateDescription;
	}
	
	@Override
	public boolean equals(Object obj) {
		 return this.stateDescription.equals(((State)obj).getState());
	}
	 
	@Override
	public int hashCode() {
		return this.stateDescription.hashCode();
	}

	/**
	 * getter for this.previousState
	 * @return State 
	 */
	public State getPreviousState() {
		return previousState;
	}

	/**
	 * setter for this.previousState 
	 * @param previousState
	 */
	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

	/**
	 * getter for this.previousAction
	 * @return Action
	 */
	public Action getPreviousAction() {
		return previousAction;
	}

	/**
	 * setter for this.previousAction
	 * @param previousAction
	 */
	public void setPreviousAction(Action previousAction) {
		this.previousAction = previousAction;
	}
	
	/**
	 * setter for this.statePrice
	 * @return double
	 */
	public double getStatePrice() {
		return statePrice;
	}

	/**
	 * setter for this.statePrice 
	 * @param statePrice
	 */
	public void setStatePrice(double statePrice) {
		this.statePrice = statePrice;
	}
	
	@Override
	public int compareTo(State state) {
		if (this.getStatePrice() > state.getStatePrice())
			return 1;
		else if (this.getStatePrice() < state.getStatePrice())
			return -1;		
		return 0;
	}
	
}
