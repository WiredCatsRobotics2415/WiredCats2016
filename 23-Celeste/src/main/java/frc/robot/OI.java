package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
    private XboxController controller;
    private final double DEADBAND=0.05;

    public int Y_AXIS_NUMBER = 1; // aka throttle
    public int X_AXIS_NUMBER = 4; // aka rotation

    public OI() {
        controller = new XboxController(0);
    }

    public double getThrottle() {
        if (Math.abs(this.controller.getRawAxis(Y_AXIS_NUMBER)) < DEADBAND) {
            return 0;
        } else {
            System.out.println(this.controller.getRawAxis(Y_AXIS_NUMBER));
            return this.controller.getRawAxis(Y_AXIS_NUMBER);
        }
    }

    public double getRotation() {
        if (Math.abs(this.controller.getRawAxis(X_AXIS_NUMBER)) < DEADBAND) {
            return 0;
        } else {
            System.out.println(this.controller.getRawAxis(X_AXIS_NUMBER));
            return this.controller.getRawAxis(X_AXIS_NUMBER);
        }
    }
}
