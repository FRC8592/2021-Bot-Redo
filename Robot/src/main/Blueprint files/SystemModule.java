import java.util.HashMap;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

/** the Systems Module holds all constants needed globally for the program still 
 * technically a module just so a state can be written out to smart dashboard */
public class HardwareConstants implements RobotModule{
    public static final int DRIVE_STICK = 0;
    public static final int TURRET_STICK = 1;

        /* hardware Module is acting a header or reference everything is public in this file I know this is not normal but all we are doing is assigning constants and pinouts
        everything is also public static as this will save time later on this module should only hold hardware constants. 
        start list here and PLEASE USE MEANINGFULL NAMES!!!!! if you don't this is unusable these are sometimes written in all caps*/
        public static final Double driveJoystickPort = 0;

        @Override void update(){

        }
        //** important info like ports or states to this*/
        @Override Map<String, Double> getState(){
            Map map = new HashMap<String, Double>();
            return map;
        }
}
