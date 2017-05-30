package model.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.domains.CommonSearchDomain;
import model.domains.SlidePuzzleDomain;
import model.domains.SlidePuzzleHeuristic;

import org.junit.Test;

/**
 * Junit astar test - this class designated to assert that the algorithm works 
 */
public class AstarSearchTest {

	@Test
	public void testSearch() {
		// domain
		CommonSearchDomain slidePuzzleDomain = new SlidePuzzleDomain("3-1,2,3,4,5,6,0,7,8");

		// Algorithm
		Searcher s = new AstarSearch();
		
		//Heuristic
		((CommonHeuristicSearcher)s).setHeuristic(new SlidePuzzleHeuristic());
		
		ArrayList<Action> actions = new ArrayList<Action>();
		actions = s.search(slidePuzzleDomain);
		
		// expected moves: "move 7" "move 8"
		assertEquals(2, actions.size());
		assertTrue("move 7".equals(actions.get(0).toString()));
		assertTrue("move 8".equals(actions.get(1).toString()));
	}

}
