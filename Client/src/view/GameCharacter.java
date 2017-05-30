package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * GameCharacter Class defines a general form of game character by int x, int y position and an image.
 *
 */
public class GameCharacter {

	int x,y;
	Image image;
	
	/**
	 * GameCharacter C'tor 
	 * @param x (int)
	 * @param y (int)
	 * @param display (Display)
	 * @param imagePath (String)
	 */
	public GameCharacter(int x, int y, Display display,String imagePath) {
		this.x = x;
		this.y = y;
		this.image = new Image(display, imagePath);
	}
	
	/**
	 * on a paint event, will draw the image at the given size.
	 * @param e (PaintEvent)
	 * @param width (int)
	 * @param height (int)
	 */
	public void paint(PaintEvent e, int width, int height) {
		e.gc.drawImage(image, 0, 0, 256, 256, x, y, width, height);
	}
}
