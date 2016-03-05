package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.WiredCatJoystick;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroIntakeCommand extends Command {


	public static WiredCatJoystick operator;
	
    public ZeroIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
    	operator = new WiredCatJoystick(1);
    	requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.setIntakeMotor(-0.5);
    	if(Math.abs(Robot.intakeSubsystem.getAngle()) > 0) Robot.intakeSubsystem.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !operator.buttons[11].get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.stopIntakeMotor();
    	Robot.intakeSubsystem.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSubsystem.stopIntakeMotor();
    	Robot.intakeSubsystem.resetEncoder();
    }
}
