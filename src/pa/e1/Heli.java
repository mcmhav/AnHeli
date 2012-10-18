package pa.e1;

import sheep.graphics.Image;

public class Heli extends AT{

	private Image[] im;
	private int msCounter;
	
	public Heli(Image[] im){
		super(im, 0.1f, 0);
		this.im = im;
		setPosition((float)Math.random()*300, (float)Math.random()*500);
		setSpeed(40, 10);
		setScale(-1, 1);
		msCounter = 0;
	}
	
	public Image getIm(){
		return im[0];
	}
	
	public void update(float dt){
		int millisecondsSinceLastFrame = (int) (dt * 1000);
		msCounter += millisecondsSinceLastFrame;
		
		if(msCounter == 100){

		}
		if(getX()<0){
			setScale(-1, 1);
			setPosition(getPosition().getX() + im[0].getWidth(), getPosition().getY());
			setSpeed(-getSpeed().getX(), getSpeed().getY());
		}
		if(getX()>=Cons.WINDOW_WIDTH){
			setScale(1, 1);
			setPosition(getPosition().getX(), getPosition().getY());
			setSpeed(-getSpeed().getX(), getSpeed().getY());
		}
		if(getY()<0){
			setSpeed(getSpeed().getX(), -getSpeed().getY());
		}
		if(getY()>=Cons.WINDOW_HEIGHT){
			setSpeed(getSpeed().getX(), -getSpeed().getY());
		}
		
		super.update(dt);
	}
}