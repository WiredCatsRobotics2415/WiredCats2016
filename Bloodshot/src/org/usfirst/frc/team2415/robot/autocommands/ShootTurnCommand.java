package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.PID;
import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootTurnCommand extends Command {
	
	private double error;
	PID pid;
	
	private boolean isDone = false;

    public ShootTurnCommand(double error) {
    	requires(Robot.driveSubsystem);
    	this.error = error;
    	
    	pid = new PID(.1, 0, 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.disableLeftBreakState();
    	Robot.driveSubsystem.disableRightBreakState();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while(Math.abs(error) > 5){

        	double power = pid.pidOut(error);
        	if(Math.abs(power) > .25) power = ((power > 0) ? 1:-1) * .25;
        	
        	Robot.driveSubsystem.setMotors(-power, -power);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    	Robot.driveSubsystem.enableLeftBreakState();
    	Robot.driveSubsystem.enableRightBreakState();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.stop();
    	Robot.driveSubsystem.enableLeftBreakState();
    	Robot.driveSubsystem.enableRightBreakState();
    }
}
