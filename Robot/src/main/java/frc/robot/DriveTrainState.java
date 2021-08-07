package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class DriveTrainState implements TelemetrySource {

	public void write(DriveTrainState driveTrainState) {
	}
    
    @Override
    public Map<String, Double> getTelemetryData() {
        return new HashMap<String, Double>();
    }
}
