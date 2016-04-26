package org.usfirst.frc.team2415.robot.autocommands;

import java.util.ArrayList;

import org.usfirst.frc.team2415.robot.DataAnalyzer;
import org.usfirst.frc.team2415.robot.PID;
import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PixyAutoCommand extends Command {

	private double motorPower, error, stdError = 0;
	private boolean isDone = false;
	
	private static final double STEADY_STATE_TOLERANCE = .025;
	private static final int SAMPLE_SIZE = 6;

	private PID pid;

	private ArrayList<Double> samples;

	
    public PixyAutoCommand() {
    	requires(Robot.opticSubsystem);
    	pid = new PID(2.1,0.6,0.3);
    	samples = new ArrayList<Double>();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	Robot.driveSubsystem.enableLeftBreakState();
    	Robot.driveSubsystem.enableRightBreakState();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double voltage = Robot.opticSubsystem.camVoltage();
    	error = (1.91) - voltage;	//1.91 is the voltage where the robot is approximately lined up to the goal, it should be changed with a new camera angle/mount

    	
//    	System.out.println("Voltage: " + voltage + "\tError: " + error);
//    	while(voltage <= .04){
//    		Robot.driveSubsystem.setMotors(-.1,-.1);//(double)Robot.autoPosChooser.getSelected(), .2*(double)Robot.autoPosChooser.getSelected());
//    	}
//    	
//    	if(samples.size() >= SAMPLE_SIZE){
//    		samples.remove(0);
//    		samples.add(error);
//    		stdError = DataAnalyzer.stdError(samples);
//    	}else samples.add(error);
    	
    	if(Math.abs(error) < STEADY_STATE_TOLERANCE) isDone = true;
//    	if(stdError <= STEADY_STATE_TOLERANCE && stdError != 0) isDone = true;
    	
    	motorPower = pid.pidOut(error);
    	motorPower = (Math.abs(motorPower) > 1) ? .5*(Math.signum(motorPower)) : motorPower;
    	Robot.driveSubsystem.setMotors(-motorPower, -motorPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	Robot.driveSubsystem.enableLeftBreakState();
    	Robot.driveSubsystem.enableRightBreakState();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	Robot.driveSubsystem.enableLeftBreakState();
    	Robot.driveSubsystem.enableRightBreakState();
    }
}
