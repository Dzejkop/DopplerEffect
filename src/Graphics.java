import java.util.ArrayList;

import acm.program.GraphicsProgram;

public class Graphics extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	static final int STAGE_INTERVAL = 50;

	// Staging vars
	private int maxStage;
	private int stageNumber = 0;
	private boolean changeStage = false;
	
	// Array holding the velocities of the ship
	private double[] sourceVelocitiesByStage = { 0,
			Wave.WaveExpansionSpeed * 0.25d, Wave.WaveExpansionSpeed * 0.5d,
			Wave.WaveExpansionSpeed * 1d, Wave.WaveExpansionSpeed * 0.15d };

	// Messages for the top label
	private String[] labelsByStage = { "Prêdkoœæ równa 0",
			"Prêdkoœæ mniejsza od prêdkoœci dŸwiêku ",
			"Prêdkoœæ równa prêdkoœci dŸwiêku (Mach 1)",
			"Prêdkoœæ wiêksza od prêdkoœci dŸwiêku (Mach 2)", "..." };

	private double currentSourceSpeed;
	private int stageInterval = STAGE_INTERVAL;

	// The wave source
	Ship ship;
	
	// Singleton
	public static Graphics instance;

	// Window parameters
	public static final double FPS = 1000 / 60;
	public static final int RES_X = 800;
	public static final int RES_Y = 600;

	// Used for updating each graphical object
	ArrayList<GraphicalObject> objects;
	
	// Spawns waves and controls source speed
	WaveManager waveManager;

	Label topLabel;

	public void init() {
		// Singleton
		instance = this;

		waveManager = new WaveManager();

		// Resize the window
		this.setSize(RES_X, RES_Y);

		// Prepare the objects array
		objects = new ArrayList<GraphicalObject>();

		// Create the ship
		ship = new Ship();
		objects.add(ship);
		ship.add(this);
		ship.setPos(RES_X / 2, RES_Y / 2);

		// Create the label
		topLabel = new Label(labelsByStage[0], RES_X / 2, 0);
		topLabel.add(this);
		objects.add(topLabel);

		// Staging preparation
		maxStage = sourceVelocitiesByStage.length;
	}

	public void run() {

		// Place the wave source in the middle of the ship
		waveManager.setSourcePos(RES_X / 2, RES_Y / 2);

		while (true) {

			update();

			pause(FPS);
		}
	}

	void update() {
		waveManager.update();

		if(isLastStage() == false) {
			stagesRoutine();
		} else {
			finalStageRoutine();
		}

		// Affect the actual speed
		waveManager.setSourceSpeed(currentSourceSpeed, 0);

		updateGraphics();
	}
	
	private void stagesRoutine() {
		if(!changeStage)manageVelocity();
		else manageStage();
	}
	
	private void finalStageRoutine() {
		topLabel.setText("Koniec");
		
		waveManager.setSourceSpeed(0, 0);
		
		ship.incrementPos(-2d, 0);
		waveManager.setSourcePos(ship.getX(), ship.getY());
	}

	private void updateGraphics() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).updateGraphics();
		}
	}

	private boolean isLastStage() {
		return stageNumber == maxStage - 1;
	}

	private void manageStage() {
		// Time to change the stage
		if (stageInterval <= 0) {
			changeStage = false;
			
			// Reset interval
			stageInterval = STAGE_INTERVAL;
			
			// Change the stage
			stageNumber++;
			
			// Change the top label
			topLabel.setText(labelsByStage[stageNumber]);
		} else {
			stageInterval--;
		}
	}

	private void manageVelocity() {
		// Interpolate the speed
		currentSourceSpeed += (sourceVelocitiesByStage[stageNumber] - currentSourceSpeed) * 0.02d;

		// Snap to value if close enough
		if (sourceVelocitiesByStage[stageNumber] - currentSourceSpeed < 0.01d) {
			currentSourceSpeed = sourceVelocitiesByStage[stageNumber];
			changeStage = true;
		}
	}

	// DEBUG FUNCTION
	void DLOG(String s) {
		System.out.println(s);
	}
}
