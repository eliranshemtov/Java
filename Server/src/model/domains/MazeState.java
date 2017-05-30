package model.domains;

import model.algorithms.State;

public class MazeState extends State {
	
	/**
	 * c'tor gets String representing the state and initialize the this.stateDescription by the given one
	 * @param description
	 */
	public MazeState(String description) {
		super(description);
	}

}
