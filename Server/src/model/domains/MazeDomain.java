package model.domains;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithms.Action;
import model.algorithms.State;

/**
 * MazeDomain class describes a maze with all of it's parameter. <br>
 * the class contains the start state of the player and the destination state,
 * the height and width of the maze, and the int[][] matrix describing where are the walls
 *
 */
public class MazeDomain implements CommonSearchDomain {

	MazeState start,destination;
	int height,width;
	int[][] matrix;
	
	/**
	 * C'tor gets maze String arguments and initialize it's data members with the string
	 * @param arguments
	 */
	public MazeDomain(String arguments) {
		String[] strArr = arguments.split(",");
		this.height = Integer.parseInt(strArr[0]);
		this.width = Integer.parseInt(strArr[1]);
		
		this.start = new MazeState(strArr[2]+","+strArr[3]);
		this.destination = new MazeState(strArr[4]+","+strArr[5]);
		
		this.matrix = new int[height][width];
		int count = 0;
		for (int i=0; i < height; i++)
			for (int j=0; j < width; j++){
				matrix[i][j] = Character.getNumericValue((strArr[6].charAt(count)));
				count++;
			}
	}
	
	@Override
	public State getStartState() {
		return this.start;
	}

	@Override
	public State getDestinationState() {
		return this.destination;
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) {
		
		HashMap<Action, State> moves = new HashMap<Action, State>();
		
		String[] coordinates = current.getState().split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		
		// check if possible to move down
		if ((x+1 < this.height) && (this.matrix[x+1][y] != 1))
			moves.put(new Action("move down"), new MazeState((x+1)+","+y));
		// check if possible to move up
		if ((x-1 >= 0) && (this.matrix[x-1][y] != 1))
			moves.put(new Action("move up"), new MazeState((x-1)+","+y));
		// check if possible to move right
		if ((y+1 < this.width) && (this.matrix[x][y+1] != 1))
			moves.put(new Action("move right"), new MazeState(x+","+(y+1)));
		// check if possible to move left
		if ((y-1 >= 0) && (this.matrix[x][y-1] != 1))
			moves.put(new Action("move left"), new MazeState(x+","+(y-1)));
			
		return moves;
	}

	public void printMatrix() {
		for (int i=0; i < this.height; i++){
			for (int j=0; j < this.width; j++)
				System.out.print(this.matrix[i][j]+ " ");
			System.out.println();
		}
	}

	@Override
	public ArrayList<String> getStringsToDisplay() {
		ArrayList<String> string = new ArrayList<String>();
		String tmp = new String();
		// concatenate every row to string
		for (int i=0; i<this.height; i++){
			for (int j=0; j<this.width; j++)
				tmp = tmp+this.matrix[i][j];
			string.add(tmp);
			tmp = "";
		}
		return string;
	}

	@Override
	public String getConcatenatedString() {
		StringBuilder str = new StringBuilder();
		// concatenate every members
		str.append(this.height);
		str.append(this.width);
		// concatenate every row to string
		str.append(this.start.getState());
		for (int i=0; i<this.height; i++)
			for (int j=0; j<this.width; j++)
				str.append(this.matrix[i][j]);
		return str.toString();
	}

	public int[][] getMatrix() {
		return matrix;
	}

}
