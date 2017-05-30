package model.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import model.domains.CommonSearchDomain;
/**
 * AstarSearch class is a CommonHeuristicSearcher <br>
 * this searcher can be used to solve search problems, and it uses heuristics to do it
 */
public class AstarSearch extends CommonHeuristicSearcher {
	
	@Override
	public ArrayList<Action> search(CommonSearchDomain domain) {
		setNumOfNodes(0);
		// set start and destination
		State start = domain.getStartState();
		State destination = domain.getDestinationState();
		
		// states that we need to evaluate
		PriorityQueue<State> openList = new PriorityQueue<State>();
		// states that have been evaluated
		HashSet<State> closedList = new HashSet<State>();
		// links every state to his G()
		HashMap<State, Double> gMap = new HashMap<State, Double>();
		
		gMap.put(start, new Double(0.0));   
		start.setStatePrice(h.getEvaluation(start, destination));
		openList.add(start);
		setNumOfNodes(getNumOfNodes()+1);
		
		// while we have states to evaluate
		while (!stop && !openList.isEmpty()){
			State currentState = openList.poll();
			if (currentState.equals(destination))
				return reconstructPath(currentState);
			
			closedList.add(currentState);
			// map all possible moves from current state
			HashMap<Action, State> neighbors = domain.getAllPossibleMoves(currentState);
			for (Action action: neighbors.keySet()){
				State nextState = neighbors.get(action);
				if (closedList.contains(nextState))
					continue;
				double tmpGScore = gMap.get(currentState) + action.getPriceAction(); 
				if (!openList.contains(nextState) || tmpGScore < gMap.get(nextState)){
					nextState.setPreviousState(currentState);
					nextState.setPreviousAction(action);
					gMap.put(nextState, tmpGScore);
					nextState.setStatePrice(gMap.get(nextState) + h.getEvaluation(nextState,destination));
					if (!openList.contains(nextState)){
						openList.add(nextState);
						setNumOfNodes(getNumOfNodes()+1);
					}
						
				}
			}
		}		
		// return an empty list if no path was found or if start equals destination
		return new ArrayList<Action>();
		
	}
	
	/**
	 * calculate the path to the destination and returns the actions
	 * @param state (State)
	 * @return ArrayList<Action>
	 */
	public ArrayList<Action> reconstructPath(State state){
		ArrayList<Action> actionList = new ArrayList<Action>();
		
		while (state.getPreviousAction() != null){
			actionList.add(state.getPreviousAction());
			state = state.getPreviousState();
		}
		Collections.reverse(actionList);
		return actionList;
	}
	
	@Override
	public String toString() {
		return "ASTAR";
	}

	@Override
	public void setNumOfNodes(int numOfNodes) {
		super.setNumOfNodes(numOfNodes);		
	}


}
