package org.usfirst.frc.team2415.robot.catapultcommands;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.intakecommands.IntakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FireSequenceCommand extends CommandGroup {
    
    public  FireSequenceCommand() {
    	requires(Robot.intakeSubsystem);
    	requires(Robot.driveSubsystem);
    	
    	addSequential(new IntakeCommand(Robot.INTAKE_ANGLE, 0), 2);
    	addSequential(new FireCatapultCommand(), 0.5);
    }
}
