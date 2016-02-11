package org.usfirst.frc.team2415.robot.Commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CatapultCommand extends Command {

    public CatapultCommand() {
    	
       requires(Robot.catapultSubsystem);
       
    }

    protected void initialize() {
    	
    	Robot.catapultSubsystem.retractCatapult();
    	
    }

    protected void execute() {
    	
    	Robot.catapultSubsystem.extendCatapult();
    	long startingTime = System.currentTimeMillis();
    	while(System.currentTimeMillis()-startingTime<500){
    		if(!Robot.catapultSubsystem.isExtended()){
    			Robot.catapultSubsystem.extendCatapult();
    		}
    	}
    	Robot.catapultSubsystem.retractCatapult();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.catapultSubsystem.retractCatapult();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
