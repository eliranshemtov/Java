package view;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * abstract ChooseDifficultyWindow Class - extends observable and implements Runnable.
 * each domain that has different difficulty levels will extend this class.
 *
 */
public abstract class ChooseDifficultyWindow extends Observable implements Runnable{
	
	protected Button easyButton;
	protected Button mediumButton;
	protected Button hardButton;
	protected Shell shell;
	protected Display display;

	/**
	 * ChooseDifficultyWindow C'tor - gets the shell properties and initialize the shell it according to them.
	 * @param display (Display)
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 */
	public ChooseDifficultyWindow(Display display, int width, int height, String title) {
		this.display = display;
		this.shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		shell.setImage(new Image(display, "resources/icon.gif"));
	}
	
	/**
	 * initWidgets() function sets the shell layout with all it's ingredients:<br>
	 * (main label and  3 levels buttons)
	 */
	public void initWidgets(){
		
		shell.setLayout(new GridLayout(3, true));
		
		final Label mainHeadline = new Label(shell, SWT.NONE);
		mainHeadline.setImage(new Image(shell.getDisplay(), "resources/difficultyLevelLable.gif"));
		mainHeadline.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER | SWT.FILL, true, true, 3, 1));
		
		easyButton = new Button (shell, SWT.PUSH);
		easyButton.setSize(80,80);
		easyButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true,1,1));
		easyButton.setImage(new Image(shell.getDisplay(), "resources/easyButton.gif"));
		
		mediumButton = new Button (shell, SWT.PUSH);
		mediumButton.setSize(80,80);
		mediumButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true,1,1));
		mediumButton.setImage(new Image(shell.getDisplay(), "resources/mediumButton.gif"));
		
		hardButton = new Button (shell, SWT.PUSH);
		hardButton.setSize(150,150);
		hardButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true,1,1));
		hardButton.setImage(new Image(shell.getDisplay(), "resources/hardButton.gif"));
	
		easyButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				easyButtonSelected();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mediumButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mediumButtonSelected();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		hardButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				hardButtonSelected();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
	}
	
	/**
	 * abstract function that forces the successor to implement the functionality events that will occur when the the suitable button is selected
	 */
	public abstract void easyButtonSelected();
	
	
	/**
	 * abstract function that forces the successor to implement the functionality events that will occur when the the suitable button is selected
	 */
	public abstract void mediumButtonSelected();
	
	/**
	 * abstract function that forces the successor to implement the functionality events that will occur when the the suitable button is selected
	 */
	public abstract void hardButtonSelected();
	
}
