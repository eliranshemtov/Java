package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/** 
 * Tile Class extends Canvas
 */
public class Tile extends Canvas {

	private int value;
	private boolean clicked;
	private Image winnerLogo;
	
	/**
	 * Tile C'tor: <br>
	 * Sets the tile's layout & grid. Sets the tile properties with the given parameters.<br>
	 * The tile size is set according to the window size (parent)
	 * @param parent (Composite)
	 * @param style (int)
	 * @param val (int)
	 * @param color (Color)
	 */
	public Tile(Composite parent, int style, int val, Color color) {
		super(parent, style);
		winnerLogo = new Image(getDisplay(), "resources/winnerLogo2.gif");
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		this.value = val;
		this.setBackground(color);
		
		this.setFont(new Font(getDisplay(), "Georgia", 20, SWT.BOLD));
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				FontMetrics fontMetrics = e.gc.getFontMetrics();
				int width = fontMetrics.getAverageCharWidth();
				
				int tileX = getSize().x/2 - (Integer.toString(value)).length() * width/2;
				int tileY = getSize().y/2 - fontMetrics.getHeight()/2 - fontMetrics.getDescent();
				int wSize = getSize().x;
				int hSize = getSize().y;
				
				e.gc.setForeground(new Color(null, 0, 0, 0));
				if (value !=0)
					e.gc.drawText(Integer.toString(value), tileX, tileY);
				else
					e.gc.drawImage(winnerLogo, 0, 0, 128, 123, 0 , 0, wSize, hSize);
			}
		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				clicked = true;
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// nothing to do here
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// nothing to do here
			}
		});
	}
	
	/**
	 * Tile.value getter 
	 * @return this.value (int)
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Tile.value setter
	 * @param value (int)
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * isClicked boolean getter
	 * @return this.clicked (boolean)
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * this.clicked setter
	 * @param clicked (boolean)
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}