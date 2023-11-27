package frc.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drivetrain {
    TalonSRX talons[];
    TalonSRX leftBackTalon;
    TalonSRX rightBackTalon;
    TalonSRX leftFrontTalon;
    TalonSRX rightFrontTalon;


    public Drivetrain() {
        leftBackTalon = new TalonSRX(RobotMap.LEFT_TALON_BACK);
        leftFrontTalon = new TalonSRX(RobotMap.LEFT_TALON_FRONT);
        rightBackTalon = new TalonSRX(RobotMap.RIGHT_TALON_BACK);
        rightFrontTalon = new TalonSRX(RobotMap.RIGHT_TALON_FRONT);
    }

    boolean INVERTED = false;
    public void DriveFunc(double throttle, double turn) {
        rightFrontTalon.set(ControlMode.PercentOutput, throttle + turn);
        rightBackTalon.set(ControlMode.PercentOutput, throttle + turn);
        leftFrontTalon.set(ControlMode.PercentOutput, throttle - turn);
        leftBackTalon.set(ControlMode.PercentOutput, throttle - turn);
    }
}
