import java.util.ArrayList;


public class WaveManager {
	ArrayList<Wave> waves;
	
	// Source position
	double xPos = 0, yPos = 0;
	
	// Source speed
	double xVelocity = 0, yVelocity = 0;
	
	// The interval between each wave
	// larger values mean less waves
	double period = 10;
	
	// Time keeping
	int sinceLastWave = 0;
	
	// 
	int maxWaveCount = 50;

	public WaveManager() {
		waves = new ArrayList<Wave>();
	}
	
	public void update() {
		
		for(int i = 0 ; i < waves.size(); i++) {
			if(waves.get(i).Dead) {
				waves.get(i).remove(Graphics.instance);
				waves.remove(i);
			}
		}
		
		Wave.VelocX = xVelocity;
		Wave.VelocY = yVelocity;
		
		if(timeToSpawn()) {
			createWave();
		}
	}
	
	boolean timeToSpawn() {
		if(sinceLastWave == 0) {
			sinceLastWave = (int)period;
			return true;
		} else {
			sinceLastWave--;
			return false;
		}
	}
	
	void createWave() {
		Wave wave = new Wave(xPos, yPos, 0);
		waves.add(wave);
		
		wave.add(Graphics.instance);
		Graphics.instance.objects.add(wave);
	}
	
	public double getPeriod() {
		return period;
	}
	
	public void setPeriod(double v) {
		period = v;
	}
	
	public void setSourceSpeed(double xVelocity, double yVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;	
	}
	
	public void increaseSourceSpeed(double x, double y) {
		xVelocity += x;
		yVelocity += y;
	}
	
	public void setSourcePos(double x, double y) {
		this.xPos = x;
		this.yPos = y;
	}
}
