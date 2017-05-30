package model.domains;

import model.algorithms.Heuristic;
import model.algorithms.State;

/**
 * an Heuristic that can be used by different algorithm to find the slide puzzle actions to solution
 */
public class SlidePuzzleHeuristic implements Heuristic {

	@Override
	public double getEvaluation(State current, State destination) {
		double sum = 0;
		int dimension = getDimension(current.getState());
		int value;
		int[][] matrix = new int[dimension][dimension];
		stringToMatrix(current.getState(), matrix, dimension);
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++){
				if (matrix[i][j] == 0)
					continue;
				else value = matrix[i][j];
				
				int targetX = (value-1)/dimension;
				int targetY = (value-1)%dimension;
				sum+= Math.abs(i-targetX) + Math.abs(j-targetY);
			}
		
		return sum;
	}
	
	/**
	 * get String, int[][] matrix and the puzzle dimension
	 * concatenate the string into matrix
	 * @param str (String)
	 * @param matrix (int[][])
	 * @param dimension (int)
	 */
	private void stringToMatrix (String str,int[][] matrix, int dimension){
		
		String[] tmp = str.split(",");
		int count = 0;
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++){
				matrix[i][j] = Integer.parseInt(tmp[count]);
				count++;
			}	
	}
	
	/**
	 * Gets a string representing the puzzle and compute the dimension of the puzzle
	 * @param str - puzzle description (String)
	 * @return
	 */
	private int getDimension (String str){
		int count = 0;
		for (int i=0; i < str.length(); i++)
			if (str.charAt(i) == ',')
				count++;
		return (int)(Math.sqrt((double)(count+1)));
	}

}
