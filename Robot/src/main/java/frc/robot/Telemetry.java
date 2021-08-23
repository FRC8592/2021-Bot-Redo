package frc.robot;

import java.util.Map;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sends telemetry data to some display or collection device like SmartDashboard
 * and/or a USB storage device that lives on the robot.
 */
public class Telemetry {

    public void send(TelemetrySource... sources) {

        // Loop through the sources, sending the data from each one
        for (TelemetrySource source : sources) {
            sendFromSource(source);
        }
    }

    public void sendFromSource(TelemetrySource source) {

        // Get the data from the source and send each data item
        Map<String, Double> data = source.getTelemetryData();
        for (Map.Entry<String, Double> dataItem : data.entrySet()) {
            sendDataItem(dataItem);
        }
    }

    private void sendDataItem(Map.Entry<String, Double> dataItem) {
        // Use SmartDashboard to send the data item
        SmartDashboard.putNumber(dataItem.getKey(), dataItem.getValue());
    }
}
