package serverGui;

import model.SolutionManager;
import network.MyTCPIPServer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
	
/**
 * WelcomeWindow Class extends {@link ServerGUI} - <br>
 * is the GUI window that welcomes the server.
 */
public class ServerWindow extends ServerGUI {

	private MyTCPIPServer server;
	private Thread t;
	private boolean closed;
	private Image start;
	private Image stop;
	
	/**
	 * C'tor - initialize super's c'tor
	 * @param width (int)
	 * @param height (int)
	 * @param title (String)
	 * @param server (MyTCPIPServer)
	 */
	public ServerWindow(int width, int height, String title, MyTCPIPServer server) {
		super(width, height, title);
		this.server = server;
		this.closed = true;
		start = new Image(display, "resources/startServer.gif");
		stop = new Image(display, "resources/stopServer.gif");		
	}
	
	/**
	 * getter for closed data member
	 * @return boolean 
	 */
	public boolean getClosed(){
		return closed;
	}
	
	/**
	 * stops the server activity
	 */
	private void stopGUIServer(){
		
		if (server != null){
			server.stopServer();
			shell.setText("Server Off");
			
		SolutionManager.getInstance().saveSolutionInFile();
		}		
	}
	
	/**
	 * starts the server activity
	 */
	private void startServer(){
		shell.setText("Server ON");
		server = new MyTCPIPServer();
		t = new Thread(server);
		t.start();
	}
	
	/**
	 * getter for this.server data member
	 * @return MyTCPIPServer
	 */
	public MyTCPIPServer getServer(){
		return server;
	}
	
	@Override
	void initWidgets() {
		MenuBar menu = new MenuBar(display, shell);
		shell.setLayout(new GridLayout(2, false));
		
		final Label mainHeadline = new Label(shell, SWT.NONE);
		mainHeadline.setText("Hello human, Please Choose your poison: ");
		mainHeadline.setImage(new Image(display, "resources/serverMainLable.gif"));
		mainHeadline.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
	
		Button startStop = new Button (shell, SWT.PUSH);
		startStop.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true, true, 1, 1));
		startStop.setImage(start);
		
		menu.exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				closed = true;
				stopGUIServer();
				shell.dispose();
				display.dispose();				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
		
		startStop.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (closed){
					startStop.setImage(stop);
					startServer();					
				}
					
				else {
					stopGUIServer();
					startStop.setImage(start);
				}
				closed = !closed;	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing do to here
			}
		});
	    
	}
}
