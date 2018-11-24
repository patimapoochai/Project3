import java.util.ArrayList;

//the base class that would provide some common functions of graphics
public class Graphic {
	protected int posX;
	protected int posY;
	
	//is this element on screen?
	protected boolean isOnScreen;
	
	//the image
	protected EZImage picture;
	
	Graphic(String filename, int x, int y){
		posX = x;
		posY = y;
		
		this.picture = EZ.addImage(filename, posX, posY);
	}
	
	public void update() {
		
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
	private void onScreenDetection() {
		if ((posY < Main.RES_Y)) {
			isOnScreen = true;
		} else {
			isOnScreen = false;
		}
	}
	
	//return the on-screen state
		public boolean getOnScreenStatus() {
			return isOnScreen;
		}
}
