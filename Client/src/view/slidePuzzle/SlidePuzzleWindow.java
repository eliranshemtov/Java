package view.slidePuzzle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import view.GameView;

/**
 * SlidePuzzleWindow Class extends GameView
 */
public class SlidePuzzleWindow extends GameView {
	private int dimension;
	
	/**
	 * SlidePuzzleBoard C'tor -  activates super's c'tor and sets this.dimension to the int parameter. 
	 * @param display (Display)
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 * @param dimension (int)
	 */
	public SlidePuzzleWindow(Display display, int width, int height, String title, int dimension) {
		super(display, width, height, title);
		this.dimension = dimension;
	}
	
	@Override
	public void initWidgets() {
		
		shell.setLayout(new GridLayout(2, false));
		
		SlidePuzzleBoard board = new SlidePuzzleBoard(shell, SWT.BORDER, dimension, 300, 300);
		board.setBackground(new Color(null, 250, 250, 250));
		board.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		board.startGame();
		
		Button restartGame = new Button(shell, SWT.PUSH);
		restartGame.setText("Shuffle Board");
		restartGame.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		Label label = new Label(shell, SWT.BOLD);
		label.setText("Choose Algorithm");
		
		Combo algorithm = new Combo(shell, SWT.READ_ONLY );
		algorithm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		String[] items = {"bfs" , "astar"};
		algorithm.setItems(items);
		algorithm.select(0);
		
		Button getSolution = new Button(shell, SWT.PUSH);
		getSolution.setText("Get Solution!");
		getSolution.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		this.list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		
		
		getSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (board.isFinished())
					return;
				
				userAction = "selectdomain slidepuzzledomain"+":"+dimension+"-"+board.StringTiles();
				SlidePuzzleWindow.this.setChanged();
				SlidePuzzleWindow.this.notifyObservers();
		
				userAction = "selectalgorithm "+algorithm.getText();
				SlidePuzzleWindow.this.setChanged();
				SlidePuzzleWindow.this.notifyObservers();
				
				if (algorithm.getText().equals("astar")){
					userAction = "selectheuristic slidepuzzleheuristic";
					SlidePuzzleWindow.this.setChanged();
					SlidePuzzleWindow.this.notifyObservers();
				}
				
				userAction = "solvedomainanddisplay";
				SlidePuzzleWindow.this.setChanged();
				SlidePuzzleWindow.this.notifyObservers();
				
				board.forceFocus();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to put here
			}
		});
		
		restartGame.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				board.shuffleBoard();
				//board.redraw();
				board.forceFocus();
				list.removeAll();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
	}	
}
