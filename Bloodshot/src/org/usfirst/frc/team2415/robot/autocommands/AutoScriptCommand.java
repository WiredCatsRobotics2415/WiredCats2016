package org.usfirst.frc.team2415.robot.autocommands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScriptCommand extends CommandGroup {
	
	public AutoScriptCommand(){
		Robot.driveSubsystem.resetYaw();
		addSequential(new ImprovedRoughTerrainCommand(.5));
		addSequential(new TurnCommand(0));
		addSequential(new StopAtDefenseCommand(-.5));
	}
	
}
