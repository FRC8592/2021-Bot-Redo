package frc.robot;

public class RobotState {

    private CollectorState collectorState = new CollectorState();
    private ControlsState controlsState = new ControlsState();
    private DriveTrainState driveTrainState = new DriveTrainState();
    private TurretState turretState = new TurretState();
    TelemetrySource[] telemetrySources = { collectorState, controlsState, driveTrainState, turretState };

    public TelemetrySource[] getTelemetrySources() {
        return telemetrySources;
    }

    public CollectorState getCollectorState() {
        return collectorState;
    }

    public ControlsState getControlsState() {
        return controlsState;
    }

    public DriveTrainState getDriveTrainState() {
        return driveTrainState;
    }

    public TurretState getTurretState() {
        return turretState;
    }

}
