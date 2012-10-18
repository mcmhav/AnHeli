package pa.e1;

import java.util.ArrayList;

import sheep.graphics.Color;
import android.graphics.Canvas;

public class DB {
	private ArrayList<String> strings;
	private TS ts;
	private int framesLastSecond;
	private int framesThisSecond;
	private int msCounter;

	public DB(TS ts) {
		strings = new ArrayList<String>();
		this.ts = ts;
		framesLastSecond = 0;
		framesThisSecond = 0;
		msCounter = 0;
	}

	public void update(float dt) {
		int millisecondsSinceLastFrame = (int) (dt * 1000);
		framesThisSecond++;
		msCounter += millisecondsSinceLastFrame;
		if (msCounter >= 1000) {
			msCounter -= 1000;
			framesLastSecond = framesThisSecond;
			framesThisSecond = 0;
		}
	}
	public void draw(Canvas canvas) {
		strings.add("FPS: " + framesLastSecond);
		strings.add("Cap-X:" + ts.getHel().getX());
		strings.add("Cap-Y:" + ts.getHel().getY());
		strings.add("Tx:" + Cons.TPX);
		strings.add("Ty:" + Cons.TPY);
		strings.add("Sx:" + ts.getHel().getSpeed().getX());
		strings.add("Sy:" + ts.getHel().getSpeed().getY());
		
		for ( int i = 0; i < strings.size(); i++ ) {
			String string = strings.get(i);
			int y = (i+1)*15;
			canvas.drawText(string, 15, y, Color.GREEN);
		}
		strings = new ArrayList<String>();
	}
}