package view.slidePuzzle;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import view.Point;
import view.Tile;

/**
 * SlidePuzzleBoard class - extends Canvas 
 * defines the bigger canvas that is made out of tiles (also canvas)
 */
public class SlidePuzzleBoard extends Canvas {

	private int dimension,width,height,movesCounter;
	private Tile[][] tiles;
	private Point zero;
	private boolean finished;

	/**
	 * SlidePuzzleBoard C'tor sets the slidePuzzleBoard's layout according to parent (Composite) and properties parameters.
	 * @param parent (Composite)
	 * @param style (int)
	 * @param dimension (int)
	 * @param width (int)
	 * @param height (int)
	 */
	public SlidePuzzleBoard(Composite parent, int style, int dimension, int width, int height) {
		super(parent, style);
		
		setLayout(new GridLayout(dimension, true));
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		this.finished = false;
		this.dimension = dimension;

		this.width = width;
		this.height = height;
	}
	
	/**
	 * generate a matrix with randomed numbers, which will compose a solvable state.
	 * initialize the tile matrix according to the generated matrix.
	 */
	public void startGame(){
		
		tiles = new Tile[dimension][dimension];
		int[][] matrix = generatePuzzle();
		this.movesCounter = 0;
	
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++){
				tiles[i][j] = new Tile(this, SWT.BORDER, matrix[i][j], new Color(null,255,255,255));
				tiles[i][j].setBounds(j*(width/dimension),i*(height/dimension),(width/dimension),(height/dimension));
				if (matrix[i][j] == 0){
					tiles[i][j].setVisible(false);
					zero = new Point(i, j);
				}
			}	
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (!finished){
					switch (e.keyCode){
					case SWT.ARROW_UP:
						if (zero.x < dimension-1){
							switchTileWithZero(zero.x+1,zero.y);
						} break;
					case SWT.ARROW_DOWN:
						if (zero.x > 0){
							switchTileWithZero(zero.x-1,zero.y);
						} break;
					case SWT.ARROW_LEFT:
						if (zero.y < dimension - 1){
							switchTileWithZero(zero.x,zero.y+1);
						} break;
					case SWT.ARROW_RIGHT:
						if (zero.y > 0){
							switchTileWithZero(zero.x,zero.y-1);
						} break;
					}
				}
				
				if (!finished)
					checkFinished();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// nothing to do here
			}
		});
		
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++)
				tiles[i][j].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseUp(MouseEvent arg0) {
						
						if (finished)
							return;
						
						for (int i=0; i < dimension; i++)
							for (int j=0; j < dimension; j++)
								if (tiles[i][j].isClicked()){
									tiles[i][j].setClicked(false);
									if (distanceOneFromZero(i,j)){
										switchTileWithZero(i, j);
										checkFinished();
									}
									return;
								}
					}
					@Override
					public void mouseDown(MouseEvent e) {
						// nothing to do here
					}
					@Override
					public void mouseDoubleClick(MouseEvent arg0) {
						// nothing to do here
					}
				});
	}
	
	/**
	 * generatePuzzle takes the final state, and "shuffles" the matrix to a solvable starting state
	 * @return int[][] (a solvable matrix)
	 */
	private int[][] generatePuzzle(){
		int[][] matrix = new int[dimension][dimension]; 
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++)
				matrix[i][j] = i*dimension + j + 1;
		matrix[dimension-1][dimension-1] = 0;
		
		int zeroXPosition = dimension-1;
		int zeroYPosition = dimension-1;
		
		// shuffle the numbers
		for (int i=0; i < 85; i++){
			ArrayList<String> possibleMoves = allLegalMoves(zeroXPosition, zeroYPosition);
			int random = (int)(Math.random()*possibleMoves.size());
			switch (possibleMoves.get(random)){
			case "up": 
					switchWithZero(new Point(zeroXPosition-1,zeroYPosition), new Point(zeroXPosition,zeroYPosition), matrix);
					zeroXPosition--;
				break;
			case "down": 
					switchWithZero(new Point(zeroXPosition+1,zeroYPosition), new Point(zeroXPosition,zeroYPosition), matrix);
					zeroXPosition++;
				break;
			case "left":
					switchWithZero(new Point(zeroXPosition,zeroYPosition-1), new Point(zeroXPosition,zeroYPosition), matrix);
					zeroYPosition--;
				break;
			case "right":
					switchWithZero(new Point(zeroXPosition,zeroYPosition+1), new Point(zeroXPosition,zeroYPosition), matrix);
					zeroYPosition++;
				break;
			}
		}
		return matrix;
		
	}
	
	/**
	 *  switch between two Points in the matrix
	 * @param place (Point)
	 * @param zero (Point)
	 * @param matrix (int[][])
	 */
	private void switchWithZero(Point place, Point zero, int[][] matrix){
		matrix [zero.x][zero.y] = matrix [place.x][place.y];
		matrix [place.x][place.y] = 0;
	}

	/**
	 *  gets the Zero position and puts in the String array all the legal moves
	 * @param zeroX (int)
	 * @param zeroY (int)
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
	 *  get tile's coordinates and switch it with the zero tile
	 * @param tileX  (int)
	 * @param tileY (int)
	 */
	private void switchTileWithZero(int tileX,int tileY){
		movesCounter++;
		
		tiles[zero.x][zero.y].setValue(tiles[tileX][tileY].getValue());
		tiles[tileX][tileY].setValue(0);
		
		tiles[zero.x][zero.y].setVisible(true);
		tiles[tileX][tileY].setVisible(false);
		
		tiles[tileX][tileY].redraw();
		tiles[zero.x][zero.y].redraw();
		
		zero.x = tileX;
		zero.y = tileY;
	}
	
	/**
	 * distanceOneFromZero gets a coordinates of a tile and tells if its one step away from switching with zero
	 * @param tileX (int)
	 * @param tileY (int)
	 * @return boolean
	 */
	private boolean distanceOneFromZero(int tileX, int tileY){
		if (1 == Math.abs(zero.x-tileX) + Math.abs(zero.y-tileY))
			return true;
		return false;
	}
	
	/**
	 * checkFinished - Check if the current state is the final state
	 * <br> if its the final state set the boolean finished (data member) to true.
	 */
	private void checkFinished(){
		this.finished = true;
		for (int i = 0; i<dimension; i++)
			for (int j = 0; j<dimension; j++)
				if (tiles[i][j].getValue() !=0)
					if (tiles[i][j].getValue() != i*dimension+j+1)
						this.finished = false;
		if (this.finished)
			tiles[dimension-1][dimension-1].setVisible(true);
	}

	/**
	 * movesCounter getter
	 * @return movesCounter (int)
	 */
	public int getMovesCounter() {
		return movesCounter;
	}

	/**
	 * concatenate the matrix into string and separate every number with a comma
	 * @return a String representing the current state
	 */
	public String StringTiles(){
		StringBuilder str = new StringBuilder();
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++)
				str.append(tiles[i][j].getValue()).append(",");
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}
	
	/**
	 * getter for finished (tells if the board is in the final state)
	 * @return this.finished (boolean)
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * shuffleBoard - Re-shuffle the slidePuzzleBoard
	 */
	public void shuffleBoard(){
		int[][] matrix = this.generatePuzzle();
		for (int i=0; i < dimension; i++)
			for (int j=0; j < dimension; j++){
				tiles[i][j].setValue(matrix[i][j]);
				tiles[i][j].redraw();
				if (matrix[i][j] == 0){
					tiles[i][j].setVisible(false);
					zero.x = i;
					zero.y = j;
				} else tiles[i][j].setVisible(true);
			}
		
		this.finished = false;
	}
}
