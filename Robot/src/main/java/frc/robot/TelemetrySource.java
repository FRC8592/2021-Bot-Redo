package frc.robot;

import java.util.Map;

/**
 * Standard Java interface for getting telemetry data from some class, typically
 * a "...State" class.
 */
public interface TelemetrySource {
    public Map<String, Double> getTelemetryData();
}
