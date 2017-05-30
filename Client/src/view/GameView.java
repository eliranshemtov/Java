package view;

import java.util.ArrayList;
import java.util.Observable;

import model.Solution;
import model.algorithms.Action;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 *GameView abstract class extends observable and implements {@link View} and {@link Runnable}
 */
public abstract class GameView extends Observable implements Runnable, View {
	protected Shell shell;
	protected List list;
	protected String userAction;
	protected GameMenuBar menu;
	protected ArrayList<Integer> numof;
	protected swtChart swtchart;
	
	/**
	 * GameView C'tor - Sets the shell properties according to the parameters given.
	 * @param display (Display)
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 */
	public GameView(Display display, int width, int height, String title) {
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		
		Image icon = new Image(display,"resources/icon.gif");
		shell.setImage(icon);
		menu = new GameMenuBar(display, shell);
		numof = new ArrayList<Integer>();
	}

	/**
	 * abstract initWidgets forces this class successors to implement this function specifically for each one
	 */
	public abstract void initWidgets();
	
	
	/**
	 * exitGameWindow function disposes the current shell and focusing back on the last window
	 */
	protected void exitGameWindow(){
		Shell mainShell = shell.getDisplay().getShells()[0];
		shell.dispose();
		mainShell.setVisible(true);
		mainShell.setFocus();
	}
	
	/**
	 * Calls the specified class initWidgets function, opens the shell, and initialize the common menu.
	 */
	public void run(){
		
		initWidgets();
		shell.open();
		
		/**
		 * on dispose button click, exitGameWindow will occur
		 */
		shell.addDisposeListener(new DisposeListener() {			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				exitGameWindow();
			}
		});
		
		menu.viewChart.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				swtchart = new swtChart(shell.getDisplay(), 600, 400, "SWTCHART TEST", numof);
				Thread t = new Thread(swtchart);
				//swtchart.run();
				t.run();
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		menu.exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				exitGameWindow();				
			}			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//nothing to do here
			}
		});
	}
	
	
	
	@Override
	public void doTask() {
		//nothing to do here
	}

	@Override
	public void start() {
		//nothing to do here
	}

	@Override
	public void displaySolution(Solution solution) {
		
		this.list.removeAll();
		for (Action a: solution.getActions())
			list.add(a.toString());
		list.redraw();
		numof.add((Integer)solution.getNumOfNodes());
		if (swtchart!=null)
			swtchart.displaySolution(solution);
	}

	@Override
	public String getUserAction() {
		return this.userAction;
	}

	@Override
	public void printErr(String str) {
		//nothing to do here
	}
	

}
