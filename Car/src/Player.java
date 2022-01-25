
public class Player extends Car{
	
	public Player(World cont){
		super();
		sprite = loadImage("Images/Car.png");
		container = cont;
		width = 40;
		height = 80;
		setY(cont.height-this.height);
	}
	
	@Override
	public void update(){
		super.update();
		if(y+container.view-1 >= container.height-height){
			vy = 0;
		}
	}
	
}
