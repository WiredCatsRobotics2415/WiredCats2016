package org.usfirst.frc.team2415.robot.catapultcommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CancelCommand extends Command {

    public CancelCommand() {
        requires(Robot.launcherSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.launcherSubsystem.closeAll();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.launcherSubsystem.closeAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.launcherSubsystem.closeAll();
    }
}
