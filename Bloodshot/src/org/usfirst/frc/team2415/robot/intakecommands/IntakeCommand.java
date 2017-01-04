package org.usfirst.frc.team2415.robot.intakecommands;

import java.util.ArrayList;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.subsystems.IntakingSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

	long startTime, intakeStart, initTime;
	
	boolean checked = false, isInstant, notStart;
	
	boolean currentCheckTime(){
		return (System.currentTimeMillis() - initTime)/1000.0 >= .6;
	}
	
    public IntakeCommand(boolean isInstant) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakingSubsystem);
    	this.isInstant = isInstant;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = System.currentTimeMillis();
    	if(Robot.operator.buttons[5].get() || Robot.operator.buttons[6].get() || Robot.gamepad.x_button.get() || Robot.gamepad.y_button.get()){
        	startTime = System.currentTimeMillis();
    	}
    	Robot.intakingSubsystem.enableBreakMode();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!checked && !isInstant){
    		if((System.currentTimeMillis() - startTime) < 750) return;
    		checked = true;
    	}
    	    	
    	if(Robot.pivotSubsystem.isIntaking()) {
    		Robot.intakingSubsystem.setIntakeSpeed(.75);
    	} else if(Robot.operator.buttons[6].get() || Robot.gamepad.x_button.get()) {
			Robot.intakingSubsystem.setIntakeSpeed(-1);
    		IntakingSubsystem.currentBool = false;
    	} else if(Robot.singleGamepadMode && Robot.gamepad.x_button.get()) {
			Robot.intakingSubsystem.setIntakeSpeed(-1);
    		IntakingSubsystem.currentBool = false;
    	} else if(Robot.operator.buttons[5].get()) {
			Robot.intakingSubsystem.setIntakeSpeed(0.5);
    		IntakingSubsystem.currentBool = false;
    	} else if(Robot.singleGamepadMode && Robot.gamepad.y_button.get()) {
			Robot.intakingSubsystem.setIntakeSpeed(0.5);
    		IntakingSubsystem.currentBool = false;
		} else if(Robot.operator.buttons[10].get()) {
			Robot.intakingSubsystem.setIntakeSpeed(0.5);
    		IntakingSubsystem.currentBool = false;
		} else {
			Robot.intakingSubsystem.setIntakeSpeed(0);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if(Robot.pivotSubsystem.isIntaking() && currentCheckTime()){
    			IntakingSubsystem.currentBool = Robot.intakingSubsystem.getCurrentBool(5);
    			if(IntakingSubsystem.currentBool){
    				if(notStart){
    					intakeStart = System.currentTimeMillis();
    					notStart = false;
    				}else if((System.currentTimeMillis() - intakeStart)/1000.0 >= .6){
    					return true;
    				}
    			}
    			else return false;
    		}
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
