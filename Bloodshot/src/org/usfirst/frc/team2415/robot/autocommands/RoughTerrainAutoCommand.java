package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrainAutoCommand extends CommandGroup {
    
	private final double DISTANCE = 12 * 12; //in inches
    public  RoughTerrainAutoCommand() {
    	addSequential(new StraightDriveCommand(DISTANCE));
    	addSequential(new WaitCommand(1, Robot.driveSubsystem));
    	addSequential(new TurnCommand(0));
    	addSequential(new StraightDriveCommand(-DISTANCE));
    }
}
