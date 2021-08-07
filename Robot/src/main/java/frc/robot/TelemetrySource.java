package frc.robot;

import java.util.Map;

public interface TelemetrySource {
    public Map<String, Double> toMap();
}
