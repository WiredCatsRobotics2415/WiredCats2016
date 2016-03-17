package org.usfirst.frc.team2415.robot.autocommands;

import java.util.ArrayList;

import org.usfirst.frc.team2415.robot.DataAnalyzer;
import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ImprovedRoughTerrainCommand extends Command {
	private final double SPEED, SAMPLE_SIZE = 18;
	
	private ArrayList<Double> samples;
	
    public ImprovedRoughTerrainCommand(double speed) {
    	requires(Robot.driveSubsystem);
    	SPEED = speed;
    	
    	samples = new ArrayList<Double>();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    private boolean checked = false, started = false, isDone = false;
    protected void execute() {
    	Robot.driveSubsystem.setMotors(SPEED, -SPEED);
    	
    	if(!checked && Robot.driveSubsystem.getRoll() > 10){
    		started = checked = true;
    	}
    	
    	if(started){
    		if(samples.size() == SAMPLE_SIZE){
    			samples.remove(0);
    			samples.add(Robot.driveSubsystem.getRoll());
    			if(DataAnalyzer.stdError(samples) < 0.01){
    				isDone = true;
    				Robot.driveSubsystem.setMotors(0, 0);
    				Robot.driveSubsystem.enableLeftBreakState();
    				Robot.driveSubsystem.enableRightBreakState();
    			}
    		}else samples.add(Robot.driveSubsystem.getRoll());
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
