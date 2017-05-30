package view.maze;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.ChooseDifficultyWindow;
import view.GameView;

/**
 * ChooseDifficultyMazeWindow Class extends ChooseDifficultyWindow
 */
public class ChooseDifficultyMazeWindow extends ChooseDifficultyWindow {
	
	GameView mazeWindow;

	/**
	 * ChooseDifficultyMazeWindow C'tor - operates super's C'tor.
	 * @param display
	 * @param width
	 * @param height
	 * @param title
	 */
	public ChooseDifficultyMazeWindow(Display display, int width, int height, String title) {
		super(display, width, height, title);
		mazeWindow = null;
	}
	
	@Override
	public void easyButtonSelected() {
		
		mazeWindow = new MazeWindow(shell.getDisplay(), 800, 610, "Mario Maze",10,10);
		ChooseDifficultyMazeWindow.this.setChanged();
		ChooseDifficultyMazeWindow.this.notifyObservers(mazeWindow);
		mazeWindow.run();
		shell.dispose();
	}

	@Override
	public void mediumButtonSelected() {
		
		mazeWindow = new MazeWindow(shell.getDisplay(), 800, 610, "Mario Maze",17,17);
		ChooseDifficultyMazeWindow.this.setChanged();
		ChooseDifficultyMazeWindow.this.notifyObservers(mazeWindow);
		mazeWindow.run();
		shell.dispose();
		
	}

	@Override
	public void hardButtonSelected() {
		
		mazeWindow = new MazeWindow(shell.getDisplay(), 800, 610, "Mario Maze",22,22);
		ChooseDifficultyMazeWindow.this.setChanged();
		ChooseDifficultyMazeWindow.this.notifyObservers(mazeWindow);
		mazeWindow.run();
		shell.dispose();
		
	}

	@Override
	public void run() {
		initWidgets();
		shell.open();
		
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				if (mazeWindow == null){
					Shell mainShell = shell.getDisplay().getShells()[0];
					shell.dispose();
					mainShell.setVisible(true);
					mainShell.setFocus();	
				}
			}
		});
	}

}
