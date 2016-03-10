package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortcullisAutonomous extends CommandGroup {
    
	private final double DISTANCE = 2 * 12; //in inches
    public  PortcullisAutonomous() {
    	addSequential(new StraightDriveCommand(DISTANCE, .5));
    	addSequential(new WaitCommand(1, Robot.driveSubsystem));
    	addParallel(new IntakeCommand(-157f, 0));
    	addSequential(new StraightDriveCommand(DISTANCE, .1), 2);
//    	addSequential(new WaitCommand(2, Robot.driveSubsystem));//need to wait so that the robot can settle
    	addParallel(new IntakeCommand(-20f, 0));
    	addSequential(new StraightDriveCommand(DISTANCE, .15));
    }
}