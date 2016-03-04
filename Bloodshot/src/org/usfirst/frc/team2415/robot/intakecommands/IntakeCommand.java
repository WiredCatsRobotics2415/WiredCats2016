package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.WiredCatJoystick;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	
	double staySetpoint;
	double desiredAngle;
	double intakeSpeed;
	double timeOutLength;
	boolean buttonState;
	boolean isChecked = false;
    
    public IntakeCommand(double desiredAngle, double intakeSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	this.intakeSpeed = intakeSpeed;
    	this.desiredAngle = (desiredAngle/360)*128;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.stopSpinMotor();
    	Robot.intakeSubsystem.stopIntakeMotor();
    	Robot.intakeSubsystem.intakeSetpoint = desiredAngle;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Math.abs(Robot.intakeSubsystem.intakeError) < 3 && isChecked){
//    		Robot.intakeSubsystem.enableBrakeMode();
//    		Robot.intakeSubsystem.stopIntakeMotor();
//    	} else {
    		Robot.intakeSubsystem.disableBrakeMode();
    		double currAngle = Robot.intakeSubsystem.getAngle();
        	Robot.intakeSubsystem.intakeError = desiredAngle - currAngle;
        	double output = Robot.intakeSubsystem.pid.pidOut(desiredAngle - currAngle);
        	Robot.intakeSubsystem.intakeOutput = output;
//        	output = (output < 0 ? -1:1) * Math.min(Math.abs(output), .75);
        	Robot.intakeSubsystem.setIntakeMotor(-output);
        	Robot.intakeSubsystem.setSpinMotor(intakeSpeed);
//        	isChecked = true;
//    	}

    	if(Robot.operator.buttons[7].get() && !Robot.intakeSubsystem.getButton()) {
    		Robot.intakeSubsystem.setSpinMotor(.7324);
    		isChecked = false;
    	}
    	if(Robot.operator.buttons[6].get()) {
    		Robot.intakeSubsystem.setSpinMotor(-1);
    		isChecked = false;
    	}
    	if(Robot.operator.buttons[2].get()) {
    		Robot.intakeSubsystem.setSpinMotor(1);
    		isChecked = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.setSpinMotor(0);
    	Robot.intakeSubsystem.stopIntakeMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.setSpinMotor(0);
    	Robot.intakeSubsystem.stopIntakeMotor();
    }
}
