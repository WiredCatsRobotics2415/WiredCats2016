package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PneumaticIntakeCommand extends Command {
	
	static boolean[] intakeState;

    public PneumaticIntakeCommand(String intakeState) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pneumaticIntakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.intakeState[0] = Robot.pneumaticIntakeSubsystem.intakeState[0];
    	this.intakeState[1] = Robot.pneumaticIntakeSubsystem.intakeState[1];
    	long startTime = System.currentTimeMillis();
    	Robot.pneumaticIntakeSubsystem.setPivot(intakeState);
    	while((System.currentTimeMillis() - startTime)/1000.0 <= 0.25);
    	Robot.pneumaticIntakeSubsystem.setPivot(new boolean[]{false,false});
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
