// Useful functions and constants for working with the Falcon 500 motor

package frc.robot;

public final class falconUtil {
    private falconUtil() {throw new UnsupportedOperationException();}

    private static final double FALCON_COUNTS_PER_REV    = 2048.0;
    private static final double FALCON_CONVERSION_FACTOR =  600.0;

    // Convert RPM into the internal velocity units used for the Falcon 500
    // Maximum unloaded RPM is 6380, but we don't check for that
    public static double rpmToFalcon(double rpm) {
        return rpm * FALCON_COUNTS_PER_REV / FALCON_CONVERSION_FACTOR;
    }

}
