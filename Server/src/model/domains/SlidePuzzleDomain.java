package model.domains;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithms.Action;
import model.algorithms.State;

/**
 * SlidePuzzleDomain class describes a slide puzzle with all of it's parameter. <br>
 * the class contains the start state of the player and the destination state,
 * and the dimension of the slide puzzle
 */
public class SlidePuzzleDomain implements CommonSearchDomain {
	
	private int dimension;
	private SlidePuzzleState start, goal;

	/**
	 * C'tor gets Slide Puzzle String arguments and initialize it's data members with the string
	 * @param arguments
	 */
	public SlidePuzzleDomain(String arguments) {
		
		String[] splits = arguments.split("-");
		this.dimension = Integer.parseInt(splits[0]);
		this.start = new SlidePuzzleState(splits[1]);
		
		StringBuilder str = new StringBuilder();
		for (int i=0; i < this.dimension; i++)
			for (int j=0; j < this.dimension; j++)
				if ((i != this.dimension-1) || (j != this.dimension-1))
					str.append((i*this.dimension)+j+1).append(",");
		
		str.append("0");
		this.goal = new SlidePuzzleState(str.toString());
	}

	@Override
	public State getStartState() {
		return this.start;
	}

	@Override
	public State getDestinationState() {
		return this.goal;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {

		HashMap<Action, State> moves = new HashMap<Action, State>();
		int[][] matrix = new int[dimension][dimension];
		
		this.stringToMatrix(current.getState(), matrix);
		Point zeroPoint = new Point(findZero(matrix));
		ArrayList<String> possibleMoves = this.allLegalMoves(zeroPoint.x, zeroPoint.y);
		
		if (possibleMoves.contains("up")){
			this.switchWithZero(new Point(zeroPoint.x - 1,zeroPoint.y), zeroPoint, matrix);
			Action action = new Action("move "+matrix[zeroPoint.x][zeroPoint.y]);
			action.setPriceAction(1);
			SlidePuzzleState state = new SlidePuzzleState(concatenateMatrix(matrix));
			moves.put(action,state);
			this.switchWithZero(zeroPoint, new Point(zeroPoint.x - 1,zeroPoint.y), matrix); // switch back
		}
		if (possibleMoves.contains("down")){
			this.switchWithZero(new Point(zeroPoint.x + 1,zeroPoint.y), zeroPoint, matrix);
			Action action = new Action("move "+matrix[zeroPoint.x][zeroPoint.y]);
			action.setPriceAction(1);
			SlidePuzzleState state = new SlidePuzzleState(concatenateMatrix(matrix));
			moves.put(action,state);
			this.switchWithZero(zeroPoint, new Point(zeroPoint.x + 1,zeroPoint.y), matrix); // switch back
		}
		if (possibleMoves.contains("left")){
			this.switchWithZero(new Point(zeroPoint.x,zeroPoint.y - 1), zeroPoint, matrix);
			Action action = new Action("move "+matrix[zeroPoint.x][zeroPoint.y]);
			action.setPriceAction(1);
			SlidePuzzleState state = new SlidePuzzleState(concatenateMatrix(matrix));
			moves.put(action,state);
			this.switchWithZero(zeroPoint, new Point(zeroPoint.x,zeroPoint.y - 1), matrix); // switch back
		}
		if (possibleMoves.contains("right")){
			this.switchWithZero(new Point(zeroPoint.x,zeroPoint.y + 1), zeroPoint, matrix);
			Action action = new Action("move "+matrix[zeroPoint.x][zeroPoint.y]);
			action.setPriceAction(1);
			SlidePuzzleState state = new SlidePuzzleState(concatenateMatrix(matrix));
			moves.put(action,state);
			this.switchWithZero(zeroPoint, new Point(zeroPoint.x,zeroPoint.y + 1), matrix); // switch back
		}
		
		return moves;
	}

	@Override
	public ArrayList<String> getStringsToDisplay() {
		String[] split = start.getState().split(",");
		ArrayList<String> strArray = new ArrayList<String>();
		for (int i=0; i < dimension; i++){
			StringBuilder str = new StringBuilder();
			for (int j=0; j < dimension; j++)
				str.append(split[i*dimension + j]);
			strArray.add(str.toString());
		}
		
		return strArray;
		
	}

	@Override
	public String getConcatenatedString() {
		return this.start.getState();
	}
	
	/**
	 * concatenate the matrix into string and separate every number with a comma
	 * @param matrix (two-dimension int array)
	 * @return String - the concatenate matrix
	 */
	private String concatenateMatrix(int[][] matrix){
		StringBuilder str = new StringBuilder();
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++)
				str.append(matrix[i][j]).append(",");
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}
	
	/**
	 * gets the Zero position and puts in the String array all the legal moves
	 * @param zeroX - int
	 * @param zeroY - int
	 * @return ArrayList<String> 
	 */
	private ArrayList<String> allLegalMoves(int zeroX, int zeroY){
		ArrayList<String> moves = new ArrayList<String>();
		if (zeroX-1 >= 0)
			moves.add("up");
		if (zeroX+1 < dimension)
			moves.add("down");
		if (zeroY-1 >= 0)
			moves.add("left");
		if (zeroY+1 < dimension)
			moves.add("right");
		
		return moves;
	}
	
	/**
	 * switch between two Points in the matrix
	 * @param place (Point) - Point to place
	 * @param zero (Point) - the zero's place
	 * @param matrix (int[][])  
	 */
	private void switchWithZero(Point place, Point zero, int[][] matrix){
		matrix [zero.x][zero.y] = matrix [place.x][place.y];
		matrix [place.x][place.y] = 0;
	}

	/**
	 * get a string and put it's values in the matrix
	 * @param str (String)
	 * @param matrix (int[][])
	 */
	private void stringToMatrix(String str,int[][] matrix){
		
		String[] tmp = str.split(",");
		int count = 0;
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++){
				matrix[i][j] = Integer.parseInt(tmp[count]);
				count++;
			}	
	}
	
	/**
	 * find and return the place of the zero in the matrix
	 * @param matrix (int[][])
	 * @return Point - the zero position
	 */
	private Point findZero(int[][] matrix){
		int i = 0,j = 0;
		for (i=0; i < dimension; i++)
			for (j=0; j < dimension; j++)
				if (matrix[i][j] == 0)
					return new Point(i,j);
		return null;
	}
}
