package frc.robot;

public class TurretModuleState {

    private double flywheelSpeed;
    private boolean isLaunching;
    private boolean isUnjamming;
    private double turretRotation;

    public double getFlywheelSpeed() {
        return flywheelSpeed;
    }

    public void setFlywheelSpeed(double flywheelSpeed) {
        this.flywheelSpeed = flywheelSpeed;
    }

    public boolean isLaunching() {
        return isLaunching;
    }

    public void setLaunching(boolean isLaunching) {
        this.isLaunching = isLaunching;
    }

    public boolean isUnjamming() {
        return isUnjamming;
    }

    public void setUnjamming(boolean isUnjamming) {
        this.isUnjamming = isUnjamming;
    }

    public double getTurretRotation() {
        return turretRotation;
    }

    public void setTurretRotation(double turretRotation) {
        this.turretRotation = turretRotation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(flywheelSpeed);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (isLaunching ? 1231 : 1237);
        result = prime * result + (isUnjamming ? 1231 : 1237);
        temp = Double.doubleToLongBits(turretRotation);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        TurretModuleState other = (TurretModuleState) obj;
        if (Double.doubleToLongBits(flywheelSpeed) != Double.doubleToLongBits(other.flywheelSpeed))
            return false;
        if (isLaunching != other.isLaunching)
            return false;
        if (isUnjamming != other.isUnjamming)
            return false;
        if (Double.doubleToLongBits(turretRotation) != Double.doubleToLongBits(other.turretRotation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TurretModuleState [flywheelSpeed=" + flywheelSpeed + ", isLaunching=" + isLaunching + ", isUnjamming="
                + isUnjamming + ", turretRotation=" + turretRotation + "]";
    }
    
}
