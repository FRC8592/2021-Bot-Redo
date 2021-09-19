package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class BasicTeleop extends ControlModule{
    private InputModule inputs;
    private OutputModule outputs;

    public BasicTeleop(InputModule inputs, OutputModule outputs){
        super(inputs, outputs);
        this.inputs = inputs;
        this.outputs = outputs;
        
    }
    @Override
    public void update() {
        // TODO Auto-generated method stub
        double forward = inputs.joystickModule.getForwardAxis();
        double turn = inputs.joystickModule.getTurnAxis();
        outputs.driveTrainModule.setForwardOut(forward);
        outputs.driveTrainModule.setTurningOut(turn);

    }

    @Override
    public Map<String, Double> getState() {
        // TODO Auto-generated method stub
        return new  HashMap<String, Double>();
    }
    
}
