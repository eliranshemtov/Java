package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import view.maze.ChooseDifficultyMazeWindow;
import view.slidePuzzle.SlidePuzzleWindow;


/**
 * WelcomeWindow Class extends {@link BasicWindow} - <br>
 * is the GUI window that welcomes the user.
 */
public class WelcomeWindow extends BasicWindow {
	
	/**
	 * C'tor - initialize super's c'tor
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 */
	public WelcomeWindow(int width, int height, String title) {
		super(width, height, title);
	}

	/**
	 * exitWindow() - disposes the shell.
	 */
	private void exitWindow(){
		shell.dispose();
	}
	
	@Override
	void initWidgets() {
			
		MenuBar menu = new MenuBar(display, shell);
		final Label mainHeadline = new Label(shell, SWT.NONE);
		mainHeadline.setImage(new Image(display, "resources/mainLable.gif"));
		mainHeadline.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
	
		Button mazeButton = new Button (shell, SWT.PUSH);
		mazeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true, 1, 1));
		mazeButton.setImage(new Image(display, "resources/mazeButton1.gif"));
		
		Button puzzleButton = new Button (shell, SWT.PUSH);
		puzzleButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true, 1, 1));
		puzzleButton.setImage(new Image(display, "resources/eightPuzzleButton.gif"));
		
		puzzleButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.setVisible(false);
				
				GameView puzzleWindow = new SlidePuzzleWindow(display,470, 360, "Slide Puzzle",3);
				WelcomeWindow.this.setChanged();
				WelcomeWindow.this.notifyObservers(puzzleWindow);
				puzzleWindow.run();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// Does nothing				
			}
		});
		
		
		mazeButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.setVisible(false);
				
				ChooseDifficultyMazeWindow difficultyWindow = new ChooseDifficultyMazeWindow(display, 693, 375, "Choose Difficulty");
				
				//GameView MazeWindow = new MazeWindow(display,800, 610, "Maze");
				WelcomeWindow.this.setChanged();
				WelcomeWindow.this.notifyObservers(difficultyWindow);
				difficultyWindow.run();
				//WelcomeWindow.this.notifyObservers(MazeWindow);
				//MazeWindow.run();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});	
		menu.exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				exitWindow();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				exitWindow();				
			}
		});
	}
	
	
	
}
