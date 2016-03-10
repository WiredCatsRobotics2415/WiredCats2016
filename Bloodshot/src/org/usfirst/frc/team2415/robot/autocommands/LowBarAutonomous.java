package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomous extends CommandGroup {
    
	private final double DISTANCE1 = 2 * 12; //in inches
	private final double DISTANCE2 = 4 * 12; //in inches

    public  LowBarAutonomous() {
        addSequential(new StraightDriveCommand(DISTANCE1, .5));
        addSequential(new WaitCommand(.25, Robot.driveSubsystem));
        addSequential(new StraightDriveCommand(DISTANCE2, .1));
    }
}
