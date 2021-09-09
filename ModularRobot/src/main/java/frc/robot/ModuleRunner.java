package frc.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//** this is an example File for How the Modules could be run together**/
public class ModuleRunner{
    /*this is a list of all modules used by the robot input module should be the first added
    output module should be the last in the list. that way sensor collection allways happens first*/
    
    private List<RobotModule> modules;


    public ModuleRunner(){
        modules = new ArrayList<RobotModule>();
    }
    /***
     * Here a sample update run to run all modules and output telemetry
     */
    public void update(){
          for(RobotModule module: modules){
              module.update();
          }  
          telemetryOutput();
    }
    void addModule(RobotModule module){
         modules.add(module);
    }
    void telemetryOutput(){
        if(!modules.isEmpty()){
            for(RobotModule module: modules){
                Map<String, Double> data = module.getState();
                for (Map.Entry<String, Double> dataItem : data.entrySet()) {
                    SmartDashboard.putNumber(dataItem.getKey(), dataItem.getValue());
                }
            }
        }
    }
}