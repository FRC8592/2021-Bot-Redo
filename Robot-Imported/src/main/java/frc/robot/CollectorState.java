package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class CollectorState implements TelemetrySource {

    private boolean collectorDeploying;
    private boolean collectorUndeploying;
    private boolean collectorUnjamming;

    @Override
    public Map<String, Double> getTelemetryData() {
        Map<String, Double> data = new HashMap<String, Double>();

        // Add all of the data represented by this class to the Map
        data.put("collectorDeploying", collectorDeploying ? 1.0 : 0.0);
        data.put("collectorUndeploying", collectorUndeploying ? 1.0 : 0.0);
        data.put("collectorUnjamming", collectorUnjamming ? 1.0 : 0.0);

        return data;
    }

    public boolean isCollectorDeploying() {
        return collectorDeploying;
    }

    public void setCollectorDeploying(boolean collectorDeploying) {
        this.collectorDeploying = collectorDeploying;
    }

    public boolean isCollectorUndeploying() {
        return collectorUndeploying;
    }

    public void setCollectorUndeploying(boolean collectorUndeploying) {
        this.collectorUndeploying = collectorUndeploying;
    }

    public boolean isCollectorUnjamming() {
        return collectorUnjamming;
    }

    public void setCollectorUnjamming(boolean collectorUnjamming) {
        this.collectorUnjamming = collectorUnjamming;
    }
    
}
