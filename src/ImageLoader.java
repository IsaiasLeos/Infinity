import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader{
	
	public static BufferedImage loadImage(String path){
		try{
			return ImageIO.read(ImageLoader.class.getResource(path));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}