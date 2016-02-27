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
	boolean isDone = false;
	double VOLTAGE_THRESHOLD = 0.0; //the value we don't want the voltage to go below
	
	WiredCatJoystick operator;
	
    public IntakeCommand(double desiredAngle, double intakeSpeed, boolean overrideButton) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	this.intakeSpeed = intakeSpeed;
    	this.desiredAngle = (desiredAngle/360)*128;
    	this.overrideButton = overrideButton;
    	
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

    	if(operator.buttons[7].get() && !Robot.intakeSubsystem.getButton()) {
    		Robot.intakeSubsystem.setSpinMotor(1);
    	}
    	if(operator.buttons[6].get()) {
    		Robot.intakeSubsystem.setSpinMotor(-1);
    	}
    	
//    	get the input and output voltage
    	System.out.println("Input Voltage: "+Robot.intakeSubsystem.getInputVoltage()
    			+", Output Voltage: "+Robot.intakeSubsystem.getOutputVoltage());
   
    	if(Robot.intakeSubsystem.getOutputVoltage() < VOLTAGE_THRESHOLD){
    		long startingTime = System.currentTimeMillis();
        	while(System.currentTimeMillis()-startingTime<300){}
        	isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
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
