import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Level {
	
	int lanes = 4;
	int laneWidth;
	int height;
	int bgy;
	World container;
	private BufferedImage background;
	ArrayList<NPC> cars = new ArrayList<NPC>();
	
	public Level(String imageLocation, int h, World cont){
		container = cont;
		height = h;
		laneWidth = cont.width/lanes;
		bgy = -height+cont.height;
		try{
		    background = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		
	}
	
	public BufferedImage getImage(){
		return background;
	}
	
	public int getY(){
		return bgy;
	}
	
}
