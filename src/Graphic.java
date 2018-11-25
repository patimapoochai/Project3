import java.util.ArrayList;

//the base class that would provide some common functions of graphics
public class Graphic {
	protected int posX;
	protected int posY;
	
	//is this element below the screen?
	protected boolean isBelowScreen;
	protected boolean isOnScreen;
	
	
	//the image
	protected EZImage picture;
	
	Graphic(String filename, int x, int y){
		posX = x;
		posY = y;
		
		this.picture = EZ.addImage(filename, posX, posY);
	}
	
//	Must be run by sub-classes in updates
	public void checks() {
		isBelowScreenCheck();
		isOnScreenCheck();
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
		setImagePosition(x, y);
	}
	
	private void setImagePosition(int posx, int posy) {
		picture.translateTo(posx, posy);
		
	}
	
	//is input points inside this image?
	boolean isIn(int x, int y) {
		boolean state = false;
		if (picture.isPointInElement(x, y) || picture.isPointInElement(x, y)) {
			state = true;
		}
		return state;
	}
	
	//return if this element on screen
	private void isBelowScreenCheck() {
		if ((posY > Main.RES_Y + picture.getHeight()/2)) {
			isBelowScreen = true;
		} else {
			isBelowScreen = false;
		}
	}
	
	//return the on-screen state
	public boolean getBelowScreenStatus() {
		return isBelowScreen;
	}
	
	private void isOnScreenCheck() {
		if ((posX > 0 - picture.getWidth()/2 && posX < Main.RES_X + picture.getWidth()/2) && (posY > 0 - picture.getHeight()/2 && posY < Main.RES_Y + picture.getHeight()/2)) {
			isOnScreen = true;
		} else {
			isOnScreen = false;
		}
	}
	
	public boolean getOnScreenStatus() {
		return isOnScreen;
	}
}
