import java.util.ArrayList;

//the base class that would provide some common functions of graphics
//Written by Patima Poochai
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
		isOnScreenCheck(this.posX, this.posY);
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
	
	private void isOnScreenCheck(int posX,int posY) {
		if ((posX > 0 - picture.getWidth()/2 && posX < Main.RES_X + picture.getWidth()/2) && (posY > 0 - picture.getHeight()/2 && posY < Main.RES_Y + picture.getHeight()/2)) {
			isOnScreen = true;
		} else {
			isOnScreen = false;
		}
	}
	
	public boolean getOnScreenStatus() {
		return isOnScreen;
	}
	
	static public boolean isPlayerOnScreen(Player player) {
		//variables to make sure that the player is really off screen
		int cushionX = player.getImage().getWidth()/2; int cushionY = player.getImage().getHeight()/2;
		if ((player.getX() > 0 - cushionX && player.getX() < Main.RES_X + cushionX) && (player.getY() > 0 - cushionY && player.getY() < Main.RES_Y + cushionY)) {
			return true;
		} else {
			return false;
		}
	}
	
	static public boolean isPlayerAboveDeathLine(Player player) {
		if ((player.getY() < Main.RES_Y + player.getImage().getHeight()/2)) {
			return true;
		} else {
			return false;
		}
	}
}
