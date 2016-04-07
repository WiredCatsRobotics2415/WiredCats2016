package org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.autocommands.StraightDriveCommand;
import org.usfirst.frc.team2415.robot.autocommands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomous extends CommandGroup {
    
	private final double DISTANCE1 = 2 * 12; //in inches
	private final double DISTANCE2 = 4 * 12; //in inches

    public  LowBarAutonomous() {
        addSequential(new StraightDriveCommand(DISTANCE1, .5));
        addSequential(new WaitCommand(1, Robot.driveSubsystem));
        addSequential(new StraightDriveCommand(DISTANCE2, .1));
    }
}
