import java.awt.Color;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class Wave implements GraphicalObject {

	public static double MaxRange = 1000;
	public static double VelocX, VelocY;
	
	public static double WaveExpansionSpeed = 4.5;
	
	public static final double ALPHA_DECREMENT = 0.01d;
	
	double PosX, PosY;
	double Range;
	double alpha = 1;
	boolean Dead = false;

	GOval WaveOuter;
	GOval WaveInner;

	public Wave(double x, double y, double r) {
		PosX = x;
		PosY = y;
		Range = r;

		create();
	}

	public void SetSpeed(double s) {
		WaveExpansionSpeed = s;
	}

	public void create() {
		WaveOuter = new GOval(PosX, PosY);
		//WaveInner = new GOval(PosX, PosY);

		WaveOuter.setFilled(false);
		//WaveInner.setFilled(true);

		//WaveInner.setColor(Color.WHITE);
	}

	
	void setPos() {
		//WaveInner.setSize(r, r);
		WaveOuter.setSize(Range, Range);

		//WaveInner.setLocation(PosX - r / 2, PosY - r / 2);
		WaveOuter.setLocation(PosX - Range / 2, PosY - Range / 2);
	}

	@Override
	public void add(GraphicsProgram pg) {
		pg.add(WaveOuter);
		//pg.add(WaveInner);
	}
	
	@Override
	public void remove(GraphicsProgram pg) {
		//pg.remove(WaveInner);
		pg.remove(WaveOuter);
	}

	@Override
	public void updateGraphics() {
		if (Dead == false) {
			
			alpha-=ALPHA_DECREMENT;
			
			WaveOuter.setColor(new Color(0, 0, 0, (float)alpha));
			
			//WaveInner.sendToBack();
			WaveOuter.sendToBack();

			PosX += VelocX;
			PosY += VelocY;
			
			Range += WaveExpansionSpeed;

			if (alpha <= 0)
				Dead = true;

			setPos();
		}
	}

}
