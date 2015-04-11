import java.awt.Font;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Label implements GraphicalObject {

	private GLabel label;
	private float fontSize = 20;
	private boolean labelChanged = true;

	public Label() {
		label = new GLabel("");

		setFont();
	}

	public Label(String text, double x, double y) {
		label = new GLabel(text, x, y);

		setFont();
	}

	private void setFont() {
		label.setFont(Font.SANS_SERIF);
		label.setFont(label.getFont().deriveFont(fontSize));
	}

	
	
	@Override
	public void updateGraphics() {
		if (labelChanged) {
			// Position self
			label.setLocation((Graphics.RES_X / 2) - (label.getWidth() / 2),
					label.getHeight());
			
			labelChanged = false;
		}
	}

	public GLabel getLabel() {
		return label;
	}

	public void setText(String newText) {
		this.label.setLabel(newText);
		labelChanged = true;
	}

	@Override
	public void add(GraphicsProgram prog) {
		prog.add(label);
	}

	@Override
	public void remove(GraphicsProgram prog) {
		prog.remove(label);
	}

}
