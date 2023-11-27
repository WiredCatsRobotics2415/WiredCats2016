package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
    private XboxController controller;
    private final double DEADBAND=0.05;

    public int Y_AXIS_NUMBER = 4; // aka throttle
    public int X_AXIS_NUMBER = 1; // aka rotation

    public OI() {
        controller = new XboxController(0);
    }

    public double getThrottle() {
        if (Math.abs(this.controller.getRawAxis(Y_AXIS_NUMBER)) < DEADBAND) {
            return 0;
        } else {
            return this.controller.getRawAxis(Y_AXIS_NUMBER);
        }
    }

    public double getRotation() {
        if (Math.abs(this.controller.getRawAxis(X_AXIS_NUMBER)) < DEADBAND) {
            return 0;
        } else {
            return this.controller.getRawAxis(X_AXIS_NUMBER);
        }
    }

    public boolean getShooterButton(int button) {
        return this.controller.getRawButtonPressed(button);
    }
}
