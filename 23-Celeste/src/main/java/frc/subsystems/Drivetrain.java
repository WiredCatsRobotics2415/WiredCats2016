package frc.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;

public class Drivetrain {
    
TalonSRX talons[];
TalonSRX leftBack;
TalonSRX leftFront;
TalonSRX rightBack;
TalonSRX rightFront;

public Drivetrain() {
    leftBack = new TalonSRX(RobotMap.LEFT_TALON_BACK);
    leftFront = new TalonSRX(RobotMap.LEFT_TALON_FRONT);
    rightBack = new TalonSRX(RobotMap.RIGHT_TALON_BACK);
    rightFront = new TalonSRX(RobotMap.RIGHT_TALON_FRONT);
}

public void DriveFunc(double throttle, double turn) {

    leftFront.set(ControlMode.PercentOutput, turn - throttle);
    leftBack.set(ControlMode.PercentOutput, turn - throttle);
    rightFront.set(ControlMode.PercentOutput, turn + throttle);
    rightBack.set(ControlMode.PercentOutput, turn + throttle);

}

}
