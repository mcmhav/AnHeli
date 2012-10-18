package pa.e1;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class TS extends State implements TouchListener{
	private Image backgroundImage = new Image(R.drawable.background);
	private Sprite backSprite;
	private Heli hel;
	private Heli hel2;
	private Heli hel3;
//	private Heli hel4;
	private DB di;
	private Image[] ima = {new Image(R.drawable.h1), new Image(R.drawable.h2), new Image(R.drawable.h3), new Image(R.drawable.h4),};
	private ArrayList<Heli> helar = new ArrayList<Heli>();
	
	public TS(MainHel mh) {
//		hel = new Heli(new Image(R.drawable.helanime));
		hel = new Heli(ima);
		hel2 = new Heli(ima);
		hel3 = new Heli(ima);
//		hel4 = new Heli(ima);
		
		helar.add(hel);
		helar.add(hel2);
		helar.add(hel3);
//		helar.add(hel4);
		
		backSprite = new Sprite(backgroundImage);
		di = new DB(this);
		this.addTouchListener(new TouchListener() {
			
			@Override
			public boolean onTouchUp(MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouchMove(MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onTouchDown(MotionEvent event) {
				Cons.TPX = event.getX();
				Cons.TPY = event.getY();
				float nsx = event.getX() - hel.getPosition().getX();
				float nsy = event.getY() - hel.getPosition().getY();
				if(nsx>0 && hel.getSpeed().getX()<0){
					hel.setScale(-1, 1);
					hel.setPosition(hel.getPosition().getX() + hel.getIm().getWidth(), hel.getPosition().getY());
				}
				if(nsx<0 && hel.getSpeed().getX()>0){
					hel.setScale(1, 1);
					hel.setPosition(hel.getPosition().getX() - hel.getIm().getWidth(), hel.getPosition().getY());

				}
				if(nsx>50||nsx<50||nsy>50||nsy<50){
					nsy = nsy/3;
					nsx = nsx/3;
				}
				hel.setSpeed(nsx, nsy);
				return false;
			}
		});
	}
	
	public void draw(Canvas canvas){
		backSprite.draw(canvas);
	    hel.draw(canvas);
	    hel2.draw(canvas);
	    hel3.draw(canvas);
//	    hel4.draw(canvas);
	    di.draw(canvas);
	}
	
	public void update(float dt) {
		 hel.update(dt);
		 hel2.update(dt);
		 hel3.update(dt);
//		 hel4.update(dt);
		 di.update(dt);
		 for (int i = 0; i < helar.size(); i++) {
			for (int j = 0; j < helar.size(); j++) {
				if(!helar.get(i).equals(helar.get(j))){
					colider(helar.get(i), helar.get(j));
				}
			}
		}
		 if(hel.collides(hel2)){
			 hel.setSpeed(-hel.getSpeed().getX(), hel.getSpeed().getY());
			 hel2.setSpeed(-hel2.getSpeed().getX(), hel2.getSpeed().getY());
		 }
		 if(hel.collides(hel3)){
			 hel.setSpeed(-hel.getSpeed().getX(), hel.getSpeed().getY());
			 hel3.setSpeed(-hel3.getSpeed().getX(), hel3.getSpeed().getY());
		 }
		 if(hel3.collides(hel2)){
			 hel3.setSpeed(-hel3.getSpeed().getX(), hel3.getSpeed().getY());
			 hel2.setSpeed(-hel2.getSpeed().getX(), hel2.getSpeed().getY());
		 }
	}
	
	private void colider(Heli s1, Heli s2){
		if(s1.collides(s2)){
			s2.setSpeed(-s2.getSpeed().getX(), s2.getSpeed().getY());
			s1.setSpeed(-s1.getSpeed().getX(), s1.getSpeed().getY());
//			if(s1.getX()<s2.getX()){
////				s2.setScale(-1, 1);
////				s2.setPosition(s2.getPosition().getX() + s2.getIm().getWidth(), s2.getPosition().getY());
//				
////				s1.setScale(1, 1);
////				s1.setPosition(s1.getPosition().getX() - s1.getIm().getWidth(), s1.getPosition().getY());
//			}else{
////				s1.setScale(-1, 1);
////				s1.setPosition(s1.getPosition().getX() + s1.getIm().getWidth(), s1.getPosition().getY());
//				s1.setSpeed(-s1.getSpeed().getX(), s1.getSpeed().getY());
//				
////				s2.setScale(1, 1);
////				s2.setPosition(s2.getPosition().getX() - s2.getIm().getWidth(), s2.getPosition().getY());
//				s2.setSpeed(-s2.getSpeed().getX(), s2.getSpeed().getY());
//			}
		}
	}
	
	public Heli getHel(){
		return hel;
	}
}