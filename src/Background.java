import java.util.ArrayList;

public class Background
{
	protected EZImage wallpaper;
	protected EZImage wallpaper2;
	protected int RES_X;
	protected int RES_Y;
	
	public static void main(String[] args)
	{
		
	}
	
	public Background(String file, int RES_X, int RES_Y)
	{
		this.RES_X = RES_X;
		this.RES_Y = RES_Y;
		this.wallpaper = new EZImage(file, RES_X, RES_Y);
		this.wallpaper2 = new EZImage(file, RES_X * 2, RES_Y);
	}
	
	public void scroll()
	{
		this.wallpaper.translateBy(this.RES_X, RES_Y--);
	}
	
	public void replacePlatform(ArrayList<Platform> platforms)
	{
		
	}
}
