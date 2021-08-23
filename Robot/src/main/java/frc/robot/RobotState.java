package frc.robot;

public class RobotState {

    private CollectorState collectorState = new CollectorState();
    private ControlsState controlsState = new ControlsState();
    private DriveTrainState driveTrainState = new DriveTrainState();
    private TurretState turretState = new TurretState();

    public CollectorState getCollectorState() {
        return collectorState;
    }

    public void setCollectorState(CollectorState collectorState) {
        this.collectorState = collectorState;
    }

    public ControlsState getControlsState() {
        return controlsState;
    }

    public void setControlsState(ControlsState controlsState) {
        this.controlsState = controlsState;
    }

    public DriveTrainState getDriveTrainState() {
        return driveTrainState;
    }

    public void setDriveTrainState(DriveTrainState driveTrainState) {
        this.driveTrainState = driveTrainState;
    }

    public TurretState getTurretState() {
        return turretState;
    }

    public void setTurretState(TurretState turretState) {
        this.turretState = turretState;
    }
}
