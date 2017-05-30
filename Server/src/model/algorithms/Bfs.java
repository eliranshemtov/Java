package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import model.domains.CommonSearchDomain;

/**
 * AstarSearch class is a CommonSearcher <br>
 * this searcher can be used to solve search problems
 */
public class Bfs extends CommonSearcher {

	@Override
	public ArrayList<Action> search(CommonSearchDomain domain) {	
		setNumOfNodes(0);
		// set start and destination
		State start = domain.getStartState();
		State destination = domain.getDestinationState();
		
		ArrayList<Action> actionsList = new ArrayList<Action>();
		
		if (start.equals(destination))
			return actionsList;
		
		// states that have been visited
		HashSet<State> visitedStates = new HashSet<State>();
		
		// states that we need to visit
		Queue<State> queue = new LinkedList<State>();
		queue.add(start);
		
		while (!stop && !queue.isEmpty()){
			State currentState = queue.remove();
			visitedStates.add(currentState);
			setNumOfNodes(getNumOfNodes()+1);
			// map all possible moves from current state
			HashMap<Action, State> neighbors = domain.getAllPossibleMoves(currentState);
			
			for (Action action: neighbors.keySet()){
				State nextState = neighbors.get(action);
				if (!visitedStates.contains(nextState)){
					nextState.setPreviousState(currentState);
					nextState.setPreviousAction(action);
					queue.add(nextState);
					if (nextState.equals(destination)){
						// reconstruct the path to the destination and returns the actions
						while (nextState.getPreviousState() != null){
							actionsList.add(nextState.getPreviousAction());
							nextState = nextState.getPreviousState();
						}
						Collections.reverse(actionsList);
						return actionsList;
					}
					visitedStates.add(nextState);
					setNumOfNodes(getNumOfNodes()+1);
				}
			}
		}
		
		return actionsList;
	}
	
	@Override
	public String toString() {
		return "BFS";
	}
	
	
	@Override
	public void setNumOfNodes(int numOfNodes) {
		super.setNumOfNodes(numOfNodes);		
	}

}
