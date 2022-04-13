package frc.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class Drivetrain {
    Talon talons[];
    Talon leftBackTalon;
    Talon rightBackTalon;
    Talon leftFrontTalon;
    Talon rightFrontTalon;

    boolean INVERTED = false;

    public Drivetrain() {
        leftBackTalon = new Talon(RobotMap.LEFT_TALON_BACK);
        rightBackTalon = new Talon(RobotMap.RIGHT_TALON_BACK);
        leftFrontTalon = new Talon(RobotMap.LEFT_TALON_FRONT);
        rightFrontTalon = new Talon(RobotMap.RIGHT_TALON_FRONT);
        talons = new Talon[] {leftBackTalon, rightBackTalon, leftFrontTalon, rightFrontTalon};

        if (INVERTED) {
            for (Talon t : talons) {
                t.setInverted(true);
            }
        }
    }
    
    public void drive(double throttle, double turn) {
        leftBackTalon.set(throttle-turn);
        leftFrontTalon.set(throttle-turn);
        rightBackTalon.set(throttle+turn);
        rightFrontTalon.set(throttle+turn);
    }
}
