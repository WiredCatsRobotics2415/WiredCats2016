package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.subsystems.IntakingSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

	long startTime;
	
	boolean checked = false, isInstant;
	
    public IntakeCommand(boolean isInstant) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakingSubsystem);
    	this.isInstant = isInstant;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.operator.buttons[2].get() || Robot.operator.buttons[6].get()){
        	startTime = System.currentTimeMillis();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	
    	if(!checked && !isInstant){
    		if((System.currentTimeMillis() - startTime) < 650) return;
    		checked = true;
    	}
    	
    	if(Robot.operator.buttons[7].get() && !Robot.intakingSubsystem.getIR()) {
		Robot.intakingSubsystem.setIntakeSpeed(0.75);
    	} else if(Robot.operator.buttons[6].get()) {
			Robot.intakingSubsystem.setIntakeSpeed(-1);
    	} else if(Robot.operator.buttons[2].get()) {
			Robot.intakingSubsystem.setIntakeSpeed(0.50 );
		} else {
			Robot.intakingSubsystem.setIntakeSpeed(0);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakingSubsystem.setIntakeSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakingSubsystem.setIntakeSpeed(0);
    }
}
