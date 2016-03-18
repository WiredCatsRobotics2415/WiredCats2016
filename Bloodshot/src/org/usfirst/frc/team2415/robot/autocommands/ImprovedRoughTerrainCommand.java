package org.usfirst.frc.team2415.robot.autocommands;

import java.util.ArrayList;

import org.usfirst.frc.team2415.robot.DataAnalyzer;
import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Drives the robot a given speed forwards over the moat, rock wall, rough terrain,
 *	and ramparts. The robot then stops when it knows that it has reached the other side. 
 */
public class ImprovedRoughTerrainCommand extends Command {
	private final double SPEED, SAMPLE_SIZE = 6 ;
	
	private ArrayList<Double> samples;
	
    public ImprovedRoughTerrainCommand(double speed) {
    	requires(Robot.driveSubsystem);
    	SPEED = speed;
    	
    	samples = new ArrayList<Double>();
    }

    protected void initialize() {
    }
    
    private boolean checked = false, started = false, isDone = false;
    protected void execute() {
    	Robot.driveSubsystem.setMotors(SPEED, -SPEED);
    	
    	if(!checked && Math.abs(Robot.driveSubsystem.getPitch()) > 10){
    		started = checked = true;
    	}
    	
    	/*Go to the DataAnalyzer class to learn more about standard error
    	 * and its other functions.
    	 */
    	
    	if(started){
    		if(samples.size() == SAMPLE_SIZE){
    			samples.remove(0);
    			samples.add(Robot.driveSubsystem.getPitch());
    			if(DataAnalyzer.stdError(samples) < 0.01){
    				isDone = true;
    				Robot.driveSubsystem.setMotors(0, 0);
    				Robot.driveSubsystem.enableLeftBreakState();
    				Robot.driveSubsystem.enableRightBreakState();
    			}
    		}else samples.add(Robot.driveSubsystem.getPitch());
    	}
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
		Robot.driveSubsystem.setMotors(0, 0);
		Robot.driveSubsystem.enableLeftBreakState();
		Robot.driveSubsystem.enableRightBreakState();
    }

    protected void interrupted() {
		Robot.driveSubsystem.setMotors(0, 0);
		Robot.driveSubsystem.enableLeftBreakState();
		Robot.driveSubsystem.enableRightBreakState();
    }
}
