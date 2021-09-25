package frc.robot;

public final class config {
    private config(){throw new UnsupportedOperationException();}

    // Turret parameters
    public static final double BALL_TRIGGER_POWER  = 0.4;   // Fixed power value for turret feed wheel
    public static final double BALL_PROCESS_POWER  = 1.0;   // Fixed power value for turret feed belt
    public static final double TURRET_SCALE        = 0.5;   // Scale joystick input for turret motion
    public static final double MAX_RPM_DEVIATION   = 100;

    // Parameters for constant-speed flywheel control
    public static final double BALL_FLYWHEEL_RPM   = 4000;
    public static final double SHOOT_RAMP_TIME     = 2.0;   // Ramp time to full speed (seconds)
    public static final double MAX_FLYWHEEL_VOLTS  = 11.0;  // Compensate to this voltage
    public static final int    FLYWHEEL_PID_INDEX  = 0;     // Primary closed-loop PID index
    public static final int    SHOOT_SLOT_INDEX    = 0;     // PID parameter set for shooting
    public static final double SHOOT_PID_P         = 0.35;  // PID and feed forward
    public static final double SHOOT_PID_I         = 0.000001;
    public static final double SHOOT_PID_D         = 0.06;
    public static final double SHOOT_PID_F         = 0.045
    ;
    // Drive parameters
    public static final double RAMP_TIME    = 0.5;      // Seconds
    public static final double FORWARD_EXPO = 2;
    public static final double TURN_EXPO    = 2;
    public static final double TURN_POWER   = 0.4;
    public static final double DRIVE_POWER  = 0.3;       
}
