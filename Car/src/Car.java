
public class Car extends Actor{
	
	static int FORWARD = 0;
	static int BACKWARD = 1;
	static int NOT_MOVING = 2;
	int vx = 0;
	int vy = 0;
	int sideSpeed = 3;
	final int acceleration = -1;
	final int maxSpeed = 10;
	int dir = NOT_MOVING;
	
	public Car(){
		width = 40;
		height = 80;
	}
	
	public void update(){
		x += vx;
		y += vy;
		if(dir == FORWARD){
			vy += acceleration;
		}else if(dir == BACKWARD){
			vy -= acceleration;
		}else{
			if(Math.abs(vy) != 0){
				if(vy > 0) vy += acceleration;
				if(vy < 0) vy -= acceleration;
			}
		}
		if(Math.abs(vy) > maxSpeed){
			if(vy > 0) vy = maxSpeed;
			if(vy < 0) vy = maxSpeed*-1;
		}
		
	}

}
