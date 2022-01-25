import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Actor {
	
	World container;
	int width;
	int height;
	int x;
	int y;
	BufferedImage sprite;
	
	public BufferedImage loadImage(String location){
		try{
		    sprite = ImageIO.read(new File(location));
		} catch (IOException e) {
			System.out.println(location);
			e.printStackTrace();
		}
		return sprite;
	}
	
	public BufferedImage getImage(){
		return sprite;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int newY){
		y = newY;
	}
	
	public int getX(){
		return x;
	}
}
