package org.usfirst.frc.team2415.robot.autocommands;

import java.util.LinkedList;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.utilities.FalconPathPlanner;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionProfileTestCommand extends Command {
	
	//create waypoint path
	double[][] waypoints = new double[][]{
	    {1, 1},
	    {5, 1},
	    {9, 12},
	    {12, 9},
	    {15, 6},
	    {19, 12}
	}; 

	double totalTime = 10; //max seconds we want to drive the path
	double timeStep = 0.2; //period of control loop on Rio, seconds
	double robotTrackWidth = 2; //distance between left and right wheels, feet

	FalconPathPlanner path = new FalconPathPlanner(waypoints);
	LinkedList<Double> left = new LinkedList<Double>();
	LinkedList<Double> right = new LinkedList<Double>();

    public MotionProfileTestCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	FalconPathPlanner path = new FalconPathPlanner(waypoints);
    	path.calculate(totalTime, timeStep, robotTrackWidth);
    	for(double step[] : path.smoothLeftVelocity){
    		left.add(step[1]);
    	}
    	for(double step[] : path.smoothRightVelocity){
    		right.add(step[1]);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.setMotors(left.removeFirst(), right.removeFirst());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.size() == 0 && right.size() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0, 0);
    }
}
