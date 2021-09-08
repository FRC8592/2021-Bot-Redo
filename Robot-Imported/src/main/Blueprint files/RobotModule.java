/***
 * This an interface for a RobotModule provided to you for the creation of a module 
 * this will help you format your robot code into into input modules, control modules, hardware modules
 * and telemtry modules
 * Since this is an interface in java Each module must implement the update method and the write state method
 */
public interface RobotModule{
    
    /*** This is the entry point int module use this module to read data from sensors or make calculations, or output commands 
     * to robot hardware if you do this write only on the update should be exposed for a module. all other methods shoud be private
     * with the exception of the getState method below. update will be called once per module by teleop Periodic
     */
     public void update();

     /** Coach Dillan Recomends state Obects or hashmaps are usefull and Coach Sam agrees With him states and hashmaps are useful this will be the function
     called to get this map from your object by the telemetry module you need to implement this for every Module you create*/
     public Map<String, Double> getState();   
}