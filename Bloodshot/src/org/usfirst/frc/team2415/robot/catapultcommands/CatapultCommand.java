package org.usfirst.frc.team2415.robot.catapultcommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CatapultCommand extends Command {
	
	private boolean finisher;

    public CatapultCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.catapultSubsystem);
        finisher = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.catapultSubsystem.retractCatapult();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.catapultSubsystem.extendCatapult();
    	long startingTime = System.currentTimeMillis();
    	while(System.currentTimeMillis()-startingTime<500){
    		if(!Robot.catapultSubsystem.isExtended()){
    			Robot.catapultSubsystem.extendCatapult();
    		}
    	}
    	Robot.catapultSubsystem.retractCatapult();
    	finisher = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finisher;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.catapultSubsystem.retractCatapult();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.catapultSubsystem.retractCatapult();
    }
}
