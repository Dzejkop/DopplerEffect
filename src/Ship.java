import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class Ship implements GraphicalObject {

	// The image
	GImage image;

	// Position
	double xPos = 0, yPos = 0;

	@Override
	public void add(GraphicsProgram prog) {
		image = new GImage("playerShip1_orange.png");

		prog.add(image);
	}

	public void setPos(double x, double y) {
		xPos = x - (image.getWidth() / 2);
		yPos = y - (image.getHeight() / 2);

		image.setLocation(xPos, yPos);
	}

	public void incrementPos(double x, double y) {
		xPos += x;
		yPos += y;

		image.setLocation(xPos, yPos);
	}
	
	public double getX() {
		return xPos + (image.getWidth() / 2);
	}
	
	public double getY() {
		return yPos + (image.getHeight() / 2);
	}

	@Override
	public void remove(GraphicsProgram prog) {
		prog.remove(image);
	}

	@Override
	public void updateGraphics() {
	}

}
