package org.usfirst.frc.team2415.robot.catapultcommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FireCatapultCommand extends Command {
	

    public FireCatapultCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.catapultSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.catapultSubsystem.fire(0);
    	Robot.catapultSubsystem.fire(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.catapultSubsystem.close(0);
    	Robot.catapultSubsystem.close(1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.catapultSubsystem.close(0);
    	Robot.catapultSubsystem.close(1);
    }
}
