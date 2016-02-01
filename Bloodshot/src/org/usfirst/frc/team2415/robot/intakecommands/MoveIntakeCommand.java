package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntakeCommand extends Command {
	
	private float desiredAngle;

    public MoveIntakeCommand(float desiredAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pidintakeSubsystem);
    	this.desiredAngle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pidintakeSubsystem.stopIntakeMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pidintakeSubsystem.changeSetpoint(desiredAngle);
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
