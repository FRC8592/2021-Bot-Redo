package frc.robot;

import java.util.HashMap;
import java.util.Map;

public class BasicTeleopModule extends ControlModule{
    private InputModule inputs;
    private OutputModule outputs;

    public BasicTeleopModule(InputModule inputs, OutputModule outputs){
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

        if(forward > -0.5 && forward < 0.5){
            forward = 0;
        }

        if(turn > -0.5 && turn < 0.5 ){
            turn = 0;
        }
    }

    @Override
    public Map<String, Double> getState() {
        // TODO Auto-generated method stub
        return new  HashMap<String, Double>();
    }
    
}
