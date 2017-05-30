package view.maze;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import view.GameView;

/**
 * MazeWindow Class extends GameView<br>
 * It contains a MazeBoard  (sort of a canvas).
 */
public class MazeWindow extends GameView {

	MazeBoard board;
	final private Image speakerOnIcon;
	final private Image mutedIcon;
	boolean musicPlayed;
	int mazeWidth, mazeHeight;
	
	/**
	 * MazeWindow C'tor - operates super's C'tor and initialize datamembers according to the given parameters.
	 * @param display (Display)
	 * @param windowWidth (int)
	 * @param windowHeight (int)
	 * @param title (String)
	 * @param mazeWidth (int)
	 * @param mazeHeight (int)
	 */
	public MazeWindow(Display display, int windowWidth, int windowHeight, String title,int mazeWidth, int mazeHeight) {
		super(display, windowWidth, windowHeight, title);
		
		this.speakerOnIcon = new Image(shell.getDisplay(), "resources/speakerIcon.gif");
		this.mutedIcon = new Image(shell.getDisplay(), "resources/muteIcon.gif");
		this.musicPlayed = true;
		
		this.mazeWidth = mazeHeight;
		this.mazeHeight = mazeHeight;
	}
		
	
	@Override
	public void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		
		this.board = new MazeBoard(shell,SWT.BORDER, mazeHeight, mazeHeight);
		this.board.setBackground(new Color(null, 250, 250, 250));
		this.board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,6));
		this.board.startGame();
		
		Button muteSound = new Button(shell, SWT.TOGGLE);
		muteSound.setSize(new Point(30, 30));
		muteSound.setImage(speakerOnIcon);
		
		Button restartGame = new Button(shell, SWT.PUSH);
		restartGame.setText("Restart Game");
		restartGame.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		Label chooseAlgo = new Label(shell, SWT.BOLD);
		chooseAlgo.setText("Choose Algorithm");
		
		Combo algorithm = new Combo(shell, SWT.READ_ONLY );
		algorithm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		String[] items = {"bfs" , "astar"};
		algorithm.setItems(items);
		algorithm.select(0);
		
		Button getSolution = new Button(shell, SWT.PUSH);
		getSolution.setText("Get Solution!");
		getSolution.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		this.list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		this.list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		getSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				userAction = "selectdomain mazedomain"+":"+board.toString();
				MazeWindow.this.setChanged();
				MazeWindow.this.notifyObservers();
		
				userAction = "selectalgorithm "+algorithm.getText();
				MazeWindow.this.setChanged();
				MazeWindow.this.notifyObservers();
				
				if (algorithm.getText().equals("astar")){
					userAction = "selectheuristic mazeheuristic";
					MazeWindow.this.setChanged();
					MazeWindow.this.notifyObservers();
				}
				
				userAction = "solvedomainanddisplay";
				MazeWindow.this.setChanged();
				MazeWindow.this.notifyObservers();
				
				board.forceFocus();
				showSolutionPath();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to put here
			}
		});
		
		muteSound.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (musicPlayed){
					muteSound.setImage(mutedIcon);
					board.getMarioMusic().stopMusic();
					board.setMuted(true);
				}else {
					muteSound.setImage(speakerOnIcon);
					board.setMuted(false);
					board.restartBackgroudMusic();
				}
				musicPlayed = !musicPlayed;
				board.forceFocus();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
		
		restartGame.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				board.generateFrameAndSuperMaze(mazeHeight,mazeWidth);
				board.redraw();
				board.forceFocus();
				list.removeAll();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
	}
	
	/**
	 * exitGameWindow stops the sounds and calls the super's exitGameWindow function.
	 */
	protected void exitGameWindow(){
		this.board.getMarioMusic().stopMusic();
		this.board.timer.cancel();
		this.board.loopMusicTask.cancel();
		super.exitGameWindow();
	}
	
	/**
	 * this function creates a matrix (int[][]) which is initialized to 0 and puts '1' in the right path to solution.
	 */
	private void showSolutionPath(){
	
		for (int i=0; i < board.coinMatrix[0].length; i++)
			for (int j=0; j < board.coinMatrix.length; j++)
				board.coinMatrix[i][j] = 0;
		
		String action = list.getItem(0);
		int counter = 0;
		Point coinPosition = new Point(board.current.x, board.current.y);
		while (action != null){
			
			if (coinPosition.y != board.coinMatrix[0].length-1){
			
				action = list.getItem(counter);
				counter ++;
				board.redraw();
				
				switch (action) {
				case "move up":
					board.coinMatrix[coinPosition.x-1][coinPosition.y] = 1;
					coinPosition.x--;
					break;
	
				case "move down":
					board.coinMatrix[coinPosition.x+1][coinPosition.y] = 1;
					coinPosition.x++;
					break;
					
				case "move left":
					board.coinMatrix[coinPosition.x][coinPosition.y-1] = 1;
					coinPosition.y--;
					break;
					
				case "move right":
					board.coinMatrix[coinPosition.x][coinPosition.y+1] = 1;
					coinPosition.y++;
					break;
				}
			
			} else 
				action = null;
		}
		
	}
}
