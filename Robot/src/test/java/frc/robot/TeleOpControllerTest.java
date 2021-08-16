package frc.robot;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TeleopControllerTest {
  @Test
  public void testSetsDesiredSpeed() {

    // Given a TeleopController
    DriveControlsState driveControlsState = new DriveControlsState();
    DriveTrainState driveTrainState = new DriveTrainState();
    TeleopController teleopController = new TeleopController(driveControlsState, driveTrainState);

    // Given that the driver is using the drive controls to move the robot forward at maximum speed
    driveControlsState.setForwardBackward(1.0);

    // When I use the TeleopController to control the robot
    teleopController.control();

    // Then it should set the desired speed to maximum
    assertThat(driveTrainState.getDesiredSpeed(), equalTo(1.0));
  }
}