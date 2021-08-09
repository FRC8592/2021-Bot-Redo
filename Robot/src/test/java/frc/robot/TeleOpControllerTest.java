package frc.robot;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
// import frc.robot.TeleOpController;

public class TeleOpControllerTest {
  @Test
  public void evaluateExpression() {
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);
    teleopController.control();
  }
}