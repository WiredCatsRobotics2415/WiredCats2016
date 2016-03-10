package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortcullisAutonomous extends CommandGroup {
    
	//TEST ON PRACTICE FIELD
	private final double DISTANCE = 2 * 12; //in inches
    public  PortcullisAutonomous(double angle) {
    	addSequential(new StraightDriveCommand(DISTANCE, .5));
    	addParallel(new IntakeCommand(-157f, 0));
    	addSequential(new WaitCommand(1, Robot.driveSubsystem));
    	addSequential(new StraightDriveCommand(DISTANCE, .1), 2);
    	addParallel(new IntakeCommand(-20f, 0));
    	addSequential(new StraightDriveCommand(DISTANCE, .15));
    	addSequential(new TurnCommand(angle));
    }
}