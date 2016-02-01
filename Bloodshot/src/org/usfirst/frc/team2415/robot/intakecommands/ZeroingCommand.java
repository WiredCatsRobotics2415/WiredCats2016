package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroingCommand extends Command {
	
	private double VOLTAGE_LIMIT = 0.5;

    public ZeroingCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pidintakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pidintakeSubsystem.setSpinMotor(0.3);
    	if(Robot.pidintakeSubsystem.getAngle() < 0 && 
    	   Robot.pidintakeSubsystem.getIntakeVoltage() <= VOLTAGE_LIMIT){
    		Robot.pidintakeSubsystem.zeroEncoder();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pidintakeSubsystem.stopIntakeMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pidintakeSubsystem.stopIntakeMotor();
    }
}
