package view.maze;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import sound.AePlayWave;
import view.GameCharacter;
import view.Point;

/**
 * MazeBoard Class extends Canvas
 * represent a maze board with all it's parameters. <br>
 * starting and ending point, the characters and sound theme.
 */
public class MazeBoard extends Canvas {

	int[][] matrix, coinMatrix;
	Point start,end, current;
	GameCharacter mario, mushroom;
	boolean finished, walking, muted;
	AePlayWave marioMusic;
	Image brickWall, coin;
	Timer timer;
	TimerTask loopMusicTask;

	final String STATIC_MARIO = "resources/staticMario.gif";
	final String WALKING_MARIO = "resources/walkingMario.gif";
	final String MARIO_WIN = "resources/marioWin.jpg";
	final String MARIO_MUSIC = "resources/Super_Mario.wav";
	final String MARIO_GAME_OVER_MUSIC = "resources/mario_gameover.wav";
	final String BRICK_WALL_IMAGE = "resources/brickWall.gif";
	final String COIN_IMAGE = "resources/marioCoin.gif";
	final String MUSHROOM = "resources/mashroom.gif";
	
	
	/**
	 * MazeBoard C'tor activates super's C'tor and sets its data members. <br>
	 * generates the maze and its frame.
	 * @param parent (Composite)
	 * @param style (int)
	 * @param width (int)
	 * @param height (int)
	 */
	public MazeBoard(Composite parent, int style, int width, int height) {
		super(parent, style);
		
		start = new Point(2, 0);
		end = new Point(height - 2,width - 1);
		brickWall = new Image(getDisplay(), BRICK_WALL_IMAGE);	
		coin = new Image(getDisplay(), COIN_IMAGE);
		timer = new Timer();
		generateFrameAndSuperMaze(height, width);
	}
	
	/**
	 * getter for this.marioMusic
	 * @return AePlayWave object
	 */
	public AePlayWave getMarioMusic() {
		return marioMusic;
	}
	
	/**
	 * set this.muted boolean
	 * @param muted
	 */
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	
	/**
	 * getter for this.muted boolean
	 * @return this.muted (boolean)
	 */
	public boolean isMuted(){
		return this.muted;
	}
	
	/**
	 * StartGame method will start the theme sounds and paint the maze board
	 */
	public void startGame(){
		
		marioMusic = new AePlayWave(MARIO_MUSIC);
		marioMusic.start();
		this.muted = false;
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				
				e.gc.setForeground(new Color(null, 0, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

				int width = getSize().x;
				int height = getSize().y;
				
				int w = width / matrix[0].length;
				int h = height / matrix.length;

				// draw the walls
				for (int i = 0; i < matrix.length; i++)
					for (int j = 0; j < matrix[i].length; j++) {
						if (matrix[i][j] != 0)
							e.gc.drawImage(brickWall,0,0,60,60,j * w, i * h, w, h);
						if (coinMatrix[i][j] != 0 && (i != current.x || j != current.y))
							e.gc.drawImage(coin,0,0,500,330,j * w , i * h , w , h);
					}
				
				String filePath;
				if (walking)
					filePath = STATIC_MARIO;
				else filePath = WALKING_MARIO;
				walking = !walking;
				
				if (finished)
					filePath = MARIO_WIN;
				
				mario = new GameCharacter(current.y*w,current.x*h, getDisplay(),filePath);
				mario.paint(e, w, h);
				
				if (!finished){
					mushroom = new GameCharacter(end.y*w,end.x*h, getDisplay(),MUSHROOM);
					mushroom.paint(e, w, h);
				}
			}
		});
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (!finished){
					switch (e.keyCode){
					case SWT.ARROW_UP:
						if (matrix[current.x-1][current.y] != 1){
							if (coinMatrix[current.x-1][current.y] == 1)
								coinMatrix[current.x-1][current.y] = 0;
							current.x --;
							redraw();
						} break;
					case SWT.ARROW_DOWN:
						if (matrix[current.x+1][current.y] != 1){
							if (coinMatrix[current.x+1][current.y] == 1)
								coinMatrix[current.x+1][current.y] = 0;
							current.x ++;
							redraw();
						} break;
					case SWT.ARROW_LEFT:
						if (current.y > 0 && matrix[current.x][current.y-1] != 1){
							if (coinMatrix[current.x][current.y-1] == 1)
								coinMatrix[current.x][current.y-1] = 0;
							current.y --;
							redraw();
						} break;
					case SWT.ARROW_RIGHT:
						if (current.y < matrix[0].length-1 &&matrix[current.x][current.y+1] != 1){
							if (coinMatrix[current.x][current.y+1] == 1)
								coinMatrix[current.x][current.y+1] = 0;
							current.y ++;
							redraw();
						} break;
					}
					
					if (!finished)
						checkFinished();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// nothing to do here
			}
		});
	}
	
	/**
	 * checkFinished function checks it the character is in the final state. if so, set the boolean data member accordingly, and switch sound theme.
	 */
	private void checkFinished(){
		if (current.y == matrix[0].length-1){
			this.finished = true;
			marioMusic.stopMusic();	
			if (!muted){
				marioMusic = new AePlayWave(MARIO_GAME_OVER_MUSIC);
				marioMusic.start();
			}
		}
	}
	
	/**
	 *  On-call, Restarts the background music.
	 */
	public void restartBackgroudMusic(){
		if (marioMusic != null && !muted && !finished){
			marioMusic.stopMusic();
			marioMusic = new AePlayWave(MARIO_MUSIC);
			marioMusic.start();
		}
	}
	
	/**
	 * Creates the maze
	 * @param height (int)
	 * @param width (int)
	 */
	public void generateFrameAndSuperMaze(int height, int width) {
		
		walking = false;
		finished = false;

		current = new Point(2,0);
		this.matrix = new int[height][width];
		this.coinMatrix = new int[height][width];
		
		loopMusicTask = new TimerTask() {
			
			@Override
			public void run() {
				restartBackgroudMusic();
			}
		};
		timer.scheduleAtFixedRate(loopMusicTask, 0, 22300);
		
		for (int i = 0; i < width; i++) {
			this.matrix[0][i] = 1;
			this.matrix[height - 1][i] = 1;
		}
		for (int i = 0; i < height; i++) {
			this.matrix[i][0] = 1;
			this.matrix[i][width - 1] = 1;
		}
		
		this.matrix[start.x][start.y] = 0;
		this.matrix[end.x][end.y] = 0;
		
		generateSuperMaze(height-1, width-1);
	}
	
	/**
	 * generates the inside of the maze
	 * @param height (int)
	 * @param width (int)
	 */
	public void generateSuperMaze(int height, int width){
		int triesLimit = 0;
		
		while (triesLimit < 500) {
			int x = ((int) (Math.random() * (height)));
			int y = ((int) (Math.random() * (width)));
			
			if (north(x,y) || west(x,y) || south(x,y) || east(x,y)){
				matrix[x][y] = 1;
				triesLimit = 0;
			}
			else triesLimit++;
		}
	}
	
	private boolean north(int x, int y){
		if (matrix[x][y] == 0 && matrix[x-1][y] == 0 && matrix[x][y-1] == 0 
				&& matrix[x][y+1] == 0 && matrix[x+1][y] == 1 && matrix[x-1][y+1] == 0 && matrix[x-1][y-1] == 0)
			return true;
		return false;
	}
	
	private boolean west(int x, int y){
		if (matrix[x][y] == 0 && matrix[x-1][y] == 0 && matrix[x+1][y] == 0
				&& matrix[x][y-1] == 0 && matrix[x][y+1] == 1 && matrix[x-1][y-1] == 0 && matrix[x+1][y-1] == 0)
			return true;
		return false;
	}
	
	private boolean south(int x, int y){
		if (matrix[x][y] == 0 && matrix[x+1][y] == 0 && matrix[x][y-1] == 0
				&& matrix[x][y+1] == 0 && matrix[x-1][y] == 1 && matrix[x+1][y+1] == 0 && matrix[x+1][y-1] == 0)
			return true;
		return false;
	}
	
	private boolean east(int x, int y){
		if (matrix[x][y] == 0 && matrix[x-1][y] == 0 && matrix[x+1][y] == 0
				&& matrix[x][y+1] == 0 && matrix[x][y-1] == 1 && matrix[x-1][y+1] == 0 && matrix[x+1][y+1] == 0)
			return true;
		return false;
	}
	
	
	/**
	 * returns a String representing the current maze state (including the character position)
	 */
	public String toString(){
		StringBuilder str = new StringBuilder();
		// concatenate every members
		str.append(matrix.length).append(",");
		str.append(matrix[0].length).append(",");
		str.append(current.x).append(",").append(current.y).append(",");
		str.append(end.x).append(",").append(end.y).append(",");
		// concatenate every row to string
		for (int i=0; i<matrix.length; i++)
			for (int j=0; j<matrix[0].length; j++)
				str.append(this.matrix[i][j]);
		return str.toString();
	}
}
