package org.usfirst.frc.team2415.robot.autocommands.SimpleObstacles;

import org.usfirst.frc.team2415.robot.Robot;
import org.usfirst.frc.team2415.robot.autocommands.PixyAutoCommand;
import org.usfirst.frc.team2415.robot.autocommands.WaitCommand;
import org.usfirst.frc.team2415.robot.catapultcommands.FireCatapultCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockWallCommand extends CommandGroup {
	
	public RockWallCommand(){
		Robot.driveSubsystem.resetYaw();
		//addSequential(new SimpleTraverseCommand(1));
		addSequential(new PixyAutoCommand());
		addSequential(new WaitCommand(.25, Robot.driveSubsystem));
		addSequential(new PixyAutoCommand());
		addSequential(new WaitCommand(.25, Robot.driveSubsystem));
		addSequential(new PixyAutoCommand());
		addSequential(new WaitCommand(1, Robot.driveSubsystem));
		addSequential(new FireCatapultCommand());
		//addSequential(new StopAtDefenseCommand(-.25));
	}
	
}
