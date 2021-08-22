package frc.robot;

public class RobotState {
    
    private DriveTrainState driveTrainState;
    private DriveControlsState driveControlsState;
    private TurretModuleState turretModuleState;
    private CollectorModuleState collectorModuleState;

    public DriveTrainState getDriveTrainState() {
        return driveTrainState;
    }

    public void setDriveTrainState(DriveTrainState driveTrainState) {
        this.driveTrainState = driveTrainState;
    }

    public DriveControlsState getDriveControlsState() {
        return driveControlsState;
    }

    public void setDriveControlsState(DriveControlsState driveControlsState) {
        this.driveControlsState = driveControlsState;
    }

    public TurretModuleState getTurretModuleState() {
        return turretModuleState;
    }

    public void setTurretModuleState(TurretModuleState turretModuleState) {
        this.turretModuleState = turretModuleState;
    }

    public CollectorModuleState getCollectorModuleState() {
        return collectorModuleState;
    }

    public void setCollectorModuleState(CollectorModuleState collectorModuleState) {
        this.collectorModuleState = collectorModuleState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((collectorModuleState == null) ? 0 : collectorModuleState.hashCode());
        result = prime * result + ((driveControlsState == null) ? 0 : driveControlsState.hashCode());
        result = prime * result + ((driveTrainState == null) ? 0 : driveTrainState.hashCode());
        result = prime * result + ((turretModuleState == null) ? 0 : turretModuleState.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RobotState other = (RobotState) obj;
        if (collectorModuleState == null) {
            if (other.collectorModuleState != null)
                return false;
        } else if (!collectorModuleState.equals(other.collectorModuleState))
            return false;
        if (driveControlsState == null) {
            if (other.driveControlsState != null)
                return false;
        } else if (!driveControlsState.equals(other.driveControlsState))
            return false;
        if (driveTrainState == null) {
            if (other.driveTrainState != null)
                return false;
        } else if (!driveTrainState.equals(other.driveTrainState))
            return false;
        if (turretModuleState == null) {
            if (other.turretModuleState != null)
                return false;
        } else if (!turretModuleState.equals(other.turretModuleState))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RobotState [collectorModuleState=" + collectorModuleState + ", driveControlsState=" + driveControlsState
                + ", driveTrainState=" + driveTrainState + ", turretModuleState=" + turretModuleState + "]";
    }
}
