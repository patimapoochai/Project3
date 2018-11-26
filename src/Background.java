import java.util.ArrayList;

public class Background
{
	protected EZImage wallpaper;
	protected EZImage wallpaper2;
	protected final int RES_X;
	protected final int RES_Y;
	
	public Background(String file, int RES_X, int RES_Y)
	{
		this.RES_X = RES_X;
		this.RES_Y = RES_Y;
		this.wallpaper = EZ.addImage(file, this.RES_X/2, this.RES_Y/2);
		this.wallpaper2 = EZ.addImage(file, this.RES_X/2, this.RES_Y + (this.RES_Y / 2));
		this.wallpaper.pushToBack();
		this.wallpaper2.pushToBack();
	}
	
	public void scroll(int scrollSpeed)
	{
		this.wallpaper.translateBy(0, scrollSpeed);
		this.wallpaper2.translateBy(0, scrollSpeed);
		if (this.wallpaper.getYCenter() >= this.RES_Y)
			this.wallpaper.translateTo(this.RES_X/2, 0);
		if (this.wallpaper2.getYCenter() >= this.RES_Y)
			this.wallpaper2.translateTo(this.RES_X/2, 0);
	}
	
	public void replacePlatform(ArrayList<Platform> platforms)
	{
		
	}
}
