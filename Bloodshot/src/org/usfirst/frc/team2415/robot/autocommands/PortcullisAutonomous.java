package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.intakecommands.PneumaticIntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortcullisAutonomous extends CommandGroup {
    
	//TEST ON PRACTICE FIELD
	private final double DISTANCE = 5 * 12; //in inches
    public  PortcullisAutonomous(double angle) {
    	addSequential(new StraightDriveCommand(DISTANCE, .5));
    	addParallel(new PneumaticIntakeCommand("Ground"));
    	addSequential(new StraightDriveCommand(DISTANCE, .5));
    }
}