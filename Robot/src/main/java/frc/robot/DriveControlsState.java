package frc.robot;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the current state of the hardware used to drive the robot. The
 * hardware could be joysticks, a game controller, keyboard, or anything we choose.
 * We read a new instance of this class from DriveControlsModule at the 
 * beginning of every robot cycle. After the controller updates some values 
 * on that instance, we write it back to the module, which updates the desired
 * output of the motors. The updated instance is also sent as telemetry.
 */
public class DriveControlsState implements TelemetrySource {

    private double forwardBackward;
    
    public double getForwardBackward() {
        return this.forwardBackward;
    }

	public void setForwardBackward(double forwardBackward) {
        this.forwardBackward = forwardBackward;
	}

    /**
     * Store convert all data represented by this class into a HashMap so that the Telemetry
     * class can more easily consume data from any module.
     * 
     * @see frc.robot.Telemetry
     * @see frc.robot.TelemetrySource
     */
    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("forwardBackward", getForwardBackward());
        
        return data;
    }
}
