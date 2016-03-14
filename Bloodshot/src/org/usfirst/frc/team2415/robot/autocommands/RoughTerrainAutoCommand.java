package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoughTerrainAutoCommand extends CommandGroup {
    
	//EDIT AT COMPETITION (ON PRACTICE FIELD)
	private final double DISTANCE = 19 * 12; //in inches
    public  RoughTerrainAutoCommand(double angle) {
    	addSequential(new StraightDriveCommand(DISTANCE, .8));
    	addSequential(new WaitCommand(1, Robot.driveSubsystem));//need to wait so that the robot can settle
    	//addSequential(new TurnCommand(angle));						//to realign the robot
    }
}
