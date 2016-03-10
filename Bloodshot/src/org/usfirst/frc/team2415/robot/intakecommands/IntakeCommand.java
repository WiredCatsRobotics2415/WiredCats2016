package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.WiredCatJoystick;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	
	private final double SAMPLE_TIME = .1;
	
	double staySetpoint;
	double desiredAngle;
	double intakeSpeed;
	double timeOutLength;
	boolean buttonState;
	
	double lastTime, time;
    
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
    	lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double currAngle = Robot.intakeSubsystem.getAngle();
    	Robot.intakeSubsystem.intakeError = desiredAngle - currAngle;
    	double output = Robot.intakeSubsystem.pid.pidOut(desiredAngle - currAngle);
    	Robot.intakeSubsystem.intakeOutput = output;
    	output = (output < 0 ? -1:1) * Math.min(Math.abs(output), .50);
    	Robot.intakeSubsystem.setIntakeMotor(-output);
    	Robot.intakeSubsystem.setSpinMotor(intakeSpeed);
        
    	time = (System.currentTimeMillis() - lastTime)/1000.0;
    	if(time >= SAMPLE_TIME){
    		if(Robot.operator.buttons[7].get() && !Robot.intakeSubsystem.getIR()) {
    			Robot.intakeSubsystem.setSpinMotor(0.7234);
    		}
			lastTime = time;
    	}
    	if(Robot.operator.buttons[6].get() && Math.abs(Robot.intakeSubsystem.intakeError) <= 2) {
    		Robot.intakeSubsystem.setSpinMotor(-1);
    	}
    	if(Robot.operator.buttons[2].get() && Math.abs(Robot.intakeSubsystem.intakeError) <= 2) {
    		Robot.intakeSubsystem.setSpinMotor(0.75);
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
