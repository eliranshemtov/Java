package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * BasicWindow Class extends Observable and implements Runnable.<br>
 * the basic form of a window. contains the main event loop.
 *
 */
public abstract class BasicWindow extends Observable implements Runnable{

	protected Display display;
	protected Shell shell;
	
	/**
	 * C'tor - gets the size of the window and it's title.
	 * <br> it sets the new shell and display and set the shell properties with the given ones.<br>
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 */
	public BasicWindow(int width, int height,String title) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		
	}
	
	/**
	 * abstract function - initWidgets()<br>
	 * every successor of this class will have to implement this function in order to set it's widgets.
	 */
	abstract void initWidgets();
	
	@Override
	/**
	 * This run functions contains the main event loop. as long as the shell isn't disposed, read and dispatch for new events.
	 */
	public void run() {
		initWidgets();
		shell.open();
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ // if the queue is empty
		       display.sleep(); // sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components		
	}
}