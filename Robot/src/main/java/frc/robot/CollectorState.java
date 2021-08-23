package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class CollectorState implements TelemetrySource {

    private boolean isDeploying;
    private boolean isUndeploying;
    private boolean isUnjamming;

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("isDeploying", isDeploying() ? 1.0 : 0.0);
        data.put("isUndeploying", isUndeploying() ? 1.0 : 0.0);
        data.put("isUnjamming", isUnjamming() ? 1.0 : 0.0);

        return data;
    }
    
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
