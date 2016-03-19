package org.usfirst.frc.team2415.robot.intakecommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PneumaticIntakeCommand extends Command {
	
	static DoubleSolenoid.Value[] intakeState;
	private static final double SAMPLE_TIME = 0.1;

    public PneumaticIntakeCommand(String intakeState) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pneumaticIntakeSubsystem);
    	Robot.pneumaticIntakeSubsystem.setIntakeState(intakeState);
    	this.intakeState[0] = Robot.pneumaticIntakeSubsystem.intakeState[0];
    	this.intakeState[1] = Robot.pneumaticIntakeSubsystem.intakeState[1];
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumaticIntakeSubsystem.setPivot(intakeState);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.operator.buttons[7].get() && !Robot.pneumaticIntakeSubsystem.getIR()) {
			Robot.pneumaticIntakeSubsystem.setIntakeSpeed(0.45);
		}
//    	
//    	time = (System.currentTimeMillis() - lastTime)/1000.0;
//    	if(time >= SAMPLE_TIME){
//    		if(Robot.operator.buttons[7].get() && !Robot.pneumaticIntakeSubsystem.getIR()) {
//    			Robot.pneumaticIntakeSubsystem.setIntakeSpeed(0.7234);
//    		}
//			lastTime = time;
//    	}
    	if(Robot.operator.buttons[6].get()) {
    		Robot.pneumaticIntakeSubsystem.setIntakeSpeed(-1);
    	}
    	if(Robot.operator.buttons[2].get()) {
    		Robot.pneumaticIntakeSubsystem.setIntakeSpeed(0.50);
    	}
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
