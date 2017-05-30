package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import config.HandleProperties;

/**
 * MenuBar Class defines the common menu that will serve  every window that will desires it.
 */
public class MenuBar {
	
	final protected MenuItem file;
	final protected MenuItem exitItem;
	final protected MenuItem help;
	final protected MenuItem openProperties;
	final protected MenuItem view;
	final protected MenuItem viewChart;
	
	/**
	 * MenuBar C'tor - Sets all the menu properties. 2 off them are the given parameters:<br>
	 * @param display (Display)
	 * @param shell (Shell)
	 */
	public MenuBar(Display display, Shell shell) {
		final Menu menu = new Menu(shell, SWT.BAR);
		file = new MenuItem(menu, SWT.CASCADE);
		file.setText("&File");
		final Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		file.setMenu(fileMenu);

		view = new MenuItem(menu, SWT.CASCADE);
		view.setText("&View");
		final Menu viewMenu = new Menu(shell, SWT.DROP_DOWN);
		view.setMenu(viewMenu);
		
		viewChart = new MenuItem(viewMenu, SWT.CASCADE);
		viewChart.setText("&View Chart");
		

		
		Image xmlIcon = new Image(shell.getDisplay(), "resources/xmlIcon.gif");
		Image exitIcon = new Image(shell.getDisplay(), "resources/exitIcon.gif");
		Image aboutIcon = new Image(shell.getDisplay(), "resources/aboutIcon.gif");
		
		help = new MenuItem(menu, SWT.CASCADE);
		help.setText("&Help");
		final Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		help.setMenu(helpMenu);
		final MenuItem about = new MenuItem(helpMenu, SWT.CASCADE);
		about.setText("&About Us");

		openProperties = new MenuItem(fileMenu, SWT.CASCADE);
		openProperties.setText("&open properties");
		
		@SuppressWarnings("unused")  // Separator!
		final MenuItem seperator = new MenuItem(fileMenu, SWT.SEPARATOR);
		
		exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&exit");
		shell.setMenuBar(menu);
		
		openProperties.setImage(xmlIcon);
		exitItem.setImage(exitIcon);
		about.setImage(aboutIcon);

		shell.setLayout(new GridLayout(2, true));
		Image icon = new Image(display, "resources/icon.gif");
		shell.setImage(icon);
		
		openProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setFilterPath("resources");
				String[] filterExt = {"*.xml" };
				fd.setFilterExtensions(filterExt);
				HandleProperties.setFILE_NAME(fd.open());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//nothing to do here
			}
		});
		
		about.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			    
				MessageBox messageBox = new MessageBox(shell,SWT.FILL);
				messageBox.setText("About Us");
				messageBox.setMessage("Eliran Shem Tov\neliran.shemtov@gmail.com\nLinkedin: goo.gl/bM6rLJ\n\n"
						+ "Tomer Hadad\nhadad.tomer@gmail.com\nLinkedin: goo.gl/0sDfVn");
				messageBox.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do here
			}
		});
	}
}
