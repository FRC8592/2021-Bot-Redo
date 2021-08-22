package frc.robot;

public class CollectorState {

    private boolean isDeploying;
    private boolean isUndeploying;
    private boolean isUnjamming;

    public boolean isDeploying() {
        return isDeploying;
    }

    public void setDeploying(boolean isDeploying) {
        this.isDeploying = isDeploying;
    }

    public boolean isUndeploying() {
        return isUndeploying;
    }

    public void setUndeploying(boolean isUndeploying) {
        this.isUndeploying = isUndeploying;
    }

    public boolean isUnjamming() {
        return isUnjamming;
    }

    public void setUnjamming(boolean isUnjamming) {
        this.isUnjamming = isUnjamming;
    }

}
