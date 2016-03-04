package org.usfirst.frc.team2415.robot.drivecommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.WiredCatGamepad;
import org.usfirst.frc.team2415.robot.WiredCatJoystick;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveCommand extends Command {
	
	private float DEADBAND = 0.05f;
	private float INTERPOLATION_FACTOR = 0.75f;   //Nathan's Settings
	private float STRAIGHT_LIMITER = 0.95f;
	private float TURN_BOOSTER = 1.3f;
	
//	private float DEADBAND = 0.05f;
//	private float INTERPOLATION_FACTOR = 0.5f;
//	private float STRAIGHT_LIMITER = .95f;
//	private float TURN_BOOSTER = 1.1f;
	

    public ArcadeDriveCommand() {
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftY, rightX;
    	
    	if(Robot.singlePlayerMode){
    		leftY = -Robot.operator.Y();
        	rightX = Robot.operator.X();
    	} else {
    		leftY = -Robot.gamepad.leftY();
        	rightX = Robot.gamepad.rightX();
    	}
    	
    	if(Math.abs(leftY) < DEADBAND) leftY = 0;
    	if(Math.abs(rightX) < DEADBAND) rightX = 0;
    	
    	leftY = INTERPOLATION_FACTOR*Math.pow(leftY, 3) + (1 - INTERPOLATION_FACTOR)*leftY;
    	rightX = INTERPOLATION_FACTOR*Math.pow(rightX, 3) + (1 - INTERPOLATION_FACTOR)*rightX;
    	
    	double left = leftY*STRAIGHT_LIMITER + rightX*TURN_BOOSTER;
    	double right =  leftY*STRAIGHT_LIMITER - rightX*TURN_BOOSTER;
    	
//    	if(Math.abs(left) >= 1) Robot.driveSubsystem.enableRightBreakState();
//    	if(Math.abs(right) >= 1) Robot.driveSubsystem.enableLeftBreakState();
    	
    	if(Robot.gamepad.rightBumper.get()){
        	Robot.driveSubsystem.setMotors(left*0.2, -right*0.2);
    	} else {
        	Robot.driveSubsystem.setMotors(left, -right);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.stop();
    }
}
