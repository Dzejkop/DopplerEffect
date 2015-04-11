import acm.program.GraphicsProgram;

public interface GraphicalObject {
	public void updateGraphics();
	
	public void add(GraphicsProgram prog);
	
	public void remove(GraphicsProgram prog);
}
