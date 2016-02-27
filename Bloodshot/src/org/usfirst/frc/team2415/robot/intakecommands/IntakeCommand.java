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
	boolean overrideButton;
	boolean buttonState;
	boolean irState = false;
	double previousIRVoltage;
	
	private double IR_THRESHOLD; //This is for the IR, it's an arbitrary value
//								and all we should need to find out is what is
//								an average change for the IR Voltage to make in
//								going from nothing to something
	
	WiredCatJoystick operator;
	
    public IntakeCommand(double desiredAngle, double intakeSpeed, boolean overrideButton) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	this.intakeSpeed = intakeSpeed;
    	this.desiredAngle = (desiredAngle/360)*128;
    	this.overrideButton = overrideButton;
    	this.previousIRVoltage = Robot.intakeSubsystem.getVoltage();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.stopSpinMotor();
    	Robot.intakeSubsystem.stopIntakeMotor();
    	Robot.intakeSubsystem.intakeSetpoint = desiredAngle;

		operator = new WiredCatJoystick(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	double currAngle = Robot.intakeSubsystem.getAngle();
    	Robot.intakeSubsystem.intakeError = desiredAngle - currAngle;
    	double output = Robot.intakeSubsystem.pid.pidOut(desiredAngle - currAngle);
    	Robot.intakeSubsystem.intakeOutput = output;
//    	output = (output < 0 ? -1:1) * Math.min(Math.abs(output), .5);
    	Robot.intakeSubsystem.setIntakeMotor(-output);
    	Robot.intakeSubsystem.setSpinMotor(intakeSpeed);

    	
    	if(operator.buttons[7].get() && !irState) {
    		Robot.intakeSubsystem.setSpinMotor(1);
    		//I think as distance increases, so does IRVoltage
    		if(Robot.intakeSubsystem.getVoltage() - previousIRVoltage > IR_THRESHOLD){
        		long startingTime = System.currentTimeMillis();
            	while(System.currentTimeMillis()-startingTime<500){}
            	irState = true;
        	}
    	}
    	
    	
    	if(operator.buttons[6].get()) {
    		irState = false;
    		Robot.intakeSubsystem.setSpinMotor(-1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return irState;
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
