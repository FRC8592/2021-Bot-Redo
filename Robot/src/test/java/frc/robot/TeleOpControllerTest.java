package frc.robot;
import org.junit.Test;

public class TeleOpControllerTest {
  @Test
  public void evaluateExpression() {
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);
    teleopController.control();
  }
}